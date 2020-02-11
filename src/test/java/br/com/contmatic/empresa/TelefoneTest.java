package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contimatic.fixture.ValidadorTelefone;
import br.com.contmatic.enums.TelefoneType;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TelefoneTest {

    Telefone telefone = null;

    Telefone telefone1 = null;

    Telefone telefone2 = null;

    Telefone telefone3 = null;

    Telefone telefone4 = null;

    ValidadorTelefone validador = null;

    Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contimatic.fixture");
    }

    @Before
    public void test() {
        this.telefone = new Telefone();
        this.telefone1 = new Telefone();
        this.telefone2 = new Telefone("11 956875488", TelefoneType.CELULAR);
        this.telefone3 = new Telefone("11 956875488", TelefoneType.CELULAR);
        this.telefone4 = new Telefone("1565321442", TelefoneType.FIXO);

        this.validador = new ValidadorTelefone();
        
        this.teste = new TreeSet<String>();
    }

    @After
    public void finaliza() {
        this.telefone = null;
        this.telefone1 = null;
        this.telefone2 = null;
        this.telefone3 = null;
        this.telefone4 = null;

        this.validador = null;
        this.teste = null;

    }

    @Test
    public void nao_deve_retornar_mensagem_de_erro() {
        assertThat(teste, Matchers.is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_numero() {
        teste.add("Numero invalido");
        assertThat(teste, Matchers.is(validador.validador("invalidos")));
    }

    @Test
    public void deve_retornar_valores() {
        telefone.setNumero("21 965452344");
        assertTrue(telefone.getNumero().equals("21 965452344"));
        telefone.setTipo(TelefoneType.CELULAR);
        assertThat(telefone.getTipo(), Matchers.is(TelefoneType.CELULAR));

    }

    @Test
    public void deve_retornar_hashCode_iguais_para_telefones_iguais() {
        assertTrue(telefone2.hashCode() == telefone3.hashCode());

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_telefones_diferentes() {
        assertTrue(telefone2.hashCode() == telefone4.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_telefone_nulo() {
        assertThat(telefone.hashCode(), Matchers.is(629));
    }

    @Test
    public void deve_retornar_true_para_cpf_iguais() {
        assertTrue(telefone2.equals(telefone3));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cliente_nulo() {
        assertTrue(telefone2.equals(telefone1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(telefone2.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_telefone_nulo() {
        assertTrue(telefone.equals(telefone2));

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_telefones_comparado_nulo() {
        assertTrue(telefone2.equals(null));
    }

    @Test
    public void deve_retornar_true_para_telefones_nulos() {
        assertTrue(telefone.equals(telefone1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_telefones_diferentes() {
        assertTrue(telefone3.equals(telefone4));
    }

    @Test
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(telefone2);
        assertThat(telefone2, Matchers.is(telefone2));
    }

}
