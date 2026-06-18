package triage;

public class VisitaCardiologica extends Visita {

// attributi
    private String frequenzaCardiaca;
    private String pressioneSist;
    private String pressioneDiast;

// costruttore
    public VisitaCardiologica(String frequenzaCard, String pressioneSis, String pressioneDias, String dat, String med, String dia) {
        super(dat, med, dia);
        frequenzaCardiaca = frequenzaCard;
        pressioneSist = pressioneSis;
        pressioneDiast = pressioneDias;
    }

// getter
    public String getFrequenzaCardiaca() {
        return frequenzaCardiaca;
    }

    public String getPressioneSist() {
        return pressioneSist;
    }

    public String getPressioneDiast() {
        return pressioneDiast;
    }

// Metodo descriviTitoloVisita()
    @Override
    public String descriviTitoloVisita() {
        return "VISITA CARDIOLOGICA";
    }
// metodo descrivi visita

    @Override
    public String descriviVisita() {
        String descrizione = "";
        descrizione += " Freq. cardiaca:     " + frequenzaCardiaca + " bpm\n";
        descrizione += " Pressione:          " + pressioneSist + "/" + pressioneDiast + " mmHg";
        return descrizione;
    }

}
