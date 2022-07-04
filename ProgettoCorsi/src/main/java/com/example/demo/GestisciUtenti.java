package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciUtenti {

	private JdbcTemplate jdbcTemplate;
	
	public GestisciUtenti(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public int insert(String username, String password) {
		String sql = "INSERT INTO utenti (email, password) VALUES (?, ?)";
		int result = jdbcTemplate.update(sql, username, password);
		return result;
	}
	
	public List<String> getIdFromUserPassword(String email, String password) {
		List<String> idList = new ArrayList<>();
		idList.addAll(jdbcTemplate.queryForList("SELECT idUtente from utenti where email='" + email + "' and password='" + password+"'", String.class));
		if(idList.get(0).equals("0")) {
			
		}else
			idList.addAll(jdbcTemplate.queryForList("SELECT tipo_ruolo from ruolo where idUtente = '"+idList.get(0) +"'", String.class));
		return idList;		
	}
	
	public List<String> getIscrizioneCorso(String email, String password) {
		List<String> idList = new ArrayList<>();
		List<String> idCorso = new ArrayList<>();
		//recupero l'idUtente avendo email e password
		idList.addAll(jdbcTemplate.queryForList("SELECT idUtente from utenti where email='" + email + "' and password='" + password+"'", String.class));
		
		if(idList.get(0).equals("0")) {
			
		}else
			//se trovo un utente recupero gli id_corso a cui è iscritto l'utente considerato
			idCorso.addAll(jdbcTemplate.queryForList("SELECT id_corso from iscritto where id_utente = '"+idList.get(0) +"'", String.class));
		List<String> listCorsi = new ArrayList<>();
		
		//ciclo sugli id_corso per recuperare i nomi corrispondenti
		for(String id_corso: idCorso) {
				if(id_corso.equals("0")) {
					
				}else {
					List<String> tmp = new ArrayList<>();
					tmp.addAll(jdbcTemplate.queryForList("SELECT nome_corso from corsi where id_corso = '"+id_corso +"'", String.class));
					listCorsi.add(tmp.get(0));
					}
			}
		return listCorsi;

	}
	
	public String getLinkIscrizioneCorso(String email, String password) {
		List<String> listCorsi =getIscrizioneCorso(email, password);
		String corsi="";
		for(String c: listCorsi) {
			corsi +="<a href=\"Utenti/Corsi/" + c + "\">" + c + "</a><br>";}			
			System.out.println(corsi);
			return corsi;
		
	}
	
	public String getUsername(String email, String password) {
		List<String> username = new ArrayList<>();
		username.addAll(jdbcTemplate.queryForList("SELECT username from utenti where email='" + email + "' and password='" + password+"'", String.class));
		return username.get(0);		
	}
	
	
}
