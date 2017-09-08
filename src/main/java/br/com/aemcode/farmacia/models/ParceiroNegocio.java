package br.com.aemcode.farmacia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parceironegocio")
public class ParceiroNegocio extends GenericModel {

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name = "cpfcnpj", unique = true, length = 20, nullable = false)
    private String cpfCnpj;

    /*
        F - Fisica
        J - Juridica
     */
    @Column(name = "pessoafisicaoujuridica", nullable = false)
    private Character pessoaFisicaOuJuridica;

    /*
        1 - Cliente
        2 - Fornecedor
        3 - Ambos
     */
    @Column(name = "tipo", nullable = false)
    private Character tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Character getPessoaFisicaOuJuridica() {
        return pessoaFisicaOuJuridica;
    }

    public void setPessoaFisicaOuJuridica(Character pessoaFisicaOuJuridica) {
        this.pessoaFisicaOuJuridica = pessoaFisicaOuJuridica;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

}
