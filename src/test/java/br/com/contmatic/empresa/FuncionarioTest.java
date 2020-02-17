package br.com.contmatic.empresa;

import static br.com.contmatic.telefone.Ddd.AC68;
import static br.com.contmatic.telefone.Ddd.AM97;
import static br.com.contmatic.telefone.Ddd.BA73;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EnderecoType;
import br.com.contmatic.fixture.ValidadorFuncionario;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    private Funcionario funcionario1 = null;

    private Funcionario funcionario2 = null;

    private Funcionario funcionario3 = null;

    private Funcionario funcionario4 = null;

    private Funcionario funcionario = null;

    private Endereco endereco = null;

    private ValidadorFuncionario validador = null;

    private Set<String> teste = null;

    private Set<Telefone> telefones = null;

    private Telefone telefone = null;

    private Telefone telefone1 = null;

    private Telefone telefone2 = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.endereco = new Endereco("rua tijuco", "56", "12323450", EnderecoType.CASA);
        this.telefones = new HashSet<Telefone>();
        this.funcionario = new Funcionario();
        this.funcionario1 = new Funcionario();
        this.funcionario2 = new Funcionario("Julia", "12131213131", "14587526645", telefones, new BigDecimal("5213"), endereco);
        this.funcionario3 = new Funcionario("Julia", "12131213131", "14587526645", telefones, new BigDecimal("5213"), endereco);
        this.funcionario4 = new Funcionario("Juliana", "98675413154", "12121212125", telefones, new BigDecimal("5213"), endereco);
        this.validador = new ValidadorFuncionario();
        this.teste = new TreeSet<String>();

    }

    @After
    public void setDownAfter() {
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

    // Testa atributos
    @Test
    public void nao_deve_retornar_mensagem_de_erro_na_validacao() {
        assertThat(teste, Matchers.is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_todos_os_erros() {
        teste.add("CPF invalido");
        teste.add("Nome invalido");
        teste.add("Pis invalido");
        teste.add("Salario invalido");
        assertThat(teste, is(validador.validador("invalidos")));
    }

    @Test
    public void deve_aceitar_nome_com_espaco() {
        assertThat(teste, is(validador.validador("nomeValidoComEspaço")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_invalido() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_nulo() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_numero() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalidoComNumero")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_caracteres_especiais() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalidoComEspeciais")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("cpfInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("cpfTamanhoInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_nulo() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("cpfNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_pis() {
        teste.add("Pis invalido");
        assertThat(teste, is(validador.validador("pisInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_pis_nulo() {
        teste.add("Pis invalido");
        assertThat(teste, is(validador.validador("pisNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_pis_pelo_tamanho() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("pisTamanhoInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_salario() {
        teste.add("Salario invalido");
        assertThat(teste, is(validador.validador("salarioInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_salario_nulo() {
        teste.add("Salario invalido");
        assertThat(teste, is(validador.validador("salarioNull")));
    }

    // Testa lista de telefone
    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefone = new Telefone(AC68, "986564582", CELULAR);
        this.telefone1 = new Telefone(BA73, "86564582", FIXO);
        this.telefone2 = new Telefone(AM97, "86564582", FIXO);
        telefones.add(telefone);
        telefones.add(telefone1);
        telefones.add(telefone2);
        assertTrue(telefones.size() == 2);
    }

    // Testa Construtor
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

    // Teste hashCode, Equals e toString
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
        assertThat(funcionario1.hashCode(), is(629));
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
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(funcionario2.toString().contains("Julia"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cpf() {
        assertTrue(funcionario2.toString().contains("12131213131"));
    }

    @Test
    public void deve_verificar_se_toString_contem_pis() {
        assertTrue(funcionario2.toString().contains("14587526645"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(funcionario2.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_salario() {
        assertTrue(funcionario2.toString().contains("salario"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecoType() {
        assertTrue(funcionario2.toString().contains("casa"));
    }
}
