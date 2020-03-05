package br.com.contmatic.easy.random.classes;

import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandomParametros.parametrosTelefone;
import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.util.HashSet;
import java.util.Set;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CpfEasyRandom;
import br.com.contmatic.easy.random.atributos.EmailEasyRandom;
import br.com.contmatic.easy.random.atributos.NomePessoaEasyRandom;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

public class ClienteRandomico {

    private static EasyRandomParameters parametrosCliente() {
        return new EasyRandomParameters().randomize(named("nome").and(ofType(String.class)).and(inClass(Cliente.class)), new NomePessoaEasyRandom())
                .randomize(named("email").and(ofType(String.class)).and(inClass(Cliente.class)), new EmailEasyRandom())
                .randomize(named("cpf").and(ofType(String.class)).and(inClass(Cliente.class)), new CpfEasyRandom());
    }

    public static Cliente clienteValido() {
        Cliente cliente = new EasyRandom(parametrosCliente()).nextObject(Cliente.class);
        Endereco endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        Telefone telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(telefone);
        cliente.setEndereco(endereco);
        cliente.setTelefones(telefones);
        return cliente;
    }
}
