package triage;

public class Paziente {

    //attributi d'istanza
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String coloreTriage;
    private Visita visita;

    // costruttore con all'interno visita direttamente assegnata a null
    public Paziente(String cF, String nom, String cogn, String col) {
        if (cF.length() == 16) {
            codiceFiscale = cF.toUpperCase();
        } else {
            System.out.println("AAAA");
        }

        if (nom.length() < 50 && nom.length() > 0) {
            nome = nom;
        }

        if (cogn.length() < 50 && cogn.length() > 0) {
            cognome = cogn;
        }

        if (col.equalsIgnoreCase("rosso") || col.equalsIgnoreCase("giallo") || col.equalsIgnoreCase("verde") || col.equalsIgnoreCase("bianco")) {
            coloreTriage = col;
        }

        visita = null;
    }

    // getter
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNomeCompleto() {
        String nomeCompleto = nome + " " + cognome;
        return nomeCompleto.toUpperCase();
    }

    public String getColoreTriage() {
        return coloreTriage;
    }

    public Visita getVisita() {
        return visita;
    }

    //setter
    public void setVisita(Visita v) {
        visita = v;
    }

    public void setCodiceFiscale(String cF) {
        if (cF.length() == 16) {
            codiceFiscale = cF;
        }
    }

    public void setNome(String nom) {
        if (nome.length() < 50 && nome.length() > 0) {
            nome = nom;
        }
    }

    public void setCognome(String cogn) {
        if (cognome.length() < 50 && cognome.length() > 0) {
            cognome = cogn;
        }
    }

    public void setColoreTriage(String col) {
        if (col.equalsIgnoreCase("rosso") || col.equalsIgnoreCase("giallo") || col.equalsIgnoreCase("verde") || col.equalsIgnoreCase("bianco")) {
            coloreTriage = col;
        }

    }

    // metodo calcola priorità 
    public String calcolaPriorita() {
        if (visita == null) {
            return "\u001B[1mNON VISITATO\u001B[0m";
        } else {
            if (coloreTriage.equalsIgnoreCase("rosso")) {
                if (visita instanceof VisitaCardiologica) {
                    return "\u001B[1mCODICE 1 - EMERGENZA\u001B[0m";
                } else {
                    return "\u001B[1mCODICE 2 - URGENTE\u001B[0m";
                }
            }
            if (coloreTriage.equalsIgnoreCase("giallo")) {
                return "\u001B[1mCODICE 3 - PRIORITÀ\u001B[0m";
            }
            return "\u001B[1mCODICE 4 - STANDARD\u001B[0m";
        }
    }

    // metodo stampa scheda
    public void stampaScheda() {
        System.out.printf("%-40s%n", "========================================");
        System.out.printf(" %-40s%n", "    \u001B[1mSCHEDA PAZIENTE PRONTO SOCCORSO\u001B[0m");
        System.out.printf("%-40s%n", "========================================");
        System.out.printf("%-40s%n", "========================================");
        System.out.printf(" %-20s%-20s%n", "Codice Fiscale:", codiceFiscale);
        System.out.printf(" %-20s%-20s%n", "Paziente:", getNomeCompleto());
        System.out.printf(" %-20s%-20s%n", "Triage:", coloreTriage);
        System.out.printf("%-40s%n", "========================================");

        // Controllo in caso di visita non ancora inserita
        if (visita != null) {
            System.out.printf(" %-40s%n", visita.descriviTitoloVisita().toUpperCase());
            System.out.printf(" %-20s%-20s%n", "Data:", visita.getData());
            System.out.printf(" %-20s%-20s%n", "Medico:", "Dr. " + visita.getMedico());
            System.out.printf(" %-20s%-20s %n", "Diagnosi:", visita.getDiagnosi());
            System.out.println(visita.descriviVisita());
            System.out.printf("%-40s%n", "========================================");
            System.out.printf(" %-18s%-22s%n", "\u001B[1mPRIORITÀ\u001B[0m:", calcolaPriorita());
            System.out.printf("%-40s%n", "========================================");
        } else {
            System.out.printf(" %-40s%n", "    ❌  \u001B[1mNESSUNA VISITA INSERITA\\u001B[0m");
            System.out.printf("%-40s%n", "========================================");
        }

    }

}
