package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.empresa.Cliente;

public class ClienteEasyRandomParametros {

    public static EasyRandomParameters teste() {
        return new EasyRandomParameters().randomize(FieldPredicates.named("nome").and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Cliente.class)), new NomeEasyRandom()).randomize(FieldPredicates.named("email").and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Cliente.class)), new EmailEasyRandom());
    }
    public static void main(String[] args) {
        System.out.println(new EasyRandom(ClienteEasyRandomParametros.teste()).nextObject(Cliente.class));
    }
}
