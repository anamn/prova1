package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationLucro.validador;
import static br.com.contmatic.easy.random.classes.LucroEasyRandomParametros.parametrosLucro;
import static br.com.contmatic.financeiro.Moeda.DOLLAR;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.joda.time.YearMonth;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.financeiro.Lucro;

public class LucroTest {

    private Lucro lucro = null;

    private Lucro lucro1 = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.lucro = new EasyRandom(parametrosLucro()).nextObject(Lucro.class);
        this.lucro1 = new EasyRandom(parametrosLucro()).nextObject(Lucro.class);
    }

    @After
    public void setDownAfter() {
        this.lucro = null;
        this.lucro1 = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(lucro)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento_menor_que_zero() {
        lucro.setInvestimento(new BigDecimal("-450"));
        validador(lucro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento_igual_a_zero() {
        lucro.setInvestimento(new BigDecimal("0"));
        validador(lucro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento_nulo() {
        lucro.setInvestimento(null);
        validador(lucro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda_menor_que_zero() {
        lucro.setRenda(new BigDecimal("-450"));
        validador(lucro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda_igual_a_zero() {
        lucro.setRenda(new BigDecimal("0"));
        validador(lucro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda_nula() {
        lucro.setRenda(null);
        validador(lucro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_mes_futuro() {
        lucro.setMes(new YearMonth(2020, DECEMBER.getValue()));
        ;
        validador(lucro);
    }
    
    //Testa metodo
    @Test
    public void deve_retornar_o_lucro_calculado() {
        BigDecimal bg=lucro.calculaLucro();
        System.out.println(bg);
    }

    // Teste Construtor
    @Test
    public void deve_retornar_valores() {
        lucro.setInvestimento(new BigDecimal("33000"));
        assertTrue(lucro.getInvestimento().equals(new BigDecimal("33000")));
        lucro.setRenda(new BigDecimal("33000"));
        assertTrue(lucro.getRenda().equals(new BigDecimal("33000")));
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        assertTrue(lucro.getMes().equals(new YearMonth(2019, AUGUST.getValue())));
        lucro.setMoeda(DOLLAR);
        assertTrue(lucro.getMoeda().equals(DOLLAR));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_retornar_hashCode_iguais_para_mes_igual() {
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        lucro1.setMes(new YearMonth(2019, AUGUST.getValue()));
        assertTrue(lucro.hashCode() == lucro1.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_mes_diferente() {
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        lucro1.setMes(new YearMonth(2019, DECEMBER.getValue()));
        assertTrue(lucro.hashCode() == lucro1.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_mes_nulo() {
        lucro.setMes(null);
        assertThat(lucro.hashCode(), is(629));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_obj() {
        assertTrue(lucro.equals(lucro));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_mes() {
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        lucro1.setMes(new YearMonth(2019, AUGUST.getValue()));
        assertTrue(lucro.equals(lucro1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_lucro_nulo() {
        lucro.setMes(null);
        assertTrue(lucro.equals(lucro1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(lucro.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_mes_nulo() {
        lucro.setMes(null);
        assertTrue(lucro.equals(lucro1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_mes_comparado_nulo() {
        assertTrue(lucro.equals(null));
    }

    @Test
    public void deve_retornar_true_para_meses_nulos() {
        lucro.setMes(null);
        lucro1.setMes(null);
        assertTrue(lucro.equals(lucro1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_meses_diferentes() {
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        lucro1.setMes(new YearMonth(2019, DECEMBER.getValue()));
        assertTrue(lucro.equals(lucro1));
    }

    @Test
    public void deve_verificar_se_toString_contem_investimento() {
        assertTrue(lucro.toString().contains("investimento"));
    }

    @Test
    public void deve_verificar_se_toString_contem_renda() {
        assertTrue(lucro.toString().contains("renda"));
    }

    @Test
    public void deve_verificar_se_toString_contem_moeda() {
        assertTrue(lucro.toString().contains("moeda"));
    }

    @Test
    public void deve_verificar_se_toString_contem_mes() {
        assertTrue(lucro.toString().contains("mes"));
    }
}
