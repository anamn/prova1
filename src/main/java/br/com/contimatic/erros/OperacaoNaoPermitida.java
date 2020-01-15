package br.com.contimatic.erros;

public class OperacaoNaoPermitida extends RuntimeException {
	
	public OperacaoNaoPermitida(String message) {
		super(message);
	}

}
