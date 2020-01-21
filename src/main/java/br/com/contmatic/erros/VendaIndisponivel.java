package br.com.contmatic.erros;

public class VendaIndisponivel extends RuntimeException{
	
	public VendaIndisponivel(String message) {
		super(message);
	}


}
