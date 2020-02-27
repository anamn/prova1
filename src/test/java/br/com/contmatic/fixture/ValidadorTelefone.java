package br.com.contmatic.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.telefone.Ddd;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ValidadorTelefone {

    public static Telefone telefone(String argumento) {
        Fixture.of(Telefone.class).addTemplate("validos", new Rule() {
            {
                Ddd ddd = Ddd.values()[new Random().nextInt(Ddd.values().length)];
                add("ddd", random(ddd));
                add("numero", randomNumeric(8, 9));
                TelefoneType telefoneType = TelefoneType.values()[new Random().nextInt(TelefoneType.values().length)];
                add("tipo", random(telefoneType));
            }
        });

        Fixture.of(Telefone.class).addTemplate("numeroInvalidoTamanho").inherits("validos", new Rule() {
            {
                add("numero", random(randomNumeric(0, 7), randomNumeric(10, 100)));
            }
        });
        
        Fixture.of(Telefone.class).addTemplate("numeroNull").inherits("validos", new Rule() {
            {
                add("numero", null);
            }
        });
        Fixture.of(Telefone.class).addTemplate("dddNull").inherits("validos", new Rule() {
            {
                add("ddd", null);
            }
        });
        
        Fixture.of(Telefone.class).addTemplate("tipoNull").inherits("validos", new Rule() {
            {
                add("tipo", null);
            }
        });
       return Fixture.from(Telefone.class).gimme(argumento);
    }

    public Set<String> validador(String argumento) {
        Telefone telefone = telefone(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Telefone>> erros = validador.validate(telefone);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }

}