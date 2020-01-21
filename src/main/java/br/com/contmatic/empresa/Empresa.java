package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.List;

import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.Inexistente;
import br.com.contmatic.erros.VendaIndisponivel;

public class Empresa {

	private String nome;
	private String cnpj;
	private double lucros;
	private String tel;
	private Endereco endereco;
	private Funcionarios funcionarios;
	private Produto produto;
	private Cliente cliente;
	private List<Cliente> clienteLista = new ArrayList<Cliente>();
	private List<String> cpfLista = new ArrayList<String>();
	private List<Funcionarios> listaFuncio = new ArrayList<Funcionarios>();
	private List<String> listaPis = new ArrayList<String>();
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	private List<String> listaCodigoProduto = new ArrayList<String>();

	public Empresa(String nome, String cnpj, String tel, double lucros, Endereco e) {
		super();
		this.tel = tel;
		this.nome = nome;
		this.setCnpj(cnpj);
		this.lucros = lucros;
		this.endereco = e;

	}

	public Empresa() {
		super();
	}

	public void addCliente(Cliente cliente) {
		if (this.validaCliente(cliente.getCpf())) {
			System.out.println("Cliente já existe");
		}
		clienteLista.add(cliente);
		cpfLista.add(cliente.getCpf());
	}

	public boolean validaCliente(String cpf2) {
		if (this.getCpfLista().contains(cpf2)) 
			return true;
		 else
			return false;
	}

	public void addFuncionario(Funcionarios funcionario) {
		if (this.validaFuncionario(funcionario.getPis())) {
			System.out.println("Funcionario já existe");
		}
		listaFuncio.add(funcionario);
		listaPis.add(funcionario.getPis());

	}

	public void removeFuncionario(Funcionarios funcionario) {
		if (this.validaFuncionario(funcionario.getPis())) {
		listaFuncio.remove(funcionario);
		listaPis.remove(funcionario.getPis());
		}
		else 
			throw new Inexistente(null);

	}

	public boolean validaFuncionario(String pis2) {
		if (this.getListaPis().contains(pis2)) 
			return true;
		 else
			return false;
	}

	public void addProduto(Produto produto) {
		listaProdutos.add(produto);
		listaCodigoProduto.add(produto.getCodigo());
	}

	public boolean validaProduto(String codigo) {
		if (this.getListaCodigoProduto().contains(codigo)) 
			return true;
		 else
			throw new Inexistente("Produto não disponivel");

	}

	public void validaVenda(String pis, String cpf, String codigo, Produto p) {
		if (this.validaFuncionario(pis) && this.validaCliente(cpf) &&
				this.validaProduto(codigo) && p.getCodigo().equals(codigo)) {
			System.out.println("Pronto pra venda! " + p);
			listaProdutos.remove(p);
			listaCodigoProduto.remove(codigo);
		}
		else if (!this.validaFuncionario(pis) && this.validaCliente(cpf) && 
				this.validaProduto(codigo) && p.getCodigo().equals(codigo))
			throw new Inexistente("Funcionario não encontrado!");
		
		else if(this.validaFuncionario(pis) && !this.validaCliente(cpf) &&
				this.validaProduto(codigo) && p.getCodigo().equals(codigo))
			throw new Inexistente("Favor cadastrar cliente");
		
		else if(this.validaFuncionario(pis) && this.validaCliente(cpf) && !this.validaProduto(codigo)
				&& p.getCodigo().equals(codigo))
			throw new Inexistente("Produto indisponivel");
		
		else if (this.validaFuncionario(pis) && this.validaCliente(cpf)
				&& this.validaProduto(codigo) && !p.getCodigo().equals(codigo))
			throw new VendaIndisponivel("Codigo não corresponde ao produto");
		else
			throw new VendaIndisponivel("");
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
			this.tel = tel;
		else
			throw new CaracteresError("Telefone invalido");
	}

	public List<Cliente> getClienteLista() {
		return clienteLista;
	}

	public List<String> getCpfLista() {
		return cpfLista;
	}

	public List<String> getListaCodigoProduto() {
		return listaCodigoProduto;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

}
