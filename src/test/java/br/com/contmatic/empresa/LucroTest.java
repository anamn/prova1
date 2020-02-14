package br.com.contmatic.empresa;

import static br.com.contmatic.enums.Moeda.DOLLAR;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.YearMonth;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.fixture.ValidadorLucro;

public class LucroTest {

    Lucro lucro = null;

    Lucro lucro1 = null;

    Lucro lucro2 = null;

    Lucro lucro3 = null;

    Lucro lucro4 = null;

    ValidadorLucro validador = null;

    Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void inicializaçao() {
        this.lucro = new Lucro();
        this.lucro1 = new Lucro();
        this.lucro2 = new Lucro(new BigDecimal("30000"), new BigDecimal("33000"), DOLLAR, new YearMonth(2020, JANUARY.getValue()));
        this.lucro3 = new Lucro(new BigDecimal("30000"), new BigDecimal("33000"), DOLLAR, new YearMonth(2020, JANUARY.getValue()));
        this.lucro4 = new Lucro(new BigDecimal("30000"), new BigDecimal("33000"), DOLLAR, new YearMonth(2019, AUGUST.getValue()));

        this.validador = new ValidadorLucro();
        this.teste = new TreeSet<String>();

    }

    @After
    public void finalizaçao() {
        this.lucro = null;
        this.lucro1 = null;
        this.lucro2 = null;
        this.lucro3 = null;
        this.lucro4 = null;

        this.validador = null;
        this.teste = null;
    }

    @Test
    public void nao_deve_retornar_erros() {
        assertThat(teste, is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("Investimento menor que zero!");
        teste.add("Renda menor que zero!");
        teste.add("A data deve ser no presente!");
        assertThat(teste, is(validador.validador("invalidos")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento() {
        teste.add("Investimento menor que zero!");
        assertThat(teste, is(validador.validador("investimentoInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda() {
        teste.add("Renda menor que zero!");
        assertThat(teste, is(validador.validador("rendaInvalida")));

    }

    @Test
    public void deve_calcular_o_lucro_da_empresa() {
        Set<Endereco> endereco = new HashSet<Endereco>();
        Set<Telefone> telefones = new HashSet<Telefone>();
        Empresa empresa = new Empresa("Pedra", "12356272839283", telefones, "pedra@hotmail.com", endereco);
        lucro2.setEmpresa(empresa);
        empresa.setLucro(lucro2);
        assertThat(lucro2.getEmpresa(), is(empresa));
        assertThat(lucro2.calculaLucro(), is(empresa.getLucro().calculaLucro()));
    }

    @Test
    public void deve_retornar_valores() {
        lucro.setInvestimento(new BigDecimal("33000"));
        assertTrue(lucro.getInvestimento().equals(new BigDecimal("33000")));
        lucro.setRenda(new BigDecimal("33000"));
        assertTrue(lucro4.getRenda().equals(new BigDecimal("33000")));
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        assertTrue(lucro4.getMes().equals(new YearMonth(2019, AUGUST.getValue())));
        lucro.setMoeda(DOLLAR);
        assertTrue(lucro4.getMoeda().equals(DOLLAR));
    }

    @Test
    public void deve_retornar_hashCode_iguais_para_mes_igual() {
        assertTrue(lucro2.hashCode() == lucro2.hashCode());

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_mes_diferente() {
        assertTrue(lucro2.hashCode() == lucro4.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_mes_nulo() {
        assertThat(lucro.hashCode(), is(23273));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_obj() {
        assertTrue(lucro2.equals(lucro2));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_mes() {
        assertTrue(lucro3.equals(lucro2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_lucro_nulo() {
        assertTrue(lucro.equals(lucro2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(lucro2.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_mes_nulo() {
        assertTrue(lucro.equals(lucro2));

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_mes_comparado_nulo() {
        assertTrue(lucro2.equals(null));
    }

    @Test
    public void deve_retornar_true_para_meses_nulos() {
        assertTrue(lucro.equals(lucro1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_meses_diferentes() {
        assertTrue(lucro3.equals(lucro4));
    }

    @Test
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(lucro2);
        assertThat(lucro2, is(lucro2));
    }

}
