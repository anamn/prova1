package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.exceptions.CaracteresException;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

	Cliente cliente = null;
	
	Cliente cliente1 = null;
	
	Cliente cliente2 = null;
	
	Cliente cliente3 = null;
	
	Cliente cliente4 = null;
	
	Endereco endereco = null;

	@Before
	public void incializacao() {
		this.endereco = new Endereco("Rua pedra", "5", "91112120");
		this.cliente = new Cliente();
		this.cliente1 = new Cliente();
		this.cliente2 = new Cliente("Maria", "12898282726", "985985466", endereco);
		this.cliente3 = new Cliente("Maria", "12898282726", "985985875", endereco);
		this.cliente4 = new Cliente("Joao", "13213213214", "921216457", endereco);
		
	}
	
	@After
	public void finaliza() {
		this.cliente=null;
		this.cliente1=null;
		this.cliente2=null;
		this.cliente3=null;
		this.cliente4=null;
	}

	@Test
	public void deve_retornar_o_endereco_passado() {
		cliente.setEndereco(endereco);
		assertTrue(cliente.getEndereco().equals(endereco));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_numeros() {
		cliente.setNome("Joao 56");
		assertTrue(cliente.getNome().equals("Joao 56"));

	}

	@Test
	public void deve_aceitar_apenas_letras() {
		cliente.setNome("Joao Silva");
		assertTrue(cliente.getNome().equals("Joao Silva"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_nome_com_menos_de_dois_digitos() {
		cliente.setNome("p");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_nome_com_mais_de_cinquenta_digitos() {
		cliente.setNome("anajskajdaiakajauanahanajaksldkfjsiajggeujdhaunshejd");
	}

	@Test
	public void deve_aceitar_cpf_com_onze_digitos() {
		cliente.setCpf("12312312323");
		assertTrue(cliente.getCpf().equals("12312312323"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_letras() {
		cliente.setCpf("123456789an");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_menos_de_onze_digitos() {
		cliente.setCpf("12309897");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_mais_de_onze_digitos() {
		cliente.setCpf("1027278278756");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_letras_no_telefone() {
		cliente.setTelefone("11 92829ajbs");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_menos_de_nove_digitos() {
		cliente.setTelefone("152461");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_mais_de_nove_digitos() {
		cliente.setTelefone("15246192828");

	}

	@Test
	public void deve_aceitar_telefone_com_nove_caracteres() {
		cliente.setTelefone("918253627");
		assertTrue(cliente.getTel().equals("918253627"));
	}

	@Test
	public void deve_retornar_valores_do_construtor() {
		
		assertTrue(cliente4.getNome().equals("Joao"));
		assertTrue(cliente4.getCpf().equals("13213213214"));
		assertTrue(cliente4.getTel().equals("921216457"));
		assertTrue(cliente4.getEndereco().equals(endereco));
	}

	@Test
	public void deve_retornar_hashCode_iguais_para_cpf_iguais() {
		assertTrue(cliente1.hashCode() == cliente1.hashCode());

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_hashCode_diferentes_para_cpf_diferentes() {
		assertTrue(cliente1.hashCode() == cliente3.hashCode());
	}

	@Test
	public void deve_testar_hashcode_para_cpf_nulo() {
		assertThat(cliente.hashCode(), Matchers.is(30));
	}

	@Test
	public void deve_retornar_true_para_cpf_iguais() {
		assertTrue(cliente2.equals(cliente3));
		System.out.println(cliente);
	}

	@Test
	public void deve_retornar_true_para_o_mesmo_cpf() {
		assertTrue(cliente1.equals(cliente1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cliente_nulo() {
		assertTrue(cliente.equals(cliente2));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_objetos_de_classes_diferentes() {
		assertTrue(cliente2.equals(new Object()));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_nulo() {
		assertTrue(cliente.equals(cliente2));

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_comparado_nulo() {
		assertTrue(cliente2.equals(null));
	}

	@Test
	public void deve_retornar_true_para_cpf_nulos() {
		assertTrue(cliente.equals(cliente1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_diferentes() {
		assertTrue(cliente3.equals(cliente4));
	}

	@Test
	public void deve_retornar_a_toString_do_objeto() {
		System.out.println(cliente2);
		assertThat(cliente2, Matchers.is(cliente2));
	}

}
