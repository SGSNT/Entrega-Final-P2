package app.loja.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;
    @NotBlank(message = "Campo nome não pode ser nulo")
    private String nome;
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    @NotBlank(message = "Cpf inválido")
    private String cpf;
    @NotNull
    @Min(1)
    private Integer idade;
    @NotBlank(message = "Telefone inválido")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> venda;

}