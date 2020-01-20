package br.com.contmatic.empresa;
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class ClienteTest {
	
	Cliente c=new Cliente();
	
	@Test
	public void deve_retornar_valores_do_construtor() {
		Cliente c= new Cliente("Joao", "13213213214","921216457", new Endereco("Rua bobos", "0", "9111212"));
		assertTrue(c.getNome().matches("Joao"));
		assertTrue(c.getCpf().matches( "13213213214"));
		assertTrue(c.getEndereco().getEndereco() == "Rua bobos");
		assertTrue(c.getEndereco().getNumero() == "0");
		assertTrue(c.getEndereco().getCep() == "9111212");
		assertTrue(c.getTel() == "921216457");
	}

}
