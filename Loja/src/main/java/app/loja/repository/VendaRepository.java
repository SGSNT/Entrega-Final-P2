package app.loja.repository;

import app.loja.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda,Long> {

    public List<Venda> findByEndereco(String endereco);

    @Query("FROM Venda v WHERE v.total > :total1 AND v.total < :total2")
    public List<Venda> findByTotal(Double total1, Double total2);

    public List<Venda> findByFormaPagamento(String formaPagamento);

}