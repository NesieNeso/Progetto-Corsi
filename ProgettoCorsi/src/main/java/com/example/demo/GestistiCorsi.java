package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestistiCorsi {

	private JdbcTemplate jdbcTemplate;
	
	public GestistiCorsi(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public List<String> getCorsi(){
		List<String> nomeCorsi = new ArrayList<>();
		nomeCorsi.addAll(jdbcTemplate.queryForList("SELECT nome_corso from corsi", String.class));
		return nomeCorsi;
	}
}
