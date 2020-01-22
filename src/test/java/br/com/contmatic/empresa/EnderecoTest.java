package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.erros.CaracteresError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTest {

	Endereco endereco = new Endereco();

	Endereco endereco1 = new Endereco("Rua tijuco", "452", "03564870");

	Endereco endereco2 = new Endereco("Rua tijuco", "452", "03564870");

	Endereco endereco3 = new Endereco("Rua tijuco", "452", "84574512");

	Endereco endereco4 = new Endereco();

	Cliente cliente = new Cliente("Geovana", "13299283373", "919283744", endereco);

	@Test
	public void deve_aceitar_endereco_com_letras_e_caracters() {
		endereco.setEndereco("Av jacaranda");
		assertThat(endereco.getEndereco(), Matchers.is("Av jacaranda"));

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_numeros_no_endereco() {
		endereco.setEndereco("Rua 4546");

	}

	@Test
	public void deve_retornar_o_valor_informado() {
		endereco.setNumero("12");
		assertTrue(endereco.getNumero().equals("12"));
	}

	@Test
	public void deve_aceitar_cep_com_oito_digitos_e_numeros() {
		endereco.setCep("23228530");
		assertTrue(endereco.getCep().equals("23228530"));

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_mais_de_oito_digitos() {
		endereco.setCep("1918171722");

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_menos_digitos() {
		endereco.setCep("192838");

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_letras() {
		endereco.setCep("182723a");

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cep_com_caracteres_especiais() {
		endereco.setCep("123232/");

	}

	@Test
	public void deve_retornar_os_valores_do_construtor() {
		Endereco endereco3 = new Endereco("Av. ac", "187", "19263720");
		assertThat(endereco3.getEndereco(), Matchers.is("Av. ac"));
		assertThat(endereco3.getCep(), Matchers.is("19263720"));
		assertThat(endereco3.getNumero(), Matchers.is("187"));

	}

	@Test
	public void deve_retornar_hashCode_iguais_para_ceps_iguais() {
		assertTrue(endereco1.hashCode() == endereco1.hashCode());

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_hashCode_diferentes_para_ceps_diferentes() {
		assertTrue(endereco1.hashCode() == endereco3.hashCode());
	}

	@Test
	public void deve_retornar_true_para_ceps_iguais() {
		assertTrue(endereco1.equals(endereco2));
	}

	@Test
	public void deve_retornar_true_para_o_mesmo_cep() {
		assertTrue(endereco1.equals(endereco1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_endereco_nulo() {
		assertTrue(endereco.equals(endereco1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_objetos_de_classes_diferentes() {
		assertTrue(endereco1.equals(cliente));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cep_nulo() {
		endereco.setEndereco("Rua nao sei");
		endereco.setNumero("12");
		assertTrue(endereco.equals(endereco1));

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cep_comparado_nulo() {
		assertTrue(endereco1.equals(null));
	}

	@Test
	public void deve_retornar_true_para_ceps_nulos() {
		assertTrue(endereco.equals(endereco4));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_ceps_diferentes() {
		assertTrue(endereco1.equals(endereco3));
	}

	@Test
	public void deve_retornar_a_toString_do_objeto() {
		System.out.println(endereco1);
	}

}
