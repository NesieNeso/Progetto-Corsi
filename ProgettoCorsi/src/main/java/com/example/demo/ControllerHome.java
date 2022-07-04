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
	
	private String bnt;
	
	@RequestMapping("/")
	public String homepage(HttpServletRequest request) {
		System.out.println("Benvenuto nella pagina iniziale");
		HttpSession req = request.getSession();
		req.setAttribute("errorLogin", "");

		
		
		
		if(flagError) {
			/* GESTIONE DEGLI ERRORI */
			System.out.println("-> errore;");
			
			flagError = false;
			if(bnt.equals("Login")) {
				req.setAttribute("errorLogin", "Password o Email errati");
			}else
				req.setAttribute("errorLogin", "c'è stato un errore nella registrazione");
			
			return "home/LoginPage";
			
		}else {		
			
			req.setAttribute("errorEmail", "");
			req.setAttribute("errorPass", "");
			/* Stiamo facendo il login o la registrazione*/
			System.out.println("-> Login o registrazione");
			return "home/LoginPage";

		}	
		
	}
			

	//Decide che pagina visualizzare, se admin o user
	@PostMapping("/redirector")
	public String redirector(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap) {
				
		GestisciUtenti gu = new GestisciUtenti(jdbcTemplate);

		HttpSession ses = request.getSession();
		bnt = request.getParameter("bnt");	


		if(email.length()==0 || password.length() == 0) {
			System.out.println(" -> -> errore");
			flagError = true;
			if(email.length()== 0 )
				System.out.println(" -> -> errore Email");
				ses.setAttribute("errorEmail", "non hai inserito un email");

			if(password.length()== 0 )
				ses.setAttribute("errorPass", "non hai inserito la password");

			return "redirect:/";
		}
		

		if(bnt.equals("Login")) {
			modelmap.put("email", email);
			modelmap.put("password", password);
			modelmap.put("username", gu.getUsername(email, password));
			modelmap.put("corsi", gu.getLinkIscrizioneCorso(email, password));
			/* Siamo in login*/
			List<String> tmp = gu.getIdFromUserPassword(email, password);
			if(tmp.size() == 2) { 
				if(tmp.get(1).equals("user")) 
					return "Utenti/HomeUtenti";
				else 
					return "Admin/HomeAdmin";
			}else{
				flagError = true;
				return "home/LoginPage";
			}
		}else {
			/* Siamo in Registrazione*/
			
			try {
				
				gu.insert(email, password);
				
				modelmap.put("email", email);
				modelmap.put("password", password);
				modelmap.put("username", gu.getUsername(email, password));
				modelmap.put("corsi", gu.getIscrizioneCorso(email, password));
				
				return "Utenti/HomeUtenti";
			}catch (Exception e) {
				flagError = true;
				return "redirect:/";
			}


		}
	
	}
	
	
	@RequestMapping("/HomeUtenti")
	public String homeUtenti() {
		return "Utenti/HomeUtenti";
	}
	
	
	
}
