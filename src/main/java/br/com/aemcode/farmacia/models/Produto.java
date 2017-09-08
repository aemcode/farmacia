package br.com.aemcode.farmacia.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "produtoclassificacao_id", updatable = true, nullable = false)
    private ProdutoClassificacao produtoClassificacao;

    @ManyToOne
    @JoinColumn(name = "produtocategoria_id", updatable = true, nullable = false)
    private ProdutoCategoria produtoCategoria;

    @ManyToOne
    @JoinColumn(name = "produtotipo_id", updatable = true, nullable = false)
    private ProdutoTipo produtoTipo;

    @ManyToOne
    @JoinColumn(name = "produtoclasse_id", updatable = true, nullable = false)
    private ProdutoClasse produtoClasse;

    @Column(name = "descricao", length = 80, nullable = false)
    private String descricao;

    @Column(name = "dosagem", length = 50, nullable = false)
    private String dosagem;

    @Column(name = "preco", nullable = false, precision = 25, scale = 2)
    private BigDecimal preco;

    public ProdutoTipo getProdutoTipo() {
        return produtoTipo;
    }

    public void setProdutoTipo(ProdutoTipo produtoTipo) {
        this.produtoTipo = produtoTipo;
    }

    public ProdutoCategoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ProdutoClassificacao getProdutoClassificacao() {
        return produtoClassificacao;
    }

    public void setProdutoClassificacao(ProdutoClassificacao produtoClassificacao) {
        this.produtoClassificacao = produtoClassificacao;
    }

    public ProdutoClasse getProdutoClasse() {
        return produtoClasse;
    }

    public void setProdutoClasse(ProdutoClasse produtoClasse) {
        this.produtoClasse = produtoClasse;
    }

}
