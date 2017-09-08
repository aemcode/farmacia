package br.com.aemcode.farmacia.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "estoque")
public class Estoque extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "matriz_id", updatable = true, nullable = false)
    private Matriz matriz;

    @ManyToOne
    @JoinColumn(name = "filial_id", updatable = true, nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "parceironegocio_id", updatable = true, nullable = false)
    private ParceiroNegocio parceiroNegocio;

    @ManyToOne
    @JoinColumn(name = "produto_id", updatable = true, nullable = false)
    private Produto produto;
    
    @Column(name = "lote", length = 40)
    private String lote;

    @Column(name = "quantidade", nullable = false)
    private Short quantidade;

    @Column(name = "valor", nullable = false, precision = 25, scale = 2)
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataValidade")
    private Date dataValidade;

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
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
