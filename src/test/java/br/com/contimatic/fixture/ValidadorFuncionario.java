package br.com.contimatic.fixture;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Funcionario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorFuncionario implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(Funcionario.class).addTemplate("validos", new Rule() {
            {
                add("nome", random("João", "Amelia", "Maria", "Zé", "Carlos", "Thaina"));
                add("cpf", random("49022518841", "32179960387"));
                add("pis", random("15487236597", "78945682135", "47596123588"));
                add("salario", random(new BigDecimal("4500"), new BigDecimal("7200"), new BigDecimal("1500")));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("nomeInvalido").inherits("validos", new Rule() {
            {
                add("nome", random("João23", "Makokhjkutrilohginimikiuhjkjhgtfrdeswa44qhyuijnbvgfdcdria", "C12los"));

            }
        });
        
        Fixture.of(Funcionario.class).addTemplate("cpfInvalido").inherits("validos", new Rule() {
            {
                add("cpf", random("49022554256", "32179930125", "1234567:>45", "79745307624", "41023752loj3"));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("pisInvalido").inherits("validos", new Rule() {
            {
                add("pis", random(null, "789456821354555", "47596358"));

            }
        });

        Fixture.of(Funcionario.class).addTemplate("salarioInvalido").inherits("validos", new Rule() {
            {
                add("salario", random(new BigDecimal("-540"), new BigDecimal("0")));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("nome", random("João23", "Makokhjkutrilohginimikiuhjkjhgtfrdeswa44qhyuijnbvgfcdria", "C12los"));
                add("cpf", random("49022554256", "32179930125", "1234567:>45", "79745307624", "41023752loj3"));
                add("pis", random(null, "789456821354555", "47596358"));
                add("salario", random(new BigDecimal("-540"), new BigDecimal("0")));
            }
        });

    }

    public Set<String> validador(String argumento) {
        Funcionario funcionario= Fixture.from(Funcionario.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Funcionario>> erros = validador.validate(funcionario);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
