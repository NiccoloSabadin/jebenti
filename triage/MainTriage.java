package triage;

import java.util.Scanner;

public class MainTriage {

// Scanner al di fuori del main per utilizzarlo in tutti i metodi della classe
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Variabili iniziali assegnate a null
        Paziente paziente = null;
        Visita visita = null;
        String scelta = null;

        // Ciclo while per il menù
        boolean condizione1 = false;
        while (!condizione1) {
            stampaMenu();
            scelta = sc.nextLine().trim();

            // Switch per direzionare la scelta da tastiera
            switch (scelta) {

                // Registrazione paziente
                case "1":
                    if (paziente == null) {
                        paziente = registraPaziente();
                        System.out.println("✅ Paziente inserito correttamente!");
                    } else {
                        System.out.println("⚠️ Errore, paziente già inserito");
                    }
                    break;

                // Registrazione visita
                case "2":
                    if (paziente == null) {
                        System.out.println("⚠️ Errore, inserire prima un paziente");
                    } else if (visita == null) {
                        visita = registraVisita();
                        paziente.setVisita(visita);
                        System.out.println("✅ Visita inserita correttamente!");
                    } else {
                        System.out.println("⚠️ Visita già inserita");
                    }
                    break;

                // Stampa della scheda del paziente
                case "3":
                    if (paziente != null) {
                        paziente.stampaScheda();
                    } else {
                        System.err.println("⚠️ Errore, assicurarsi di aver già registrato un paziente");
                    }
                    break;

                // Stampa codice di priorità
                case "4":
                    if (paziente == null) {
                        System.out.println("⚠️ Errore, assicurarsi di aver già registrato un paziente");
                    } else {
                        System.out.println("PRIORITÀ:");
                        System.out.println(paziente.calcolaPriorita());
                    }
                    break;

                // Uscita dal menu/chiusura programma
                case "5":
                    condizione1 = true;
                    System.out.println();
                    System.out.println("✅ Sessione terminata con successo, arrivederci");
                    break;

                // tutti gli altri casi
                default:
                    System.out.println("⚠️ Errore, inserire opzione valida");

                    sc.close();
            }
        }
    }

    // Metodo per registrazione del tipo di visita
    private static Visita registraVisita() {
        stampaMenuVisite();
        String sceltaVisita = sc.nextLine().trim();

        // Variabili iniziali assegnate a null
        Visita visita = null;
        String tipoVisita = null;
        String dat = null;
        String med = null;
        String dia = null;
        String rep = null;
        String parteCorpo = null;
        String frequenzaCard = null;
        String pressioneSist = null;
        String pressioneDiast = null;

        // Controlli generici
        // Controllo data
        boolean cond1 = false;
        while (!cond1) {
            System.out.println("Inserire data in formato gg/mm/aaaa: ");
            dat = sc.nextLine().trim().toUpperCase();
            if (dat.length() == 10 && (dat.substring(0, 2).matches("\\d++")) && (dat.charAt(2) == '/') && (dat.substring(3, 5).matches("\\d++")) && (dat.charAt(5) == '/') && (dat.substring(6, 10).matches("\\d++"))) {
                cond1 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, inserire data in formato gg/mm/aaaa! ");
            }
        }

        // Controllo medico
        boolean cond2 = false;
        while (!cond2) {
            System.out.println("Inserire il cognome del medico curante:");
            med = sc.nextLine().trim();
            med = med.substring(0, 1).toUpperCase() + med.substring(1);

            if (med.length() < 50 && med.length() > 0) {
                cond2 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, lunghezza testo non valida");
            }
        }

        // Controllo diagnosi
        boolean cond3 = false;
        while (!cond3) {
            System.out.println("Inserire la diagnosi:");
            dia = sc.nextLine().trim();
            dia = dia.substring(0, 1).toUpperCase() + dia.substring(1);

            if (dia.length() < 50 && dia.length() > 0) {
                cond3 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, lunghezza testo non valida");
            }
        }

        // Switch per gli attributi specifici della visita scelta 
        switch (sceltaVisita) {
            case "1":
                // Controllo e inserimento reparto (generica)
                boolean cond4 = false;
                while (!cond4) {
                    System.out.println("Inserire il reparto:");
                    rep = sc.nextLine().trim();
                    rep = rep.substring(0, 1).toUpperCase() + rep.substring(1);

                    if (rep.length() < 50 && rep.length() > 0) {
                        cond4 = true;
                    } else {
                        System.err.println("⚠️ Errore di inserimento, reparto non valido");
                    }
                }

                // Creazione visita specifica
                visita = new VisitaGenerica(rep, dat, med, dia);
                break;

            case "2":
                // Controllo e inserimento parteCorpo (ortopedica)
                boolean cond5 = false;
                while (!cond5) {
                    System.out.println("Inserire la zona del corpo interessata:");
                    parteCorpo = sc.nextLine().trim();
                    parteCorpo = parteCorpo.substring(0, 1).toUpperCase() + parteCorpo.substring(1);

                    if (parteCorpo.length() < 50 && parteCorpo.length() > 0) {
                        cond5 = true;
                    } else {
                        System.err.println("⚠️ Errore di inserimento, Errore di inserimento, lunghezza testo non valida");
                    }
                }

                // Creazione visita specifica
                visita = new VisitaOrtopedica(parteCorpo, dat, med, dia);
                break;

            case "3":
                // Controllo e inserimento frequenzaCard (cardiologica)
                boolean cond6 = false;
                while (!cond6) {
                    System.out.println("Inserire la frequenza cardiaca registrata:");
                    frequenzaCard = sc.nextLine().trim();
                    if (frequenzaCard.length() > 0 && (frequenzaCard.length() <= 3) && (frequenzaCard.matches("\\d++"))) {
                        cond6 = true;
                    } else {
                        System.err.println("⚠️ Errore di inserimento, inserire una cifra valida");
                    }
                }
                // Controllo pressioneSist
                boolean cond7 = false;
                while (!cond7) {
                    System.out.println("Inserire la pressione sistolica registrata:");
                    pressioneSist = sc.nextLine().trim();
                    if (pressioneSist.length() > 0 && (pressioneSist.length() <= 3) && (pressioneSist.matches("\\d++"))) {
                        cond7 = true;
                    } else {
                        System.err.println("⚠️ Errore di inserimento, inserire una cifra valida");
                    }
                }

                // Controllo pressioneDiast
                boolean cond8 = false;
                while (!cond8) {
                    System.out.println("Inserire la pressione diastolica registrata:");
                    pressioneDiast = sc.nextLine().trim();
                    if (pressioneDiast.length() > 0 && (pressioneDiast.length() <= 3) && (pressioneDiast.matches("\\d++"))) {
                        cond8 = true;
                    } else {
                        System.err.println("⚠️ Errore di inserimento, inserire una cifra valida");
                    }
                }

                // Creazione visita specifica
                visita = new VisitaCardiologica(frequenzaCard, pressioneSist, pressioneDiast, dat, med, dia);
        }
        return visita;
    }

    // Metodo menù scelta visite
    private static void stampaMenuVisite() {
        System.out.printf("%-40s%n", "=======================");
        System.out.println("\u001B[1m     MENÙ VISITE\u001B[0m");
        System.out.printf("%-40s%n", "=======================");
        System.out.println("\u001B[1m1\u001B[0m - Generica");
        System.out.println("\u001B[1m2\u001B[0m - Ortopedica");
        System.out.println("\u001B[1m3\u001B[0m - Cardiologica");
        System.out.printf("%-40s%n", "=======================");
        System.out.println("Inserisci il numero corrispondente al tipo di visita: ");
    }

    // Metodo menù principale
    private static void stampaMenu() {
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

    // Metodo di controllo con cicli while per registrazione paziente
    private static Paziente registraPaziente() {

        // Variabili iniziali assegnate a null
        String cF = null;
        String nom = null;
        String cogn = null;
        String col = null;

        // Inserire CF
        boolean cond1 = false;
        while (!cond1) {
            System.out.println("Inserire Codice Fiscale: ");
            cF = sc.nextLine().trim().toUpperCase();
            if (cF.length() == 16 && (!cF.isEmpty())) {
                cond1 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, inserire Codice Fiscale valido! ");
            }
        }

        // Inserire nome
        boolean cond2 = false;
        while (!cond2) {
            System.out.println("Inserire nome: ");
            nom = sc.nextLine().trim().toUpperCase();
            if (!nom.isEmpty() && nom.length() < 50) {
                cond2 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, inserire nome valido! ");
            }
        }

        // Inserire cognome
        boolean cond3 = false;
        while (!cond3) {
            System.out.println("Inserire cognome: ");
            cogn = sc.nextLine().trim().toUpperCase();
            if (!cogn.isEmpty() && cogn.length() < 50) {
                cond3 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, inserire cognome valido! ");
            }
        }

        // Inserire colore triage
        boolean cond4 = false;
        while (!cond4) {
            System.out.println("Inserire codice colore triage: ");
            col = sc.nextLine().trim().toUpperCase();
            if (col.equalsIgnoreCase("rosso") || col.equalsIgnoreCase("giallo") || col.equalsIgnoreCase("verde") || col.equalsIgnoreCase("bianco")) {
                cond4 = true;
            } else {
                System.err.println("⚠️ Errore di inserimento, inserire codice colore valido! ");
            }
        }

        // Creazione paziente
        Paziente paz = new Paziente(cF, nom, cogn, col);

        return paz;
    }

}
