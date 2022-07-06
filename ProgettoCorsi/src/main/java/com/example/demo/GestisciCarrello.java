package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciCarrello {

	private JdbcTemplate jdbcTemplate;
	
	public GestisciCarrello(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	/**
	 * 
	 * @param idUtente
	 * @inserito {true -> inserti nel carrello
	 * 			 false - non inseriti
	 */
	public String getLinkCorsi(String idUtente, boolean inserito) {
		GestistiCorsi gC = new GestistiCorsi(jdbcTemplate);
		GestisciUtenti gU = new GestisciUtenti(jdbcTemplate);
		String link = "", email = "", password = "", corsi="";
		List<String> listaNuoviCorsi = new ArrayList<String>();
		if(inserito) {
			String sql = "SELECT c.nome_corso "
					+ "FROM corsi c\r\n"
					+ "join carrello l on l.id_corso = c.id_corso\r\n"
					+ "where l.id_utente = '" +idUtente+"' && acquistato = 0";
			
			List<String> list = jdbcTemplate.queryForList(sql, String.class);
			for(String l: list)
				corsi +="<a href=rimuovidalCarrello?corso="+l+">" + l + "</a><br>";
				
		}else {
			
			email = gU.getEmailFromId(idUtente);
			password = gU.getPasswordFromId(idUtente);
			listaNuoviCorsi = gU.listaNuoviCorsi(email, password);
			for(String c: listaNuoviCorsi) {
				corsi +="<a href=inserisciNelCarrello?corso="+c+">" + c + "</a><br>";}
			
		}
		
		return corsi;
	}

	// aggiunge nome_corso al carrello dell'utente id_utente e al db iscritto
	public void acquistaCorso(String id_utente, String nome_corso) {
		
		
	}	


	// aggiunge al carrello
	public void inserisciCorso(String id_utente, String nome_corso) {
		String sql = "select o.id_costo"
				+ " from corsi c"
				+ " join costo o on o.id_costo = c.id_costo"
				+ " where nome_corso = '" + nome_corso+"'";
		int costo = jdbcTemplate.queryForObject(sql, Integer.class);
		
		sql = "select id_corso"
				+ " from corsi"
				+ " where nome_corso = '" + nome_corso+"'";
		int id =  jdbcTemplate.queryForObject(sql, Integer.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  

		sql = "insert INTO carrello (id_utente, id_corso, id_costo, dataora, acquistato) VALUES  (?,?,?,?, '0')";
		
		
		jdbcTemplate.update(sql, id_utente, id, costo, now);
		
	}	
	
	public String getCostoScontato(String nomeCorso) {
		Map<String, Object> euroSconto = jdbcTemplate.queryForMap(" select euro, sconto"
				+ " from costo "
				+ "where id_costo in ( select id_costo from corsi where nome_corso='" + nomeCorso + "')" );
		
		return (Integer)euroSconto.get("euro") + ", " + (Integer) euroSconto.get("sconto"); 
	}

	
}

