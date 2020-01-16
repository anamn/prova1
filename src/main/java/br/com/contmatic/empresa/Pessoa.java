package br.com.contmatic.empresa;

public class Pessoa {
	
	private String nome;
	private String sobrenome;
	private String cpf;
	Endereco endereco;
	
	public Pessoa(String nome, String sobrenome, String cpf, Endereco endereco) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Pessoa() {
		super();

	}

	@Override
	public int hashCode() {
		final int prime = 60;
		int result = 1;
		result = prime * result + ((getCpf() == null) ? 0 : getCpf().hashCode());
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
		Pessoa outro = (Pessoa) obj;
		if (getCpf() == null) {
			if (outro.getCpf() != null) {
				return false;
			}
		} else if (!getCpf().equals(outro.getCpf()))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return " nome:" + nome +" "+ sobrenome + ", cpf:" + cpf;
	}

}
