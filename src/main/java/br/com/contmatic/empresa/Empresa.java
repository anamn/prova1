package br.com.contmatic.empresa;

public class Empresa {
	
	private String nome;
	private int cnpj;
	private double lucros;
	Endereco endereco;
	Funcionarios funcionarios;
	Produto produto;
	Cliente cliente;
	
	public Empresa(String nome, int cnpj, double lucros, Endereco endereco, Produto produto) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.lucros = lucros;
		this.endereco = endereco;
		this.produto = produto;
	}
	
	public Empresa() {
		super();
	}

	@Override
	public String toString() {
		return "Empresa:" + nome + ", CNPJ: " + cnpj;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cnpj;
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
		if (cnpj != other.cnpj)
			return false;
		return true;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public int getCnpj() {
		return cnpj;
	}
	
	public double getLucros() {
		return lucros;
	}
	public void setLucros(double lucros) {
		this.lucros = lucros;
	}
	
	

}
