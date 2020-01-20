package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionarios;
import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.ValorNegativo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

	Funcionarios f = new Funcionarios();
	Endereco e = new Endereco("rua tijuco", "56", "1232345");

	@Test(expected = ValorNegativo.class)
	public void nao_deve_aceitar_salario_menor_que_zero() {
		f.setSalario(-98);
	}

	@Test(expected = ValorNegativo.class)
	public void nao_deve_aceitar_salario_igual_a_zero() {
		f.setSalario(0);
	}

	@Test
	public void deve_aceitar_salario_positivo() {
		f.setSalario(570);
		assertTrue(f.getSalario() == 570);
	}

	@Test
	public void deve_aceitar_apenas_numeros() {
		f.setPis("10292791212");
		assertThat(f.getPis(), Matchers.is("10292791212"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_mais_de_onze_caracteres() {
		f.setPis("192829282728");

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_menos_de_onde_caracteres() {
		f.setPis("0198291");
		assertTrue(f.getPis() == "0198291");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras() {
		f.setPis("0128918khhi");
		assertTrue(f.getPis() == "0128918khhi");
	}

	@Test
	public void deve_retornar_os_valores_do_construtor() {
		Funcionarios f2 = new Funcionarios("Julia", "12131213131","987546123", e, 1200, "12121212125");
		assertTrue(f2.getEndereco() == e);
		assertTrue(f2.getCpf() == "12131213131");
		assertTrue(f2.getNome() == "Julia");
		assertTrue(f2.getSalario() == 1200);
		assertTrue(f2.getPis() == "12121212125");
		assertTrue(f2.getTel() == "987546123");
		

	}

}
