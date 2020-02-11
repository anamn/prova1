package br.com.contmatic.empresa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteTest.class, EmpresaTest.class, EnderecoTest.class, FuncionarioTest.class, LucroTest.class,
		ProdutoTest.class, TelefoneTest.class })
public class SuiteTest {

}
