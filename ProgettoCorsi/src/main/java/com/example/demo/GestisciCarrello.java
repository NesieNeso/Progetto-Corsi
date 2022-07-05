package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestisciCarrello {

	private JdbcTemplate jdbcTemplate;
	
	public GestisciCarrello(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
}
