package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationEmpresa.validador;
import static br.com.contmatic.easy.random.classes.EmpresaEasyRandomParametros.parametrosEmpresa;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandomParametros.parametrosTelefone;
import static br.com.contmatic.endereco.EnderecoType.CASA;
import static br.com.contmatic.telefone.Ddd.AM92;
import static br.com.contmatic.telefone.Ddd.SP11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

    private Empresa empresa;

    private Empresa empresa1;

    private Endereco endereco;

    private Set<Endereco> enderecos;

    private Set<Telefone> telefones;

    private Telefone telefone;

    private Set<Produto> produtos;

    private Set<Funcionario> funcionarios;

    private Set<Cliente> clientes;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void setUpBefore() {
        this.telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
        this.telefones = new HashSet<Telefone>();
        this.telefones.add(telefone);
        this.enderecos = new HashSet<Endereco>();
        this.endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        this.enderecos.add(endereco);
        this.empresa = new EasyRandom(parametrosEmpresa()).nextObject(Empresa.class);
        empresa.setTelefones(telefones);
        empresa.setEnderecos(enderecos);
        this.empresa1 = new EasyRandom(parametrosEmpresa()).nextObject(Empresa.class);
        empresa1.setTelefones(telefones);
        empresa1.setEnderecos(enderecos);
    }

    @After
    public void setDownAfter() {
        this.telefones = null;
        this.enderecos = null;
        this.empresa = null;
        this.empresa1 = null;
    }

    // Testa listas
    @Test
    public void nao_deve_adicionar_o_mesmo_produto() {
        produtos = new HashSet<>();
        produtos.add(new Produto("Notbook", "Notbook samsung versao 7", new BigDecimal("2500"), "12345678910", 3));
        produtos.add(new Produto("Notbook", "Notbook samsung versao 7", new BigDecimal("2500"), "12345678910", 3));
        produtos.add(new Produto("Notbook", "Notbook samsung versao 8", new BigDecimal("2500"), "123456654789", 3));
        empresa.setProdutos(produtos);
        assertTrue(empresa.getProdutos().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        this.telefones.remove(telefone);
        telefones.add(new Telefone(SP11, "986564582", CELULAR));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        assertTrue(empresa.getTelefones().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_endereco() {
        this.enderecos.remove(endereco);
        enderecos.add(new Endereco("Rua Safira", "Vila Pedras", "45A", "03556878", CASA));
        enderecos.add(new Endereco("Rua Safira", "Vila Pedras", "45A", "03556878", CASA));
        enderecos.add(new Endereco("Rua Outono", "Vila Estacao", "65A", "03245875", CASA));
        assertTrue(empresa.getEnderecos().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_cliente() {
        clientes = new HashSet<>();
        clientes.add(new Cliente("Ana", "80974025054", telefones, "ana@hotmail.com", endereco));
        clientes.add(new Cliente("Ana", "80974025054", telefones, "ana@hotmail.com", endereco));
        clientes.add(new Cliente("João", "69460183034", telefones, "joazinho@hotmail.com", endereco));
        empresa.setClientes(clientes);
        assertTrue(empresa.getClientes().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_funcionario() {
        funcionarios = new HashSet<>();
        funcionarios.add(new Funcionario("Ana", "80974025054", "53503636917", telefones, new BigDecimal("1500"), endereco));
        funcionarios.add(new Funcionario("Ana", "80974025054", "53503636917", telefones, new BigDecimal("1500"), endereco));
        funcionarios.add(new Funcionario("João", "80974025054", "94885325000", telefones, new BigDecimal("1500"), endereco));
        empresa.setFuncionarios(funcionarios);
        assertTrue(empresa.getFuncionarios().size() == 2);
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_na_validacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(empresa)));
    }

    @Test
    public void deve_aceitar_espaco_no_nome() {
        Set<String> teste = new TreeSet<String>();
        empresa.setNome("Antone Flae");
        assertThat(teste, is(validador(empresa)));
    }

    @Test
    public void deve_aceitar_numero_no_nome() {
        Set<String> teste = new TreeSet<String>();
        empresa.setNome("Antone009");
        assertThat(teste, is(validador(empresa)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome_pelo_tamanho_maior() {
        empresa.setNome(RandomStringUtils.randomAlphabetic(51));
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome_pelo_tamanho_menor() {
        empresa.setNome(RandomStringUtils.randomAlphabetic(1));
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome_null() {
        empresa.setNome(null);
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cnpj() {
        empresa.setCnpj("12345264578459");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cnpj_nulo() {
        empresa.setCnpj(null);
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_site_sem_o_ponto_com_ponto_br() {
        empresa.setSite("onixbr");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_site_so_com_os_pontos() {
        empresa.setSite(".com.br");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_de_arroba() {
        empresa.setEmail("anahotmail.com");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_ponto() {
        empresa.setEmail("ana@hotmailcom");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_com() {
        empresa.setEmail("ana@hotmail.");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_numero_depois_do_arroba() {
        empresa.setEmail("cliente@3873.com.br");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_letra_maiuscula() {
        empresa.setEmail("CLIENTE@HOTMAIL.COM");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_espaco() {
        empresa.setEmail("cliente @hotmail.com");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_sem_nada() {
        empresa.setEmail("cliente");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_nulo() {
        empresa.setEmail(null);
        validador(empresa);
    }

    @Test
    public void deve_retornar_o_endereco_passado() {
        empresa.setEnderecos(enderecos);
        assertThat(empresa.getEnderecos(), is(enderecos));
    }

    // Testa Construtor
    @Test
    public void deve_validaar_os_valores_passados() {
        empresa.setNome("Lg");
        assertEquals("Lg", empresa.getNome());
        empresa.setEmail("lg@gmail.com");
        assertEquals("lg@gmail.com", empresa.getEmail());
        empresa.setCnpj("02828446000134");
        assertEquals("02828446000134", empresa.getCnpj());
        empresa.setTelefones(telefones);
        assertEquals(telefones, empresa.getTelefones());
        empresa.setEnderecos(enderecos);
        assertEquals(enderecos, empresa.getEnderecos());
        empresa.setSite("www.lg.com.br");
        assertEquals("www.lg.com.br", empresa.getSite());

    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_retornar_hashCode_iguais_para_cnpj_iguais() {
        empresa.setCnpj("14217869000105");
        empresa1.setCnpj("14217869000105");
        assertTrue(empresa.hashCode() == empresa1.hashCode());
    }

    @Test
    public void deve_testar_hashcode_para_cnpj_nulo() {
        empresa.setCnpj(null);
        assertThat(empresa.hashCode(), is(629));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_hashCode_diferentes_para_cnpj_diferentes() {
        empresa.setCnpj("14217869000105");
        empresa1.setCnpj("45303282000134");
        assertTrue(empresa.hashCode() == empresa1.hashCode());
    }

    @Test
    public void deve_retornar_true_para_cnpj_iguais() {
        empresa.setCnpj("14217869000105");
        empresa1.setCnpj("14217869000105");
        assertTrue(empresa.equals(empresa1));
    }

    @Test
    public void deve_retornar_true_para_a_mesma_empresa() {
        assertTrue(empresa.equals(empresa));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_objetos_de_classes_diferentes() {
        assertTrue(empresa.equals(new Object()));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cnpj_nulo() {
        empresa1.setCnpj(null);
        assertTrue(empresa1.equals(empresa));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cpf_comparado_nulo() {
        assertTrue(empresa.equals(null));
    }

    @Test
    public void deve_retornar_true_para_cpf_nulos() {
        empresa.setCnpj(null);
        empresa1.setCnpj(null);
        assertTrue(empresa.equals(empresa1));
    }

    @Test(expected = AssertionError.class)
    public void deve_retornar_false_para_cnpj_diferentes() {
        empresa.setCnpj("14217869000105");
        empresa1.setCnpj("45303282000134");
        assertTrue(empresa.equals(empresa1));
    }

    @Test
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(empresa.toString().contains("nome"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cnpj() {
        assertTrue(empresa.toString().contains("cnpj"));
    }

    @Test
    public void deve_verificar_se_toString_contem_email() {
        assertTrue(empresa.toString().contains("email"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(empresa.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecos() {
        assertTrue(empresa.toString().contains("enderecos"));
    }

    @Test
    public void deve_verificar_se_toString_contem_clientes() {
        assertTrue(empresa.toString().contains("clientes"));
    }

    @Test
    public void deve_verificar_se_toString_contem_funcionarios() {
        assertTrue(empresa.toString().contains("funcionarios"));
    }

    @Test
    public void deve_verificar_se_toString_contem_produtos() {
        assertTrue(empresa.toString().contains("produtos"));
    }

    @Test
    public void deve_verificar_se_toString_contem_lucro() {
        assertTrue(empresa.toString().contains("lucro"));
    }

    @Test
    public void deve_verificar_se_toString_contem_site() {
        assertTrue(empresa.toString().contains("site"));
    }

    @Test
    public void deve_adicionar_empresas_ao_banco_de_dados() {
        for(int i = 0 ; i < 70 ; i++) {
            this.empresa = new EasyRandom(parametrosEmpresa()).nextObject(Empresa.class);
            empresa.setTelefones(telefones);
            empresa.setEnderecos(enderecos);
            MongoClient dadosEmpresa = new MongoClient("localhost");
            MongoDatabase bancoDeDados = dadosEmpresa.getDatabase("Prova3");
            MongoCollection<Document> empresas = bancoDeDados.getCollection("Empresa");
            empresas.insertOne(new Document("_id", empresa.getCnpj()).append("Nome da Empresa", empresa.getNome()).append("Email", empresa.getEmail()).append("Site", empresa.getSite())
                    .append("Telefone", empresa.getTelefones().toString()).append("Endereço", empresa.getEnderecos().toString()));
            dadosEmpresa.close();
        }
    }
}
