package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CpfEasyRandom;
import br.com.contmatic.easy.random.atributos.EmailEasyRandom;
import br.com.contmatic.easy.random.atributos.NomePessoaEasyRandom;
import br.com.contmatic.empresa.Cliente;

public class ClienteEasyRandomParametros {

    public static EasyRandomParameters parametrosCliente() {
        return new EasyRandomParameters().randomize(named("nome").and(ofType(String.class)).and(inClass(Cliente.class)), new NomePessoaEasyRandom())
                .randomize(named("email").and(ofType(String.class)).and(inClass(Cliente.class)), new EmailEasyRandom())
                .randomize(named("cpf").and(ofType(String.class)).and(inClass(Cliente.class)), new CpfEasyRandom());
    }
}
