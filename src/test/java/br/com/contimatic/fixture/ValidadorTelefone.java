package br.com.contimatic.fixture;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Telefone;
import br.com.contmatic.enums.TelefoneType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorTelefone implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Telefone.class).addTemplate("validos", new Rule() {
            {
                add("numero", random("11986325477", "21954236188", "(15)36512455", "(69)63542748","(75)986235426","81986542399","(95)69654875"));
                add("tipo", random(TelefoneType.CELULAR, TelefoneType.FIXO));
            }
        });

        Fixture.of(Telefone.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("numero", random("20965845","2398754264455","259865423lk","26954236188","2963542748","3063542748","3663542748","3963542748","4063542748","5263542748","5663542748","5763542748","5863542748","5963542748","6063542748","7063542748","7263542748","7663542748","7863542748","8063542748","9063542748"));
            }
        });

    }

    public Set<String> validador(String argumento) {
        Telefone telefone = Fixture.from(Telefone.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Telefone>> erros = validador.validate(telefone);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }

}
