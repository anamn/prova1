package br.com.contmatic.easy.random.atributos;

import java.util.Arrays;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class LogradouroEasyRandom implements Randomizer<String> {

    private java.util.List<String> logradouro = Arrays.asList("Rua Elena", "Rua Jacarandá", "Av Ipiranga", "av paulista", "RUA JAPÃO");

    @Override
    public String getRandomValue() {
        return logradouro.get(new Random().nextInt(2));
    }

}
