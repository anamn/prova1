package br.com.contmatic.empresa;

import static br.com.contmatic.fixture.TelefoneMetodosParaTest.telefone;
import static br.com.contmatic.fixture.TelefoneMetodosParaTest.validador;
import static br.com.contmatic.telefone.Ddd.AL82;
import static br.com.contmatic.telefone.Ddd.PR44;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.telefone.Telefone;
import nl.flotsam.xeger.Xeger;

public class TelefoneTest {

    private Telefone telefone = null;

    private Telefone telefone1 = null;

    private Telefone telefone2 = null;

    private Telefone telefone3 = null;

    private Telefone telefone4 = null;

    private Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.telefone = new Telefone();
        this.telefone1 = new Telefone();
        this.telefone2 = new Telefone(AL82, "956875488", CELULAR);
        this.telefone3 = new Telefone(AL82, "956875488", CELULAR);
        this.telefone4 = new Telefone(PR44, "1565321442", FIXO);
        this.teste = new TreeSet<String>();
    }

    @After
    public void setDownAfter() {
        this.telefone = null;
        this.telefone1 = null;
        this.telefone2 = null;
        this.telefone3 = null;
        this.telefone4 = null;
        this.teste = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_mensagem_de_erro() { 
        Telefone easyTel= telefone("([9]?[0-9]{4}-?[0-9]{4})");
        assertThat(teste, is(validador(easyTel)));
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_numero_pelo_tamanho() {
        teste.add("Numero invalido");
        Telefone easyTel=telefone("([9]?[0-9]{4}-?[0-9]{6})");
        assertThat(teste, is(validador(easyTel)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_numero_nulo() {
        teste.add("Numero invalido");
        EasyRandom easy= new EasyRandom();
        Telefone easyTel = easy.nextObject(Telefone.class);
        easyTel.setNumero(null);
        assertThat(teste, is(validador(easyTel)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_ddd_nulo() {
        teste.add("DDD nulo");
        EasyRandom easy= new EasyRandom();
        Telefone easyTel = easy.nextObject(Telefone.class);
        Xeger geradorNumero= new Xeger("([9]?[0-9]{4}-?[0-9]{4})");
        easyTel.setNumero(geradorNumero.generate());
        easyTel.setDdd(null);
        assertThat(teste, is(validador(easyTel)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_nulo() {
        teste.add("Tipo nulo");
        EasyRandom easy= new EasyRandom();
        Telefone easyTel = easy.nextObject(Telefone.class);
        Xeger geradorNumero= new Xeger("([9]?[0-9]{4}-?[0-9]{4})");
        easyTel.setNumero(geradorNumero.generate());
        easyTel.setTipo(null);
        assertThat(teste, is(validador(easyTel)));
    }

    // Teste Construtor
    @Test
    public void deve_retornar_valores() {
        telefone.setNumero("21 965452344");
        assertTrue(telefone.getNumero().equals("21 965452344"));
        telefone.setTipo(CELULAR);
        assertThat(telefone.getTipo().getTipo(), is(CELULAR.getTipo()));
        telefone.setDdd(PR44);
        assertThat(telefone.getDdd().toString(), is(PR44.toString()));
    }

    // Testes HashCode, Equals e toString
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
        assertThat(telefone.hashCode(), is(23273));
    }

    @Test
    public void deve_retornar_true_para_numero_iguais() {
        assertTrue(telefone2.equals(telefone3));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_numero() {
        assertTrue(telefone3.equals(telefone3));
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
    public void deve_verificar_se_toString_contem_ddd() {
        assertTrue(telefone2.toString().contains("AL82"));
    }

    @Test
    public void deve_verificar_se_toString_contem_numero() {
        assertTrue(telefone2.toString().contains("956875488"));
    }

    @Test
    public void deve_verificar_se_toString_contem_tipo() {
        assertTrue(telefone2.toString().contains("celular"));
    }
}
