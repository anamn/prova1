package br.com.contmatic.easy.random;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class NomeEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new Xeger("[A-Z]{1}[aA-zZáÁ-úÚ\\s]{2,50}").generate();
    }
    

}
