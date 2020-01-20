package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionarios;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.erros.CaracteresError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

	Empresa a = new Empresa();
	Endereco n = new Endereco("Rua alves", "165a", "1234542");
	Produto p = new Produto("Blusa", 5, "13");
	Funcionarios fun0 = new Funcionarios("Luiz", "18171121310","987545213", n, 9182, "12154748447");
	Funcionarios fun1 = new Funcionarios("Maria", "18171121310","945421542", n, 9182, "45695123415");
	Funcionarios fun2 = new Funcionarios("Paula", "18171121310","987541236", n, 9182, "14563158201");
	Funcionarios fun3 = new Funcionarios("Lais", "18171121310","975421562", n, 9182, "74951682164");
	Funcionarios fun4 = new Funcionarios("Fernada", "18171121301","945754824", n, 9182, "12121212121");

	@Test
	public void deve_verificar_o_nome_da_empresa() {
		a.setNome("Onix");
		assertTrue(a.getNome().matches("Onix"));

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras_no_cnpj() {
		a.setCnpj("123456789aa345");
		assertThat(a.getCnpj(), Matchers.is("12345678912345"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_menos_de_quartorze_digitos() {
		a.setCnpj("12725783");
		assertThat(a.getCnpj(), Matchers.is("12725783"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cnpj_com_mais_de_quartorze_digitos() {
		a.setCnpj("283929873972994033");
		assertTrue(a.getCnpj() == "283929873972994033");
	}

	@Test
	public void deve_aceitar_cnpj_com_quartorze_digitos_e_numeros() {
		a.setCnpj("12982787988786");
		assertTrue(a.getCnpj() == "12982787988786");
	}

	@Test
	public void deve_verificar_o_lucro_da_empresa() {
		a.setLucros(545.54);
		assertTrue(a.getLucros() == 545.54);

	}

	@Test
	public void deve_verificar_o_construtor() {
		Empresa em = new Empresa("Tele", "12356272839283","989754875", 520000, n, p);
		assertEquals("Tele", em.getNome());
		assertEquals("12356272839283", em.getCnpj());
		assertThat(em.getLucros(), Matchers.is(520000.0));
		assertEquals(n, em.getEndereco());
		assertEquals(p, em.getProduto());

	}

	@Before
	public void inicializa() {
		a.addFuncionario(fun0);
		a.addFuncionario(fun1);
		a.addFuncionario(fun2);
		a.addFuncionario(fun3);
		a.addFuncionario(fun4);
	}
	
	@After
	public void finaliza() {
		
	}

	@Test(timeout = 10)
	public void deve_validar_funcionario() {
		a.validaFuncionario("12121212121");
		
	}
	
	@Test
	public void deve_remover_funcionario() {
		a.removeFuncionario(fun4);
	}
}
