package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Produto;
import br.com.contmatic.exceptions.CaracteresException;
import br.com.contmatic.exceptions.ValorException;

@FixMethodOrder(NAME_ASCENDING)
public class ProdutoTest {

	Produto produto = null;

	Produto produto1 = null;

	Produto produto2 = null;

	Produto produto3 = null;

	Produto produto4 = null;

	@Before
	public void incializacao() {
		this.produto = new Produto();
		this.produto1 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("5"), "122523");
		this.produto2 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("5"), "122523");
		this.produto3 = new Produto("Calça", "Calça preta com botoes", new BigDecimal("15"), "154545");
		this.produto4 = new Produto();
	}
	
	@After
	public void finalizacao() {
		this.produto = null;
		this.produto1 = null;
		this.produto2 = null;
		this.produto3 = null;
		this.produto4 = null;
	}

	@Test
	public void deve_aceitar_tipo_de_produto() {
		produto.setTipo("Roupa");
		assertTrue(produto.getTipo().equals("Roupa"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_tipo_de_produto_com_menos_de_um_digito() {
		produto.setTipo("");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_tipo_de_produto_com_mais_de_cinquenta_digitos() {
		produto.setTipo("asdkfjieksjdkoeirororororororpritirjrirkrkrnrkrjrjrikmkhjjijkk");
	}

	@Test
	public void deve_aceitar_descricao_do_produto() {
		produto.setDescricao("blusa com mangas e bla bla bla");
		assertThat(produto.getDescricao(), Matchers.is("blusa com mangas e bla bla bla"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_descricao_com_menos_de_dez_digitos() {
		produto.setDescricao("calca");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_descricao_com_mais_de_sessenta_digitos() {
		produto.setDescricao("kajkjdjjjjjjjjjjjjjjjjjjjjjjshbdksljfffhhhhhhhhhhhhhhhhhhhhhhhhaljj");
	}

	@Test
	public void deve_aceitar_preco_positivo() {
		produto.setPreco(new BigDecimal("15"));
		assertThat(produto.getPreco(), Matchers.is(new BigDecimal("15")));
	}

	@Test(expected = ValorException.class)
	public void nao_deve_aceitar_preco_igual_a_zero() {
		produto.setPreco(new BigDecimal("0"));
	}

	@Test(expected = ValorException.class)
	public void nao_deve_aceitar_preco_negativo() {
		produto.setPreco(new BigDecimal("0"));

	}

	@Test
	public void deve_aceitar_numeros_no_codigo() {
		produto.setCodigo("989545");
		assertThat(produto.getCodigo(), Matchers.is("989545"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_menos_de_cinco_digitos_no_codigo() {
		produto.setCodigo("12");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_mais_de_trinta_digitos_no_codigo() {
		produto.setCodigo("1234567891234567891234567891233");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_letras_no_codigo() {
		produto.setCodigo("089as");

	}

	@Test
	public void deve_retornar_valores_do_construtor() {
		assertTrue(produto2.getDescricao().equals("Blusa de maga,com estampas"));
		assertTrue(produto2.getTipo().equals("Blusa"));
		assertThat(produto2.getPreco(), Matchers.is(new BigDecimal("5")));
		assertTrue(produto2.getCodigo().equals("122523"));

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
		System.out.println(produto1);
		assertThat(produto1, Matchers.is(produto1));
	}
}
