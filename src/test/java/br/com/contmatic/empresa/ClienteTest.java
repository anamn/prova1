package br.com.contmatic.empresa;

import static br.com.contmatic.endereco.EnderecoType.APARTAMENTO;
import static br.com.contmatic.telefone.Ddd.MA99;
import static br.com.contmatic.telefone.Ddd.RJ21;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.fixture.ValidadorCliente;
import br.com.contmatic.telefone.Telefone;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

    private Cliente cliente = null;

    private Cliente cliente1 = null;

    private Cliente cliente2 = null;

    private Cliente cliente3 = null;

    private Cliente cliente4 = null;

    private Endereco endereco = null;

    private ValidadorCliente validador = null;

    private Set<String> teste = null;

    private Telefone telefone = null;

    private Telefone telefone1 = null;

    private Telefone telefone2 = null;

    private Set<Telefone> telefones = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void incializacao() {
        this.endereco = new Endereco("Rua pedra", "5", "91112120", APARTAMENTO);
        this.telefones = new HashSet<>();
        this.cliente = new Cliente();
        this.cliente1 = new Cliente();
        this.cliente2 = new Cliente("Maria", "12898282726", telefones, "maria@hotmail.com", endereco);
        this.cliente3 = new Cliente("Maria", "12898282726", telefones, "maria@hotmail.com", endereco);
        this.cliente4 = new Cliente("Joao", "13213213214", telefones, "jao@gmail.com", endereco);
        this.validador = new ValidadorCliente();
        this.teste = new TreeSet<String>();
    }

    @After
    public void finaliza() {
        this.endereco = null;
        this.telefones = null;
        this.cliente = null;
        this.cliente1 = null;
        this.cliente2 = null;
        this.cliente3 = null;
        this.cliente4 = null;
        this.validador = null;
        this.teste = null;
    }

    // Testa lista de telefone
    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefone = new Telefone(MA99, "986564582", CELULAR);
        this.telefone1 = new Telefone(RJ21, "8656-4582", FIXO);
        this.telefone2 = new Telefone(RJ21, "8656-4582", FIXO);
        telefones.add(telefone);
        telefones.add(telefone1);
        telefones.add(telefone2);
        cliente.setTelefones(telefones);
        assertTrue(cliente.getTelefones().size() == 2);
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao() {
        assertThat(teste, is(validador.validador("validos")));
    }

    @Test
    public void deve_aceitar_nome_com_espaco() {
        assertThat(teste, is(validador.validador("nomeValidoComEspaço")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("CPF invalido");
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("invalidos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_invalido() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_nulo() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_numero() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalidoComNumero")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_caracteres_especiais() {
        teste.add("Nome invalido");
        assertThat(teste, is(validador.validador("nomeInvalidoComEspeciais")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("cpfInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("cpfTamanhoInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_nulo() {
        teste.add("CPF invalido");
        assertThat(teste, is(validador.validador("cpfNull")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email() {
        teste.add("Email invalido");
        assertThat(teste, is(validador.validador("emailInvalido")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_null() {
        teste.add("Email invalido");
        assertThat(teste, is(validador.validador("emailNull")));
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
        assertTrue(cliente1.hashCode() == cliente.hashCode());
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_cpf_diferentes() {
        assertTrue(cliente1.hashCode() == cliente3.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_cpf_nulo() {
        assertThat(cliente.hashCode(), Matchers.is(629));
    }

    @Test
    public void deve_retornar_true_para_cpf_iguais() {
        assertTrue(cliente2.equals(cliente3));
    }
    
    @Test
    public void deve_retornar_true_o_mesmo_cpf() {
        assertTrue(cliente3.equals(cliente3));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cliente_nulo() {
        assertTrue(cliente.equals(cliente2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(cliente2.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_nulo() {
        assertTrue(cliente.equals(cliente2));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_comparado_nulo() {
        assertTrue(cliente2.equals(null));
    }

    @Test
    public void deve_retornar_true_para_cpf_nulos() {
        assertTrue(cliente.equals(cliente1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_diferentes() {
        assertTrue(cliente3.equals(cliente4));
    }

    @Test
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(cliente2.toString().contains("Maria"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cpf() {
        assertTrue(cliente2.toString().contains("12898282726"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(cliente2.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_email() {
        assertTrue(cliente2.toString().contains("maria@hotmail.com"));
    }

    @Test
    public void deve_verificar_se_toString_contem_endereco() {
        assertTrue(cliente2.toString().contains("endereco"));
    }
}
