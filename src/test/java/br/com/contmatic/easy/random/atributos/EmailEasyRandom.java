package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class EmailEasyRandom implements Randomizer<String>{

    @Override
    public String getRandomValue() {
        return new Xeger("([a-z0-9-_.]{5,20}[@][a-z]{2,9}[.][a-z]{2,5}[.a-z]?)|([a-z]{5,20}[@][a-z]{2,9}[.][a-z]{2,5}[.][a-z]?)").generate();
    }

}
