package triage;

import java.util.Scanner;

public class MainTriage {

    public static void main(String[] args) {
        // 1. INIZIALIZZAZIONE DELLE COMPONENTI (Composizione Object Tree)
        Scanner scanner = new Scanner(System.in);
        InputTriageReader reader = new InputTriageReader(scanner);
        TriageView view = new TriageView();
        TriageManager manager = new TriageManager(reader, view);

        // Memoria interna temporanea dello stato dell'applicazione
        Paziente paziente = null;
        Visita visita = null;

        // 2. CICLO DI ESECUZIONE PRINCIPALE
        boolean inEsecuzione = true;
        while (inEsecuzione) {
            view.stampaMenuPrincipale();
            String scelta = reader.leggiStringa(""); // Cattura l'istruzione digitata

            switch (scelta) {
                case "1":
                    if (paziente == null) {
                        // Il Main non sa come si crea un paziente, chiede al manager
                        paziente = manager.creaNuovoPaziente();
                        view.mostraMessaggio("✅ Paziente inserito correttamente!");
                    } else {
                        view.mostraErrore("⚠️ Errore, paziente già inserito");
                    }
                    break;

                case "2":
                    if (paziente == null) {
                        view.mostraErrore("⚠️ Errore, inserire prima un paziente");
                    } else if (visita == null) {
                        // Il Main delega completamente la logica delle sotto-visite
                        visita = manager.gestisciRegistrazioneVisita();
                        if (visita != null) {
                            paziente.setVisita(visita); // Aggancia l'oggetto visita al paziente
                            view.mostraMessaggio("✅ Visita inserita correttamente!");
                        }
                    } else {
                        view.mostraErrore("⚠️ Visita già inserita");
                    }
                    break;

                case "3":
                    if (paziente != null) {
                        paziente.stampaScheda(); // Sfrutta il polimorfismo interno al modello Paziente
                    } else {
                        view.mostraErrore("⚠️ Errore, assicurarsi di aver già registrato un paziente");
                    }
                    break;

                case "4":
                    if (paziente == null) {
                        view.mostraErrore("⚠️ Errore, assicurarsi di aver già registrato un paziente");
                    } else {
                        // Visualizza il calcolo dinamico generato internamente al modello del paziente
                        view.mostraMessaggio("PRIORITÀ:\n" + paziente.calcolaPriorita());
                    }
                    break;

                case "5":
                    inEsecuzione = false; // Interrompe il loop determinando la chiusura controllata
                    view.mostraMessaggio("\n✅ Sessione terminata con successo, arrivederci");
                    break;

                default:
                    view.mostraErrore("⚠️ Errore, inserire opzione valida");
            }
        }

        // Chiusura sicura della risorsa hardware dello scanner una volta usciti dal
        // ciclo while
        scanner.close();
    }
}