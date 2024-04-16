package app.loja.repository;

import app.loja.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    public List<Funcionario> findByNome(String nome);

    @Query("FROM Funcionario f WHERE f.idade > :idade")
    public List<Funcionario> findByIdade(int idade);

    public List<Funcionario> findByMatricula(int matricula);
}