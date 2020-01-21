package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionarios;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.erros.CaracteresError;
import br.com.contmatic.erros.Inexistente;
import br.com.contmatic.erros.VendaIndisponivel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {
	
	Empresa empresa = new Empresa();
	Endereco n = new Endereco("Rua alves", "165a", "1234542");
	Produto p = new Produto("Blusa", 5, "13");
	Produto p2 = new Produto("Calça", 10, "15");
	Funcionarios fun0 = new Funcionarios("Luiz", "18171121310","987545213", n, 9182, "12154748447");
	Funcionarios fun1 = new Funcionarios("Maria", "18171121310","945421542", n, 9182, "45695123415");
	Funcionarios fun2 = new Funcionarios("Paula", "18171121310","987541236", n, 9182, "14563158201");
	Funcionarios fun3 = new Funcionarios("Lais", "18171121310","975421562", n, 9182, "74951682164");
	Funcionarios fun4 = new Funcionarios("Fernada", "18171121301","945754824", n, 9182, "12121212121");
	Cliente cli1=new Cliente("Felipe", "12546975554", "987546211", new Endereco());
	Cliente cli2=new Cliente("Igor", "56487521975", "954261544", new Endereco());
	Cliente cli3=new Cliente("Isadora", "54621345132", "986545251", new Endereco());
	Cliente cli4=new Cliente("Fernanda", "14575623154", "984858755", new Endereco());
	
	@BeforeClass
	public static void indica_comeco_do_teste() {
		System.out.println("Começando teste");
	}
	
	@AfterClass
	public static void deve_printar_o_fim_do_teste() {
		System.out.println("Teste Finalizado!");
	}
	
	@Test
	public void deve_verificar_o_nome_da_empresa() {
		empresa.setNome("Onix");
		assertTrue(empresa.getNome().matches("Onix"));

	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_letras_no_cnpj() {
		empresa.setCnpj("1234567tt66345");
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_menos_de_quartorze_digitos() {
		empresa.setCnpj("12725783");
		assertThat(empresa.getCnpj(), Matchers.is("12725783"));
	}

	@Test(expected = CaracteresError.class)
	public void nao_deve_aceitar_cnpj_com_mais_de_quartorze_digitos() {
		empresa.setCnpj("283929873972994033");
		assertTrue(empresa.getCnpj() == "283929873972994033");
	}

	@Test
	public void deve_aceitar_cnpj_com_quartorze_digitos_e_numeros() {
		empresa.setCnpj("12982787988786");
		assertTrue(empresa.getCnpj() == "12982787988786");
	}

	@Ignore
	@Test
	public void deve_verificar_o_lucro_da_empresa() {
		empresa.setLucros(545.54);
		assertTrue(empresa.getLucros() == 545.54);

	}

	@Test
	public void deve_verificar_o_construtor() {
		Empresa em = new Empresa("Tele", "12356272839283","989754875", 520000, n);
		assertEquals("Tele", em.getNome());
		assertEquals("12356272839283", em.getCnpj());
		assertEquals("989754875", em.getTel());
		assertTrue(em.getLucros() == 520000.0);
		assertEquals(n, em.getEndereco());

	}

	@Before
	public void inicializa() {
		empresa.addFuncionario(fun0);
		empresa.addFuncionario(fun1);
		empresa.addFuncionario(fun2);
		empresa.addFuncionario(fun3);
		empresa.addFuncionario(fun4);
	}
	
	@After
	public void finaliza() {
		empresa.removeFuncionario(fun0);
		empresa.removeFuncionario(fun1);
		empresa.removeFuncionario(fun2);
		empresa.removeFuncionario(fun3);
		empresa.removeFuncionario(fun4);
	}
	
	@Before
	public void deve_adicionar_cliente_a_lista() {
		empresa.addCliente(cli1);
		empresa.addCliente(cli2);
		empresa.addCliente(cli3);
		empresa.addCliente(cli4);
	}
	
	@Test
	public void deve_validar_cliente() {
		empresa.validaCliente("12546975554");
		
	}
	
	@Test
	public void nao_deve_encontrar_cliente() {
		empresa.validaCliente("10192827262");
	}

	@Test(timeout = 10)
	public void deve_validar_funcionario() {
		empresa.validaFuncionario("12121212121");
		
	}
	
	@Test
	public void nao_deve_validar_funcionario() {
		empresa.validaFuncionario("17282727283");
	}

	
	@Before
	public void deve_adicionar_produto() {
		empresa.addProduto(p);
		empresa.addProduto(p2);
	}
	
	@Test(timeout = 10)
	public void deve_validar_produto() {
		empresa.validaProduto("15");
	}
	
	@Test(expected = Inexistente.class)
	public void nao_deve_validar_produto() {
		empresa.validaProduto("14");
	}
	
	@Test(expected = AssertionError.class)
	public void deve_validar_venda() {
		empresa.validaVenda("12154748447", "56487521975", "13", p);
		assertTrue(empresa.getListaCodigoProduto().contains("13"));
		assertTrue(empresa.getListaProdutos().contains(p));
	}
	
	@Test(expected = Inexistente.class)
	public void nao_deve_validar_venda_pelo_funcionario() {
		empresa.validaVenda("13241514267", "56487521975", "13", p);
	}
	
	@Test(expected = Inexistente.class)
	public void nao_deve_validar_venda_pelo_cliente() {
		empresa.validaVenda("12154748447", "45754654854", "13", p);
	}
	
	@Test(expected = Inexistente.class)
	public void nao_deve_validar_venda_pelo_produto() {
		empresa.validaVenda("12154748447", "56487521975", "14", p);
	}
	
	@Test(expected = VendaIndisponivel.class)
	public void nao_deve_validar_produto_nao_corresponde_ao_codigo() {
		empresa.validaVenda("12154748447", "56487521975", "15", p);
			
	}
	
}
