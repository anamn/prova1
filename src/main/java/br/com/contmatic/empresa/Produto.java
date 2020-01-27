package br.com.contmatic.empresa;

import java.math.BigDecimal;

import br.com.contmatic.exceptions.CaracteresException;
import br.com.contmatic.exceptions.ValorException;

public class Produto {

	private String tipo;

	private BigDecimal preco;

	private String descricao;

	private String codigo;

	public Produto(String tipo, String descricao, BigDecimal preco, String codigo) {
		super();
		this.setTipo(tipo);
		this.setDescricao(descricao);
		this.setPreco(preco);
		this.setCodigo(codigo);
	}

	public Produto() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao.length() > 10 && descricao.length() <= 60) {
			this.descricao = descricao;
		} else {
			throw new CaracteresException("O descrição do produto deve conter de onze a sessenta digitos");
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo.length() > 0 && tipo.length() <= 50) {
			this.tipo = tipo;
		} else {
			throw new CaracteresException("O tipo de produto deve conter de um a cinquenta caracteres");
		}
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		int d = preco.compareTo(new BigDecimal("0"));
		if (d > 0) {
			this.preco = preco;
		} else {
			throw new ValorException("Valor invalido!");
		}

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (codigo.matches("[\\d]+") && codigo.length() <= 30 && codigo.length() > 5) {
			this.codigo = codigo;
		} else {
			throw new CaracteresException("Codigo deve conter de cinco a trinta digitos e apenas letras!");
		}
	}

	@Override
	public String toString() {
		return "Produto:" + tipo + ", preço:" + preco + ", codigo:" + codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 30;
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

}
