package triage;

public class ValidatoreDati {

	public static boolean validaData(String data) {
		if (data == null || data.length() != 10)
			return false;
		return data.substring(0, 2).matches("\\d+") &&
				data.charAt(2) == '/' &&
				data.substring(3, 5).matches("\\d+") &&
				data.charAt(5) == '/' &&
				data.substring(6, 10).matches("\\d+");
	}

	public static boolean validaTesto(String testo, int maxLunghezza) {
		return testo != null && !testo.isEmpty() && testo.length() < maxLunghezza;
	}

	public static boolean validaColoreTriage(String colore) {
		if (colore == null)
			return false;
		String c = colore.toUpperCase();
		return c.equals("ROSSO") || c.equals("GIALLO") || c.equals("VERDE") || c.equals("BIANCO");
	}

	public static String capitalizza(String testo) {
		if (testo == null || testo.isEmpty())
			return "";
		return testo.substring(0, 1).toUpperCase() + testo.substring(1);
	}
}