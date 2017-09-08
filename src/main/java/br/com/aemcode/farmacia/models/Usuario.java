package br.com.aemcode.farmacia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuario")
public class Usuario extends GenericModel {

    private String codUsuario;

    private String nome;

    private String email;

    private String senha;

    private Character tipo;

    @Column(name = "codusuario", unique = true, length = 40, nullable = false)
    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Column(name = "nome", length = 60, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "email", length = 60, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "senha", length = 32, nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "tipo", nullable = false)
    public Character getTipo() {
        return tipo;
    }

    @Transient
    public String getTipoFormatado() {
        String tipoFormatado = null;

        switch (tipo) {
            case 'A':
                tipoFormatado = "Administrador";
                break;
            case 'S':
                tipoFormatado = "Suporte";
                break;
            case 'G':
                tipoFormatado = "Gestor";
                break;
            case 'B':
                tipoFormatado = "Balconista";
                break;
        }
        return tipoFormatado;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codUsuario=" + codUsuario + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipo=" + tipo + '}';
    }

}
