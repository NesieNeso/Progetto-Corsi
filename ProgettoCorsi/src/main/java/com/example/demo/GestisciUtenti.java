package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciUtenti {

	private JdbcTemplate jdbcTemplate;
	
	public GestisciUtenti(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		test();
	}
	
	public int insert(String username, String nome, String cognome, String password, String email) {
		String sql = "INSERT INTO utenti (username, nome, cognome, password, email) VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, username, nome, cognome, password, email);
		return result;
	}
	
	public List<String> getIdFromUserPassword(String username, String password) {
		List<String> idList = new ArrayList<>();
		idList.addAll(jdbcTemplate.queryForList("SELECT idUtente from utenti where username='" + username + "' and password='" + password+"'", String.class));
		return idList;		
	}
	
	public void test() {
		System.out.println("Prova inserimento:");
		insert("LB", "Luigi", "Bianchi", "lbpass", "lb@outlook.it");
		System.out.println("Prova ricerca id:");		
		String list = String.join("\n", getIdFromUserPassword("JG","password"));
		System.out.println(list);
	}
	
}
