package triage;

public class VisitaGenericaFactory implements VisitaFactory {

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