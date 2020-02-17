package br.com.contmatic.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EnderecoType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ValidadorEndereco {

    public static Endereco endereco(String argumento) {
        Fixture.of(Endereco.class).addTemplate("validos", new Rule() {
            {
                add("endereco", randomAlphabetic(5, 40));
                add("numero", randomNumeric(2, 19));
                add("cep", randomNumeric(8));
                EnderecoType enderecoType = EnderecoType.values()[new Random().nextInt(EnderecoType.values().length)];
                add("enderecoType", random(enderecoType));

            }
        });

        Fixture.of(Endereco.class).addTemplate("enderecoInvalido").inherits("validos", new Rule() {
            {
                add("endereco", random(randomAlphabetic(1, 4), randomAlphabetic(52), randomAscii(1, 4), randomAscii(58)));
            }
        });

        Fixture.of(Endereco.class).addTemplate("enderecoNull").inherits("validos", new Rule() {
            {
                add("endereco", null);
            }
        });

        Fixture.of(Endereco.class).addTemplate("enderecoValidoComEspaco").inherits("validos", new Rule() {
            {
                add("endereco", name());
            }
        });

        Fixture.of(Endereco.class).addTemplate("enderecoInvalidoNumero").inherits("validos", new Rule() {
            {
                add("endereco", random(randomAlphabetic(9) + randomNumeric(5)));
            }
        });

        Fixture.of(Endereco.class).addTemplate("enderecoInvalidoComEspeciais").inherits("validos", new Rule() {
            {
                add("endereco", randomAlphabetic(9) + random("?", ":", "&", "!", "@", "#", "$", "¨¨", "*"));
            }
        });

        Fixture.of(Endereco.class).addTemplate("numeroInvalido").inherits("validos", new Rule() {
            {
                add("numero", randomNumeric(21, 40));

            }
        });

        Fixture.of(Endereco.class).addTemplate("numeroValidoComLetra").inherits("validos", new Rule() {
            {
                add("numero", randomNumeric(21, 40) + randomAlphabetic(5));

            }
        });

        Fixture.of(Endereco.class).addTemplate("numeroNull").inherits("validos", new Rule() {
            {
                add("numero", null);

            }
        });

        Fixture.of(Endereco.class).addTemplate("cepValidoComTraco").inherits("validos", new Rule() {
            {
                add("cep", random(randomNumeric(5) + "-" + randomNumeric(3)));

            }
        });

        Fixture.of(Endereco.class).addTemplate("cepInvalido").inherits("validos", new Rule() {
            {
                add("cep", random(randomNumeric(9, 50), randomNumeric(1, 8), randomAscii(1, 5)));

            }
        });
        Fixture.of(Endereco.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("endereco", random(randomAlphabetic(6, 42), randomAscii(5, 58)));
                add("numero", randomNumeric(21, 40));
                add("cep", random(randomNumeric(9, 50), randomNumeric(1, 8), randomAscii(1, 5)));

            }
        });
        return Fixture.from(Endereco.class).gimme(argumento);
    }

    public Set<String> validador(String argumento) {
        Endereco endereco = endereco(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Endereco>> erros = validador.validate(endereco);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
