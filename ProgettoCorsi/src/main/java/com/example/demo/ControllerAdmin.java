package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerAdmin {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostMapping("/Admin/HomeAdmin/allUsers")
	public String allUsers() {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showAll();
		
		return "/Admin/HomeAdmin";
	}
	
	

}
