package br.com.contmatic.empresa;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.YearMonth;

import br.com.contmatic.enums.Moeda;

@Valid
public class Lucro {

    private Empresa empresa;

    @DecimalMin(value = "1.00", message = "Investimento menor que zero!")
    private BigDecimal investimento;

    @NotNull(message = "Renda menor que zero!")
    @DecimalMin(value = "1.00", message = "Renda menor que zero!")
    private BigDecimal renda;

    private Moeda moeda;

    @PastOrPresent(message = "A data deve ser no presente!")
    private YearMonth mes;

    public Lucro(BigDecimal investimento, BigDecimal renda, Moeda moeda, YearMonth mes) {
        super();
        this.moeda = moeda;
        this.renda = renda;
        this.investimento = investimento;
        this.mes = mes;

    }

    public Lucro() {
        super();
    }

    public BigDecimal calculaLucro() {
        return (this.getRenda().subtract(this.investimento)).multiply(new BigDecimal("100")).divide(this.getInvestimento());
    }

    public BigDecimal getInvestimento() {
        return investimento;
    }

    public void setInvestimento(BigDecimal investimento) {
        this.investimento = investimento;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal ganho) {
        this.renda = ganho;

    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;

    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public YearMonth getMes() {
        return mes;
    }

    public void setMes(YearMonth mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(empresa).append(mes).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lucro other = (Lucro) obj;
        return new EqualsBuilder().append(other.empresa, empresa).append(other.mes, mes).isEquals();

    }

}
