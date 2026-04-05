package br.com.yanborges.Produtos.Service;

import br.com.yanborges.Produtos.Model.Produto;
import br.com.yanborges.Produtos.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Produto atualizar(Long id, Produto produto) {
        // 1. Busca o produto que já está no banco
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    // 2. Atualiza apenas se o campo no JSON não for nulo
                    if (produto.getNome() != null) {
                        produtoExistente.setNome(produto.getNome());
                    }
                    if (produto.getDescricao() != null) {
                        produtoExistente.setDescricao(produto.getDescricao());
                    }
                    if (produto.getPreco() != null) {
                        produtoExistente.setPreco(produto.getPreco());
                    }

                    // 3. Salva o objeto "produtoExistente" (que manteve os dados antigos onde não houve mudança)
                    return produtoRepository.save(produtoExistente);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: " + id));
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



