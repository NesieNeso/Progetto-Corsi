package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerCorsi {
	private String corso="";
	private String id="";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private void setId(HttpSession session) {
		id=session.getAttribute("id").toString();
		System.out.println("l'utente di id " + id + " sta navigando il corso di " + corso);
	}
	
	private int getLastPage() {
		return jdbcTemplate.queryForObject(("SELECT pagina_attuale from (utenti u join iscritto i on u.idUtente = i.id_utente) join corsi c on c.id_corso = i.id_corso where i.id_utente = " + id + " and c.nome_corso = '" + corso + "'"), Integer.class);
	}
	
	private void setLastPage(String pagina) {
		jdbcTemplate.update("update iscritto set pagina_attuale=" + pagina + " where id_utente=" + id + " and id_corso in( select id_corso from corsi where nome_corso = '" + corso + "')");
	}
	
	@RequestMapping("Utenti/Corsi/uncinetto")
	public String uncinetto(HttpServletRequest req) {
		this.corso="uncinetto";
		setId(req.getSession());
		return "Utenti/Corsi/uncinetto/"+ getLastPage();
	}
	
	@RequestMapping("Utenti/Corsi/falegnameria")
	public String falegnameria(HttpServletRequest req) {
		this.corso="falegnameria";
		setId(req.getSession());
		return "Utenti/Corsi/falegnameria/" + getLastPage();
	}
	
	@RequestMapping("Utenti/Corsi/cucina")
	public String cucina(HttpServletRequest req) {
		this.corso="cucina";
		setId(req.getSession());
		return "Utenti/Corsi/cucina/"+ getLastPage();
	}
	
	
	//Calcola la prossima pagina, utilizzando i parametri corso e pagina
	@RequestMapping("/nextPage")
	public String nextPage(HttpServletRequest req) {
		String pagina = req.getParameter("pagina");
		String risultato = "Utenti/Corsi/" + corso + "/" + pagina;
		setLastPage(pagina);		
		return risultato;
	}
}
