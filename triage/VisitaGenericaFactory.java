package triage;

/**
 * Sotto-Factory specializzato nella creazione di oggetti di tipo
 * {@link VisitaGenerica}.
 * Implementa l'interfaccia {@link VisitaFactory} per integrarsi nel meccanismo
 * di creazione polimorfica gestito da {@link TriageFactory}.
 */
public class VisitaGenericaFactory implements VisitaFactory {

    /**
     * Implementa la logica di creazione per una visita generica, estraendo
     * il reparto dai parametri variabili.
     *
     * @param data               La data in cui è avvenuta la visita.
     * @param medico             Il cognome del medico curante.
     * @param diagnosi           La diagnosi formulata.
     * @param parametriSpecifici Array varargs dove l'indice [0] deve contenere il
     *                           nome del reparto.
     *                           Se assente, viene impostato un valore di default.
     * @return Un'istanza di {@link VisitaGenerica}.
     */
    @Override
    public Visita creaVisita(String data, String medico, String diagnosi, String... parametriSpecifici) {
        // Per la VisitaGenerica, ci aspettiamo che il reparto sia il primo elemento dei
        // parametri extra
        String reparto = (parametriSpecifici != null && parametriSpecifici.length > 0)
                ? parametriSpecifici[0]
                : "Non specificato";

        return new VisitaGenerica(reparto, data, medico, diagnosi);
    }
}