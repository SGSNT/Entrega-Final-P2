package app.loja.repository;

import app.loja.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByNome(String nome);

    @Query("FROM Cliente c WHERE c.idade > :idade")
    public List<Cliente> findByIdade(int idade);

    public List<Cliente> findByCpf(String cpf);

}