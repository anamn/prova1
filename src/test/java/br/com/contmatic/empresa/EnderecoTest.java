package br.com.contmatic.empresa;

import static br.com.contmatic.enums.EnderecoType.APARTAMENTO;
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

import br.com.contmatic.enums.EnderecoType;
import br.com.contmatic.fixture.ValidadorEndereco;

@FixMethodOrder(NAME_ASCENDING)
public class EnderecoTest {

    Endereco endereco = null;

    Endereco endereco1 = null;

    Endereco endereco2 = null;

    Endereco endereco3 = null;

    Endereco endereco4 = null;

    ValidadorEndereco validador = null;

    Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void incializa() {
        this.endereco = new Endereco();
        this.endereco1 = new Endereco("Rua tijuco", "452", "03564870", EnderecoType.APARTAMENTO);
        this.endereco2 = new Endereco("Rua tijuco", "452", "03564870", EnderecoType.CASA);
        this.endereco3 = new Endereco("Rua tijuco", "452", "84574512", EnderecoType.COMENCIAL);
        this.endereco4 = new Endereco();
        
        this.validador = new ValidadorEndereco();
        this.teste = new TreeSet<String>();

    }

    @After
    public void finaliza() {
        this.endereco = null;
        this.endereco1 = null;
        this.endereco2 = null;
        this.endereco3 = null;
        this.endereco4 = null;
        
        this.validador = null;
        this.teste = null;
    }

    @Test
    public void nao_deve_retornar_erros() {
        assertThat(teste, is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("CEP invalido!");
        teste.add("Endereço invalido!");
        teste.add("Numero invalido!");
        assertThat(teste, is(validador.validador("invalidos")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_endereco() {
        teste.add("Endereço invalido!");
        assertThat(teste, is(validador.validador("enderecoInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero() {
        teste.add("Numero invalido!");
        assertThat(teste, is(validador.validador("numeroInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cep() {
        teste.add("CEP invalido!");
        assertThat(teste, is(validador.validador("cepInvalido")));

    }

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
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(endereco1);
        assertThat(endereco1, is(endereco1));
    }

}
