package com.example.demo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciCarrello {

	private JdbcTemplate jdbcTemplate;
	
	public GestisciCarrello(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public String getLinkCorsi(String idUtente) {
		GestistiCorsi gC = new GestistiCorsi(jdbcTemplate);
		GestisciUtenti gU = new GestisciUtenti(jdbcTemplate);
		String link = "";
		
		String sqlMail = "select email from utenti where idUtente like '" + idUtente +"';";
		String email = jdbcTemplate.queryForObject(sqlMail, String.class);
		String sqlPsw = "select password from utenti where idUtente like '" + idUtente +"';";
		String password = jdbcTemplate.queryForObject(sqlPsw, String.class);
		
		link=gU.getLinkIscrizioneCorso(email, password);
		
		return link;
	}	
}
