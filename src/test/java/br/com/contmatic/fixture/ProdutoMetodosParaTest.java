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

import br.com.contmatic.empresa.Produto;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ProdutoMetodosParaTest {

    public static Produto produto(String argumento) {
        Fixture.of(Produto.class).addTemplate("validos", new Rule() {
            {
                add("tipo", random(randomAlphabetic(1, 50), randomAscii(1, 50)));
                add("descricao", random(randomAlphabetic(10, 60), randomAscii(10, 60)));
                add("codigo", randomNumeric(5, 30));
                add("preco", random(new BigDecimal(randomNumeric(1, 50))));
            }

        });

        Fixture.of(Produto.class).addTemplate("tipoInvalidoPeloTamanhoMenor").inherits("validos", new Rule() {
            {
                add("tipo", randomAlphabetic(1));

            }
        });

        Fixture.of(Produto.class).addTemplate("tipoInvalidoPeloTamanhoMaior").inherits("validos", new Rule() {
            {
                add("tipo", randomAlphabetic(51, 100));

            }
        });

        Fixture.of(Produto.class).addTemplate("tipoNull").inherits("validos", new Rule() {
            {
                add("tipo", null);

            }
        });
        Fixture.of(Produto.class).addTemplate("descricaoInvalidoPeloTamanhoMenor").inherits("validos", new Rule() {
            {
                add("descricao", randomAlphabetic(1, 9));

            }
        });

        Fixture.of(Produto.class).addTemplate("descricaoInvalidoPeloTamanhoMaior").inherits("validos", new Rule() {
            {
                add("descricao", randomAlphabetic(61, 100));

            }
        });

        Fixture.of(Produto.class).addTemplate("descricaoNull").inherits("validos", new Rule() {
            {
                add("descricao", null);

            }
        });

        Fixture.of(Produto.class).addTemplate("codigoInvalido").inherits("validos", new Rule() {
            {
                add("codigo", random(randomNumeric(31, 100), randomNumeric(1, 4)));

            }
        });

        Fixture.of(Produto.class).addTemplate("codigoNull").inherits("validos", new Rule() {
            {
                add("codigo", null);

            }
        });

        Fixture.of(Produto.class).addTemplate("quantidadeInvalida").inherits("validos", new Rule() {
            {
                add("quantidade", random(range(51, 1000), range(-10, 0)));

            }
        });

        Fixture.of(Produto.class).addTemplate("quantidadeNull").inherits("validos", new Rule() {
            {
                add("quantidade", null);

            }
        });

        Fixture.of(Produto.class).addTemplate("precoInvalido").inherits("validos", new Rule() {
            {
                add("preco", random(new BigDecimal("-" + randomNumeric(10))));
            }
        });
        
        Fixture.of(Produto.class).addTemplate("precoNull").inherits("validos", new Rule() {
            {
                add("preco", null);
            }
        });
        Fixture.of(Produto.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("tipo", random(randomAlphabetic(51, 100), randomAscii(51, 100)));
                add("descricao", random(randomAlphabetic(61, 100), randomAscii(61, 100)));
                add("codigo", random(randomNumeric(31, 100), randomNumeric(1, 4)));
                add("quantidade", random(range(51, 1000), range(-10, 0)));
                add("preco", random(new BigDecimal("-" + randomNumeric(10))));
            }
        });
        return Fixture.from(Produto.class).gimme(argumento);
    }

    public Set<String> validador(String argumento) {
        Produto produto = produto(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Produto>> erros = validador.validate(produto);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
