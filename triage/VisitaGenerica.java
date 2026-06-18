package triage;

public class VisitaGenerica extends Visita {

// attributi
    private String reparto;

// Costruttore
    public VisitaGenerica(String repart, String dat, String med, String dia) {
        super(dat, med, dia);
        reparto = repart;
    }

// getter
    public String getReparto() {
        return reparto;
    }

// Metodo descriviTitoloVisita()
    @Override
    public String descriviTitoloVisita() {
        return "VISITA GENERICA";
    }

// metodo descriviVisita()
    @Override
    public String descriviVisita() {
        String descrizione = " Reparto:\t     " + reparto;
        return descrizione;
    }

}
