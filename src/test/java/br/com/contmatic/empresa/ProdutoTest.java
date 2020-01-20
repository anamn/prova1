package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Produto;
import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.ValorNegativo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest {

	Produto p = new Produto();

	@Test(expected = ValorNegativo.class)
	public void nao_deve_aceitar_preco_negativo() {
		p.setPreco(-5);

	}

	@Test
	public void deve_aceitar_preco_diferente_de_zero() {
		p.setPreco(14.99);
		assertThat(p.getPreco(), Matchers.is(14.99));
	}
	
	@Test
	public void deve_aceitar_numeros_no_codigo() {
		p.setCodigo("989");
		assertThat(p.getCodigo(), Matchers.is("989"));
	}
	
	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras_no_codigo() {
		p.setCodigo("089as");
		
	}
	
	@Test
	public void deve_retornar_valores_do_construtor() {
		Produto p2 = new Produto("Blusa", 5, "13");
		assert (p2.getTipo() == "Blusa");
		assertThat(p2.getPreco(), Matchers.is(5.0));
		assert (p2.getCodigo() == "13");
		System.out.println(p2);
	}

}
