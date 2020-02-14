package br.com.contmatic.enums;

public enum EnderecoType {

                          CASA(0, "casa"),
                          APARTAMENTO(1, "apartamento"),
                          COMENCIAL(2,"comencial");

    private String nome;
    private int numero;

    EnderecoType(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        EnderecoType.APARTAMENTO.getNome();
        EnderecoType.APARTAMENTO.getNumero();
        
        return super.toString();
    }
}
