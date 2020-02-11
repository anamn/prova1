package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contimatic.fixture.ValidadorProduto;
import br.com.contmatic.empresa.Produto;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class ProdutoTest {

    Produto produto = null;

    Produto produto1 = null;

    Produto produto2 = null;

    Produto produto3 = null;

    Produto produto4 = null;

    ValidadorProduto validador = null;

    Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contimatic.fixture");
    }

    @Before
    public void incializacao() {
        this.produto = new Produto();
        this.produto1 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("5"), "122523", 5);
        this.produto2 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("5"), "122523", 2);
        this.produto3 = new Produto("Calça", "Calça preta com botoes", new BigDecimal("15"), "154545", 2);
        this.produto4 = new Produto();

        this.validador = new ValidadorProduto();
        this.teste = new TreeSet<>();
    }

    @After
    public void finalizacao() {
        this.produto = null;
        this.produto1 = null;
        this.produto2 = null;
        this.produto3 = null;
        this.produto4 = null;

        this.validador = null;
        this.teste = null;
    }

    @Test
    public void nao_deve_retornar_erros() {
        assertThat(teste, Matchers.is(validador.validador("validos")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("Codigo invalido");
        teste.add("Descrição invalida");
        teste.add("Preço invalido");
        teste.add("Quantidade menor que zero");
        teste.add("Tipo invalido");
        assertThat(teste, Matchers.is(validador.validador("invalidos")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo() {
        teste.add("Codigo invalido");
        assertThat(teste, Matchers.is(validador.validador("codigoInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao() {
        teste.add("Descrição invalida");
        assertThat(teste, Matchers.is(validador.validador("descricaoInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco() {
        teste.add("Preço invalido");
        assertThat(teste, Matchers.is(validador.validador("precoInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade() {
        teste.add("Quantidade menor que zero");
        assertThat(teste, Matchers.is(validador.validador("quantidadeInvalida")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo() {
        teste.add("Tipo invalido");
        assertThat(teste, Matchers.is(validador.validador("tipoInvalido")));

    }

    @Test
    public void deve_retornar_valores() {
        produto.setDescricao("Blusa de maga,com estampas");
        assertTrue(produto.getDescricao().equals("Blusa de maga,com estampas"));
        produto.setTipo("Blusa");
        assertTrue(produto.getTipo().equals("Blusa"));
        produto.setPreco(new BigDecimal("5"));
        assertThat(produto.getPreco(), Matchers.is(new BigDecimal("5")));
        produto.setCodigo("122523");
        assertTrue(produto.getCodigo().equals("122523"));
        produto.setQuantidade(3);
        assertTrue(produto.getQuantidade().equals(3));

    }

    @Test
    public void deve_retornar_hashCode_iguais_para_codigo_iguais() {
        assertTrue(produto1.hashCode() == produto2.hashCode());

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_codigo_diferentes() {
        assertTrue(produto1.hashCode() == produto3.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_codigo_nulo() {
        assertThat(produto.hashCode(), Matchers.is(629));
    }

    @Test
    public void deve_retornar_true_para_codigo_iguais() {
        assertTrue(produto1.equals(produto2));
    }

    @Test
    public void deve_retornar_true_para_o_mesmo_codigo() {
        assertTrue(produto1.equals(produto1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_produto_nulo() {
        assertTrue(produto.equals(produto1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(produto1.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_codigo_nulo() {
        assertTrue(produto.equals(produto1));

    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_codigo_comparado_nulo() {
        assertTrue(produto1.equals(null));
    }

    @Test
    public void deve_retornar_true_para_codigo_nulos() {
        assertTrue(produto.equals(produto4));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_codigo_diferentes() {
        assertTrue(produto1.equals(produto3));
    }

    @Test
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(produto1);
        assertThat(produto1, Matchers.is(produto1));
    }
}
