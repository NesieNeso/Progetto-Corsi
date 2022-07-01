package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerAdmin {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/HomeAdmin/allUsers")
	public String allUsers() {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		return "/HomeAdmin";
	}
	
	

}
