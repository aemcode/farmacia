package br.com.aemcode.farmacia.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "funcionario")
public class Funcionario extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "matriz_id", updatable = true, nullable = false)
    private Matriz matriz;

    @ManyToOne
    @JoinColumn(name = "filial_id", updatable = true, nullable = false)
    private Filial filial;

    @OneToOne
    @JoinColumn(name = "pessoa_id", updatable = true, nullable = false)
    private Pessoa pessoa;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataadmissao", nullable = false)
    private Date dataAdmissao;

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

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
