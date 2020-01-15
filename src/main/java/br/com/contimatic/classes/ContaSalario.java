package br.com.contimatic.classes;

import br.com.contimatic.erros.OperacaoNaoPermitida;

public class ContaSalario extends Conta {

	public ContaSalario(Cliente cliente, double saldo, double limite) {
		super(cliente, saldo, limite);
	}
	@Override
	public void tranfere(Conta conta, double valor) {
		throw new OperacaoNaoPermitida("Não é permitido fazer tranferencias");
	}

}
