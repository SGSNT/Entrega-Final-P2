package app.loja.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String endereco;
    @NotNull
    private Double total;
    @NotBlank(message = "Campo Forma de Pagamento não deve ficar em Branco")
    private String formaPagamento;
    @NotBlank(message = "Campo Status não deve ficar em branco")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("venda")
    private Funcionario funcionario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("venda")
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(

            name = "venda_produto", joinColumns = @JoinColumn(name = "venda_id"), inverseJoinColumns = @JoinColumn(name = "produto_id")

    )

    private List<Produto> produtos;

}
