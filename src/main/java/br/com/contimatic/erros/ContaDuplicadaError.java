package br.com.contimatic.erros;

public class ContaDuplicadaError extends RuntimeException {

	public ContaDuplicadaError(String message) {
		super(message);
	}

}
