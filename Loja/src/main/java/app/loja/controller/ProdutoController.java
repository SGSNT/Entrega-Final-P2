package app.loja.controller;

import app.loja.entity.Produto;
import app.loja.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody Produto produto){
        try {
            String salvar = this.produtoService.save(produto);
            return new ResponseEntity<>(salvar, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update (@Valid @PathVariable long id, @RequestBody Produto produto){
        try {
            String alterar = this.produtoService.update(id, produto);
            return new ResponseEntity<>(alterar, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Produto> findById(@Valid @PathVariable long id){

        try {

            Produto produto = this.produtoService.findById(id);
            return new ResponseEntity<>(produto,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Produto>> listAll(){
        try {
            List<Produto> lista = this.produtoService.listAll();
            return new ResponseEntity<>(lista,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable long id){
        try {
            String deletar = this.produtoService.delete(id);
            return new ResponseEntity<>(deletar, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Produto>> findByNome(@Valid @RequestParam String nome){

        try {

            List<Produto> produto = this.produtoService.findByNome(nome);
            return new ResponseEntity<>(produto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/findByValor")

    public ResponseEntity<List<Produto>> findByValor(@Valid @RequestParam Double valor){
        try {

            List<Produto> produto = this.produtoService.findByValor(valor);

            return new ResponseEntity<>(produto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByCategoria")
    public ResponseEntity<List<Produto>> findByCategoria(@Valid @RequestParam String categoria){

        try {

            List<Produto> produto = this.produtoService.findByCategoria(categoria);
            return new ResponseEntity<>(produto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


}