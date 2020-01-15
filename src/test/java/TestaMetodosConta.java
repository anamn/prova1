
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import br.com.contimatic.classes.Conta;
import br.com.contimatic.erros.SaldoInsuficiente;

public class TestaMetodosConta {
	
	@Parameter
	double saldo = 10;
	double limite=10;
	Conta e;
	double valor =5;
	
	@Before
	@Test (expected = SaldoInsuficiente.class)
	public void saca() {
		if (valor <= this.limite || valor <= this.saldo) {
			this.saldo -= valor;
			System.out.println("Saldo após saque: " + this.saldo);
		}
		System.out.println("Saldo insuficiente para saque");
	}
	@After
	@Test(timeout = 30)
	public void deposita() {
		this.saldo += valor;
		System.out.println("Saldo após deposito " + ":" + this.saldo);
	}

	@Ignore
	@Test
	public void transfere() {
		this.saca();
		if (valor <= this.limite || valor <= this.saldo)
			this.deposita();
	}

	
	@Test
	public void addConta() {
		List<Conta> contas = new ArrayList<Conta>();
		contas.add(e);
	}

}
