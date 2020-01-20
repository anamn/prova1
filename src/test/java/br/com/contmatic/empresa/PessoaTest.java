package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Pessoa;
import br.com.contmatic.erros.CaracteresError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessoaTest {

	Pessoa pessoa = new Pessoa();

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_numeros() {
		pessoa.setNome("Joao 56");
		assertThat(pessoa.getNome(), Matchers.is("Joao 56"));

	}

	@Test
	public void deve_aceitar_apenas_letras() {
		pessoa.setNome("Joao Silva");
		assertThat(pessoa.getNome(), Matchers.is("Joao Silva"));
	}

	@Test
	public void deve_aceitar_cpf_com_onze_digitos() {
		pessoa.setCpf("12312312323");
		assertThat(pessoa.getCpf(), Matchers.is("12312312323"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras() {
		pessoa.setCpf("123456789an");
		assertThat(pessoa.getCpf(), Matchers.is("123456789an"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_menos_de_onze_digitos() {
		pessoa.setCpf("12309897");
		assertThat(pessoa.getCpf(), Matchers.is("12309897"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_mais_de_onze_digitos() {
		pessoa.setCpf("1027278278756");
		assertThat(pessoa.getCpf(), Matchers.is("1027278278756"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras_no_telefone() {
		pessoa.setTelefone("11 92829ajbs");
	}

	@Test (expected = CaracteresError.class)
	public void nao_deve_aceitar_telefone_com_menos_de_nove_digitos() {
		pessoa.setTelefone("152461");
		
	}
	
	@Test (expected = CaracteresError.class)
	public void nao_deve_aceitar_telefone_com_mais_de_nove_digitos() {
		pessoa.setTelefone("15246192828");
		
	}

	@Test
	public void deve_aceitar_telefone_com_nove_caracteres() {
		pessoa.setTelefone("918253627");
		assertTrue(pessoa.getTel() == "918253627");
	}

	@Test
	public void deve_retornar_valores_do_construtor() {
		Pessoa p = new Pessoa("Ana", "12901929111", "956457588", new Endereco("Av. ac", "187", "1926372"));
		p.getNome().matches("Ana");
		assertEquals(p.getCpf(), "12901929111");
		assertTrue(p.getEndereco().getEndereco() == "Av. ac");
		assertTrue(p.getEndereco().getNumero() == "187");
		assertTrue(p.getEndereco().getCep() == "1926372");
	}

}
