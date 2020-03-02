package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationFuncionario.validador;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.easy.random.classes.FuncionarioEasyRandomParametros.parametrosFuncionario;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandomParametros.parametrosTelefone;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ValidationException;

import org.hamcrest.Matchers;
import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    private Funcionario funcionario;

    private Funcionario funcionario1;

    private Endereco endereco;

    private Set<Telefone> telefones;

    private Telefone telefone;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        this.telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
        this.telefones = new HashSet<Telefone>();
        telefones.add(telefone);
        this.funcionario = new EasyRandom(parametrosFuncionario()).nextObject(Funcionario.class);
        this.funcionario.setEndereco(endereco);
        this.funcionario.setTelefones(telefones);
        this.funcionario1 = new EasyRandom(parametrosFuncionario()).nextObject(Funcionario.class);
        this.funcionario1.setEndereco(endereco);
        this.funcionario1.setTelefones(telefones);

    }

    @After
    public void setDownAfter() {
        this.endereco = null;
        this.funcionario = null;
        this.funcionario1 = null;
        this.telefones = null;
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_mensagem_de_erro_na_validacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, Matchers.is(validador(funcionario)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_maior() {
        funcionario.setNome(randomAlphabetic(52));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_menor() {
        funcionario.setNome(randomAlphabetic(1));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_nulo() {
        funcionario.setNome(null);
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_numero() {
        funcionario.setNome("Ana98");
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_caracteres_especiais() {
        funcionario.setNome("Ana(*_");
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf() {
        funcionario.setCpf(randomNumeric(11));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_caracter_especial() {
        funcionario.setCpf("112542654_*");
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_letra() {
        funcionario.setCpf("112542654aa");
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho_maior() {
        funcionario.setCpf(randomNumeric(12));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho_menor() {
        funcionario.setCpf(randomNumeric(10));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_nulo() {
        funcionario.setCpf(null);
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_pis_invalido() {
        funcionario.setPis("12548765946");
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_pis_nulo() {
        funcionario.setPis(null);
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_pis_pelo_tamanho_maior() {
        funcionario.setPis(randomNumeric(12));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_pis_pelo_tamanho_menor() {
        funcionario.setPis(randomNumeric(10));
        validador(funcionario);
    }

    @Test(expected = ValidationException.class)
    public void deve_retonar_mensagem_de_erro_no_pis_caracter_especial() {
        funcionario.setPis("13548726-*_");
        validador(funcionario);
    }

    @Test(expected = ValidationException.class)
    public void deve_retonar_mensagem_de_erro_no_pis_letra() {
        funcionario.setPis("34587625ajs");
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_salario_igual_a_zero() {
        funcionario.setSalario(new BigDecimal("0"));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_salario_menor_que_zero() {
        funcionario.setSalario(new BigDecimal("-450"));
        validador(funcionario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_salario_nulo() {
        funcionario.setSalario(null);
        validador(funcionario);
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
        funcionario.setPis("83007695449");
        funcionario1.setPis("83007695449");
        assertTrue(funcionario.hashCode() == funcionario1.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_pis_diferentes() {
        funcionario.setPis("83007695449");
        funcionario1.setPis("48500625578");
        assertTrue(funcionario.hashCode() == funcionario1.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_pis_nulo() {
        funcionario.setPis(null);
        assertThat(funcionario.hashCode(), is(629));
    }

    @Test
    public void deve_retornar_true_para_pis_iguais() {
        funcionario.setPis("83007695449");
        funcionario1.setPis("83007695449");
        assertTrue(funcionario.equals(funcionario1));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_funcionario() {
        assertTrue(funcionario.equals(funcionario));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(funcionario.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_pis_nulo() {
        funcionario1.setPis(null);
        assertTrue(funcionario1.equals(funcionario));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_pis_comparado_nulo() {
        assertTrue(funcionario.equals(null));
    }

    @Test
    public void deve_retornar_true_para_pis_nulos() {
        funcionario.setPis(null);
        funcionario1.setPis(null);
        assertTrue(funcionario1.equals(funcionario));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_pis_diferentes() {
        funcionario.setPis("83007695449");
        funcionario1.setPis("48500625578");
        assertTrue(funcionario.equals(funcionario1));
    }

    @Test
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(funcionario.toString().contains("nome"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cpf() {
        assertTrue(funcionario.toString().contains("cpf"));
    }

    @Test
    public void deve_verificar_se_toString_contem_pis() {
        assertTrue(funcionario.toString().contains("pis"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(funcionario.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_salario() {
        assertTrue(funcionario.toString().contains("salario"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecoType() {
        assertTrue(funcionario.toString().contains("enderecoType"));
    }
}
