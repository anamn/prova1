import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Test;

import br.com.contmatic.empresa.Empresa;


public class TestaEmpresa {
	
	Empresa a=new Empresa();
	
	
	
	@Test
	public void testeNome() {
		a.setNome("ana");
		assertTrue(a.getNome().matches("ana"));

	}
	
	@Test
	public void testeCnpj() {
		a.setCnpj(109);
		assertThat(a.getCnpj(), Matchers.is(109));
	
	}
	
	public void testeLucro() {
		assert ();
	}
	
	
	
	
}
