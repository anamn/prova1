package br.com.contmatic.empresa;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.enums.TelefoneType;

public class Telefone {

    @NotEmpty(message = "Numero invalido")
    @Pattern(regexp = "([(]?)([1][1-9])([)]?)([0-9]{8,9})|([(]?)([2][1-2])([)]?)([0-9]{8,9})|([(]?)([2][4])([)]?)([0-9]{8,9})|([(]?)([2][7-8])([)]?)([0-9]{8,9})|([(]?)([3][1-5])([)]?)([0-9]{8,9})|([(]?)([3][7-8])([)]?)([0-9]{8,9})|([(]?)([4][1-9])([)]?)([0-9]{8,9})|([(]?)([5][1])([)]?)([0-9]{8,9})|([(]?)([5][3-5])([)]?)([0-9]{8,9})|([(]?)([6][1-9])([)]?)([0-9]{8,9})|([(]?)([7][1])([)]?)([0-9]{8,9})|([(]?)([7][3-5])([)]?)([0-9]{8,9})|([(]?)([7][7])([)]?)([0-9]{8,9})|([(]?)([7][9])([)]?)([0-9]{8,9})|([(]?)([8][1-9])([)]?)([0-9]{8,9})|([(]?)([9][1-9])([)]?)([0-9]{8,9})", message = "Numero invalido")
    private String numero;

    private TelefoneType tipo;

    public Telefone(String numero, TelefoneType tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public Telefone() {
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
