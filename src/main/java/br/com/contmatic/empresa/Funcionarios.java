package br.com.contmatic.empresa;

import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.ValorNegativo;

public class Funcionarios extends Pessoa {

	private double salario;
	private String pis;

	public Funcionarios(String nome, String cpf, String tel, Endereco endereco, double salario, String pis) {
		super(nome,cpf, tel, endereco);
		this.salario = salario;
		this.setPis(pis);
	}

	public Funcionarios() {
	}

	@Override
	public String toString() {
		return "Funcionario:" +super.toString()+ ", nº: " + pis ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionarios other = (Funcionarios) obj;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		return true;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		if (salario > 0)
			this.salario = salario;
		else
			throw new ValorNegativo("Salario negativo!");
	}

	public void setPis(String pis) {
		if (pis.matches("[\\d]+") && pis.length() == 11)
			this.pis = pis;
		else
			throw new CaracteresError("PIS invalido!");
	}

	public String getPis() {
		return pis;
	}

}
