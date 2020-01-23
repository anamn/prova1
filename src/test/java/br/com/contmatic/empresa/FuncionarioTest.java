package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.ValorNegativo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

<<<<<<< HEAD
	Funcionario f = new Funcionario();
	Endereco e = new Endereco("rua tijuco", "56", "1232345");
=======
	Funcionario funcionario = new Funcionario(); 

	Funcionario funcionario4 = new Funcionario();

	Endereco endereco = new Endereco("rua tijuco", "56", "12323450");

	Funcionario funcionario1 = new Funcionario("Julia", "12131213131", "14587526645", "987546123", 1200, endereco);

	Funcionario funcionario2 = new Funcionario("Julia", "12131213131", "14587526645", "987546123", 1200, endereco);

	Funcionario funcionario3 = new Funcionario("Juliana", "98675413154", "12121212125", "987548545", 1500, endereco);

	@Test
	public void deve_aceitar_nome_com_letras() {
		funcionario.setNome("Jose");
		;
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_nome_com_numeros() {
		funcionario.setNome("12na");
	}

	@Test
	public void deve_aceitar_cpf_com_onze_digitos_sem_letras() {
		funcionario.setCpf("12312312312");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cpf_com_letras() {
		funcionario.setCpf("123456a89aj");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cpf_com_menos_de_onze_digitos() {
		funcionario.setCpf("1928192892");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cpf_com_mais_de_onze_digitos() {
		funcionario.setCpf("123123484948");
	}

	@Test
	public void deve_aceitar_telefone_com_nove_digitos() {
		funcionario.setTelefone("829374577");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_telefone_com_menos_de_nove_digitos() {
		funcionario.setTelefone("1019283");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_telefone_com_mais_de_nove_digitos() {
		funcionario.setTelefone("1827283748");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_telefone_com_letras() {
		funcionario.setTelefone("1921230aa");
	}

	@Test
	public void deve_retornar_o_endereco_passado() {
		funcionario.setEndereco(endereco);
		assertThat(funcionario.getEndereco(), Matchers.is(endereco));
	}
>>>>>>> branch_ana

	@Test(expected = ValorNegativo.class)
	public void nao_deve_aceitar_salario_menor_que_zero() {
		funcionario.setSalario(-98);
	}

	@Test(expected = ValorNegativo.class)
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

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_mais_de_onze_caracteres() {
		funcionario.setPis("192829282728");

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_menos_de_onde_caracteres() {
		funcionario.setPis("0198291");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras() {
		funcionario.setPis("0128918khhi");

	}

	@Test
	public void deve_retornar_os_valores_do_construtor() {
<<<<<<< HEAD
		Funcionario f2 = new Funcionario("Julia", "12131213131","987546123", e, 1200, "12121212125");
		assertTrue(f2.getEndereco() == e);
		assertTrue(f2.getCpf() == "12131213131");
		assertTrue(f2.getNome() == "Julia");
		assertTrue(f2.getSalario() == 1200);
		assertTrue(f2.getPis() == "12121212125");
		assertTrue(f2.getTel() == "987546123");
		
=======
>>>>>>> branch_ana

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
		funcionario.hashCode();
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
		assertTrue(funcionario1.equals(endereco));
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
		System.out.println(funcionario1);
	}

}
