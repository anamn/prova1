package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.math.BigDecimal;

import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CpfEasyRandom;
import br.com.contmatic.easy.random.atributos.NomePessoaEasyRandom;
import br.com.contmatic.easy.random.atributos.PisEasyRandom;
import br.com.contmatic.easy.random.atributos.ValoresEasyRandom;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioEasyRandomParametros {

    public static EasyRandomParameters parametrosFuncionario() {
        return new EasyRandomParameters().randomize(named("salario").and(ofType(BigDecimal.class)).and(inClass(Funcionario.class)), new ValoresEasyRandom())
                .randomize(named("nome").and(ofType(String.class)).and(inClass(Funcionario.class)), new NomePessoaEasyRandom())
                .randomize(named("cpf").and(ofType(String.class)).and(inClass(Funcionario.class)), new CpfEasyRandom())
                .randomize(named("pis").and(ofType(String.class)).and(inClass(Funcionario.class)), new PisEasyRandom());
    }
}
