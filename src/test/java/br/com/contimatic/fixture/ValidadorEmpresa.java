package br.com.contimatic.fixture;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorEmpresa implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Empresa.class).addTemplate("validos", new Rule() {
            {
                add("nome", random("Onix", "Nascimento", "Contmatic"));
                add("cnpj", random("02828446000134", "72610132000146", "26159125000152"));
                add("email", random("ana@gmail.com", "onix@hotmail.com", "nascimento@ig.com"));
                add("site", random("http://onix.com.br", "http://nascimento.com.br", "http://contmatic.com.br"));
            }
        });

        Fixture.of(Empresa.class).addTemplate("nomeInvalido").inherits("validos", new Rule() {
            {
                add("nome", random("João23", "Makokhjkutrilohginimikiuhjkjhgtfrdeswa44qhyuijnbvgfcdria", "C12los"));
            }
        });
        Fixture.of(Empresa.class).addTemplate("cnpjInvalido").inherits("validos", new Rule() {
            {
                add("cnpj", random("49022554256452", "321754859930125", "1234567:>45", "79745307624", "41023752loj3"));
            }
        });

        Fixture.of(Empresa.class).addTemplate("emailInvalido").inherits("validos", new Rule() {
            {
                add("email", random("empresahotmail.com", "ajjisjw"));
            }
        });

        Fixture.of(Empresa.class).addTemplate("siteInvalido").inherits("validos", new Rule() {
            {
                add("site", random("onixbr", ".com.br"));
            }
        });

        Fixture.of(Empresa.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("nome", random("João23", "Makokhjkutrilohginimikiuhjkjhgtfrdeswa44qhyuijnbvgfcdria", "C12los"));
                add("cnpj", random("49022554256452", "321754859930125", "1234567:>45", "79745307624", "41023752loj3"));
                add("email", random("empresahotmail.com", "ajjisjw"));
                add("site", random("onixbr", ".com.br"));
            }
        });

    }

    public Set<String> validador(String argumento) {
        Empresa empresa = Fixture.from(Empresa.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Empresa>> erros = validador.validate(empresa);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
