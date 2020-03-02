package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationEndereco.validador;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.endereco.EnderecoType.APARTAMENTO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;

@FixMethodOrder(NAME_ASCENDING)
public class EnderecoTest {

    private Endereco endereco;
    private Endereco endereco1;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        this.endereco1 = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
    }

    @After
    public void setDownAfter() {
        this.endereco = null;
        this.endereco1 = null;
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao() {
        System.out.println(endereco);
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(endereco)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_null() {
        endereco.setLogradouro(null);
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_com_numero() {
        endereco.setLogradouro("Rua 8789");
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_com_caracter_especial() {
        endereco.setLogradouro("R-a j!!");
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_pelo_tamanho_maior() {
        endereco.setLogradouro(randomAlphabetic(52));
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_pelo_tamanho_menor() {
        endereco.setLogradouro(randomAlphabetic(4));
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_null() {
        endereco.setBairro(null);
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_com_numero() {
        endereco.setBairro("Bairro 8789");
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_com_caracter_especial() {
        endereco.setBairro("V!l@ j!!");
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_pelo_tamanho_maior() {
        endereco.setBairro(randomAlphabetic(41));
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_pelo_tamanho_menor() {
        endereco.setBairro(randomAlphabetic(2));
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_pelo_tamanho_maior() {
        endereco.setNumero(randomNumeric(9, 20));
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_igual_a_zero() {
        endereco.setNumero("0");
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_null() {
        endereco.setNumero(null);
        validador(endereco);
    }

    @Test
    public void deve_aceitar_cep_com_traco() {
        Set<String> teste = new TreeSet<String>();
        endereco.setCep("03251-070");
        assertThat(teste, is(validador(endereco)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cep_pelo_tamanho_maior() {
        endereco.setCep(randomNumeric(9, 15));
        validador(endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cep_pelo_tamanho_menor() {
        endereco.setCep(randomNumeric(2, 7));
        validador(endereco);
    }

    // Testa Construtor
    @Test
    public void deve_retornar_os_valores() {
        endereco.setLogradouro("Av. ac");
        assertThat(endereco.getLogradouro(), is("Av. ac"));
        endereco.setBairro("Vila diva");
        assertThat(endereco.getBairro(), is("Vila diva"));
        endereco.setCep("19263720");
        assertThat(endereco.getCep(), is("19263720"));
        endereco.setNumero("187");
        assertThat(endereco.getNumero(), is("187"));
        endereco.setEnderecoType(APARTAMENTO);
        assertThat(endereco.getEnderecoType(), is(APARTAMENTO));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_retornar_hashCode_iguais_para_ceps_iguais() {
        endereco.setCep("06542-828");
        endereco1.setCep("06542-828");
        assertTrue(endereco.hashCode() == endereco1.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_ceps_diferentes() {
        endereco.setCep("03298-865");
        endereco1.setCep("06542-828");
        assertTrue(endereco1.hashCode() == endereco.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_cep_nulo() {
        endereco.setCep(null);
        assertThat(endereco.hashCode(), is(629));
    }

    @Test
    public void deve_retornar_true_para_ceps_iguais() {
        endereco.setCep("06542-828");
        endereco1.setCep("06542-828");
        assertTrue(endereco1.equals(endereco));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_cep() {
        assertTrue(endereco.equals(endereco));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_endereco_nulo() {
        endereco.setCep("06542-828");
        this.endereco1 = null;
        assertTrue(endereco.equals(endereco1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(endereco.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cep_nulo() {
        endereco.setCep(null);
        endereco1.setCep("06542-828");
        assertTrue(endereco.equals(endereco1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cep_comparado_nulo() {
        assertTrue(endereco1.equals(null));
    }

    @Test
    public void deve_retornar_true_para_ceps_nulos() {
        endereco.setCep(null);
        endereco1.setCep(null);
        assertTrue(endereco.equals(endereco1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_ceps_diferentes() {
        endereco.setCep("03298-865");
        endereco1.setCep("06542-828");
        assertTrue(endereco.equals(endereco1));
    }

    @Test
    public void deve_verificar_se_toString_contem_logradouro() {
        assertTrue(endereco.toString().contains("logradouro"));
    }

    @Test
    public void deve_verificar_se_toString_contem_bairro() {
        assertTrue(endereco.toString().contains("bairro"));
    }

    @Test
    public void deve_verificar_se_toString_contem_numero() {
        assertTrue(endereco.toString().contains("numero"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cep() {
        assertTrue(endereco.toString().contains("cep"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecoType() {
        assertTrue(endereco.toString().contains("enderecoType"));
    }
}
