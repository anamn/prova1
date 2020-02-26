package br.com.contmatic.fixture;


import static br.com.contmatic.fixture.TelefoneMetodosParaTest.telefone;

import java.util.HashSet;
import java.util.Set;

import org.jeasy.random.EasyRandom;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.randomicos.GeradorCpf;
import br.com.contmatic.telefone.Telefone;
import nl.flotsam.xeger.Xeger;

public class EasyTeste {
    public static void main(String[] args) {
      
        Set<Telefone> telefones= new HashSet<Telefone>();
        telefones.add(telefone("([9]?[0-9]{4}-?[0-9]{4})"));
        telefones.add(telefone("([9]?[0-9]{4}-?[0-9]{4})"));
        telefones.add(telefone("([9]?[0-9]{4}-?[0-9]{4})"));
        
        Xeger gerador= new Xeger("([a-z0-9-_.]{5,20}[@][a-z]{2,9}[.][a-z]{2,5}[.a-z]?)|([a-z]{5,20}[@][a-z]{2,9}[.][a-z]{2,5}[.a-z]?)");
        Cliente easyCli = new EasyRandom().nextObject(Cliente.class);
        easyCli.setCpf(new EasyRandom().nextObject(GeradorCpf.class).getCpfValido());
        easyCli.setEmail(gerador.generate());
        easyCli.setTelefones(telefones);
        easyCli.setNome(geradorNome.generate());
        easyCli.setEndereco(EnderecoMetodosParaTest.enderecoValido());
        System.out.println(easyCli);

    }
    
}
