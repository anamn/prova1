package br.com.contmatic.empresa;

import br.com.contmatic.erros.CaracteresError;

public class Endereco {

	private String endereco;

	private String numero;

	private String cep;

	public Endereco(String endereco, String numero, String cep) {
		super();
		this.setEndereco(endereco);
		this.setCep(cep);
		this.numero = numero;
	}

	public Endereco() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
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
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
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
		if (endereco.matches("[^\\d]+"))
			this.endereco = endereco;
		else
			throw new CaracteresError("Endereco invalido! Digite apenas letras.");
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if (cep.length() == 8 && cep.matches("[\\d]+")) {
			this.cep = cep;
		} else
			throw new CaracteresError("CEP INVALIDO!");
	}

}
