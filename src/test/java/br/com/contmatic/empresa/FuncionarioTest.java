package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.exceptions.CaracteresException;
import br.com.contmatic.exceptions.ValorNegativoException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

	private Funcionario funcionario = new Funcionario(); 

	private Funcionario funcionario4 = new Funcionario();

	private Endereco endereco = new Endereco("rua tijuco", "56", "12323450");

	private Funcionario funcionario1 = new Funcionario("Julia", "12131213131", "14587526645", "987546123", 1200, endereco);

	private Funcionario funcionario2 = new Funcionario("Julia", "12131213131", "14587526645", "987546123", 1200, endereco);

	private Funcionario funcionario3 = new Funcionario("Juliana", "98675413154", "12121212125", "987548545", 1500, endereco);

	@Test
	public void deve_aceitar_nome_com_letras() {
		funcionario.setNome("Jose");
		assertTrue(funcionario.getNome().equals("Jose"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_nome_com_numeros() {
		funcionario.setNome("12na");
	}

	@Test
	public void deve_aceitar_cpf_com_onze_digitos_sem_letras() {
		funcionario.setCpf("12312312312");
		assertTrue(funcionario.getCpf().equals("12312312312"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cpf_com_letras() {
		funcionario.setCpf("123456a89aj");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cpf_com_menos_de_onze_digitos() {
		funcionario.setCpf("1928192892");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cpf_com_mais_de_onze_digitos() {
		funcionario.setCpf("123123484948");
	}

	@Test
	public void deve_aceitar_telefone_com_nove_digitos() {
		funcionario.setTelefone("829374577");
		assertTrue(funcionario.getTelefone().equals("829374577"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_menos_de_nove_digitos() {
		funcionario.setTelefone("1019283");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_mais_de_nove_digitos() {
		funcionario.setTelefone("1827283748");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_letras() {
		funcionario.setTelefone("1921230aa");
	}

	@Test
	public void deve_retornar_o_endereco_passado() {
		funcionario.setEndereco(endereco);
		assertThat(funcionario.getEndereco(), Matchers.is(endereco));
	}

	@Test(expected = ValorNegativoException.class)
	public void nao_deve_aceitar_salario_menor_que_zero() {
		funcionario.setSalario(-98);
	}

	@Test(expected = ValorNegativoException.class)
	public void nao_deve_aceitar_salario_igual_a_zero() {
		funcionario.setSalario(0);
	}

	@Test
	public void deve_aceitar_salario_positivo() {
		funcionario.setSalario(570);
		assertTrue(funcionario.getSalario() == 570);
	}

	@Test
	public void deve_aceitar_apenas_numeros_no_pis() {
		funcionario.setPis("10292791212");
		assertThat(funcionario.getPis(), Matchers.is("10292791212"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_mais_de_onze_caracteres() {
		funcionario.setPis("192829282728");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_menos_de_onde_caracteres() {
		funcionario.setPis("0198291");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_letras() {
		funcionario.setPis("0128918khhi");

	}

	@Test
	public void deve_retornar_os_valores_do_construtor() {

		assertTrue(funcionario1.getEndereco().equals(endereco));
		assertTrue(funcionario1.getCpf().equals("12131213131"));
		assertTrue(funcionario1.getNome().equals("Julia"));
		assertTrue(funcionario1.getSalario() == 1200);
		assertTrue(funcionario1.getPis().equals("14587526645"));
		assertTrue(funcionario1.getTelefone().equals("987546123"));

	}

	@Test
	public void deve_retornar_hashCode_iguais_para_pis_iguais() {
		assertTrue(funcionario1.hashCode() == funcionario2.hashCode());

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_hashCode_diferentes_para_pis_diferentes() {
		assertTrue(funcionario1.hashCode() == funcionario3.hashCode());
	}

	@Test
	public void deve_testar_hashcode_para_pis_nulo() {
		assertThat(funcionario.hashCode(), Matchers.is(30));
	}

	@Test
	public void deve_retornar_true_para_pis_iguais() {
		assertTrue(funcionario1.equals(funcionario2));
	}

	@Test
	public void deve_retornar_true_para_o_mesmo_funcionario() {
		assertTrue(funcionario1.equals(funcionario1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_funcionario_nulo() {
		assertTrue(funcionario.equals(funcionario1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_objetos_de_classes_diferentes() {
		assertTrue(funcionario1.equals(new Object()));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_pis_nulo() {
		assertTrue(funcionario.equals(funcionario1));

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_pis_comparado_nulo() {
		assertTrue(funcionario1.equals(null));
	}

	@Test
	public void deve_retornar_true_para_pis_nulos() {
		assertTrue(funcionario.equals(funcionario4));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_pis_diferentes() {
		assertTrue(funcionario1.equals(funcionario3));
	}

	@Test
	public void deve_retornar_a_toString_do_objeto() {
		assertThat(funcionario1, Matchers.is(funcionario1));
	}

}
