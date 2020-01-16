package br.com.contmatic.empresa;

public class Produto {

	private String tipo;
	private double preco;
	private String codigo;
	
	public Produto(String tipo, double preco, String codigo) {
		super();
		this.tipo = tipo;
		this.preco = preco;
		this.codigo = codigo;
	}
	
	public Produto() {
		super();
	}

	@Override
	public String toString() {
		return "Produto:" + tipo + ", preço:" + preco + ", codigo:" + codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
