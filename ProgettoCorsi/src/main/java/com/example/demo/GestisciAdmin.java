package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciAdmin {
	
	private JdbcTemplate jdbcTemplate;
	
	public GestisciAdmin(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
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
	
	public void showUsers() {
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
		}
		System.out.println("--------------------------------------------");
	}
	
	public void showAdmin() {
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
		}
		System.out.println("--------------------------------------------");

	}
}
	
