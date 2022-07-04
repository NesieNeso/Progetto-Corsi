package gestioneConnessione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Connessione {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private JdbcTemplate temp;
	
	public Connessione() {
		// TODO Auto-generated constructor stub
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Autowired
	public JdbcTemplate getTemp() {
		return temp;
	}
	
	
	
}
