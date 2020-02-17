package br.com.contmatic.endereco;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum EnderecoType {

                          CASA("casa"),
                          APARTAMENTO("apartamento"),
                          COMENCIAL("comencial");

    private String nome;

    private EnderecoType(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
      
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
