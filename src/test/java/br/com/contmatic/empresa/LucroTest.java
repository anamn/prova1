package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.exceptions.ValorException;

public class LucroTest {

	Lucro lucro = null;
	Lucro lucro2 = null;

	@Before
	public void inicializaçao() {
		this.lucro = new Lucro();
		this.lucro2 = new Lucro(new BigDecimal("30000"), new BigDecimal("33000"), "Euro");

	}

	@After
	public void finalizaçao() {
		this.lucro = null;
		this.lucro2 = null;
	}

	@Test
	public void testa_construtor() {
		assertThat(lucro2.getInvestimento(), Matchers.is(new BigDecimal("30000")));
		assertThat(lucro2.getGanho(), Matchers.is(new BigDecimal("33000")));
		assertThat(lucro2.getMoeda(), Matchers.is("Euro"));
	}

	@Test(expected = ValorException.class)
	public void nao_deve_guardar_valor_menor_que_zero() {
		lucro.setMoeda("dollar");
		lucro.setGanho(new BigDecimal("-512"));
	}

	@Test(expected = ValorException.class)
	public void nao_deve_guardar_valor_igual_que_zero() {
		lucro.setMoeda("euro");
		lucro.setGanho(new BigDecimal("0"));
	}

	@Test
	public void deve_guardar_ganho_da_empresa() {
		lucro.setMoeda("real");
		lucro.setGanho(new BigDecimal("50000"));
		assertThat(lucro.getGanho(), Matchers.is(new BigDecimal("50000")));
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_moeda_invalida() {
		lucro.setMoeda("ajhssa");
		lucro.setGanho(new BigDecimal("50000"));
	}

	@Test(expected = ValorException.class)
	public void nao_deve_guardar_valor_menor_que_zero_no_ivestimento() {
		lucro.setMoeda("dollar");
		lucro.setInvestimento(new BigDecimal("-51"));
	}

	@Test(expected = ValorException.class)
	public void nao_deve_guardar_valor_igual_que_zero_no_ivestimento() {
		lucro.setMoeda("dollar");
		lucro.setInvestimento(new BigDecimal("0"));
	}

	@Test
	public void deve_guardar_investimento_da_empresa() {
		lucro.setMoeda("real");
		lucro.setInvestimento(new BigDecimal("50000"));
		assertThat(lucro.getInvestimento(), Matchers.is(new BigDecimal("50000")));
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_outro_tipo_de_moeda_invalida() {
		lucro.setMoeda("alsl");
		lucro.setInvestimento(new BigDecimal("50000"));
	}

	@Test
	public void deve_retornar_true_para_dollar() {
		assertTrue(lucro.setMoeda("Dollar"));
	}

	@Test
	public void deve_retornar_true_para_euro() {
		assertTrue(lucro.setMoeda("euro"));
	}

	@Test
	public void deve_retornar_true_para_real() {
		assertTrue(lucro.setMoeda("Real"));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_qualquer_moeda_ivalida() {
		assertTrue(lucro.setMoeda("bollar"));
	}

	@Test
	public void testa_toString() {
		lucro.setMoeda("real");
		lucro.setInvestimento(new BigDecimal("50000"));
		lucro.setGanho(new BigDecimal("55000"));
		System.out.println(lucro);
		assertThat(lucro, equalTo(lucro));
	}

}
