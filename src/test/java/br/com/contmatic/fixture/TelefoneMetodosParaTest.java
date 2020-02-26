package br.com.contmatic.fixture;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.jeasy.random.EasyRandom;

import com.google.common.base.Preconditions;

import br.com.contmatic.telefone.Telefone;
import nl.flotsam.xeger.Xeger;

public class TelefoneMetodosParaTest {
    
    public static Telefone telefone(String string) {
    EasyRandom easy= new EasyRandom();
    Telefone easyTel = easy.nextObject(Telefone.class);
    Xeger geradorNumero= new Xeger(string);
    easyTel.setNumero(geradorNumero.generate());
    return easyTel;
    
    }
    
    public static Set<String> validador(Telefone telefone) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Telefone>> erros = validador.validate(telefone);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;
 
    }

}
