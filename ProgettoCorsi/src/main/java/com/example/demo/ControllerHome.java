package com.example.demo;

import java.net.http.HttpRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerHome {

	
	private String convertpassword(char[] password) {
		byte[] gt = {54,4,48};
		SecretKeyFactory factory;
		SecretKey secret = null;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password, gt , 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return secret.toString();
	}
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private boolean flagError = false;
	
	@RequestMapping("/")
	public String homepage(HttpServletRequest request) {
		GestisciUtenti gu = new GestisciUtenti(jdbcTemplate);

		
		HttpSession req = request.getSession();
		
		
		if(flagError) {
			/* GESTIONE DEGLI ERRORI */
			req.setAttribute("errorLogin", "c'è stato un errore nel login");
			return "home/LoginPage";
			
			
		}else {		
			/* Stiamo facendo il login o la registrazione*/
			System.out.println("Login");
			return "home/LoginPage";
		
		}	
		
	}
			

	//Decide che pagina visualizzare, se admin o user
	@PostMapping("/redirector")
	public String redirector(HttpServletResponse response, HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap) {
		

		
		GestisciUtenti gu = new GestisciUtenti(jdbcTemplate);
		modelmap.put("email", email);
		modelmap.put("password", password);
		
		
		System.out.println( "you pushed button " + request.getParameter("bnt"));
		

		if(request.getParameter("bnt").equals("Login")) {
			/* Siamo in login*/
			List<String> tmp = gu.getIdFromUserPassword(email, password);
			if(tmp.size() == 2) {
				if(tmp.get(1).equals("user")) 
					return "Utenti/HomeUtenti";
				
				else 
					return "Admin/HomeAdmin";
				
			}
		}else {
			/* Siamo in Registrazione*/
			System.out.println(email + ", " + password);
			
			gu.insert(email, password);
			return "Utenti/HomeUtenti";
		}
		

		return "/";
	
	}
	
	@RequestMapping("/HomeAdmin")
	public String homeAdmin() {
		return "Admin/HomeAdmin";
	}
	
	@RequestMapping("/HomeUtenti")
	public String homeUtenti() {
		return "Utenti/HomeUtenti";
	}
	
	
	
}
