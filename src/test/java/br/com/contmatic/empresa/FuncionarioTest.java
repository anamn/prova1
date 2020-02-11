package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contimatic.fixture.ValidadorFuncionario;
import br.com.contmatic.enums.EnderecoType;
import br.com.contmatic.enums.TelefoneType;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    Funcionario funcionario1 = null;

    Funcionario funcionario2 = null;

    Funcionario funcionario3 = null;

    Funcionario funcionario4 = null;

    Funcionario funcionario = null;

    Endereco endereco = null;

    ValidadorFuncionario validador = null;

    Set<String> teste = null;

    Set<Telefone> telefones = null;

    Telefone telefone = null;
    Telefone telefone1 = null;
    Telefone telefone2 = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contimatic.fixture");
    }

    @Before
    public void inicializacao() {
        this.endereco = new Endereco("rua tijuco", "56", "12323450", EnderecoType.CASA);
        this.telefones = new HashSet<Telefone>();
        
        this.funcionario = new Funcionario();
        this.funcionario1 = new Funcionario();
        this.funcionario2 = new Funcionario("Julia", "12131213131", "14587526645", telefones, new BigDecimal("5213"), endereco);
        this.funcionario3 = new Funcionario("Julia", "12131213131", "14587526645", telefones, new BigDecimal("5213"), endereco);
        this.funcionario4 = new Funcionario("Juliana", "98675413154", "12121212125", telefones, new BigDecimal("5213"), endereco);
        
        this.validador = new ValidadorFuncionario();
        this.teste = new TreeSet<>();

    }

    @After
    public void finalizacao() {
        this.endereco = null;
        
        this.funcionario = null;
        this.funcionario1 = null;
        this.funcionario2 = null;
        this.funcionario3 = null;
        this.funcionario4 = null;
        
        this.telefones = null;
        this.validador = null;
        this.teste = null;

    }

    @Test
    public void nao_deve_retornar_mensagem_de_erro() {
        assertThat(teste, Matchers.is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_todos_os_erros() {
        teste.add("CPF invalido");
        teste.add("Nome invalido");
        teste.add("Pis invalido");
        teste.add("Salario invalido");
        assertThat(teste, Matchers.is(validador.validador("invalidos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_cpf() {
        teste.add("CPF invalido");
        assertThat(teste, Matchers.is(validador.validador("cpfInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome() {
        teste.add("Nome invalido");
        assertThat(teste, Matchers.is(validador.validador("nomeInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_pis() {
        teste.add("Pis invalido");
        assertThat(teste, Matchers.is(validador.validador("pisInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_salario() {
        teste.add("Salario invalido");
        assertThat(teste, Matchers.is(validador.validador("salarioInvalido")));
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefone = new Telefone("15986564582", TelefoneType.CELULAR);
        this.telefone1 = new Telefone("2186564582", TelefoneType.FIXO);
        this.telefone2 = new Telefone("2186564582", TelefoneType.FIXO);
        telefones.add(telefone);
        telefones.add(telefone1);
        telefones.add(telefone2);
        assertTrue(telefones.size() == 2);
    }

    @Test
    public void deve_retornar_os_valores() {
        funcionario.setEndereco(endereco);
        assertTrue(funcionario.getEndereco().equals(endereco));
        funcionario.setCpf("12131213131");
        assertTrue(funcionario.getCpf().equals("12131213131"));
        funcionario.setNome("Julia");
        assertTrue(funcionario.getNome().equals("Julia"));
        funcionario.setSalario(new BigDecimal("5213"));
        assertTrue(funcionario.getSalario().equals(new BigDecimal("5213")));
        funcionario.setPis("14587526645");
        assertTrue(funcionario.getPis().equals("14587526645"));
        funcionario.setTelefones(telefones);
        assertTrue(funcionario.getTelefones().equals(telefones));

    }

    @Test
    public void deve_retornar_hashCode_iguais_para_pis_iguais() {
        assertTrue(funcionario2.hashCode() == funcionario3.hashCode());

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_pis_diferentes() {
        assertTrue(funcionario2.hashCode() == funcionario4.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_pis_nulo() {
        assertThat(funcionario1.hashCode(), Matchers.is(629));
    }

    @Test
    public void deve_retornar_true_para_pis_iguais() {
        assertTrue(funcionario2.equals(funcionario3));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_funcionario() {
        assertTrue(funcionario2.equals(funcionario2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_funcionario_nulo() {
        assertTrue(funcionario1.equals(funcionario2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(funcionario2.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_pis_nulo() {
        assertTrue(funcionario1.equals(funcionario2));

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_pis_comparado_nulo() {
        assertTrue(funcionario2.equals(null));
    }

    @Test
    public void deve_retornar_true_para_pis_nulos() {
        assertTrue(funcionario1.equals(funcionario));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_pis_diferentes() {
        assertTrue(funcionario2.equals(funcionario4));
    }

    @Test
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(funcionario2);
        assertThat(funcionario2, Matchers.is(funcionario2));
    }

}
