package app.loja.service;

import app.loja.entity.Produto;
import app.loja.entity.Venda;
import app.loja.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public String save(Venda venda) {


        double valorTotal = this.calcularTotal(venda.getProdutos());

        venda.setTotal(valorTotal);

        this.vendaRepository.save(venda);

        return "Venda realizada";
    }

    public String update(long id, Venda venda) {

        venda.setId(id);
        this.vendaRepository.save(venda);

        double valorTotal = this.calcularTotal(venda.getProdutos());
        venda.setTotal(valorTotal);

        return "Venda alterada";
    }

    public Venda verificarStatus(Venda venda) {

        if(venda.getStatus().isEmpty()){
            throw new RuntimeException("O status da venda não pode ficar vazio");
        }

        if(venda.getStatus().equals("Cancelado")) {

            venda.setTotal(0.0);
            venda.setProdutos(null);

        }
        return venda;
    }

    public double calcularTotal(List<Produto> produtos) {

        double soma = 0;
        if(produtos != null)
            for(int i = 0; i < produtos.size();i++) {

                soma+= produtos.get(i).getValor();

            }
        return soma;
    }

    public List<Venda> listAll() {

        return this.vendaRepository.findAll();

    }

    public Venda findById(long id) {

        Venda venda = this.vendaRepository.findById(id).get();

        return venda;
    }

    public String delete(long id) {

        this.vendaRepository.deleteById(id);

        return "Venda deletada";
    }

    public List<Venda> findByEndereco(String endereco){

        return vendaRepository.findByEndereco(endereco);
    }

    public List<Venda> findByTotal(Double total1, Double total2){

        return vendaRepository.findByTotal(total1,total2);
    }

    public List<Venda> findByFormaPagamento(String formaPagamento){

        return vendaRepository.findByFormaPagamento(formaPagamento);
    }

}