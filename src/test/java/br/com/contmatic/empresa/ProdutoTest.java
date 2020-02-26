package br.com.contmatic.empresa;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.fixture.ProdutoMetodosParaTest;

@FixMethodOrder(NAME_ASCENDING)
public class ProdutoTest {

    private Produto produto = null;

    private Produto produto1 = null;

    private Produto produto2 = null;

    private Produto produto3 = null;

    private Produto produto4 = null;

    private ProdutoMetodosParaTest validador = null;

    private Set<String> teste = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.produto = new Produto();
        this.produto1 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("5"), "122523", 5);
        this.produto2 = new Produto("Blusa", "Blusa de maga,com estampas", new BigDecimal("5"), "122523", 2);
        this.produto3 = new Produto("Calça", "Calça preta com botoes", new BigDecimal("15"), "154545", 2);
        this.produto4 = new Produto();
        this.validador = new ProdutoMetodosParaTest();
        this.teste = new TreeSet<String>();
    }

    @After
    public void setDownAfter() {
        this.produto = null;
        this.produto1 = null;
        this.produto2 = null;
        this.produto3 = null;
        this.produto4 = null;
        this.validador = null;
        this.teste = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_erros_da_validacao() {
        assertThat(teste, is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("Codigo invalido");
        teste.add("Descrição invalida");
        teste.add("Preço invalido");
        teste.add("Quantidade menor que zero");
        teste.add("Tipo invalido");
        assertThat(teste, is(validador.validador("invalidos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo() {
        teste.add("Codigo invalido");
        assertThat(teste, is(validador.validador("codigoInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_nulo() {
        teste.add("Codigo invalido");
        assertThat(teste, is(validador.validador("codigoNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_tamanho_menor() {
        teste.add("Descrição invalida");
        assertThat(teste, is(validador.validador("descricaoInvalidoPeloTamanhoMenor")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_tamanho_maior() {
        teste.add("Descrição invalida");
        assertThat(teste, is(validador.validador("descricaoInvalidoPeloTamanhoMaior")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_nula() {
        teste.add("Descrição invalida");
        assertThat(teste, is(validador.validador("descricaoNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco() {
        teste.add("Preço invalido");
        assertThat(teste, is(validador.validador("precoInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade() {
        teste.add("Quantidade menor que zero");
        assertThat(teste, is(validador.validador("quantidadeInvalida")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_nula() {
        teste.add("Quantidade menor que zero");
        assertThat(teste, is(validador.validador("quantidadeNula")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_tamanho_menor() {
        teste.add("Tipo invalido");
        assertThat(teste, is(validador.validador("tipoInvalidoPeloTamanhoMenor")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_tamanho_maior() {
        teste.add("Tipo invalido");
        assertThat(teste, is(validador.validador("tipoInvalidoPeloTamanhoMaior")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_nulo() {
        teste.add("Tipo invalido");
        assertThat(teste, is(validador.validador("tipoNull")));
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
        assertTrue(produto1.hashCode() == produto2.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_codigo_diferentes() {
        assertTrue(produto1.hashCode() == produto3.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_codigo_nulo() {
        assertThat(produto.hashCode(), is(629));
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
    public void deve_verificar_se_toString_contem_tipo() {
        assertTrue(produto1.toString().contains("Blusa"));
    }

    @Test
    public void deve_verificar_se_toString_contem_preco() {
        assertTrue(produto1.toString().contains("5"));
    }

    @Test
    public void deve_verificar_se_toString_contem_descricao() {
        assertTrue(produto1.toString().contains("Blusa de maga,com estampas"));
    }

    @Test
    public void deve_verificar_se_toString_contem_codigo() {
        assertTrue(produto1.toString().contains("122523"));
    }

    @Test
    public void deve_verificar_se_toString_contem_quantidade() {
        assertTrue(produto1.toString().contains("5"));
    }
}
