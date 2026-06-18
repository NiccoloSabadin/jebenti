package triage;

/**
 * Modello di dati che rappresenta una visita generica.
 * Estende la classe base {@link Visita} aggiungendo il dettaglio del reparto
 * specifico.
 */
public class VisitaGenerica extends Visita {

    // Attributi specifici della visita generica
    private String reparto;

    /**
     * Costruttore completo per inizializzare una visita generica.
     *
     * @param repart Nome del reparto di competenza.
     * @param dat    Data della visita.
     * @param med    Cognome del medico.
     * @param dia    Descrizione della diagnosi.
     */
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
