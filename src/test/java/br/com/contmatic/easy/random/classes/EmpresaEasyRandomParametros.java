package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CnpjEasyRandom;
import br.com.contmatic.easy.random.atributos.EmailEasyRandom;
import br.com.contmatic.easy.random.atributos.NomeEmpresaEasyRandom;
import br.com.contmatic.easy.random.atributos.SiteEasyRandom;
import br.com.contmatic.empresa.Empresa;

public class EmpresaEasyRandomParametros {

    public static EasyRandomParameters parametrosEmpresa() {
        return new EasyRandomParameters().randomize(named("nome").and(ofType(String.class)).and(inClass(Empresa.class)), new NomeEmpresaEasyRandom())
                .randomize(named("cnpj").and(ofType(String.class)).and(inClass(Empresa.class)), new CnpjEasyRandom())
                .randomize(named("email").and(ofType(String.class)).and(inClass(Empresa.class)), new EmailEasyRandom())
                .randomize(named("site").and(ofType(String.class)).and(inClass(Empresa.class)), new SiteEasyRandom());
    }
    
    public static void main(String[] args) {
        System.out.println(new EasyRandom(parametrosEmpresa()).nextObject(Empresa.class));
    }
}
