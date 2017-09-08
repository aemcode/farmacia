package br.com.aemcode.farmacia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "produtoclassificacao")
public class ProdutoClassificacao extends GenericModel {

    @Column(name = "descricao", length = 60, nullable = false)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
