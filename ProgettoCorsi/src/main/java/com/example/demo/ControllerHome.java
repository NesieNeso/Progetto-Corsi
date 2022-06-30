package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerHome {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public String homepage() {
		
		return "home/Homepage";
	}
	
	@RequestMapping("/redirector")
	public String redirector() {
		if(true) {
			return "Admin/HomeAdmin";
		}
		else {
			return "Utenti/HomeUtenti";
		}
	}
	
	
}
