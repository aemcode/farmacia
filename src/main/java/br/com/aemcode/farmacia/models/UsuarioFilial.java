package br.com.aemcode.farmacia.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuariofilial")
public class UsuarioFilial extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "matriz_id", updatable = true, nullable = false)
    private Matriz matriz;

    @ManyToOne
    @JoinColumn(name = "filial_id", updatable = true, nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "usuario_id", updatable = true, nullable = false)
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
