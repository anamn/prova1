package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationProduto.validador;
import static br.com.contmatic.easy.random.classes.ProdutoEasyRandomParametros.parametrosProduto;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Produto;

@FixMethodOrder(NAME_ASCENDING)
public class ProdutoTest {

    private Produto produto;

    private Produto produto1;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.produto = new EasyRandom(parametrosProduto()).nextObject(Produto.class);
        this.produto1 = new EasyRandom(parametrosProduto()).nextObject(Produto.class);
    }

    @After
    public void setDownAfter() {
        this.produto = null;
        this.produto1 = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_erros_da_validacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(produto)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_pelo_tamanho_maior() {
        produto.setCodigo(randomNumeric(51));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_pelo_tamanho_menor() {
        produto.setCodigo(randomNumeric(4));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_nulo() {
        produto.setCodigo(null);
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_tamanho_menor() {
        produto.setDescricao(randomAlphabetic(9));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_tamanho_maior() {
        produto.setDescricao(randomAlphabetic(61));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_nula() {
        produto.setDescricao(null);
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco_menor_que_zero() {
        produto.setPreco(new BigDecimal("-50"));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco_igual_a_zero() {
        produto.setPreco(new BigDecimal("0"));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco_nulo() {
        produto.setPreco(null);
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_igual_a_zero() {
        produto.setQuantidade(0);
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_menor_que_zero() {
        produto.setQuantidade(-9);
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_nula() {
        produto.setQuantidade(null);
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_tamanho_menor() {
        produto.setTipo(randomAlphabetic(1));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_tamanho_maior() {
        produto.setTipo(randomAlphabetic(51));
        validador(produto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_nulo() {
        produto.setTipo(null);
        validador(produto);
    }

    // Teste Construtor
    @Test
    public void deve_retornar_valores() {
        produto.setDescricao("Blusa de maga,com estampas");
        assertTrue(produto.getDescricao().equals("Blusa de maga,com estampas"));
        produto.setTipo("Blusa");
        assertTrue(produto.getTipo().equals("Blusa"));
        produto.setPreco(new BigDecimal("5"));
        assertThat(produto.getPreco(), is(new BigDecimal("5")));
        produto.setCodigo("122523");
        assertTrue(produto.getCodigo().equals("122523"));
        produto.setQuantidade(3);
        assertTrue(produto.getQuantidade().equals(3));
    }

    // Testes HashCode, Equals e toString
    @Test
    public void deve_retornar_hashCode_iguais_para_codigo_iguais() {
        produto.setCodigo("55956833");
        produto1.setCodigo("55956833");
        assertTrue(produto.hashCode() == produto1.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_codigo_diferentes() {
        produto.setCodigo("55956833");
        produto1.setCodigo("56361822");
        assertTrue(produto.hashCode() == produto1.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_codigo_nulo() {
        produto.setCodigo(null);
        assertThat(produto.hashCode(), is(629));
    }

    @Test
    public void deve_retornar_true_para_codigo_iguais() {
        produto.setCodigo("55956833");
        produto1.setCodigo("55956833");
        assertTrue(produto.equals(produto1));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_codigo() {
        assertTrue(produto.equals(produto));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(produto1.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_codigo_nulo() {
        produto.setCodigo(null);
        assertTrue(produto.equals(produto1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_codigo_comparado_nulo() {
        assertTrue(produto1.equals(null));
    }

    @Test
    public void deve_retornar_true_para_codigo_nulos() {
        produto.setCodigo(null);
        produto1.setCodigo(null);
        assertTrue(produto.equals(produto1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_codigo_diferentes() {
        produto.setCodigo("55956833");
        produto1.setCodigo("56361822");
        assertTrue(produto.equals(produto1));
    }

    @Test
    public void deve_verificar_se_toString_contem_tipo() {
        assertTrue(produto1.toString().contains("tipo"));
    }

    @Test
    public void deve_verificar_se_toString_contem_preco() {
        assertTrue(produto1.toString().contains("preco"));
    }

    @Test
    public void deve_verificar_se_toString_contem_descricao() {
        assertTrue(produto1.toString().contains("descricao"));
    }

    @Test
    public void deve_verificar_se_toString_contem_codigo() {
        assertTrue(produto1.toString().contains("codigo"));
    }

    @Test
    public void deve_verificar_se_toString_contem_quantidade() {
        assertTrue(produto1.toString().contains("quantidade"));
    }
}
