package com.example.demo;

import java.util.ArrayList;
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
		String link = "", email = "", password = "", corsi="";
		List<String> listaNuoviCorsi = new ArrayList<String>();
		
		email = gU.getEmailFromId(idUtente);
		password = gU.getPasswordFromId(idUtente);
		listaNuoviCorsi = gU.listaNuoviCorsi(email, password);
		
		for(String c: listaNuoviCorsi) {
			corsi +="<a href=inserisciNelCarrello?corso="+c+">" + c + "</a><br>";}
		
		return corsi;
	}

	// aggiunge nome_corso al carrello dell'utente id_utente e al db iscritto
	public void acquistaCorso(String id_utente, String nome_corso) {
		
		
	}	
}
