package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerCorsi {

	@RequestMapping("Utenti/Corsi/uncinetto")
	public String uncinetto() {
		int i;
		/*
		 * TODO:recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/uncinetto/"+ i;
	}
	
	@RequestMapping("Utenti/Corsi/falegnameria")
	public String falegnameria() {
		int i;
		/*
		 * TODO:recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/falegnameria/"+ i;
	}
	
	@RequestMapping("Utenti/Corsi/cucina")
	public String cucina() {
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
		String corso = req.getParameter("corso");
		String pagina = req.getParameter("pagina");
		String risultato = "Utenti/Corsi/" + corso + "/" + pagina;
		
		/*
		 * TODO: aggiorna ultima pagina visitata
		 */
		
		return risultato;
	}
}
