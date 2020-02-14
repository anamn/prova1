package br.com.contmatic.empresa;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.enums.Ddd;
import br.com.contmatic.enums.TelefoneType;

public class Telefone {

    private Ddd ddd;

    @NotEmpty(message = "Numero invalido")
    @Pattern(regexp = "([9]?[0-9]{4}-?[0-9]{4})", message = "Numero invalido")
    private String numero;

    private TelefoneType tipo;

    public Telefone(Ddd ddd, String numero, TelefoneType tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ddd = ddd;
    }

    public Telefone() {
    }
    
    public Ddd getDdd() {
        return ddd;
    }

    public void setDdd(Ddd ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TelefoneType getTipo() {
        return tipo;
    }

    public void setTipo(TelefoneType tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(numero).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        return new EqualsBuilder().append(other.numero, numero).isEquals();

    }

}
