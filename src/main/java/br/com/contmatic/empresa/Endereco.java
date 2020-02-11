package br.com.contmatic.empresa;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.enums.EnderecoType;

@Valid
public class Endereco {

    @NotEmpty(message = "Endereço invalido!")
    @Pattern(regexp = "[^\\d]{5,40}", message = "Endereço invalido!")
    private String endereco;

    @NotEmpty(message = "Numero invalido!")
    @Pattern(regexp = "[aA-zZ,1-9]{1,20}", message = "Numero invalido!")
    private String numero;

    @NotEmpty(message = "CEP invalido!")
    @Pattern(regexp = "[\\d]{8}", message = "CEP invalido!")
    private String cep;

    private EnderecoType enderecoType;

    public Endereco(String endereco, String numero, String cep, EnderecoType enderecoType) {
        super();
        this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.enderecoType = enderecoType;
    }

    public Endereco() {
        super();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public EnderecoType getEnderecoType() {
        return enderecoType;
    }

    public void setEnderecoType(EnderecoType enderecoType) {
        this.enderecoType = enderecoType;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cep).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return new EqualsBuilder().append(other.cep, cep).isEquals();

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
