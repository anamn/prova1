package br.com.contmatic.empresa;

import java.math.BigDecimal;

import br.com.contmatic.exceptions.ValorException;

public class Lucro {

	private BigDecimal investimento;

	private BigDecimal ganho;

	private String moeda;

	public Lucro(BigDecimal investimento, BigDecimal ganho, String moeda) {
		super();
		this.setMoeda(moeda);
		this.setGanho(ganho);
		this.setInvestimento(investimento);

	}

	public Lucro() {
		super();
	}

	public BigDecimal getInvestimento() {
		return investimento;
	}

	public void setInvestimento(BigDecimal investimento) {
		if (investimento.compareTo(new BigDecimal("0")) > 0 && this.setMoeda(this.moeda)) {
			this.investimento = investimento;
		} else {
			throw new ValorException("Valor invalido! Digite um valor maior que zero em real");
		}
	}

	public BigDecimal getGanho() {
		return ganho;
	}

	public void setGanho(BigDecimal ganho) {
		if (ganho.compareTo(new BigDecimal("0")) > 0 && this.setMoeda(this.moeda)) {
			this.ganho = ganho;
		} else {
			throw new ValorException("Valor invalido! Digite um valor maior que zero em real");
		}
	}

	@Override
	public String toString() {
		BigDecimal x = (this.getGanho().subtract(this.investimento)).multiply(new BigDecimal("100"))
				.divide(this.getInvestimento());
		StringBuilder lucro = new StringBuilder(x + "").append("%");
		return lucro.toString();

	}

	public String getMoeda() {
		return moeda;
	}

	public boolean setMoeda(String moeda) {
		if (moeda.equalsIgnoreCase("real") || moeda.equalsIgnoreCase("dollar") || moeda.equalsIgnoreCase("euro")) {
			this.moeda = moeda;
			return true;
		} else {
			return false;
		}

	}
}
