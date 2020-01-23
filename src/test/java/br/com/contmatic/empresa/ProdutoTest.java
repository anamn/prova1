package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Produto;
import br.com.contmatic.exceptions.CaracteresException;
import br.com.contmatic.exceptions.ValorNegativoException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest {

	private Produto produto = new Produto();

	private Produto produto1 = new Produto("Blusa", 5, "13");

	private Produto produto2 = new Produto("Blusa", 5, "13");

	private Produto produto3 = new Produto("Calça", 15, "15");

	private Produto produto4 = new Produto();

	@Test
	public void deve_aceitar_tipo_de_produto() {
		produto.setTipo("Roupa");
		assertTrue(produto.getTipo().equals("Roupa"));
	}

	@Test(expected = ValorNegativoException.class)
	public void nao_deve_aceitar_preco_negativo() {
		produto.setPreco(-5);

	}

	@Test
	public void deve_aceitar_preco_diferente_de_zero() {
		produto.setPreco(14.99);
		assertThat((Double) produto.getPreco(), Matchers.is((double) 14.99));
	}

	@Test
	public void deve_aceitar_numeros_no_codigo() {
		produto.setCodigo("989");
		assertThat(produto.getCodigo(), Matchers.is("989"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_letras_no_codigo() {
		produto.setCodigo("089as");

	}

	@Test
	public void deve_retornar_valores_do_construtor() {
		assert (produto2.getTipo() == "Blusa");
		assertThat(produto2.getPreco(), Matchers.is(5.0));

		assert (produto2.getCodigo() == "13");
		System.out.println(produto2);
	}

	@Test
	public void deve_retornar_hashCode_iguais_para_codigo_iguais() {
		assertTrue(produto1.hashCode() == produto2.hashCode());

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_hashCode_diferentes_para_codigo_diferentes() {
		assertTrue(produto1.hashCode() == produto3.hashCode());
	}

	@Test
	public void deve_testar_hashcode_para_codigo_nulo() {
		assertThat(produto.hashCode(), Matchers.is(30));
	}

	@Test
	public void deve_retornar_true_para_codigo_iguais() {
		assertTrue(produto1.equals(produto2));
	}

	@Test
	public void deve_retornar_true_para_o_mesmo_codigo() {
		assertTrue(produto1.equals(produto1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_produto_nulo() {
		assertTrue(produto.equals(produto1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_objetos_de_classes_diferentes() {
		assertTrue(produto1.equals(new Object()));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_codigo_nulo() {
		assertTrue(produto.equals(produto1));

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_codigo_comparado_nulo() {
		assertTrue(produto1.equals(null));
	}

	@Test
	public void deve_retornar_true_para_codigo_nulos() {
		assertTrue(produto.equals(produto4));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_codigo_diferentes() {
		assertTrue(produto1.equals(produto3));
	}

	@Test
	public void deve_retornar_a_toString_do_objeto() {
		assertThat(produto1, Matchers.is(produto1));
	}
}
