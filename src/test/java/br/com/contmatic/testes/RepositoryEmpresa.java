package br.com.contmatic.testes;

import static br.com.contmatic.easy.random.classes.LucroEasyRandomParametros.parametrosLucro;

import org.bson.Document;
import org.jeasy.random.EasyRandom;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.easy.random.classes.EmpresaEasyRandomParametros;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.financeiro.Lucro;

public class RepositoryEmpresa {
    @Test
    public void teste() {

        MongoClient dadosEmpresa = new MongoClient("localhost");
        MongoDatabase bancoDeDados = dadosEmpresa.getDatabase("Prova3");
        MongoCollection<Document> empresas = bancoDeDados.getCollection("Empresa");
        for(int i = 0 ; i < 10 ; i++) {
            empresas.insertOne(criaDocumentoEmpresa());
        }
    }

    public static Document criaDocumentoEmpresa() {
        Empresa empresa;
        empresa = EmpresaEasyRandomParametros.empresaValida();
        empresa.setLucro(new EasyRandom(parametrosLucro()).nextObject(Lucro.class));
        Document emp = Document.parse(empresa.toString());
        emp.append("_id", empresa.getCnpj());
        return emp;
    }
}
