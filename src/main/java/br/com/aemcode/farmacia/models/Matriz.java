package br.com.aemcode.farmacia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matriz")
public class Matriz extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "cidade_id", updatable = true, nullable = false)
    private Cidade cidade;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "cnpj", length = 20, nullable = false)
    private String cnpj;

    @Column(name = "rua", length = 80, nullable = false)
    private String rua;

    @Column(name = "numero", length = 20, nullable = false)
    private String numero;

    @Column(name = "complemento", length = 40, nullable = true)
    private String complemento;

    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "telefone", length = 20, nullable = true)
    private String telefone;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
