package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
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

import br.com.contimatic.fixture.ValidadorCliente;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.enums.EnderecoType;
import br.com.contmatic.enums.TelefoneType;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

    Cliente cliente = null;

    Cliente cliente1 = null;

    Cliente cliente2 = null;

    Cliente cliente3 = null;

    Cliente cliente4 = null;

    Endereco endereco = null;

    ValidadorCliente validador = null;

    Set<String> teste = null;

    Telefone telefone = null;
    Telefone telefone1 = null;
    Telefone telefone2 = null;

    Set<Telefone> telefones = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contimatic.fixture");
    }

    @Before
    public void incializacao() {
        this.endereco = new Endereco("Rua pedra", "5", "91112120", EnderecoType.APARTAMENTO);
        this.telefones = new HashSet<>();

        this.cliente = new Cliente();
        this.cliente1 = new Cliente();
        this.cliente2 = new Cliente("Maria", "12898282726", telefones, "maria@hotmail.com", endereco);
        this.cliente3 = new Cliente("Maria", "12898282726", telefones, "maria@hotmail.com", endereco);
        this.cliente4 = new Cliente("Joao", "13213213214", telefones, "jao@gmail.com", endereco);
        
        this.validador = new ValidadorCliente();
        this.teste = new TreeSet<>();
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

    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefone = new Telefone("(11)986564582", TelefoneType.CELULAR);
        this.telefone1 = new Telefone("1586564582", TelefoneType.FIXO);
        this.telefone2 = new Telefone("1586564582", TelefoneType.FIXO);
        telefones.add(telefone);
        telefones.add(telefone1);
        telefones.add(telefone2);
        cliente.setTelefones(telefones);
        assertTrue(cliente.getTelefones().size() == 2);
    }

    @Test
    public void nao_deve_retornar_erros() {
        assertThat(teste, Matchers.is(validador.validador("validos")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_em_todos_os_campos() {
        teste.add("CPF invalido");
        teste.add("Nome invalido");
        assertThat(teste, Matchers.is(validador.validador("invalidos")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome() {
        teste.add("Nome invalido");
        assertThat(teste, Matchers.is(validador.validador("nomeInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf() {
        teste.add("CPF invalido");
        assertThat(teste, Matchers.is(validador.validador("cpfInvalido")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email() {
        teste.add("Email invalido");
        assertThat(teste, Matchers.is(validador.validador("emailInvalido")));

    }

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
    public void deve_retornar_a_toString_do_objeto() {
        System.out.println(cliente2);
        assertThat(cliente2, Matchers.is(cliente2));
    }

}
