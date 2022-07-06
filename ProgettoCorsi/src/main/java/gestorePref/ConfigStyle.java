package gestorePref;

public class ConfigStyle {
	String Background;
	String fontSize;
	String fontFamily;
	String color = "blue";
	
	public String getBackground() {
		return Background;
	}
	public void setBackground(String background) {
		Background = background;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "ConfigStyle [Background=" + Background + ", fontSize=" + fontSize + ", fontFamily=" + fontFamily
				+ ", color=" + color + "]";
	}
	
	
}
