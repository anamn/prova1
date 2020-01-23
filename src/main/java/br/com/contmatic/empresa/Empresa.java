package br.com.contmatic.empresa;

import java.util.List;

import br.com.contmatic.erros.CaracteresException;

public class Empresa {

	private String nome;

	private String cnpj;

	private double lucros;

	private String telefone;

	private String email;

	private Endereco endereco;

	private List<Cliente> clientes;

	private List<Funcionario> funcionarios;

	private List<Produto> produtos;

	public Empresa(String nome, String cnpj, String telefone, String email, double lucros, Endereco e) {
		super();
		this.setTelefone(telefone);
		this.setNome(nome);
		this.setCnpj(cnpj);
		this.setEmail(email);
		this.lucros = lucros;
		this.endereco = e;

	}

	public Empresa() {
		super();
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

	public void setCnpj(String cnpj) {
		if (cnpj.length() == 14 && cnpj.matches("[\\d]+"))
			this.cnpj = cnpj;
		else
			throw new CaracteresException("CNPJ INVALIDO!");
	}

	public String getCnpj() {
		return cnpj;
	}

	public double getLucros() {
		return lucros;
	}

	public void setLucros(double lucros) {
		this.lucros = lucros;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if (telefone.matches("[\\d]+") && telefone.length() <= 9 && telefone.length() >= 8)
			this.telefone = telefone;
		else
			throw new CaracteresException("Telefone invalido");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email.matches(".+@.+\\.[a-z]+"))
			this.email = email;
		else
			throw new CaracteresException("Email invalido");
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Empresa:" + nome + ", CNPJ: " + cnpj + ", Telefone: " + telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}
}
