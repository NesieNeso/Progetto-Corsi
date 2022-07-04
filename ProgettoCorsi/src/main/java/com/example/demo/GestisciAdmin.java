package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciAdmin {
	
	private JdbcTemplate jdbcTemplate;
	
	public GestisciAdmin(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public String clear() {
		return "";
	}
	
	public String showAll() {
		String Lista = "";
		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList("SELECT nome from utenti", String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList("SELECT cognome from utenti", String.class));
		List<String> allEmailsList = new ArrayList<>();
		allEmailsList.addAll(jdbcTemplate.queryForList("SELECT email from utenti order by idUtente", String.class));
		System.out.println("------------TUTTI GLI UTENTI----------------");
		for(int i = 0; i<allNamesList.size(); i++) {
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i));
			System.out.println("");
			Lista += "nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i) +"</br>";
		}
		System.out.println("--------------------------------------------");
		//System.out.println(Lista);
		return Lista;
	}
	
	public String showUsers() {
		String Lista = "";
		String sqlNome = "select nome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'user';";
		String sqlCognome = "select cognome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'user';";
		String sqlEmail = "select email from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'user';";
		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList(sqlNome, String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList(sqlCognome, String.class));
		List<String> allEmailsList = new ArrayList<>();
		allEmailsList.addAll(jdbcTemplate.queryForList(sqlEmail, String.class));
		System.out.println("------------SOLO GLI UTENTI----------------");
		for(int i = 0; i<allNamesList.size(); i++) {
			
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i));
			System.out.println("");
			Lista += "nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i) +"</br>";
			
		}
		System.out.println("--------------------------------------------");
		//System.out.println(Lista);
		return Lista;
	}
	
	public String showAdmin() {
		String Lista = "";
		String sqlNome = "select nome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'admin';";
		String sqlCognome = "select cognome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'admin';";
		String sqlEmail = "select email from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'admin';";
		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList(sqlNome, String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList(sqlCognome, String.class));
		List<String> allEmailsList = new ArrayList<>();
		allEmailsList.addAll(jdbcTemplate.queryForList(sqlEmail, String.class));
		System.out.println("------------TUTTI GLI ADMIN----------------");
		for(int i = 0; i<allNamesList.size(); i++) {
			
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i));
			System.out.println("");
			Lista += "nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i) +"</br>";
			
		}
		System.out.println("--------------------------------------------");
		//System.out.println(Lista);
		return Lista;
	}
	
	public String showBanned() {
		String Lista = "";
		String sqlNome = "select nome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'ban';";
		String sqlCognome = "select cognome from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'ban';";
		String sqlEmail = "select email from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'ban';";
		List<String> allNamesList = new ArrayList<>();
		allNamesList.addAll(jdbcTemplate.queryForList(sqlNome, String.class));
		List<String> allSurnamesList = new ArrayList<>();
		allSurnamesList.addAll(jdbcTemplate.queryForList(sqlCognome, String.class));
		List<String> allEmailsList = new ArrayList<>();
		allEmailsList.addAll(jdbcTemplate.queryForList(sqlEmail, String.class));
		System.out.println("------------TUTTI I BANNATI----------------");
		for(int i = 0; i<allNamesList.size(); i++) {
			
			System.out.print("nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i));
			System.out.println("");
			Lista += "nome: " + allNamesList.get(i) + " cognome: " + allSurnamesList.get(i) + " email: " + allEmailsList.get(i) +"</br>";
			
		}
		System.out.println("--------------------------------------------");
		//System.out.println(Lista);
		return Lista;
	}
	
	public int ban(String mailBannato) {
		int flag = 0;
		String sqlBan = "select u.idUtente from utenti u join ruolo r on u.idUtente = r.idUtente where email like '" + mailBannato + "';";
		int idBannare = jdbcTemplate.queryForObject(sqlBan, Integer.class);
		
		String sqlBanning;
		try {
			sqlBanning = "update ruolo set tipo_ruolo = 'ban' where idutente = " + idBannare +";";
			flag = jdbcTemplate.update(sqlBanning);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERRORE" + e.toString());
			e.printStackTrace();
		}
		
		return flag;
	}

	public int check(String email) {
		int result = -1;
		String mailFlag = email;
		
		String sqlMailAll = "select email from utenti;";
		List<String> allEmailsList = new ArrayList<>();
		allEmailsList.addAll(jdbcTemplate.queryForList(sqlMailAll, String.class));
		
		// controllo che la mail esista nel DB
		for(String k : allEmailsList) {
			if(k.equals(mailFlag)) {
				result = 1;
			} 
		}
		
		// essendo nel db, vado a vedere a che ruolo appartiene la mail
		if(result == 1) {
			
			String sqlMailUsers = "select email from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'user';";
			List<String> usersEmailsList = new ArrayList<>();
			usersEmailsList.addAll(jdbcTemplate.queryForList(sqlMailUsers, String.class));
			
			String sqlMailAdmin = "select email from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'admin';";
			List<String> adminsEmailsList = new ArrayList<>();
			adminsEmailsList.addAll(jdbcTemplate.queryForList(sqlMailAdmin, String.class));
			
			String sqlMailBanned = "select email from utenti u join ruolo r on u.idUtente = r.idUtente where tipo_ruolo like 'ban';";
			List<String> bannedEmailsList = new ArrayList<>();
			bannedEmailsList.addAll(jdbcTemplate.queryForList(sqlMailBanned, String.class));
			
			for(String k : usersEmailsList) {
				if(k.equals(mailFlag)) result = 2;
			}
			
			for(String k : adminsEmailsList) {
				if(k.equals(mailFlag)) result = 3;
			}
			
			for(String k : bannedEmailsList) {
				if(k.equals(mailFlag)) result = 4;
			}
			
		}
		
		return result;
	}

	public int sban(String emailSbannare) {
		int flag = 0;
		String sqlBan = "select u.idUtente from utenti u join ruolo r on u.idUtente = r.idUtente where email like '" + emailSbannare + "';";
		int idBannare = jdbcTemplate.queryForObject(sqlBan, Integer.class);
		
		String sqlBanning;
		try {
			sqlBanning = "update ruolo set tipo_ruolo = 'user' where idutente = " + idBannare +";";
			flag = jdbcTemplate.update(sqlBanning);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERRORE" + e.toString());
			e.printStackTrace();
		}
		
		return flag;
	}
}
	
