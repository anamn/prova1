package br.com.contmatic.empresa;

public class Endereco {
	private String endereco;
	private int numero;
	private int cep;

	public Endereco(String endereco, int numero, int cep) {
		super();
		this.endereco = endereco;
		this.numero = numero;
		this.cep = cep;
	}
 
	public Endereco() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cep;
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
		Endereco other = (Endereco) obj;
		if (cep != other.cep)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco:" + endereco + ", nº:" + numero + ", CEP:" + cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

}
