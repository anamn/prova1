package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationCliente.validador;
import static br.com.contmatic.easy.random.classes.ClienteEasyRandomParametros.parametrosCliente;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandomParametros.parametrosTelefone;
import static br.com.contmatic.telefone.Ddd.AM92;
import static br.com.contmatic.telefone.Ddd.SP11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

    private Cliente cliente;

    private Cliente cliente1;

    private Endereco endereco;

    private Telefone telefone;

    private Set<Telefone> telefones;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void incializacao() {
        this.endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        this.telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
        this.telefones = new HashSet<Telefone>();
        telefones.add(telefone);
        this.cliente = new EasyRandom(parametrosCliente()).nextObject(Cliente.class);
        this.cliente.setEndereco(endereco);
        this.cliente.setTelefones(telefones);
        this.cliente1 = new EasyRandom(parametrosCliente()).nextObject(Cliente.class);
        this.cliente1.setEndereco(endereco);
        this.cliente1.setTelefones(telefones);

    }

    @After
    public void finaliza() {
        this.endereco = null;
        this.telefones = null;
        this.cliente = null;
        this.cliente1 = null;
    }
    
    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefones.remove(telefone);
        telefones.add(new Telefone(SP11, "986564582", CELULAR));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        assertTrue(cliente.getTelefones().size() == 2);
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(cliente)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_maior() {
        cliente.setNome(randomAlphabetic(52));
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_menor() {
        cliente.setNome(randomAlphabetic(1));
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_nulo() {
        cliente.setNome(null);
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_numero() {
        cliente.setNome("Ana098");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_caracteres_especiais() {
        cliente.setNome("Ana_*");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf() {
        cliente.setCpf(randomNumeric(11));
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho_maior() {
        cliente.setCpf(randomNumeric(12));
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_caracter_especial() {
        cliente.setCpf("112542654_*");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_letra() {
        cliente.setCpf("112542654aa");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho_menor() {
        cliente.setCpf(randomNumeric(10));
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_nulo() {
        cliente.setCpf(null);
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_de_arroba() {
        cliente.setEmail("anahotmail.com");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_ponto() {
        cliente.setEmail("ana@hotmailcom");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_com() {
        cliente.setEmail("ana@hotmail.");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_numero_depois_do_arroba() {
        cliente.setEmail("cliente@3873.com.br");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_letra_maiuscula() {
        cliente.setEmail("CLIENTE@HOTMAIL.COM");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_espaco() {
        cliente.setEmail("cliente @hotmail.com");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_sem_nada() {
        cliente.setEmail("cliente");
        validador(cliente);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_null() {
        cliente.setEmail(null);
        validador(cliente);
    }

    // Testa Construtor
    @Test
    public void deve_retornar_valores_passados() {
        cliente.setNome("Maria");
        assertTrue(cliente.getNome().equals("Maria"));
        cliente.setCpf("32179960387");
        assertTrue(cliente.getCpf().equals("32179960387"));
        cliente.setTelefones(telefones);
        assertTrue(cliente.getTelefones().equals(telefones));
        cliente.setEndereco(endereco);
        assertTrue(cliente.getEndereco().equals(endereco));
        cliente.setEmail("maria@hotmail.com");
        assertTrue(cliente.getEmail().equals("maria@hotmail.com"));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_retornar_hashCode_iguais_para_cpf_iguais() {
        cliente.setCpf("49022518841");
        cliente1.setCpf("49022518841");
        assertTrue(cliente1.hashCode() == cliente.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_cpf_diferentes() {
        cliente.setCpf("49022518841");
        cliente1.setCpf("32179960387");
        assertTrue(cliente1.hashCode() == cliente.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_cpf_nulo() {
        cliente.setCpf(null);
        assertThat(cliente.hashCode(), Matchers.is(629));
    }

    @Test
    public void deve_retornar_true_para_cpf_iguais() {
        cliente.setCpf("49022518841");
        cliente1.setCpf("49022518841");
        assertTrue(cliente.equals(cliente1));
    }

    @Test
    public void deve_retornar_true_o_mesmo_cpf() {
        assertTrue(cliente.equals(cliente));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cliente_nulo() {
        cliente.setCpf(null);
        assertTrue(cliente.equals(cliente1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(cliente.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_nulo() {
        cliente.setCpf(null);
        cliente1.setCpf("49022518841");
        assertTrue(cliente.equals(cliente1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_comparado_nulo() {
        assertTrue(cliente.equals(null));
    }

    @Test
    public void deve_retornar_true_para_cpf_nulos() {
        cliente.setCpf(null);
        cliente1.setCpf(null);
        assertTrue(cliente.equals(cliente1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_diferentes() {
        cliente.setCpf("49022518841");
        cliente1.setCpf("32179960387");
        assertTrue(cliente.equals(cliente1));
    }

    @Test
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(cliente.toString().contains("nome"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cpf() {
        assertTrue(cliente.toString().contains("cpf"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(cliente.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_email() {
        assertTrue(cliente.toString().contains("email"));
    }

    @Test
    public void deve_verificar_se_toString_contem_endereco() {
        assertTrue(cliente.toString().contains("endereco"));
    }
}
