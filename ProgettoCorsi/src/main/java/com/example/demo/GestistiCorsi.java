package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;

public class GestistiCorsi {

	private JdbcTemplate jdbcTemplate;
	
	public GestistiCorsi(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate=jdbcTemplate;
	}

}
