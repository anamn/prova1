package br.com.contmatic.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ValidadorEmpresa {

    public static Empresa empresa(String argumento) {
        Fixture.of(Empresa.class).addTemplate("validos", new Rule() {
            {
                add("nome", firstName());
                add("cnpj", cnpj());
                add("email", "{nome}@gamil.com");
                add("site", random("http://{nome}.com.br"));
            }
        });

        Fixture.of(Empresa.class).addTemplate("nomeInvalido").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(52), randomAscii(58)));
            }
        });
        Fixture.of(Empresa.class).addTemplate("cnpjInvalido").inherits("validos", new Rule() {
            {
                add("cnpj", random(randomAlphanumeric(14), randomAscii(14), randomNumeric(5, 20), null));
            }
        });

        Fixture.of(Empresa.class).addTemplate("emailInvalido").inherits("validos", new Rule() {
            {
                add("email", random("empresahotmail.com", "ajjisjw", null));
            }
        });

        Fixture.of(Empresa.class).addTemplate("siteInvalido").inherits("validos", new Rule() {
            {
                add("site", random("onixbr", ".com.br"));
            }
        });

        Fixture.of(Empresa.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("nome", random(randomAlphabetic(1), randomAlphabetic(51), randomAscii(5), null));
                add("cnpj", random(randomAlphanumeric(14), randomAscii(14), randomNumeric(5, 20), null));
                add("email", random("empresahotmail.com", "ajjisjw", null));
                add("site", random("onixbr", ".com.br"));
            }
        });
        
        return Fixture.from(Empresa.class).gimme(argumento);

    }

    public Set<String> validador(String argumento) {
        Empresa empresa = empresa(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Empresa>> erros = validador.validate(empresa);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
