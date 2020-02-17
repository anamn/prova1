package br.com.contmatic.financeiro;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum Moeda {
    
    REAL("Real"),
    EURO("Euro"),
    DOLLAR("Dollar");

    private String tipo;

    private Moeda(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }

    public String getTipo() {
        return tipo;
    }
}