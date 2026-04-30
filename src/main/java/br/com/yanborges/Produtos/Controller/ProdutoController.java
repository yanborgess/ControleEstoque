package br.com.yanborges.Produtos.Controller;

import br.com.yanborges.Produtos.Dto.ProdutoDTO;
import br.com.yanborges.Produtos.Model.Produto;
import br.com.yanborges.Produtos.Service.ProdutoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody ProdutoDTO dto){
        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .build();
        produtoService.salvar(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Produto> listar(){
        return produtoService.listar();
    }
    
    @PatchMapping("/{id}")
    public Produto atualizar(@PathVariable Long id,
                             @RequestBody Produto produto){
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorID(@PathVariable Long id){
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

}
