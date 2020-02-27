package br.com.contmatic.easy.random.atributos;

import java.util.Arrays;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class BairroEasyRandom implements Randomizer<String>{
    private java.util.List<String> bairro = Arrays.asList("Vila industrial", "VILA ROSA", "Freguesia do ó", "Aricanduva", "Consolação");

    @Override
    public String getRandomValue() {
        return bairro.get(new Random().nextInt(2));
    }
}
