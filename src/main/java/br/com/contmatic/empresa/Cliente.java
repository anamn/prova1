package br.com.contmatic.empresa;

public class Cliente extends Pessoa{

	public Cliente(String nome, String sobrenome, String cpf, Endereco endereco) {
		super(nome, sobrenome, cpf, endereco);
	}
	
	public Cliente() {
		super();
	}
}
