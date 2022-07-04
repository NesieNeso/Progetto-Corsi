package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerIscritto {
	
	private JdbcTemplate jdbcTemplate;
	
	public ControllerIscritto(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@RequestMapping("/iscrizioneNuovoCorso")
	public String iscrizione(HttpServletRequest req,ModelMap modelmap) {

		HttpSession session = req.getSession();
		String id_utente = session.getAttribute("id").toString();
		String nome_corso = req.getParameter("corso");
		List<String> tmp = new ArrayList<>();
		tmp.addAll(jdbcTemplate.queryForList("SELECT id_corso from corsi where nome_corso = '"+ nome_corso +"'", String.class));
		String id_corso = tmp.get(0);
		String sql = "Insert INTO iscritto (id_corso, id_utente, pagina_attuale) VALUES (?,?,?)";
		int result = jdbcTemplate.update(sql,id_corso, id_utente, 0);
		modelmap.put("corso", nome_corso);
		
		GestisciUtenti gu = new GestisciUtenti(jdbcTemplate);
		Map<String,Object> tmpm = new HashMap<>();
		tmpm=jdbcTemplate.queryForMap("SELECT * from utenti where idUtente = '"+ id_utente +"'");
		String email = (String)tmpm.get("email");
		String password = (String)tmpm.get("password");
		String username = (String)tmpm.get("username");
		modelmap.put("email", email);
		modelmap.put("username", username);
		modelmap.put("corsi", gu.getLinkIscrizioneCorso(email, password));
		modelmap.put("corsiNuovi", gu.getLinkNuoviCorsi(email, password));
		
		if(result > 0) {	
			return "/Utenti/HomeUtenti";}
		else {
			req.setAttribute("errorInsertion", "Errore nella registrazione");
			return "/Utenti/HomeUtenti";
		}
			
	}
	

}
