package br.com.contimatic.fixture;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Cliente;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorCliente implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Cliente.class).addTemplate("validos", new Rule() {
            {
                add("nome", random("João", "Amelia", "Maria", "Zé", "Carlos", "Thaina"));
                add("email", random("joao@gmail.com", "amelia@hotmail.com", "carlos@ig.com"));
                add("cpf", random("49022518841", "32179960387", "490.225.188-41"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("nomeInvalido").inherits("validos", new Rule() {
            {
                add("nome", random("João23", "Makokhjkutrilohginimikiuhjkjhgtfrdeswa44qhyuijnbvgfdcdria", "C12los"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("cpfInvalido").inherits("validos", new Rule() {
            {
                add("cpf", random("49022554256", "32179930125", "1234567:>45", "79745307624", "41023752loj3"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("emailInvalido").inherits("validos", new Rule() {
            {
                add("email", random("clientehotmail.com", "ajjisjw"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("nome", random("João23", "Makokhjkutrilohginimikiuhjkjhgtfrdeswa44qhyuijnbvgfcdria", "C12los"));
                add("cpf", random("49022554256", "32179930125", "1234567:>45", "79745307624", "41023752loj3"));
            }
        });

    }

    public Set<String> validador(String argumento) {
        Cliente cliente = Fixture.from(Cliente.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Cliente>> erros = validador.validate(cliente);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
