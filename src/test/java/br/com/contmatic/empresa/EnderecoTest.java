package br.com.contmatic.empresa;

import static br.com.contmatic.endereco.EnderecoType.APARTAMENTO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EnderecoType;
import br.com.contmatic.fixture.EnderecoMetodosParaTest;

@FixMethodOrder(NAME_ASCENDING)
public class EnderecoTest {

    private Endereco endereco = null;

    private Endereco endereco1 = null;

    private Endereco endereco2 = null;

    private Endereco endereco3 = null;

    private Endereco endereco4 = null;

    private EnderecoMetodosParaTest validador = null;

    private Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.endereco = new Endereco();
        this.endereco1 = new Endereco("Rua tijuco", "452", "03564870", EnderecoType.APARTAMENTO);
        this.endereco2 = new Endereco("Rua tijuco", "452", "03564870", EnderecoType.CASA);
        this.endereco3 = new Endereco("Rua tijuco", "452", "84574512", EnderecoType.COMERCIAL);
        this.endereco4 = new Endereco();

        this.validador = new EnderecoMetodosParaTest();
        this.teste = new TreeSet<String>();

    }

    @After
    public void setDownAfter() {
        this.endereco = null;
        this.endereco1 = null;
        this.endereco2 = null;
        this.endereco3 = null;
        this.endereco4 = null;
        this.validador = null;
        this.teste = null;
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao() {
        assertThat(teste, is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("CEP invalido!");
        teste.add("Endereço invalido!");
        teste.add("Numero invalido!");
        assertThat(teste, is(validador.validador("invalidos")));
    }

    @Test
    public void deve_aceitar_no_endereco_espaco() {
        assertThat(teste, is(validador.validador("enderecoValidoComEspaco")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_endereco_null() {
        teste.add("Endereço invalido!");
        assertThat(teste, is(validador.validador("enderecoNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_endereco_com_numero() {
        teste.add("Endereço invalido!");
        assertThat(teste, is(validador.validador("enderecoInvalidoNumero")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_endereco_com_caracter_especial() {
        teste.add("Endereço invalido!");
        assertThat(teste, is(validador.validador("enderecoInvalidoComEspeciais")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_endereco_pelo_tamanho() {
        teste.add("Endereço invalido!");
        assertThat(teste, is(validador.validador("enderecoInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_aceitar_letra_no_numero() {
        assertThat(teste, is(validador.validador("numeroValidoComLetra")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_pelo_tamanho() {
        teste.add("Numero invalido!");
        assertThat(teste, is(validador.validador("numeroInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_null() {
        teste.add("Numero invalido!");
        assertThat(teste, is(validador.validador("numeroNull")));
    }

    @Test
    public void deve_aceitar_cep_com_traco() {
        assertThat(teste, is(validador.validador("cepValidoComTraco")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cep_pelo_tamanho() {
        teste.add("CEP invalido!");
        assertThat(teste, is(validador.validador("cepInvalido")));
    }

    // Testa Construtor
    @Test
    public void deve_retornar_os_valores() {
        Endereco endereco3 = new Endereco();
        endereco3.setEndereco("Av. ac");
        assertThat(endereco3.getEndereco(), is("Av. ac"));
        endereco3.setCep("19263720");
        assertThat(endereco3.getCep(), is("19263720"));
        endereco3.setNumero("187");
        assertThat(endereco3.getNumero(), is("187"));
        endereco3.setEnderecoType(APARTAMENTO);
        assertThat(endereco3.getEnderecoType(), is(APARTAMENTO));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_retornar_hashCode_iguais_para_ceps_iguais() {
        assertTrue(endereco1.hashCode() == endereco1.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_ceps_diferentes() {
        assertTrue(endereco1.hashCode() == endereco3.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_cep_nulo() {
        assertThat(endereco.hashCode(), is(629));
    }

    @Test
    public void deve_retornar_true_para_ceps_iguais() {
        assertTrue(endereco1.equals(endereco2));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_cep() {
        assertTrue(endereco1.equals(endereco1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_endereco_nulo() {
        assertTrue(endereco.equals(endereco1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(endereco1.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cep_nulo() {
        endereco.setEndereco("Rua nao sei");
        endereco.setNumero("12");
        assertTrue(endereco.equals(endereco1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cep_comparado_nulo() {
        assertTrue(endereco1.equals(null));
    }

    @Test
    public void deve_retornar_true_para_ceps_nulos() {
        assertTrue(endereco.equals(endereco4));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_ceps_diferentes() {
        assertTrue(endereco1.equals(endereco3));
    }

    @Test
    public void deve_verificar_se_toString_contem_endereco() {
        assertTrue(endereco2.toString().contains("Rua tijuco"));
    }

    @Test
    public void deve_verificar_se_toString_contem_numero() {
        assertTrue(endereco2.toString().contains("452"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cep() {
        assertTrue(endereco2.toString().contains("03564870"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecoType() {
        assertTrue(endereco2.toString().contains("casa"));
    }
}
