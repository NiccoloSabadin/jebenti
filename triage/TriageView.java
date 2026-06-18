package triage;

public class TriageView {

	public void stampaMenuPrincipale() {
		System.out.printf("%-40s%n", "=======================");
		System.out.println("\u001B[1m         MENÙ\u001B[0m");
		System.out.printf("%-40s%n", "=======================");
		System.out.println("\u001B[1m1\u001B[0m - Registra paziente");
		System.out.println("\u001B[1m2\u001B[0m - Assegna una visita");
		System.out.println("\u001B[1m3\u001B[0m - Stampa la scheda");
		System.out.println("\u001B[1m4\u001B[0m - Calcola priorità");
		System.out.println("\u001B[1m5\u001B[0m - Uscire");
		System.out.printf("%-40s%n", "=======================");
		System.out.println("Digitare numero corrispondente all'opzione desiderata:");
	}

	public void stampaMenuVisite() {
		System.out.printf("%-40s%n", "=======================");
		System.out.println("\u001B[1m     MENÙ VISITE\u001B[0m");
		System.out.printf("%-40s%n", "=======================");
		System.out.println("\u001B[1m1\u001B[0m - Generica");
		System.out.println("\u001B[1m2\u001B[0m - Ortopedica");
		System.out.println("\u001B[1m3\u001B[0m - Cardiologica");
		System.out.printf("%-40s%n", "=======================");
		System.out.println("Inserisci il numero corrispondente al tipo di visita: ");
	}

	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	public void mostraErrore(String errore) {
		System.err.println(errore);
	}
}