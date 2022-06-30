package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerHome {

	@RequestMapping("/")
	public String homepage() {
		return "home/Homepage";
	}
	
	@PostMapping("/redirector")
	public String redirector() {
		
		/**
		 * prendo dati dal form

		 *
		 *
		 * mi connetto al db e prendo id
		 * 	1 -> ok
		 *  2 -> o non esiste o la pss è sbagliata
		 * 
		 * int utenteID = GestisciUtenti.getIdFromUserPassword(... ... );
		 * 
		 * mi prendo il ruolo (user o admin)
		 * 
		 * 
		 * chiamo la pagina dedicata
		 * 
		 */
		
		if(true) {
			return "Admin/HomeAdmin";
		}
		else {
			return "Utenti/HomeUtenti";
		}
	}
	
}
