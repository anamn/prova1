import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.runners.MethodSorters;
import org.hamcrest.Matcher;
import org.junit.*;
import org.junit.runners.Parameterized.Parameter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteJUnit {

	@Parameter
	List<Double> numeros = new ArrayList<Double>();
	int velocidadeCarro = 60;
	int velocidadeRadar = 40;

	@Test(timeout = 40)
	public void bsoma() {
		Optional<Double> num = numeros.stream().reduce((n1, n2) -> n1 + n2);
		System.out.println(num);
	
	}
 
	@Test(expected = AssertionError.class)
	public void asserts() {
		int x = 0;
		assertThat(x, is(x>1 && x<8));
		assert (x == 7);
	    assertEquals(x, 7);
		boolean m = "37".matches("37");
		System.out.println("Segundo teste");
	}
	
	@Ignore
	@Test
	public void testeErro() {
		System.out.println("inicio do main");
		metodo1();
		System.out.println("fim do main");
	}

	static void metodo1() {
		System.out.println("inicio do metodo2");
		SomaDoisInteiros cc = new SomaDoisInteiros();
		try {
			for (int i = 0; i <= 15; i++) {
				cc.soma(3, 4);
				if (i == 5) {
					cc = null;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("erro "+ e);
		}

	}
}
