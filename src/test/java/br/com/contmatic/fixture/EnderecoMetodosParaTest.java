package br.com.contmatic.fixture;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.jeasy.random.EasyRandom;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import nl.flotsam.xeger.Xeger;

public class EnderecoMetodosParaTest {

    public static Endereco enderecoValido() {
        Endereco endereco = new EasyRandom().nextObject(Endereco.class);
        Xeger geradorEndereco = new Xeger("[A-Z][aA-zZáÁ-úÚ\\s]{5,40}");
        Xeger geradorCep = new Xeger("([0-9]{5}-?[0-9]{3})");
        Xeger geradorNumero = new Xeger("[aA-zZ0-9]{1,20}");
        endereco.setEndereco(geradorEndereco.generate());
        endereco.setNumero(geradorNumero.generate());
        endereco.setCep(geradorCep.generate());
        return endereco;

    }

    public static Set<String> validador(Endereco endereco) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Endereco>> erros = validador.validate(endereco);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
