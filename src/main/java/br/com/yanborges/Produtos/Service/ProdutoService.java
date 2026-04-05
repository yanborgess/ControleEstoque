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

    public Produto atualizar(Long id, Produto produto){
        Optional<Produto> prod = produtoRepository.findById(id);
        if (prod.isEmpty()){
            throw new RuntimeException("Produto não escontrado");
        }else {
            return produtoRepository.save(produto);
        }
    }

    public void deletar(Long id){
        if (!produtoRepository.existsById(id)){
            throw  new RuntimeException("Id não escontrado");
        }
        produtoRepository.deleteById(id);
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não escontrado"));
    }
}



