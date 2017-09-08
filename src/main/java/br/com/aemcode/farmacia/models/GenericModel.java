package br.com.aemcode.farmacia.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class GenericModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql
    private Long id;

    @ManyToOne
    @JoinColumn(name = "createdby", updatable = true, nullable = true)
    private Usuario createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedby", updatable = true, nullable = true)
    private Usuario updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = true)
    private Date updated;

    @Column(name = "isactive", nullable = false)
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Usuario createdBy) {
        this.createdBy = createdBy;
    }

    public Usuario getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Usuario updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    /*
    @Transient
    public String getAtivoFormatado() {
        String ativoFormatado = "Não";

        if (ativo) {
            ativoFormatado = "Sim";
        }

        return ativoFormatado;
    }
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /*
    @Override
    public String toString() {
        return "GenericModel{" + "id=" + id + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", created=" + created + ", updated=" + updated + ", ativo=" + ativo + '}';
    }
*/
    /**
     *
     * Conversor genérico OMNIFACES Serve para o objeto daquele tipo. O toString
     * é feito por dominio.
     *
     * Se não tivesse feito modelo generico, o toString deveria ser criado em
     * todas as classes modelos
     */
    /*@Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    }
     */
}
