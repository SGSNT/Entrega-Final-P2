package app.loja.service;

import app.loja.entity.Funcionario;
import app.loja.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario) {

        this.funcionarioRepository.save(funcionario);
        return funcionario.getNome() + "Funcionário cadastrado com sucesso";

    }

    public String update(long id, Funcionario funcionario) {

        funcionario.setIdFuncionario(id);
        this.funcionarioRepository.save(funcionario);

        return "Funcionário alterado com sucesso";
    }

    public List<Funcionario> listAll() {

        return this.funcionarioRepository.findAll();
    }

    public Funcionario findById(long id) {

        Funcionario funcionario = this.funcionarioRepository.findById(id).get();

        return funcionario;
    }

    public String delete(long id) {

        if(id <= 0)

            throw new RuntimeException();

        this.funcionarioRepository.deleteById(id);

        return "Funcionário deletado com sucesso";

    }

    public List<Funcionario> findByNome(String nome) {

        if(nome == null )

            throw new RuntimeException();

        return funcionarioRepository.findByNome(nome);
    }

    public List<Funcionario> findByIdade(int idade) {

        if(idade <= 0)

            throw new RuntimeException();


        return funcionarioRepository.findByIdade(idade);

    }

    public List<Funcionario> findByMatricula(int matricula) {

        if(matricula <= 0)

            throw new RuntimeException();
        return funcionarioRepository.findByMatricula(matricula);
    }
}