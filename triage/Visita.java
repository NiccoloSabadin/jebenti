package triage;

public abstract class Visita {

// attributi d'istanza
    protected String data;
    protected String medico;
    protected String diagnosi;

// costruttore
    public Visita(String dat, String med, String dia) {

        data = dat;
        medico = med;
        diagnosi = dia;

    }

// getter
    public String getData() {
        return data;
    }

    public String getMedico() {
        return medico;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    // Metodo abstract Titolo
    public abstract String descriviTitoloVisita();

    // metodo abstract Descrizione
    public abstract String descriviVisita();
}
