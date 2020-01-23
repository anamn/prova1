package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.exceptions.CaracteresException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

	private Empresa empresa = new Empresa();

	private Empresa empresa4 = new Empresa();

	private Endereco endereco = new Endereco("Rua alves", "165a", "12345420");

	private Empresa empresa1 = new Empresa("Pedra", "12356272839283", "989754875", "pedra@hotmail.com", 520000, endereco);

	private Empresa empresa2 = new Empresa("Papel", "12356272839283", "989754875", "papel@hotmail.com", 520000, endereco);

	private Empresa empresa3 = new Empresa("Tesoura", "18546284537546", "989757458", "tesoura@hotmail.com", 520000, endereco);

	private Produto produto1 = new Produto("Blusa", 5, "13");

	private Produto produto2 = new Produto("Calça", 10, "15");

	private Funcionario funcionario1 = new Funcionario("Maria", "18171121310", "45695123415", "945421542", 9182, endereco);

	private Funcionario funcionario2 = new Funcionario("Paula", "18171121310", "14563158201", "987541236", 9182, endereco);

	private Funcionario funcionario3 = new Funcionario("Lais", "18171121310", "74951682164", "975421562", 9182, endereco);

	private Funcionario funcionario4 = new Funcionario("Fernada", "18171121301", "12121212121", "945754824", 9182, endereco);

	private Cliente cliente1 = new Cliente("Felipe", "12546975554", "987546211", new Endereco());

	private Cliente cliente2 = new Cliente("Igor", "56487521975", "954261544", new Endereco());

	private Cliente cliente3 = new Cliente("Isadora", "54621345132", "986545251", new Endereco());

	private Cliente cliente4 = new Cliente("Fernanda", "14575623154", "984858755", new Endereco());

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Começando teste");
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println("Teste Finalizado!");
	}

	@Test
	public void deve_verificar_lucro_armazenado() {
		empresa.setLucros(845);
		assertTrue(empresa.getLucros() == 845.0);
	}

	@Test
	public void deve_retornar_o_endereco_passado() {
		empresa.setEndereco(endereco);
		assertThat(empresa.getEndereco(), Matchers.is(endereco));
	}

	@Test
	public void deve_verificar_o_nome_da_empresa() {
		empresa.setNome("Onix");
		assertTrue(empresa.getNome().equals("Onix"));

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_numeros_no_nome() {
		empresa.setNome("1-0empresa");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_letras_no_cnpj() {
		empresa.setCnpj("1234567tt66345");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_menos_de_quartorze_digitos_no_cnpj() {
		empresa.setCnpj("12725783");
		assertTrue(empresa.getCnpj().equals("12725783"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_cnpj_com_mais_de_quartorze_digitos() {
		empresa.setCnpj("283929873972994033");
	}

	@Test
	public void deve_aceitar_cnpj_com_quartorze_digitos_e_numeros() {
		empresa.setCnpj("12982787988786");
		assertTrue(empresa.getCnpj().equals("12982787988786"));
	}

	@Ignore
	@Test
	public void deve_verificar_o_lucro_da_empresa() {
		empresa.setLucros(545.54);
		assertThat(empresa.getLucros(), Matchers.is(545.54));

	}

	@Test
	public void deve_aceitar_telefone_com_nove_digitos() {
		empresa.setTelefone("987542611");
		assertTrue(empresa.getTelefone().equals("987542611"));
	}

	@Test
	public void deve_aceitar_telefone_com_oito_digitos() {
		empresa.setTelefone("98754265");
		assertTrue(empresa.getTelefone().equals("98754265"));
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_mais_de_nove_digitos() {
		empresa.setTelefone("9875421647");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_com_menos_de_oito_digitos() {
		empresa.setTelefone("1928933");

	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_telefone_contendo_letras() {
		empresa.setTelefone("457548als");
	}

	@Test
	public void deve_aceitar_email() {
		empresa.setEmail("empresa@gmail.com");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_email_invalido() {
		empresa.setEmail("aksla");
	}

	@Test(expected = CaracteresException.class)
	public void nao_deve_aceitar_email_com() {
		empresa.setEmail("empresa@gmail");

	}

	@Test(expected = CaracteresException.class)
	public void nao_dev_aceitar_email_sem_o_arroba() {
		empresa.setEmail("empresahotmail.com");

	}

	@Test
	public void deve_adicionar_os_funcionarios_e_verificar_o_armazenamento() {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(funcionario1);
		funcionarios.add(funcionario2);
		funcionarios.add(funcionario3);
		funcionarios.add(funcionario4);
		empresa.setFuncionarios(funcionarios);
		assertTrue(empresa.getFuncionarios() == funcionarios);

	}

	@Before
	public void deve_adicionar_os_clientes_e_verificar_o_armazenamento() {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		empresa.setClientes(clientes);
		assertTrue(empresa.getClientes() == clientes);
	}

	@Before
	public void deve_adicionar_os_produtos_e_verificar_o_armazenamento() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(produto1);
		produtos.add(produto2);
		empresa.setProdutos(produtos);
		assertTrue(empresa.getProdutos() == produtos);

	}

	@After
	public void finaliza_as_listas() {
		empresa.setProdutos(null);
		empresa.setClientes(null);
		empresa.setFuncionarios(null);
	}

	@Test
	public void deve_verificar_o_construtor() {

		assertEquals("Pedra", empresa1.getNome());
		assertEquals("pedra@hotmail.com", empresa1.getEmail());
		assertEquals("12356272839283", empresa1.getCnpj());
		assertEquals("989754875", empresa1.getTelefone());
		assertTrue(empresa1.getLucros() == 520000.0);
		assertEquals(endereco, empresa1.getEndereco());

	}

	@Test
	public void deve_retornar_hashCode_iguais_para_cnpj_iguais() {
		assertTrue(empresa1.hashCode() == empresa2.hashCode());

	}

	@Test
	public void deve_testar_hashcode_para_cnpj_nulo() {
		assertThat(empresa.hashCode(), Matchers.is(30));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_hashCode_diferentes_para_cnpj_diferentes() {
		assertTrue(empresa1.hashCode() == empresa3.hashCode());
	}

	@Test
	public void deve_retornar_true_para_cnpj_iguais() {
		assertTrue(empresa1.equals(empresa2));
	}

	@Test
	public void deve_retornar_true_para_a_mesma_empresa() {
		assertTrue(empresa1.equals(empresa1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_empresa_nula() {
		assertTrue(empresa.equals(empresa1));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_objetos_de_classes_diferentes() {
		assertTrue(empresa1.equals(new Object()));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cnpj_nulo() {
		assertTrue(empresa.equals(empresa1));

	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cpf_comparado_nulo() {
		assertTrue(empresa1.equals(null));
	}

	@Test
	public void deve_retornar_true_para_cpf_nulos() {
		assertTrue(empresa.equals(empresa4));
	}

	@Test(expected = AssertionError.class)
	public void deve_retornar_false_para_cnpj_diferentes() {
		assertTrue(empresa1.equals(empresa3));
	}

	@Test
	public void deve_retornar_a_toString_do_objeto() {
		assertThat(empresa1, Matchers.is(empresa1));
	}

}
