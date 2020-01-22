package br.com.contmatic.empresa;


import static org.junit.Assert.*;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.erros.CaracteresError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

	Cliente cliente = new Cliente();
	
	Cliente cliente4 = new Cliente();
	
	Endereco endereco=new Endereco("Rua pedra", "0", "91112120");

	Cliente cliente1 = new Cliente("Maria", "12898282726", "985985466",endereco);

	Cliente cliente2= new Cliente("Maria", "12898282726", "985985875",endereco);

	Cliente cliente3 = new Cliente("Joao", "13213213214", "921216457",endereco);
	
	@Test
	public void deve_retornar_o_endereco_passado(){
		cliente.setEndereco(endereco);
		assertTrue(cliente.getEndereco().equals(endereco)); 
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_numeros() {
		cliente.setNome("Joao 56");
		assertTrue(cliente.getNome().equals("Joao 56"));

	}

	@Test
	public void deve_aceitar_apenas_letras() {
		cliente.setNome("Joao Silva"); 
		assertTrue(cliente.getNome().equals("Joao Silva"));
	}

	@Test
	public void deve_aceitar_cpf_com_onze_digitos() {
		cliente.setCpf("12312312323");
		assertTrue(cliente.getCpf().equals("12312312323"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras() {
		cliente.setCpf("123456789an");
		
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_menos_de_onze_digitos() {
		cliente.setCpf("12309897");
		
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_mais_de_onze_digitos() {
		cliente.setCpf("1027278278756");
		
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras_no_telefone() {
		cliente.setTelefone("11 92829ajbs");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_telefone_com_menos_de_nove_digitos() {
		cliente.setTelefone("152461");

	}

	@Test(expected = CaracteresError.class)
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
		assertTrue(cliente3.getNome().equals("Joao"));

		assertTrue(cliente3.getCpf().equals("13213213214"));

		assertTrue(cliente3.getEndereco().getEndereco().equals( "Rua pedra"));

		assertTrue(cliente3.getEndereco().getNumero().equals("0"));

		assertTrue(cliente3.getEndereco().getCep().equals("91112120"));

		assertTrue(cliente3.getTel().equals("921216457"));
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
	public void deve_retornar_true_para_cpf_iguais() {
		assertTrue(cliente1.equals(cliente2));
	}

	@Test
	public void deve_retornar_true_para_o_mesmo_cpf() {
		assertTrue(cliente1.equals(cliente1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cliente_nulo() {
		assertTrue(cliente.equals(cliente1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_objetos_de_classes_diferentes() {
		assertTrue(cliente1.equals(endereco));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_nulo() {
		assertTrue(cliente.equals(cliente1));

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_comparado_nulo() {
		assertTrue(cliente1.equals(null));
	}

	@Test
	public void deve_retornar_true_para_cpf_nulos() {
		assertTrue(cliente.equals(cliente4));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_diferentes() {
		assertTrue(cliente1.equals(cliente3));
	}

	@Test
	public void deve_retornar_a_toString_do_objeto() {
		System.out.println(cliente1);
	}

}
