package app.loja.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFuncionario;
    @NotBlank(message = "Campo nome não pode ser em branco")
    private String nome;
    @NotNull
    private Integer idade;
    @NotNull
    private Integer matricula;

    @OneToMany(mappedBy = "funcionario")
    private List<Venda> venda;
}