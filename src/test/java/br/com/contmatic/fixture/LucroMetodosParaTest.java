package br.com.contmatic.fixture;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.joda.time.YearMonth;

import com.google.common.base.Preconditions;

import br.com.contmatic.financeiro.Lucro;
import br.com.contmatic.financeiro.Moeda;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class LucroMetodosParaTest {

    public static Lucro lucro(String argumento) {
        Fixture.of(Lucro.class).addTemplate("validos", new Rule() {
            {
                add("investimento", random(new BigDecimal(randomNumeric(1, 1000))));
                add("renda", random(new BigDecimal(randomNumeric(1, 100))));
                Moeda moeda = Moeda.values()[new Random().nextInt(Moeda.values().length)];
                add("moeda", random(moeda));
                add("mes", random(new YearMonth(2019, DECEMBER.getValue()), new YearMonth(2020, JANUARY.getValue()), new YearMonth(2020, FEBRUARY.getValue())));
            }
        });

        Fixture.of(Lucro.class).addTemplate("investimentoInvalido").inherits("validos", new Rule() {
            {
                add("investimento", random(new BigDecimal("-" + randomNumeric(5))));
            }
        });
        
        Fixture.of(Lucro.class).addTemplate("investimentoZero").inherits("validos", new Rule() {
            {
                add("investimento", new BigDecimal("0"));
            }
        });

        Fixture.of(Lucro.class).addTemplate("investimentoNull").inherits("validos", new Rule() {
            {
                add("investimento", null);
            }
        });

        Fixture.of(Lucro.class).addTemplate("rendaInvalida").inherits("validos", new Rule() {
            {
                add("renda", random(new BigDecimal("-" + randomNumeric(5))));
            }
        });
        
        Fixture.of(Lucro.class).addTemplate("rendaNull").inherits("validos", new Rule() {
            {
                add("renda", null);
            }
        });
        
        Fixture.of(Lucro.class).addTemplate("rendaZero").inherits("validos", new Rule() {
            {
                add("renda", new BigDecimal("0"));
            }
        });

        Fixture.of(Lucro.class).addTemplate("mesInvalido").inherits("validos", new Rule() {
            {
                add("mes",
                    random(new YearMonth(2020, MARCH.getValue()), new YearMonth(2020, APRIL.getValue()), new YearMonth(2020, MAY.getValue()), new YearMonth(2020, JUNE.getValue()),
                        new YearMonth(2020, JULY.getValue()), new YearMonth(2020, AUGUST.getValue()), new YearMonth(2020, SEPTEMBER.getValue()), new YearMonth(2020, OCTOBER.getValue()),
                        new YearMonth(2020, NOVEMBER.getValue()), new YearMonth(2020, DECEMBER.getValue())));
            }
        });

        Fixture.of(Lucro.class).addTemplate("invalidos").inherits("validos", new Rule() {
            {
                add("investimento", random(new BigDecimal("-" + randomNumeric(10))));
                add("renda", random(new BigDecimal("-" + randomNumeric(10))));
                add("mes",
                    random(new YearMonth(2020, Month.MARCH.getValue()), new YearMonth(2020, Month.APRIL.getValue()), new YearMonth(2020, Month.MAY.getValue()),
                        new YearMonth(2020, Month.JUNE.getValue()), new YearMonth(2020, Month.JULY.getValue()), new YearMonth(2020, Month.AUGUST.getValue()),
                        new YearMonth(2020, Month.SEPTEMBER.getValue()), new YearMonth(2020, Month.OCTOBER.getValue()), new YearMonth(2020, Month.NOVEMBER.getValue()),
                        new YearMonth(2020, Month.DECEMBER.getValue())));
            }
        });

        return Fixture.from(Lucro.class).gimme(argumento);
    }

    public Set<String> validador(String argumento) {
        Lucro lucro = lucro(argumento);
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Lucro>> erros = validador.validate(lucro);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;
    }
}
