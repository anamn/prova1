package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.erros.CaracteresError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTest {

	Endereco end = new Endereco();

	@Test
	public void deve_aceitar_cep_com_sete_digitos_e_numeros() {
		end.setCep("2322853");
		assertTrue(end.getCep() == "2322853");

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_mais_de_sete_digitos() {
		end.setCep("1918171722");
		assertTrue(end.getCep() == "1918171722");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_menos_digitos() {
		end.setCep("192838");
		assertTrue(end.getCep() == "192838");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_letras() {
		end.setCep("182723a");
		assertTrue(end.getCep() == "182723a");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_caracteres_especiais() {
		end.setCep("123232/");
		assertTrue(end.getCep() == "123232/");
	}

	@Test
	public void deve_aceitar_endereco_com_letras_e_caracters() {
		end.setEndereco("Av. jacarandá");
		assertThat(end.getEndereco(), Matchers.is("Av. jacarandá"));

	}
	
	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_numeros() {
		end.setEndereco("Rua 4546");
		assertTrue(end.getEndereco() == "Rua 4546");
	}

	@Test
	public void deve_retornar_o_valor_informado() {
		end.setNumero("12");
		assertTrue(end.getNumero() == "12");
	}
	

	@Test
	public void deve_retornar_os_valores_do_construtor() {
		Endereco e = new Endereco("Av. ac", "187", "1926372");
		assertThat(e.getEndereco(), Matchers.is("Av. ac"));
		assertThat(e.getCep(), Matchers.is("1926372"));
		assertThat(e.getNumero(), Matchers.is("187"));
	}
}
