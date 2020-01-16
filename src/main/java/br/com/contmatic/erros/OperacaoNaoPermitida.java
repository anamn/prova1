package br.com.contmatic.erros;

public class OperacaoNaoPermitida extends RuntimeException {
	
	public OperacaoNaoPermitida(String message) {
		super(message);
	}

}
