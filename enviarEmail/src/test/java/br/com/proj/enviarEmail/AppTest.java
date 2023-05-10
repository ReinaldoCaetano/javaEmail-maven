package br.com.proj.enviarEmail;

import org.junit.Test;


public class AppTest {

	
	@Test
	public void testeEmail() throws Exception {

		
		EnviaEmail enviaEmail = new EnviaEmail("reinaldo.caetano@fatec.sp.gov.br, reinaldovcaetano@gmail.com", "Reinaldo Developer Java"
				, "Testando Assunto Email", "Esse é o texto do email para teste");
		
		enviaEmail.enviarEmail();
		
		
	}
}
