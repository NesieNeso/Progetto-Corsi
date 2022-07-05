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
	
	/**
	 * 
	 * 	Ritrona -1 se non esiste
	 * 	ritrona > 0 se esiste
	 * 
	 * */
	private int getId(String email, String password) {
		try {
		return jdbcTemplate.queryForObject(("SELECT idUtente from utenti where email='" + email + "' and password='" + password+"'"), Integer.class);
		}catch (Exception e) {
			return -1;
		}
	}
	
	public int insert(String email, String password) {
		String sql = "INSERT INTO utenti (email, password) VALUES (?, ?)";
		int result = jdbcTemplate.update(sql, email, password);
		
		System.out.println("id: " +  getId(email, password));
		if(result == 1) {
			sql = "INSERT INTO ruolo (idutente, tipo_ruolo) VALUES (?, 'user')";
			return jdbcTemplate.update(sql,  getId(email, password));
		}else
			return -1;
	}
	
	
	public List<String> getIdFromUserPassword(String email, String password) {
		List<String> idList = new ArrayList<>();
		idList.addAll(jdbcTemplate.queryForList("SELECT idUtente from utenti where email='" + email + "' and password='" + password+"'", String.class));
		System.out.println("è nulla: " + (idList == null) + ", è grossa: " + idList.size());
		if(idList != null && idList.size() > 0) 
			idList.addAll(jdbcTemplate.queryForList("SELECT tipo_ruolo from ruolo where idUtente = '"+idList.get(0) +"'", String.class));

		return idList;		
	}
	
	public List<String> getIscrizioneCorso(String email, String password) {
		List<String> idList = new ArrayList<>();
		//recupero l'idUtente avendo email e password
		idList.addAll(jdbcTemplate.queryForList("SELECT idUtente from utenti where email='" + email + "' and password='" + password+"'", String.class));
		return getIscrizioneCorso(idList.get(0));
	}
	
	public List<String> getIscrizioneCorso(String id) {
		List<String> idCorso = new ArrayList<>();
		if(id.equals("0")) {
			
		}else
			//se trovo un utente recupero gli id_corso a cui è iscritto l'utente considerato
			idCorso.addAll(jdbcTemplate.queryForList("SELECT id_corso from iscritto where id_utente = '"+id +"'", String.class));
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
	
	public List<String> listaNuoviCorsi(String email, String password) {
		GestistiCorsi gc = new GestistiCorsi(jdbcTemplate);
		List<String> corsiNuovi = gc.getCorsi();
		corsiNuovi.removeAll(getIscrizioneCorso(email, password));
		System.out.println(corsiNuovi);
		return corsiNuovi;
	}
	
	public String getLinkNuoviCorsi(String email, String password) {
		List<String> listCorsi = listaNuoviCorsi(email, password);
		String corsi="";
		for(String c: listCorsi) {
			corsi +="<a href=iscrizioneNuovoCorso?corso="+c+">" + c + "</a><br>";}
			return corsi;	
	}
	
	public String getLinkIscrizioneCorso(String email, String password) {
		List<String> listCorsi = getIscrizioneCorso(email, password);
		String corsi="";
		for(String c: listCorsi) {
			corsi +="<a href=\"Utenti/Corsi/" + c + "\">" + c + "</a><br>";}
			return corsi;	

	}
	
	public String getUsername(String email, String password) {
		List<String> username = new ArrayList<>();
		username.addAll(jdbcTemplate.queryForList("SELECT username from utenti where email='" + email + "' and password='" + password+"'", String.class));
		return username.get(0);		
	}
	
	public String getEmailFromId(String idUtente) {
		String sqlMail = "select email from utenti where idUtente like '" + idUtente +"';";
		String email = jdbcTemplate.queryForObject(sqlMail, String.class);
		return email;
	}
	
	public String getPasswordFromId(String idUtente) {
		String sqlPsw = "select password from utenti where idUtente like '" + idUtente +"';";
		String password = jdbcTemplate.queryForObject(sqlPsw, String.class);
		return password;
	}
	
}