package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.List;

import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.Inexistente;

public class Empresa {

	private String nome;
	private String cnpj;
	private double lucros;
	private String tel;
	Endereco endereco;
	Funcionarios funcionarios;
	Produto produto;
	Cliente cliente;
	List<Funcionarios> listaFuncio = new ArrayList<Funcionarios>();
	List<String> listaPis = new ArrayList<String>();

	public Empresa(String nome, String cnpj, String tel, double lucros, Endereco e, Produto produto) {
		super();
		this.tel=tel;
		this.nome = nome;
		this.setCnpj(cnpj);
		this.lucros = lucros;
		this.endereco = e;
		this.produto = produto;
	}

	public Empresa() {
		super();
	}

	public void addFuncionario(Funcionarios funcionarios) {
		listaFuncio.add(funcionarios);
		listaPis.add(funcionarios.getPis());

	}
	
	public void removeFuncionario(Funcionarios funcionario) {
		listaFuncio.remove(funcionario);
		listaPis.remove(funcionario.getPis());
		System.out.println(funcionario +" foi removido!");
	}

	public void validaFuncionario(String pis2) {
		if (this.getListaPis().contains(pis2))
			System.out.println("Funcionario encontrado!");
		else
			throw new Inexistente("Funcionario não encontrado");

	}

	@Override
	public String toString() {
		return "Empresa:" + nome + ", CNPJ: " + cnpj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
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

	public void setNome(String nome) {
		if (nome.matches("[^\\d]+"))
			this.nome = nome;
		else
			throw new CaracteresError("O nome deve conter apenas letras.");
	}

	public String getNome() {
		return nome;
	}
	
	public void setCnpj(String cnpj) {
		if (cnpj.length() == 14 && cnpj.matches("[\\d]+"))
			this.cnpj = cnpj;
		 else
			throw new CaracteresError("CNPJ INVALIDO!");
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

	public Funcionarios getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Funcionarios> getListaFuncio() {
		return listaFuncio;
	}

	public List<String> getListaPis() {
		return listaPis;
	}

	public String getTel() {
		return tel;
	}

	public void setTelefone(String tel) {
		if (tel.matches("[\\d]+") && tel.length() == 9)
			this.tel=tel;
		else
			throw new CaracteresError("Telefone invalido");
	}
	 

}
