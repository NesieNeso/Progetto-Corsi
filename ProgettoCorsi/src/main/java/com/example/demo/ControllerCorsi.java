package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerCorsi {

	@RequestMapping("Utenti/Corsi/uncinetto")
	public String uncinetto() {
		int i;
		/*
		 * recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/uncinetto/"+ i;
	}
	
	@RequestMapping("Utenti/Corsi/falegnameria")
	public String falegnameria() {
		int i;
		/*
		 * recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/falegnameria/"+ i;
	}
	
	@RequestMapping("Utenti/Corsi/cucina")
	public String cucina() {
		int i;
		/*
		 * recupera int i come ultima pagina visitata
		 */
		i=0;
		return "Utenti/Corsi/cucina/"+ i;
	}
	
	//Calcola la prossima pagina, utilizzando i parametri corso e pagina
	@PostMapping("/nextPage")
	public String nextPage(@RequestParam("corso") String corso, @RequestParam("pagina") String pagina, ModelMap modelmap) {
		modelmap.put("corso", corso);
		modelmap.put("pagina", pagina);
		String risultato = "Utenti/Corsi/" + corso + "/" + pagina;
		return risultato;
	}
}
