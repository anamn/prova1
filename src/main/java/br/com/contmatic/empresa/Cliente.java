package br.com.contmatic.empresa;

import br.com.contmatic.exceptions.CaracteresException;

public class Cliente {

	private String nome;

	private String cpf;

	private String telefone;

	private Endereco endereco;

	public Cliente(String nome, String cpf, String telefone, Endereco endereco) {
		super();
		this.setTelefone(telefone);
		this.setCpf(cpf);
		this.setNome(nome);
		this.endereco = endereco;
	}

	public Cliente() {
	}

	public void setNome(String nome) {
		if (nome.matches("[^\\d]+") && nome.length() <= 50 && nome.length() > 1) {
			this.nome = nome;
		} else {
			throw new CaracteresException(
					"O nome deve conter apenas letras e ter no minimo dois digitos e no maximo cinquenta");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		if (cpf.length() == 11 && cpf.matches("[\\d]+")) {
			this.cpf = cpf;
		} else {
			throw new CaracteresException("CPF INVALIDO!");
		}
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

	public String getTel() {
		return telefone;
	}

	public void setTelefone(String tel) {
		if (tel.matches("[\\d]+") && tel.length() == 9) {
			this.telefone = tel;
		} else {
			throw new CaracteresException("Telefone invalido");
		}
	}

	@Override
	public String toString() {
		return new StringBuilder("Nome:").append(nome).append(", CPF:").append(cpf).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}