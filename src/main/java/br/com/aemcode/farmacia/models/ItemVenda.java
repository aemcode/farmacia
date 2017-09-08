package br.com.aemcode.farmacia.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemvenda")
public class ItemVenda extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "venda_id", updatable = true, nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", updatable = true, nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Short quantidade;

    @Column(name = "valor", nullable = false, precision = 25, scale = 2)
    private BigDecimal valor;

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Short quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
