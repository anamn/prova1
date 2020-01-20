package br.com.contmatic.erros;

public class Inexistente extends RuntimeException{
	public Inexistente(String message) {
		super(message);
	}
}
