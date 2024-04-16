package app.loja.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import app.loja.entity.Cliente;
import app.loja.entity.Funcionario;
import app.loja.entity.Produto;
import app.loja.entity.Venda;
import app.loja.repository.VendaRepository;

@SpringBootTest
public class VendaControllerTest{

    @Autowired
    VendaController vendaController;

    @MockBean
    VendaRepository vendaRepository;

    @BeforeEach
    void setup() {
        List<Venda> lista = new ArrayList<Venda>();
        for(int i=0; i<3;i++) {

            lista.add(new Venda((long)i ,"endereco",0.5,"cartao","OK",
                    new Funcionario(i,"funcionario"+i,20,1234,new ArrayList<Venda>()),
                    new Cliente(i,"cliente"+i,"123.456.789-10",10,"45999999999",new ArrayList<Venda>()),
                    new ArrayList<Produto>())
            );
            for(int j=0;j<3; j++){
                lista.get(i).getProdutos().add(new Produto((long) (j + i), "produto " + (j + i), 10.0 * j + i, "categoria"));
            }
            for (int j = 0; j < 2; j++) {
                lista.get(i).getCliente().getVenda().add(lista.get(i));
                lista.get(i).getFuncionario().getVenda().add(lista.get(i));
            }
        }


        when(this.vendaRepository.findAll()).thenReturn(lista);
        when(this.vendaRepository.findById((long) 1)).thenReturn(Optional.of(lista.get(1)));
        when(this.vendaRepository.save(lista.get(1))).thenReturn(lista.get(1));
        when(this.vendaRepository.findByEndereco("endereco")).thenReturn(lista);
        when(this.vendaRepository.findByTotal(0.0,1.0)).thenReturn(lista);
        when(this.vendaRepository.findByFormaPagamento("cartao")).thenReturn(lista);
    }

    @Test
    void findAllOk() {
        ResponseEntity<List<Venda>> response = this.vendaController.listAll();
        List<Venda> lista = response.getBody();
        assertEquals(3,lista.size());
    }

    @Test
    void FindByIdOk() {
        ResponseEntity<Venda> response  = this.vendaController.findById(1);
        Venda venda = response.getBody();
        assertEquals(1,venda.getId());
    }

    @Test
    void SaveOk() {
        Venda venda = new Venda(0,"endereco",0.5,"cartao","OK",null,null,new ArrayList<Produto>());
        ResponseEntity<String> response = this.vendaController.save(venda);
        int httpStatus = response.getStatusCode().value();
        assertEquals(201,httpStatus);
    }

    @Test
    void updateOk() {
        Venda venda = new Venda(0,"endereco",0.5,"cartao","OK",null,null,new ArrayList<Produto>());
        ResponseEntity<String> response = this.vendaController.update(1,venda);
        int httpStatus = response.getStatusCode().value();
        assertEquals(200,httpStatus);
    }

    @Test
    void deleteOk() {
        ResponseEntity<String> response = this.vendaController.delete(1);
        int httpStatus = response.getStatusCode().value();
        assertEquals(200,httpStatus);
    }

    @Test
    void findByEnderecoOk() {
        ResponseEntity<List<Venda>> response = this.vendaController.findByEndereco("endereco");
        int httpStatus = response.getStatusCode().value();
        assertEquals(200, httpStatus);
    }

    @Test
    void findByTotalOk() {
        ResponseEntity<List<Venda>> response = this.vendaController.findByTotal(0.0, 1.0);
        int httpStatus = response.getStatusCode().value();
        assertEquals(200,httpStatus);
    }

    @Test
    void FindByFormaPagamentoOkk() {
        ResponseEntity<List<Venda>> response = this.vendaController.findByFormaPagamento("cartao");
        int httpStatus = response.getStatusCode().value();
        assertEquals(200,httpStatus);
    }
}
