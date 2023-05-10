package br.com.proj.enviarEmail;

import org.junit.Test;


public class AppTest {

	
	@Test
	public void testeEmail() throws Exception {

		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("Eu estou aprendendo java. <br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso ao projeto click no botão Abaixo. <br/><br/>");
		stringBuilderTextoEmail.append
		("<a target=\"_blank\" href=\"https://github.com/ReinaldoCaetano/javaEmail-maven\" style=\"color:purple;padding: 14px 25px;"
				+ "text-aling:center;text-decoration:none;display-inline:block;"
				+ "border-radius:25px;font-size:20px;border:1px solid purple; "
				+ "	\">Acessar GitHub do Projeto </a><br/><br/>");
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\">Reinaldo Vitor Caetano</span><br/>");
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\">E-mail: reinaldo.caetano@fatec.sp.gov.br</span>");
		
		
		EnviaEmail enviaEmail = new EnviaEmail("reinaldo.caetano@fatec.sp.gov.br", "Reinaldo Developer Java"
				, "Testando Assunto Email", stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmail(true);
		
		
	}
}
