package br.com.contmatic.telefone;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum TelefoneType {

                          CELULAR("celular"),
                          FIXO("fixo");

    private String tipo;

    private TelefoneType(String tipo) {
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
