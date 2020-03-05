package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationFuncionario.validador;
import static br.com.contmatic.easy.random.classes.FuncionarioRandomico.funcionarioValido;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ValidationException;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Funcionario;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    private Funcionario funcionario;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.funcionario = funcionarioValido();

    }

    @After
    public void setDownAfter() {
        this.funcionario = null;
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
        funcionario.setCpf("12131213131");
        assertTrue(funcionario.getCpf().equals("12131213131"));
        funcionario.setNome("Julia");
        assertTrue(funcionario.getNome().equals("Julia"));
        funcionario.setSalario(new BigDecimal("5213"));
        assertTrue(funcionario.getSalario().equals(new BigDecimal("5213")));
        funcionario.setPis("14587526645");
        assertTrue(funcionario.getPis().equals("14587526645"));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
        EqualsVerifier.forClass(Funcionario.class).suppress(Warning.NONFINAL_FIELDS).withIgnoredFields("nome").withIgnoredFields("cpf").withIgnoredFields("salario").withIgnoredFields("endereco")
                .withIgnoredFields("telefones").verify();
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
