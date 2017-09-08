package br.com.aemcode.farmacia.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "matriz_id", updatable = true, nullable = false)
    private Matriz matriz;

    @ManyToOne
    @JoinColumn(name = "filial_id", updatable = true, nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "parceironegocio_id", updatable = true, nullable = true)
    private ParceiroNegocio parceiroNegocio;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", updatable = true, nullable = false)
    private Funcionario funcionario;

    @Column(name = "valortotal", nullable = false, precision = 25, scale = 2)
    private BigDecimal valorTotal;

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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
