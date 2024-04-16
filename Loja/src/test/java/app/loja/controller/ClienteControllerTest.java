package app.loja.controller;

import app.loja.entity.Cliente;
import app.loja.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClienteController clienteController;

    @MockBean
    ClienteRepository clienteRepository;

    @BeforeEach
    void setup(){

        List<Cliente> lista = new ArrayList<>();

        lista.add(new Cliente(1,"João Ninguem","111.111.111-11",25,"(45)88888-8888",null));
        lista.add(new Cliente(2,"João Sem Braço","222.222.222-22",15,"(44)99999-9999",null));
        when(this.clienteRepository.findAll()).thenReturn(lista);
        when(this.clienteRepository.save(Mockito.any())).thenReturn(new Cliente(3,"João Dias","989.989.989-97",27,"(98)78787-7878",null));
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente(4,"Henrique Dias","132.442.352-26",55,"(44)239559-9898",null)));
        doNothing().when(this.clienteRepository).deleteById(1L);
        when(this.clienteRepository.findByNome("Cleitin")).thenReturn(lista);
        when(this.clienteRepository.findByIdade(38)).thenReturn(lista);
    }

    @Test
    void cenario01(){

        ResponseEntity<List<Cliente>> response = this.clienteController.listAll();

        assertEquals(2,response.getBody().size());
    }

    @Test
    void TestMetodoSave(){

        Cliente cliente = new Cliente(3,"Renato Aragão","333.333.333-33",19,"(55)78787-9898",null);

        ResponseEntity<String> response = this.clienteController.save(cliente);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        System.out.println("Cliente Salvo: " + cliente.getNome());
    }

    @Test
    void TestMetodoSaveException(){

        ResponseEntity<String> response = this.clienteController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Erro ao salvar um cliente");
    }

    @Test
    void TestMetodoUpdate(){

         Cliente cliente = new Cliente(4,"John Cena","878.987.798-98",25,"(78)54789-9878",null);

         ResponseEntity<String> alterar = this.clienteController.update(cliente.getIdCliente(),cliente);

         assertEquals(HttpStatus.OK,alterar.getStatusCode());

         System.out.println("Cliente Alterado: " + cliente.getNome());
    }

    @Test
    void TestMetodoUpdateException(){

        Cliente cliente = new Cliente(4,"John Cena","878.987.798-98",25,"(78)54789-9878",null);

        ResponseEntity<String> alterar = this.clienteController.update(cliente.getIdCliente(),null);

        assertEquals(HttpStatus.BAD_REQUEST,alterar.getStatusCode());

        System.out.println("Cliente não encontrado para alteração");
    }

    @Test
    void TestFindById(){

        ResponseEntity<Cliente> response = this.clienteController.findById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id Encontrado");
    }

    @Test
    void TestFindByIdException(){

        ResponseEntity<Cliente> response = this.clienteController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id não encontrado");
    }

    @Test
    void TestDeleteById(){

        Cliente cliente = new Cliente(6,"Ovelha","555.555.555-55",17,"(89)09090-0909",null);

        long id = cliente.getIdCliente();

        ResponseEntity<String> response = this.clienteController.delete(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id: " + cliente.getIdCliente() + " -> Nome: " + cliente.getNome());
    }

    @Test
    void TestDeleteByIdException(){

        ResponseEntity<String> response = this.clienteController.delete(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id Não encontrado");
    }

    @Test
    void TestFindByNome(){

        Cliente cliente = new Cliente(7,"Carlos Alberto","777.777.777-77",27,"(98)47854-8989",null);

        String nome = cliente.getNome();

        ResponseEntity<List<Cliente>> response = this.clienteController.findByNome(nome);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Nome: " + nome);
    }

    @Test
    void TestFindByNomeException(){

        ResponseEntity<List<Cliente>> response = this.clienteController.findByNome(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Nome do cliente não encontrado");
    }

    @Test
    void TestFindByIdade(){

        Cliente cliente = new Cliente(8,"Kronos","888.888.888-88",21,"(09)78787-8787",null);

        Integer idade = cliente.getIdade();

        ResponseEntity<List<Cliente>> response = this.clienteController.findByIdade(idade);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Idade: " + idade);
    }

    @Test
    void TestFindByIdadeException(){

        ResponseEntity<List<Cliente>> response = this.clienteController.findByIdade(-1);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Nenhum funcionaráio encontrado com a idade especificada");
    }

    @Test
    void TestFindByCpf(){

        Cliente cliente = new Cliente(9,"Kratos","999.999.999-99",16,"(89)12009-1920",null);

        String cpf = cliente.getCpf();

        ResponseEntity<List<Cliente>> response = this.clienteController.findByCpf(cpf);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Cpf: " + cpf);
    }

    @Test
    void TestFindByCpfException(){

        ResponseEntity<List<Cliente>> response = this.clienteController.findByCpf("");

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("CPF não encontrado");
    }
}
