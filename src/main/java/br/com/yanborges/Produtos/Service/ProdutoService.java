package br.com.yanborges.Produtos.Service;

import br.com.yanborges.Produtos.Model.Produto;
import br.com.yanborges.Produtos.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    public Produto atualizar(Long id, Produto dto) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    if (dto.getNome() != null) produtoExistente.setNome(dto.getNome());
                    if (dto.getDescricao() != null) produtoExistente.setDescricao(dto.getDescricao());
                    if (dto.getPreco() != null) produtoExistente.setPreco(dto.getPreco());

                    return produtoRepository.save(produtoExistente);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletar(Long id){
        if (!produtoRepository.existsById(id)){
            throw  new RuntimeException("Id não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não escontrado"));
    }
}



