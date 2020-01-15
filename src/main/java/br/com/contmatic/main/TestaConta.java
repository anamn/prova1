package br.com.contmatic.main;

import br.com.contimatic.classes.Cliente;
import br.com.contimatic.classes.Conta;

public class TestaConta {
	public static void main(String[] args) {
//Criando Contas
		Conta c1 = new Conta(new Cliente("Ana", "2314"), 2000, 3000);
		Conta c2 = new Conta(new Cliente("Maria", "16271"), 2500, 4000);
		Conta c3 = new Conta(new Cliente("Joao", "19281"), 2000, 5000);
		Conta c4 = new Conta(new Cliente("Joao", "19281"), 2000, 5000);

//Usando metodos
		c1.deposita(500);
		c3.tranfere(c2, 5000);
		System.out.println(c1.equals(c2));
		System.out.println(c3.hashCode());
		System.out.println(c1);
	
		}
}
