package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class NomeEmpresaEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new Xeger("[aA-zZáÁ-úÚ0-9\\s]{2,50}").generate();
    }

}
