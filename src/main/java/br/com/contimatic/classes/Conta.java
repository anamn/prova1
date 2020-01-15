package br.com.contimatic.classes;

import java.util.ArrayList;
import java.util.List;

import br.com.contimatic.erros.ContaDuplicadaError;
import br.com.contimatic.erros.SaldoInsuficiente;

public class Conta {
	Cliente cliente;
	private double saldo;
	private double limite;

	public Conta(Cliente cliente, double saldo, double limite) {
		super();
		this.cliente = cliente;
		this.saldo = saldo;
		this.limite = limite;
		
	}

	public void saca(double valor) {
		if (valor <= this.limite || valor<= this.saldo) {
			this.saldo -= valor;
			System.out.println("Saldo após saque: " + this.cliente.getNome() + ":" + this.saldo);
		}else
		throw new SaldoInsuficiente("Saldo insuficiente para saque");
	}

	public void deposita(double valor) {
		this.saldo += valor;
		System.out.println("Saldo após deposito " + this.cliente.getNome() + ":" + this.saldo);

	}

	public void tranfere(Conta conta, double valor) {
			this.saca(valor);
			if (valor <= this.limite || valor<=this.saldo)	conta.deposita(valor);
		
			}
		

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public int hashCode() {
		final int prime = 3;
		int result = 1;
		result = prime * result + ((cliente.getCpf() == null) ? 0 : cliente.getCpf().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta outro = (Conta) obj;
		if (cliente.getCpf() == null) {
			if (outro.cliente.getCpf() != null) {
				return false;
			}
		} else if (!cliente.getCpf().equals(outro.cliente.getCpf()))
			return false;
		if (!cliente.getNome().equals(outro.cliente.getNome())) {
			return false;
		}
		if (cliente.getNome() == null) {
			if (outro.cliente.getNome() != null)
				return false;
		}
		throw new ContaDuplicadaError("Conta duplicada");
	}

	@Override
	public String toString() {
		return "Cliente:" + cliente;
	}

}
