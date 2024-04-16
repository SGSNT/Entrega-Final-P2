package app.loja.controller;

import app.loja.entity.Produto;
import app.loja.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    ProdutoController produtoController;

    @MockBean
    ProdutoRepository produtoRepository;

    @BeforeEach
    void setup(){
        List<Produto> lista = new ArrayList<>();
        lista.add(new Produto(1,"Rolex",1500.0,"Relógio"));
        lista.add(new Produto(2,"Calça Jeans",80.0,"Moda"));
        when(this.produtoRepository.findAll()).thenReturn(lista);
        when(this.produtoRepository.save(Mockito.any())).thenReturn(new Produto(3,"Rolex",1500.0,"Relógio"));
        when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto(5,"Rolex",1500.0,"Relógio")));
        doNothing().when(this.produtoRepository).deleteById(1L);
        when(this.produtoRepository.findByNome("Anel")).thenReturn(lista);
        when(this.produtoRepository.findByValor(150.0)).thenReturn(lista);
    }


    @Test
    void cenario01(){
        ResponseEntity<List<Produto>> response = this.produtoController.listAll();

        assertEquals(2, response.getBody().size());
    }

    @Test
    void testMetodoSave(){

        Produto produto = new Produto(3, "Calça Sarja", 45.0, "Moda");

        ResponseEntity<String> response = this.produtoController.save(produto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        System.out.println("Salvo: " + produto.getNome());

    }

    @Test
    void testMetodoSaveException(){

        ResponseEntity<String> response = this.produtoController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Erro ao salvar");

    }

    @Test
    void testMetodoUpdate(){

        Produto produto = new Produto(1,"Regata Social",55.5,"Moda");

        ResponseEntity<String> response = this.produtoController.update(produto.getId(),produto);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Produto Alterado: " + produto.getNome());
    }

    @Test
    void testMetodoUpdateException(){

        Produto produto = new Produto(1,"Regata Social",55.5,"Moda");

        ResponseEntity<String> response = this.produtoController.update(produto.getId(),null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Produto não encontrado para fazer a alteração");
    }

    @Test
    void testMetodoFindById(){

        ResponseEntity<Produto> response = this.produtoController.findById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void testMetodoFindByIdException(){

        ResponseEntity<Produto> response = this.produtoController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id não encontrado");
    }

    @Test
    void testDeleteById(){

        Produto produto = new Produto(6, "Calça Moletom", 40.0, "Moda");

        long id = produto.getId();

        ResponseEntity<String> response = this.produtoController.delete(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id: " + produto.getId() + " -> Produto Excluido: " + produto.getNome());
    }

    @Test
    void testDeleteByIdException(){

        ResponseEntity<String> response = this.produtoController.delete(-1);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id não encontrado para exclusão");
    }

    @Test
    void TestFindByNome(){

        Produto produto = new Produto(7, "Calça", 100.0, "Moda");

        String findNome = produto.getNome();

        ResponseEntity<List<Produto>> response = this.produtoController.findByNome(findNome);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Nome do Produto: " + findNome);

    }

    @Test
    void TestFindByNomeException(){

        ResponseEntity<List<Produto>> response = this.produtoController.findByNome(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Produto não encontrado");

    }

    @Test
    void TestFindByValor(){

        Produto produto = new Produto(8, "Vestido", 100.0, "Moda");

        Double valor = produto.getValor();

        ResponseEntity<List<Produto>> response = this.produtoController.findByValor(valor);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Valor: " + "R$ " + valor);
    }

    @Test
    void TestFindByValorException(){

        ResponseEntity<List<Produto>> response = this.produtoController.findByValor(0.0);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Valor não pode ser menor ou igual a zero");
    }

    @Test
    void TestFindByCategoria(){

        Produto produto = new Produto(9, "Nike Sb", 200.0, "Tenis");

        String categoria = produto.getCategoria();

        ResponseEntity<List<Produto>> response = this.produtoController.findByCategoria(categoria);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Produto: " + produto.getNome() + " -> Categoria: " + categoria);
    }

    @Test
    void TestFindByCategoriaException(){

        ResponseEntity<List<Produto>> response = this.produtoController.findByCategoria("");

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Produto não foi encontrado");
    }


}