package br.com.contmatic.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.randomicos.GeradorCpf;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ValidadorCliente {

    static GeradorCpf gerador = new GeradorCpf();

    public static Cliente cliente(String argumento) {
        Fixture.of(Cliente.class).addTemplate("validos", new Rule() {
            {
                add("nome", firstName());
                add("email", random("cliente@gmail.com", "clien_te@hotmail.com", "cliente3@ig.com.br", "clinte3@contmatic.com.br"));
                add("cpf", gerador.getCpfValido());
            }
        });

        Fixture.of(Cliente.class).addTemplate("nomeInvalido").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(1), randomAlphabetic(52)));
            }
        });

        Fixture.of(Cliente.class).addTemplate("nomeNull").inherits("validos", new Rule() {
            {
                add("nome", null);
            }
        });

        Fixture.of(Cliente.class).addTemplate("nomeValidoComEspaço").inherits("validos", new Rule() {
            {
                add("nome", name());
            }
        });

        Fixture.of(Cliente.class).addTemplate("nomeInvalidoComNumero").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(9) + randomNumeric(5)));
            }
        });

        Fixture.of(Cliente.class).addTemplate("nomeInvalidoComEspeciais").inherits("validos", new Rule() {
            {
                add("nome", randomAlphabetic(9) + random("?", ":", "&", "!", "@", "#", "$", "¨¨", "*"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("cpfInvalido").inherits("validos", new Rule() {
            {
                add("cpf", gerador.getCpfInvalido());
            }
        });

        Fixture.of(Cliente.class).addTemplate("cpfTamanhoInvalido").inherits("validos", new Rule() {
            {
                add("cpf", random(randomNumeric(1, 18), randomAscii(1, 12)));
            }
        });
        Fixture.of(Cliente.class).addTemplate("cpfNull").inherits("validos", new Rule() {
            {
                add("cpf", null);
            }
        });

        Fixture.of(Cliente.class).addTemplate("emailInvalido").inherits("validos", new Rule() {
            {
                add("email", random("clientehotmail.com", "allep", "cliente.com", "cliente @hotmail.com", "CLIENTE@HOTMAIL.COM", "cliente@hotmail.", "cliente@3873.com.br"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("emailNull").inherits("validos", new Rule() {
            {
                add("email", null);
            }
        });

        Fixture.of(Cliente.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(1), randomAlphabetic(52), randomAscii(5), randomAscii(58)));
                add("cpf", random(randomNumeric(1, 11), randomAscii(1, 11)));
                add("email", random("clientehotmail.com", "ajjisjw"));
            }
        });
        return Fixture.from(Cliente.class).gimme(argumento);
    }

    public Set<String> validador(String argumento) {
        Cliente cliente = cliente(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Cliente>> erros = validador.validate(cliente);
        Set<String> erros2 = new TreeSet<String>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }

}