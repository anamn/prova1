package br.com.contimatic.fixture;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.enums.EnderecoType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorEndereco implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Endereco.class).addTemplate("validos", new Rule() {
            {
                add("endereco", random("Rua tijuco", "Rua jacaranda", "Av dos estados"));
                add("numero", random("41", "31A", "1"));
                add("cep", random("91829282", "18182928", "18292837"));
                add("enderecoType", random(EnderecoType.APARTAMENTO, EnderecoType.CASA, EnderecoType.COMENCIAL));

            }
        });

        Fixture.of(Endereco.class).addTemplate("enderecoInvalido").inherits("validos", new Rule() {
            {
                add("endereco", random("Rua ti526co", "Makokahjkutrilohginimikiuhjkjhgtfrdeslsjksyuijnbvgfcdria"));
            }
        });

        Fixture.of(Endereco.class).addTemplate("numeroInvalido").inherits("validos", new Rule() {
            {
                add("numero", random("3154542125151515487845465487A"));

            }
        });

        Fixture.of(Endereco.class).addTemplate("cepInvalido").inherits("validos", new Rule() {
            {
                add("cep", random("918292", "18182954528", "182aj837"));

            }
        });
        Fixture.of(Endereco.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("endereco", random("Rua ti526co", "Makokhjkutrilohginimikiuhjkjhgtfrdeslsjksyuijnbvgfcdria"));
                add("numero", random("31545421555525151515487845465487A"));
                add("cep", random("918292", "18182954528", "182aj837"));

            }
        });

    }

    public Set<String> validador(String argumento) {
        Endereco endereco = Fixture.from(Endereco.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Endereco>> erros = validador.validate(endereco);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
