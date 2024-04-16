package app.loja.repository;

import app.loja.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("FROM Produto p WHERE p.nome = :nome ")
    public List<Produto> findByNome(String nome);

    public List<Produto> findByValor(double valor);

    public List<Produto> findByCategoria(String categoria);
}