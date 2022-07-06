package gestorePref;

public class ConfigGeographical {
	private String lingua;
	private String valuta;
	private String simbolo;
	
	public ConfigGeographical(String lingua, String valuta) {
		this.lingua = lingua;
		this.valuta = valuta;
	}
	
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getValuta() {
		return valuta;
	}
	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	@Override
	public String toString() {
		return "ConfigGeographical [lingua=" + lingua + ", valuta=" + valuta + "]";
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
		
	}
	public String getSimbolo() {
		return simbolo;
	}

		
}
