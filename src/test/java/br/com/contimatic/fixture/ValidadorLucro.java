package br.com.contimatic.fixture;

import java.math.BigDecimal;
import java.time.Month;
import org.joda.time.YearMonth;

import com.google.common.base.Preconditions;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.contmatic.empresa.Lucro;
import br.com.contmatic.enums.Moeda;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ValidadorLucro implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Lucro.class).addTemplate("validos", new Rule() {
            {
                add("investimento", random(new BigDecimal("4500"), new BigDecimal("7200"), new BigDecimal("1500")));
                add("renda", random(new BigDecimal("45000"), new BigDecimal("72500"), new BigDecimal("15100")));
                add("moeda", random(Moeda.EURO, Moeda.DOLLAR, Moeda.REAL));
                add("mes", random(new YearMonth(2019, Month.DECEMBER.getValue()), new YearMonth(2020, Month.JANUARY.getValue()), new YearMonth(2020, Month.FEBRUARY.getValue())));
            }
        });

        Fixture.of(Lucro.class).addTemplate("investimentoInvalido").inherits("validos", new Rule() {
            {
                add("investimento", random(new BigDecimal("0"), new BigDecimal("-70")));
            }
        });

        Fixture.of(Lucro.class).addTemplate("rendaInvalida").inherits("validos", new Rule() {
            {
                add("renda", random(new BigDecimal("0"), new BigDecimal("-70")));
            }
        });

        Fixture.of(Lucro.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("mes",
                    random(new YearMonth(2020, Month.MARCH.getValue()), new YearMonth(2020, Month.APRIL.getValue()), new YearMonth(2020, Month.MAY.getValue()),
                        new YearMonth(2020, Month.JUNE.getValue()), new YearMonth(2020, Month.JULY.getValue()), new YearMonth(2020, Month.AUGUST.getValue()),
                        new YearMonth(2020, Month.SEPTEMBER.getValue()), new YearMonth(2020, Month.OCTOBER.getValue()), new YearMonth(2020, Month.NOVEMBER.getValue()),
                        new YearMonth(2020, Month.DECEMBER.getValue())));
            }
        });

        Fixture.of(Lucro.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("investimento", random(new BigDecimal("0"), new BigDecimal("-70")));
                add("renda", random(new BigDecimal("0"), new BigDecimal("-70")));
                add("mes",
                    random(new YearMonth(2020, Month.MARCH.getValue()), new YearMonth(2020, Month.APRIL.getValue()), new YearMonth(2020, Month.MAY.getValue()),
                        new YearMonth(2020, Month.JUNE.getValue()), new YearMonth(2020, Month.JULY.getValue()), new YearMonth(2020, Month.AUGUST.getValue()),
                        new YearMonth(2020, Month.SEPTEMBER.getValue()), new YearMonth(2020, Month.OCTOBER.getValue()), new YearMonth(2020, Month.NOVEMBER.getValue()),
                        new YearMonth(2020, Month.DECEMBER.getValue())));
            }
        });

    }

    public Set<String> validador(String argumento) {
        Lucro lucro = Fixture.from(Lucro.class).gimme(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Lucro>> erros = validador.validate(lucro);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;
    }
}
