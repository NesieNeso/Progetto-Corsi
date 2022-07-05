package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Utenti/Corsi")
public class ControllerCorsi {
	private String corso="";
	private String id=null;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private void setId(HttpSession session) {
		try {
		id=session.getAttribute("id").toString();
		}
		catch(NullPointerException e) {
			id=null;
		}
		System.out.println("l'utente di id " + id + " sta navigando il corso di " + corso);
	}
	
	private int getLastPage() {
		return jdbcTemplate.queryForObject(("SELECT pagina_attuale from (utenti u join iscritto i on u.idUtente = i.id_utente) join corsi c on c.id_corso = i.id_corso where i.id_utente = " + id + " and c.nome_corso = '" + corso + "'"), Integer.class);
	}
	
	private void setLastPage(String pagina) {
		jdbcTemplate.update("update iscritto set pagina_attuale=" + pagina + " where id_utente=" + id + " and id_corso in( select id_corso from corsi where nome_corso = '" + corso + "')");
	}
	
	@RequestMapping(value = "/{nomeCorso}")
	public String uncinetto(HttpServletRequest req, @PathVariable String nomeCorso) {
		this.corso=nomeCorso;
		setId(req.getSession());
		if(id==null)
			return "home/LoginPage";
		return "Utenti/Corsi/" + nomeCorso + "/" + getLastPage();
	}	
	
	//Calcola la prossima pagina, utilizzando i parametri corso e pagina
	@RequestMapping("/nextPage")
	public String nextPage(HttpServletRequest req) {
		System.out.println("PASSO DI QUI");
		String pagina = req.getParameter("pagina");
		String risultato = "Utenti/Corsi/" + corso + "/" + pagina;
		setLastPage(pagina);		
		return risultato;
	}
}
