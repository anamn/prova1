package br.com.contimatic.erros;

public class SaldoInsuficiente extends RuntimeException {
	
	public SaldoInsuficiente(String message) {
		super(message);
	}

}
