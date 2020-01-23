package br.com.contmatic.empresa;

import br.com.contmatic.exceptions.CaracteresException;
import br.com.contmatic.exceptions.ValorNegativoException;

public class Funcionario {

	private double salario;

	private String pis;

	private String nome;

	private String cpf;

	private String telefone;

	private Endereco endereco;

	public Funcionario(String nome, String cpf, String pis, String telefone, double salario, Endereco endereco) {
		super();
		this.setSalario(salario);
		this.setPis(pis);
		this.setNome(nome);
		this.setCpf(cpf);
		this.setTelefone(telefone);
		this.endereco = endereco;
	}

	public Funcionario() {
	}

	public void setNome(String nome) {
		if (nome.matches("[^\\d]+"))
			this.nome = nome;
		else
			throw new CaracteresException("O nome deve conter apenas letras.");
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		if (cpf.length() == 11 && cpf.matches("[\\d]+")) {
			this.cpf = cpf;
		} else
			throw new CaracteresException("CPF INVALIDO!");
	}

	public String getCpf() {
		return cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String tel) {
		if (tel.matches("[\\d]+") && tel.length() == 9)
			this.telefone = tel;
		else
			throw new CaracteresException("Telefone invalido");
	}

	public String getTelefone() {
		return telefone;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		if (salario > 0)
			this.salario = salario;
		else
			throw new ValorNegativoException("Salario negativo!");
	}

	public void setPis(String pis) {
		if (pis.matches("[\\d]+") && pis.length() == 11)
			this.pis = pis;
		else
			throw new CaracteresException("PIS invalido!");
	}

	public String getPis() {
		return pis;
	}

	@Override
	public String toString() {
		return "Funcionario:" + "Nome:" + nome + ", CPF:" + cpf + ", nº: " + pis;
	}

	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		return true;
	}

}
