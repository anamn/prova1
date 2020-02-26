package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.telefone.Telefone;

public class TelefoneEasyRandomParametros {

    public static EasyRandomParameters telefone() {
        return new EasyRandomParameters().randomize(FieldPredicates.named("numero").and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Telefone.class)), new NumeroTelefoneEasyRandom());
    }
    
    public static void main(String[] args) {
        System.out.println(new EasyRandom(TelefoneEasyRandomParametros.telefone()).nextObject(Telefone.class));
    }
}
