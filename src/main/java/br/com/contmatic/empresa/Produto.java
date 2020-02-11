package br.com.contmatic.empresa;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Valid
public class Produto {

    @NotEmpty(message = "Tipo invalido")
    @Length(min = 0, max = 50, message = "Tipo invalido")
    private String tipo;

    @DecimalMin(value = "1.00", message = "Preço invalido")
    private BigDecimal preco;

    @NotEmpty(message = "Descrição invalida")
    @Size(min = 10, max = 60, message = "Descrição invalida")
    private String descricao;

    @NotEmpty(message = "Codigo invalido")
    @Pattern(regexp = "[\\d]{5,30}", message = "Codigo invalido")
    private String codigo;

    @Range(min = 1, max = 50, message = "Quantidade menor que zero")
    private Integer quantidade;
    
    public Produto(String tipo, String descricao, BigDecimal preco, String codigo, Integer quantidade) {
        super();
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public Produto() {
        super();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;

    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(codigo).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        return new EqualsBuilder().append(other.codigo, codigo).isEquals();

    }

}
