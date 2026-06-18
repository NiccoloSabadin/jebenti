package triage;

import java.util.Scanner;

public class InputTriageReader {
	private final Scanner scanner;

	public InputTriageReader(Scanner scanner) {
		this.scanner = scanner;
	}

	public String leggiStringa(String messaggio) {
		System.out.println(messaggio);
		return scanner.nextLine().trim();
	}

	public String leggiDataObbligatoria() {
		while (true) {
			String data = leggiStringa("Inserire data in formato gg/mm/aaaa: ");
			if (ValidatoreDati.validaData(data)) {
				return data.toUpperCase();
			}
			System.err.println("⚠️ Errore di inserimento, inserire data in formato gg/mm/aaaa! ");
		}
	}

	public String leggiTestoValidato(String messaggio, int maxLunghezza) {
		while (true) {
			String testo = leggiStringa(messaggio);
			if (ValidatoreDati.validaTesto(testo, maxLunghezza)) {
				return ValidatoreDati.capitalizza(testo);
			}
			System.err.println("⚠️ Errore di inserimento, lunghezza testo non valida o campo vuoto.");
		}
	}

	public String leggiColoreTriage() {
		while (true) {
			String colore = leggiStringa("Inserire codice colore triage (ROSSO, GIALLO, VERDE, BIANCO): ");
			if (ValidatoreDati.validaColoreTriage(colore)) {
				return colore.toUpperCase();
			}
			System.err.println("⚠️ Errore di inserimento, inserire codice colore valido! ");
		}
	}
}