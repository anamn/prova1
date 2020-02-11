package br.com.contimatic.fixture;

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
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorProduto implements TemplateLoader {

    @Override
    public void load() {
        
        Fixture.of(Produto.class).addTemplate("validos", new Rule() {
            {
                add("tipo", random("notbook", "celular 8", "tablet", "notbook 5c", "lkojghnuyrhtfvgtbnhyjhgfdeaswdcfvgbhnjunhtfghjk"));
                add("descricao", random("Bateria interna, preto", "Camera frontal alta qualidade", "64g de memoria", "jshdjkelirgheurjaishebdnmjfkieutghytervgyqmjshdgtfghybnjui"));
                add("codigo", random("989545", "098782736475893847589384756172"));
                add("quantidade", random(1, 2, 3, 4, 5, 6, 7, 8));
                add("preco", random(new BigDecimal("4500"), new BigDecimal("720"), new BigDecimal("1500"), new BigDecimal("2900")));
            }

        });
        Fixture.of(Produto.class).addTemplate("tipoInvalido").inherits("validos", new Rule() {
            {
                add("tipo", random("", "asdkfjieksjdkoeirororororororpritirjrirkrkrnrkrjrjrikmkhjjijkk"));

            }
        });
        Fixture.of(Produto.class).addTemplate("descricaoInvalido").inherits("validos", new Rule() {
            {
                add("descricao", random("calca", "kajkjdjjjjjjjjjjjjjjjjjjjjjjshbdksljfffhhhhhhhhhhhhhhhhhhhhhhhhaljj"));

            }
        });

        Fixture.of(Produto.class).addTemplate("codigoInvalido").inherits("validos", new Rule() {
            {
                add("codigo", random("12", "1234567891234567891234567891233", "089as"));

            }
        });

        Fixture.of(Produto.class).addTemplate("quantidadeInvalida").inherits("validos", new Rule() {
            {
                add("quantidade", random(0, -52));

            }
        });

        Fixture.of(Produto.class).addTemplate("precoInvalido").inherits("validos", new Rule() {
            {
                add("preco", random(new BigDecimal("-540"), new BigDecimal("0")));
            }
        });
        Fixture.of(Produto.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("tipo", random("", "asdkfjieksjdkoeirororororororpritirjrirkrkrnrkrjrjrikmkhjjijkk"));
                add("descricao", random("calca", "kajkjdjjjjjjjjjjjjjjjjjjjjjjshbdksljfffhhhhhhhhhhhhhhhhhhhhhhhhaljj"));
                add("codigo", random("12", "1234567891234567891234567891233", "089as"));
                add("quantidade", random(0,-52));
                add("preco", random(new BigDecimal("-540.6"), new BigDecimal("0")));
            }
        });

    }

    public Set<String> validador(String argumento) {
        Produto produto= Fixture.from(Produto.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Produto>> erros = validador.validate(produto);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
