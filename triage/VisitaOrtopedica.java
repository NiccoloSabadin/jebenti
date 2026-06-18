package triage;

public class VisitaOrtopedica extends Visita {
// attributi

    private String parteCorpo;

// costruttore
    public VisitaOrtopedica(String parteCor, String dat, String med, String dia) {
        super(dat, med, dia);
        parteCorpo = parteCor;
    }

// getter
    public String getParteCorpo() {
        return parteCorpo;
    }

// Metodo descriviTitoloVisita()
    @Override
    public String descriviTitoloVisita() {
        return "VISITA ORTOPEDICA";
    }

// metodo descriviVisita()
    @Override
    public String descriviVisita() {
        String descrizione = " Zona interessata:   " + parteCorpo;
        return descrizione;
    }
}
