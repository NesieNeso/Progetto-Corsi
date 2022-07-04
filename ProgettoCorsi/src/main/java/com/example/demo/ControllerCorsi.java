package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerCorsi {
	private String corso="";

	@RequestMapping("Utenti/Corsi/uncinetto")
	public String uncinetto() {
		this.corso="uncinetto";
		int i;
		/*
		 * TODO:recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/uncinetto/"+ i;
	}
	
	@RequestMapping("Utenti/Corsi/falegnameria")
	public String falegnameria() {
		this.corso="falegnameria";
		int i;
		/*
		 * TODO:recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/falegnameria/"+ i;
	}
	
	@RequestMapping("Utenti/Corsi/cucina")
	public String cucina() {
		this.corso="cucina";
		int i;
		/*
		 * TODO:recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/cucina/"+ i;
	}
	
	
	//Calcola la prossima pagina, utilizzando i parametri corso e pagina
	@RequestMapping("/nextPage")
	public String nextPage(HttpServletRequest req) {
		String pagina = req.getParameter("pagina");
		String risultato = "Utenti/Corsi/" + corso + "/" + pagina;
		
		/*
		 * TODO: aggiorna ultima pagina visitata
		 * usa session per prendere id e salvalo.
		 */
		
		return risultato;
	}
}
