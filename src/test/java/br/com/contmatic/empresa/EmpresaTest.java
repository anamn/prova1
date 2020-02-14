package br.com.contmatic.empresa;

import static br.com.contmatic.enums.Ddd.AM92;
import static br.com.contmatic.enums.Ddd.SP11;
import static br.com.contmatic.enums.EnderecoType.COMENCIAL;
import static br.com.contmatic.enums.Moeda.DOLLAR;
import static br.com.contmatic.enums.TelefoneType.CELULAR;
import static br.com.contmatic.enums.TelefoneType.FIXO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static java.time.Month.JANUARY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.YearMonth;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.fixture.ValidadorEmpresa;

@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

    Empresa empresa = null;

    Empresa empresa2 = null;

    Empresa empresa3 = null;

    Empresa empresa4 = null;

    Empresa empresa1 = null;

    Endereco endereco = null;

    Set<Endereco> enderecos = null;

    Produto produto1 = null;

    Produto produto2 = null;

    Set<Funcionario> funcionarios = null;

    Funcionario funcionario1 = null;

    Funcionario funcionario2 = null;

    Set<Cliente> clientes = null;

    Cliente cliente = null;

    Cliente cliente1 = null;

    Cliente cliente2 = null;

    Lucro lucro = null;

    ValidadorEmpresa validador = null;

    Set<String> teste = null;

    Telefone telefone = null;
    Telefone telefone1 = null;
    Telefone telefone2 = null;

    Set<Telefone> telefones = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @AfterClass
    public static void setDownAfterClass() {
        System.out.println("Teste Finalizado!");
    }

    @Before
    public void incializacao() {
        this.telefones = new HashSet<Telefone>();
        this.enderecos = new HashSet<Endereco>();
        this.endereco = new Endereco("Rua alves", "165a", "12345420", COMENCIAL);

        this.empresa = new Empresa();
        this.empresa1 = new Empresa();
        this.empresa2 = new Empresa("Pedra", "12356272839283", telefones, "pedra@hotmail.com", enderecos);
        this.empresa3 = new Empresa("Papel", "12356272839283", telefones, "papel@hotmail.com", enderecos);
        this.empresa4 = new Empresa("Tesoura", "18546284537546", telefones, "tesoura@hotmail.com", enderecos);

        this.produto1 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("15"), "1545483", 2);
        this.produto2 = new Produto("Calça", "Calça preta com botoes", new BigDecimal("50"), "1845755", 5);

        this.funcionarios = new HashSet<Funcionario>();
        this.funcionario1 = new Funcionario("Maria", "18171121310", "45695123415", telefones, new BigDecimal("1542"), endereco);
        this.funcionario2 = new Funcionario("Paula", "18171121310", "14563158201", telefones, new BigDecimal("1542"), endereco);

        this.clientes = new HashSet<Cliente>();
        this.cliente = new Cliente();
        this.cliente1 = new Cliente("Felipe", "12546975554", telefones, "felipe@hig.com", new Endereco());
        this.cliente2 = new Cliente("Maria", "56487521975", telefones, "maria@hotmail.com", new Endereco());

        this.validador = new ValidadorEmpresa();
        this.teste = new TreeSet<String>();

        this.lucro = new Lucro(new BigDecimal("30000"), new BigDecimal("33000"), DOLLAR, new YearMonth(2020, JANUARY.getValue()));
    }

    @After
    public void finalizacao() {
        this.telefones = null;
        this.enderecos = null;
        this.endereco = null;

        this.empresa = null;
        this.empresa1 = null;
        this.empresa2 = null;
        this.empresa3 = null;
        this.empresa4 = null;

        this.produto1 = null;
        this.produto2 = null;

        this.funcionario1 = null;
        this.funcionario2 = null;

        this.cliente1 = null;
        this.cliente2 = null;

        this.lucro = null;

        this.validador = null;
        this.teste = null;

    }

    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefone = new Telefone(SP11,"986564582", CELULAR);
        this.telefone1 = new Telefone(AM92,"8656-4582", FIXO);
        this.telefone2 = new Telefone(AM92,"8656-4582", FIXO);
        telefones.add(telefone);
        telefones.add(telefone1);
        telefones.add(telefone2);
        empresa.setTelefones(telefones);
        assertTrue(empresa.getTelefones().size() == 2);
    }

    @Test
    public void deve_validar_cliente_e_nao_retornar_nada() {
        clientes.add(cliente1);
        clientes.add(cliente2);
        empresa.setClientes(clientes);
        assertTrue(empresa.validaCliente(cliente1));
        assertTrue(empresa.getClientes().size() == 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_validar_cliente_e_retornar_mensagem_de_erro() {
        clientes.add(cliente);
        clientes.add(cliente2);
        empresa.setClientes(clientes);
        assertTrue(empresa.validaCliente(cliente1));
    }

    @Test
    public void deve_adicionar_os_produtos_e_verificar_o_armazenamento() {
        Set<Produto> produtos = new HashSet<Produto>();
        produtos.add(produto1);
        produtos.add(produto2);
        empresa.setProdutos(produtos);
        assertTrue(empresa.getProdutos() == produtos);

    }

    @Test
    public void deve_validar_funcionario_e_nao_retornar_nada() {
        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);
        empresa.setFuncionarios(funcionarios);
        assertTrue(empresa.validaFuncionario(funcionario1));
        assertTrue(empresa.getFuncionarios().size() == 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_validar_funcionario_e_retornar_mensagem_de_erro() {
        funcionarios.add(funcionario1);
        empresa.setFuncionarios(funcionarios);
        assertTrue(empresa.validaFuncionario(funcionario2));
    }

    @After
    public void finaliza_as_listas() {
        empresa.setProdutos(null);
        empresa.setClientes(null);
        empresa.setFuncionarios(null);
    }

    @Test
    public void nao_deve_retornar_erros() {
        assertThat(teste, is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("CNPJ invalido");
        teste.add("Email invalido");
        teste.add("Nome invalido");
        teste.add("Site invalido");
        assertThat(teste, is(validador.validador("invalidos")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cnpj() {
        teste.add("CNPJ invalido");
        assertThat(teste, is(validador.validador("cnpjInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_site() {
        teste.add("Site invalido");
        assertThat(teste, is(validador.validador("siteInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email() {
        teste.add("Email invalido");
        assertThat(teste, is(validador.validador("emailInvalido")));

    }

    @Test
    public void deve_retornar_o_lucro() {
        empresa.setLucro(lucro);
        assertThat(new BigDecimal(10), is(empresa.getLucro().calculaLucro()));
    }

    @Test
    public void deve_retornar_o_endereco_passado() {
        empresa.setEnderecos(enderecos);
        assertThat(empresa.getEnderecos(), is(enderecos));
    }

    @Test
    public void deve_validaar_os_valores_passados() {
        empresa.setNome("Lg");
        assertEquals("Lg", empresa.getNome());
        empresa.setEmail("lg@gmail.com");
        assertEquals("lg@gmail.com", empresa.getEmail());
        empresa.setCnpj("02828446000134");
        assertEquals("02828446000134", empresa.getCnpj());
        empresa.setTelefones(telefones);
        assertEquals(telefones, empresa.getTelefones());
        empresa.setEnderecos(enderecos);
        assertEquals(enderecos, empresa.getEnderecos());
        empresa.setSite("www.lg.com.br");
        assertEquals("www.lg.com.br", empresa.getSite());

    }

    @Test
    public void deve_retornar_hashCode_iguais_para_cnpj_iguais() {
        assertTrue(empresa2.hashCode() == empresa3.hashCode());

    }

    @Test
    public void deve_testar_hashcode_para_cnpj_nulo() {
        assertThat(empresa.hashCode(), is(629));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_cnpj_diferentes() {
        assertTrue(empresa2.hashCode() == empresa4.hashCode());
    }

    @Test
    public void deve_retornar_true_para_cnpj_iguais() {
        assertTrue(empresa2.equals(empresa3));
    }

    @Test
    public void deve_retornar_true_para_a_mesma_empresa() {
        assertTrue(empresa2.equals(empresa2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_empresa_nula() {
        assertTrue(empresa.equals(empresa2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(empresa2.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cnpj_nulo() {
        assertTrue(empresa.equals(empresa2));

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_comparado_nulo() {
        assertTrue(empresa2.equals(null));
    }

    @Test
    public void deve_retornar_true_para_cpf_nulos() {
        assertTrue(empresa.equals(empresa1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cnpj_diferentes() {
        assertTrue(empresa2.equals(empresa4));
    }

    @Test
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(empresa2);
        assertThat(empresa2, is(empresa2));
    }

}
