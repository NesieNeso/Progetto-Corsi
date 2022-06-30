package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerHome {

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
	
	@RequestMapping("/HomeAdmin")
	public String homeAdmin() {
		return "Admin/HomeAdmin";
	}
	
}
