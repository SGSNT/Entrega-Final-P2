package app.loja.service;

import app.loja.entity.Produto;
import app.loja.entity.Venda;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {

    @InjectMocks
    VendaService vendaService;

    @Test
    @DisplayName("Teste unitário para retorno do valor total")
    void resultadoTotal() {

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1, "Calça Jeans", 50.0, "Moda"));
        produtos.add(new Produto(2, "Camisa Polo", 80.0, "Moda"));
        produtos.add(new Produto(3, "Calça de Moletom", 40.0, "Moda"));
        produtos.add(new Produto(4, "Casaco Corta Vento", 100.0, "Moda"));

        double total = vendaService.calcularTotal(produtos);

        assertEquals(270.0, total);

        System.out.println("Total: " + " R$ " + total);
    }

    @Test
    @DisplayName("Teste unitário para gerar uma exception de valor nulo")
    void retornarException() {

        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto(5, "Calça Sarja", null, "Moda"));
        produtos.add(new Produto(6, "Camisa Manga Longa", 30.0, "Moda"));
        produtos.add(new Produto(7, "Regata", 20.0, "Moda"));

        assertThrows(RuntimeException.class, () -> {
            vendaService.calcularTotal(produtos);
        });

        System.out.println("Exception para valor nullo: " + produtos);
    }


    @Test
    @DisplayName("Teste Unitário para verificar se status está como cancelado, lista de produtos nula e para zerar o carrinho")
    void verificarStatus() {

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(8, "Casaco de Couro", 1250.0, "Moda"));
        produtos.add(new Produto(9, "Vestido", 130.0, "Moda"));

        Venda venda = new Venda();
        venda.setId(1);
        venda.setEndereco("Rua Street 12");
        venda.setTotal(1250.0);
        venda.setFormaPagamento("Cartão de Débito");
        venda.setStatus("Cancelado");
        venda.setProdutos(produtos);

        vendaService.verificarStatus(venda);

        assertEquals("Cancelado", venda.getStatus());
        assertNull(venda.getProdutos());
        assertEquals(0.0, venda.getTotal());

        System.out.println("Status: " + venda.getStatus() + " Produtos: " + venda.getProdutos() + " Total: " + " R$ " + venda.getTotal());
    }

    @Test
    @DisplayName("Teste Unitário para gerar exception de validação")
    void exceptionValidacao(){

        Venda venda = new Venda();
        venda.setId(2);
        venda.setEndereco("Rua Street 13");
        venda.setTotal(250.0);
        venda.setFormaPagamento("Pix");
        venda.setStatus("");

        assertThrows(RuntimeException.class, () -> {

            vendaService.verificarStatus(venda);

        });

        System.out.println("Status: " + venda.getStatus());
    }
}
