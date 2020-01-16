package br.com.contmatic.empresa;

public class Funcionarios extends Pessoa{
	
	private double salario;
	private String numeroDeCadastro;
	
	public Funcionarios(String nome, String sobrenome, String cpf, Endereco endereco,
			double salario, String numeroDeCadastro) {
		super(nome, sobrenome, cpf, endereco);
		this.salario = salario;
		this.numeroDeCadastro = numeroDeCadastro;
	}
	
	public Funcionarios() {
		super();
	}



	@Override
	public String toString() {
		return "Funcionario: nº: " + numeroDeCadastro + super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numeroDeCadastro == null) ? 0 : numeroDeCadastro.hashCode());
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
		if (numeroDeCadastro == null) {
			if (other.numeroDeCadastro != null)
				return false;
		} else if (!numeroDeCadastro.equals(other.numeroDeCadastro))
			return false;
		return true;
	}


	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getNumeroDeCadastro() {
		return numeroDeCadastro;
	}
	
}
