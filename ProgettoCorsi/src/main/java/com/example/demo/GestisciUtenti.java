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
	
	public String getIscrizioneCorso(String email, String password) {
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
			String corsi=String.join("<br>", listCorsi);
			return corsi;
	}
	
	public String getUsername(String email, String password) {
		List<String> username = new ArrayList<>();
		username.addAll(jdbcTemplate.queryForList("SELECT username from utenti where email='" + email + "' and password='" + password+"'", String.class));
		return username.get(0);		
	}
	
	public void showAll() {

		List<String> allList = new ArrayList<>();
		allList.addAll(jdbcTemplate.queryForList("SELECT * from utenti", String.class));
		for(String k : allList)
			System.out.println(k);

		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList("SELECT nome from utenti", String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList("SELECT cognome from utenti", String.class));

		for(int i = 0; i<allNamesList.size(); i++) {
			
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i));
			System.out.println("");
			
		}
	}
	
	public void showUsers() {
		String sqlNome = "select nome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'user';";
		String sqlCognome = "select cognome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'user';";
		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList(sqlNome, String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList(sqlCognome, String.class));

		for(int i = 0; i<allNamesList.size(); i++) {
			
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i));
			System.out.println("");
		}
	}
	
	public void showAdmin() {
		String sqlNome = "select nome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'admin';";
		String sqlCognome = "select cognome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'admin';";
		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList(sqlNome, String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList(sqlCognome, String.class));

		for(int i = 0; i<allNamesList.size(); i++) {
			
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i));
			System.out.println("");
		}

	}
	
}
