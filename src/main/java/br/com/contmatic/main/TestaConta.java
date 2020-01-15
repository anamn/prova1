package br.com.contmatic.main;

import br.com.contimatic.classes.Cliente;
import br.com.contimatic.classes.Conta;
import br.com.contimatic.classes.ContaCorrente;
import br.com.contimatic.classes.ContaSalario;

public class TestaConta {
	public static void main(String[] args) {
//Criando Contas
		Conta c1 = new ContaCorrente(new Cliente("Ana", "2314"), 2000, 3000);
		Conta c2 = new ContaCorrente(new Cliente("Maria", "16271"), 2500, 4000);
		Conta c3 = new ContaSalario(new Cliente("Joao", "19281"), 2000, 5000);
		Conta c4 = new ContaSalario(new Cliente("Joao", "19281"), 2000, 5000);
		
//Usando metodos
		c1.deposita(500);
		c2.tranfere(c3, 50);
		c3.equals(c1);
		System.out.println(c1.equals(c2));
		System.out.println(c4.hashCode());
		System.out.println(c1);
	
		}
}
