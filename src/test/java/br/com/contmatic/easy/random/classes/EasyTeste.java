package br.com.contmatic.easy.random.classes;


import java.util.HashSet;
import java.util.Set;

import org.jeasy.random.EasyRandom;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.randomicos.GeradorCpf;
import br.com.contmatic.telefone.Telefone;

public class EasyTeste {
    public static void main(String[] args) {
      
        Set<Telefone> telefones= new HashSet<Telefone>();
        Telefone tel=new EasyRandom(TelefoneEasyRandomParametros.telefone()).nextObject(Telefone.class);
        Telefone tel1=new EasyRandom(TelefoneEasyRandomParametros.telefone()).nextObject(Telefone.class);
        telefones.add(tel);
        telefones.add(tel1);
        Cliente easyCli = new EasyRandom(ClienteEasyRandomParametros.cliente()).nextObject(Cliente.class);
        easyCli.setCpf(new EasyRandom().nextObject(GeradorCpf.class).getCpfValido());
        easyCli.setTelefones(telefones);
        easyCli.setEndereco(new EasyRandom(EnderecoEasyRandomParametros.endereco()).nextObject(Endereco.class));
        System.out.println(easyCli);

    }
    
}
