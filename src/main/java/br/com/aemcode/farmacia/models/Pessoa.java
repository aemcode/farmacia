package br.com.aemcode.farmacia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "cidade_id", updatable = true, nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id", updatable = true, nullable = true)
    private Usuario usuario;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "cpf", unique = true, length = 20, nullable = false)
    private String cpf;

    @Column(name = "rg", length = 20, nullable = false)
    private String rg;

    @Column(name = "rua", length = 80, nullable = false)
    private String rua;

    @Column(name = "numero", nullable = false)
    private Short numero;

    @Column(name = "complemento", length = 50)
    private String complemento;

    @Column(name = "bairro", length = 60, nullable = false)
    private String bairro;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "telefone", length = 20, nullable = true)
    private String telefone;

    @Column(name = "celular", length = 20, nullable = true)
    private String celular;

    @Column(name = "email", length = 60, nullable = true)
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Short getNumero() {
        return numero;
    }

    public void setNumero(Short numero) {
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
