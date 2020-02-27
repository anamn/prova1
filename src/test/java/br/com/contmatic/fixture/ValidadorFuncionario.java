package br.com.contmatic.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.randomicos.GeradorCpf;
import br.com.contmatic.randomicos.GeradorDePis;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ValidadorFuncionario {

    static GeradorDePis pis = new GeradorDePis();
    static GeradorCpf cpf = new GeradorCpf();

    public static Funcionario funcionario(String argumento) {
        Fixture.of(Funcionario.class).addTemplate("validos", new Rule() {
            {
                add("nome", firstName());
                add("cpf", cpf.getCpfValido());
                add("pis", pis.getPisValido());
                add("salario", random(new BigDecimal(randomNumeric(7))));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("nomeInvalido").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(1), randomAlphabetic(52)));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("nomeNull").inherits("validos", new Rule() {
            {
                add("nome", null);
            }
        });

        Fixture.of(Funcionario.class).addTemplate("nomeValidoComEspaço").inherits("validos", new Rule() {
            {
                add("nome", name());
            }
        });

        Fixture.of(Funcionario.class).addTemplate("nomeInvalidoComNumero").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(9) + randomNumeric(5)));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("nomeInvalidoComEspeciais").inherits("validos", new Rule() {
            {
                add("nome", randomAlphabetic(9) + random("?", ":", "&", "!", "@", "#", "$", "¨¨", "*"));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("cpfInvalido").inherits("validos", new Rule() {
            {
                add("cpf", cpf.getCpfInvalido());
            }
        });

        Fixture.of(Funcionario.class).addTemplate("cpfNull").inherits("validos", new Rule() {
            {
                add("cpf", null);
            }
        });

        Fixture.of(Funcionario.class).addTemplate("cpfTamanhoInvalido").inherits("validos", new Rule() {
            {
                add("cpf", random(randomNumeric(1, 10), randomNumeric(11, 100)));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("pisInvalido").inherits("validos", new Rule() {
            {
                add("pis", pis.getPisInvalido());

            }
        });

        Fixture.of(Funcionario.class).addTemplate("pisNull").inherits("validos", new Rule() {
            {
                add("pis", null);

            }
        });

        Fixture.of(Funcionario.class).addTemplate("pisTamanhoInvalido").inherits("validos", new Rule() {
            {
                add("pis", random(randomNumeric(1, 10), randomNumeric(11, 100)));
                ;

            }
        });

        Fixture.of(Funcionario.class).addTemplate("salarioNegativo").inherits("validos", new Rule() {
            {
                add("salario", random(new BigDecimal("-" + randomNumeric(10))));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("salarioNull").inherits("validos", new Rule() {
            {
                add("salario", null);
            }
        });

        Fixture.of(Funcionario.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(1, 51), randomAscii(5, 58), null));
                add("cpf", random(randomNumeric(1, 18), randomAscii(1, 11), null));
                add("pis", random(randomNumeric(11, 100), randomNumeric(1, 10), randomAscii(1, 11), null));
                add("salario", random(new BigDecimal("-" + randomNumeric(10))));

            }
        });
        return Fixture.from(Funcionario.class).gimme(argumento);
    }

    public Set<String> validador(String argumento) {
        Funcionario funcionario = funcionario(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Funcionario>> erros = validador.validate(funcionario);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}