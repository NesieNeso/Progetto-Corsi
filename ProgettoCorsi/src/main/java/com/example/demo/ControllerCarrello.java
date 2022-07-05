package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerCarrello {


	private JdbcTemplate jdbcTemplate;
	private GestisciCarrello gc;
	public ControllerCarrello(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		 gc = new GestisciCarrello(jdbcTemplate);
	}
	
	@RequestMapping("Utenti/Carrello")
	public String carrello(HttpServletRequest req, ModelMap modelmap){
		/*restituisce una stringa con i corsi non ancora acquistati
		 * con il seguente formato:
		 * "<a href=inserisciNelCarrello?corso="+ nome_corso +">" + nome_corso + "</a><br>"
		 */
		HttpSession session = req.getSession();
		String id_utente = session.getAttribute("id").toString();
		System.out.println("CORSI NUOVI: " + gc.getLinkCorsi(id_utente));
		modelmap.put("corsi", gc.getLinkCorsi(id_utente));
		return "Utenti/Carrello";
	}
	
	/*
	@RequestMapping("/inserisciNelCarrello")
	public String iscrizione(HttpServletRequest req,ModelMap modelmap) {
		HttpSession session = req.getSession();
		String id_utente = session.getAttribute("id").toString();
		String nome_corso = req.getParameter("corso");

		gc.acquistaCorso(id_utente, nome_corso);

		
		 // aggiunge nome_corso al carrello dell'utente id_utente e al 
		  //db iscritto
		 
		gc.acquistaCorso(id_utente,nome_corso);
		
		return carrello(req, modelmap);
	}*/
	
	
	
}
