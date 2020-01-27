package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.exceptions.CaracteresException;

@FixMethodOrder(NAME_ASCENDING)
public class EnderecoTest {

	Endereco endereco = null;

	Endereco endereco1 = null;

	Endereco endereco2 = null;

	Endereco endereco3 = null;

	Endereco endereco4 = null;

	@Before
	public void incializa() {
		this.endereco = new Endereco();
		this.endereco1 = new Endereco("Rua tijuco", "452", "03564870");
		this.endereco2 = new Endereco("Rua tijuco", "452", "03564870");
		this.endereco3 = new Endereco("Rua tijuco", "452", "84574512");
		this.endereco4 = new Endereco();
	}

	@After
	public void finaliza() {
		this.endereco = null;
		this.endereco1 = null;
		this.endereco2 = null;
		this.endereco3 = null;
		this.endereco4 = null;
	}

	@Test
	public void deve_aceitar_endereco_com_letras_e_caracters() {
		endereco.setEndereco("Av jacaranda");
		assertThat(endereco.getEndereco(), Matchers.is("Av jacaranda"));

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_endereco_com_menos_de_cinco_digitos() {
		endereco.setEndereco("av. a");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_mais_de_quarenta_digitos_no_endereco() {
		endereco.setEndereco("avenida atuanhajaimalainatadlsksisnsohaja");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_numeros_no_endereco() {
		endereco.setEndereco("Rua 4546");

	}

	@Test
	public void deve_retornar_o_valor_informado() {
		endereco.setNumero("12");
		assertTrue(endereco.getNumero().equals("12"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_menos_que_um_digito() {
		endereco.setNumero("");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_mais_de_vinte_digitos() {
		endereco.setNumero("123456789123456789003");
	}

	@Test
	public void deve_aceitar_cep_com_oito_digitos_e_numeros() {
		endereco.setCep("23228530");
		assertTrue(endereco.getCep().equals("23228530"));

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cep_com_mais_de_oito_digitos() {
		endereco.setCep("1918171722");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cep_com_menos_digitos() {
		endereco.setCep("192838");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cep_com_letras() {
		endereco.setCep("1827238a");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cep_com_caracteres_especiais() {
		endereco.setCep("1232320/");

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
	public void deve_testar_hashcode_para_cep_nulo() {
		assertThat(endereco.hashCode(), Matchers.is(30));
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
		assertTrue(endereco1.equals(new Object()));
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
		assertThat(endereco1, Matchers.is(endereco1));
	}

}
