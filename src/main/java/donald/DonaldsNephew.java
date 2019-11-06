package donald;

import java.util.HashMap;
import java.util.Map;

public enum DonaldsNephew {
	HUI("red"),
	DUI("blue"),
	LUI("green");
	
	private String color;
	private Map<String, String> inLanguage;
	
	private DonaldsNephew(String color) {
		this.color = color;
		this.inLanguage = new HashMap<>();
		switch (color) {
		case "red": {
			inLanguage.put("sk","Hui");
			inLanguage.put("dk","Rip");
			inLanguage.put("it","Qui");
			break;
		}
		case "blue": {
			inLanguage.put("sk","Dui");
			inLanguage.put("dk","Rap");
			inLanguage.put("it","Quo");
			break;
		}
		case "green": {
			inLanguage.put("sk","Lui");
			inLanguage.put("dk","Rup");
			inLanguage.put("it","Qua");
			break;
		}
		}
	}
	
	public String getByLanguage(String language) {
		return inLanguage.get(language);
	}
	
	public static DonaldsNephew getByName(String name) {
		for (DonaldsNephew nephew : values()) {
			if (nephew.inLanguage.containsValue(name)) {
				return nephew;
			}
		}
		return null;
	}

	public static DonaldsNephew getByColor(String color) {
		for (DonaldsNephew nephew : values()) {
			if (nephew.color.equals(color)) {
				return nephew;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DonaldsNephew.LUI.getByLanguage("dk"));
		System.out.println(DonaldsNephew.valueOf("LUI").getByLanguage("it"));
		System.out.println(DonaldsNephew.getByName("Quo").getByLanguage("sk"));
		System.out.println(DonaldsNephew.getByColor("red").getByLanguage("sk"));
	}
}
