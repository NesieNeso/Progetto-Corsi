package com.example.demo;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

		
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	private boolean flagError = false;
	private boolean ban = false;
	
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
				req.setAttribute("errorLogin", "Account già registrato");
			
			return "home/LoginPage";
			
		}else if (ban){
			ban = false;
			System.out.println("-> Ban;");
			req.setAttribute("errorLogin", "Sei stato bannato");
			return "home/LoginPage";
		}else {		
			System.out.println("-> Login o registrazione;");
			req.setAttribute("errorEmail", "");
			req.setAttribute("errorPass", "");
			/* Stiamo facendo il login o la registrazione*/

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

			/* Siamo in login*/
			List<String> tmp = gu.getIdFromUserPassword(email, password);
					
			if(tmp.size() == 2) {
				if(tmp.get(1).equals("user")) { 
					System.out.println("Login");
					/*SONO UN UTENTE*/
					ses.setAttribute("id", tmp.get(0));
					
					modelmap.put("email", email);
					modelmap.put("password", password);
					modelmap.put("username", gu.getUsername(email, password));
					modelmap.put("corsi", gu.getIscrizioneCorso(email, password));
					return "Utenti/HomeUtenti";
				}else if(tmp.get(1).equals("ban")) {
					System.out.println("Bannato");
					ban = true;
					return "redirect:/";
				}else 
					return "Admin/HomeAdmin";
			}else{
				System.out.println("errore ");
				flagError = true;
				return "redirect:/";
			}
		}else {
			/* Siamo in Registrazione*/
			
			try {
				
				int res = gu.insert(email, password);
				if(res > 0) {
					return "home/LoginPage";
				}else {
					return "redirect:/";
				}
				
			}catch (Exception e) {
				flagError = true;
				return "redirect:/";
			}


		}
	
	}
	
	
	
}
