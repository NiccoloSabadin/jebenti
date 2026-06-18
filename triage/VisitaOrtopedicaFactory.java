package triage;

/**
 * Sotto-Factory specializzato nella fabbricazione di oggetti di tipo
 * VisitaOrtopedica.
 * Implementa l'interfaccia VisitaFactory per integrarsi nel meccanismo di
 * delega polimorfica.
 */
public class VisitaOrtopedicaFactory implements VisitaFactory {

	/**
	 * Sovrascrive il metodo factory generico per estrarre le informazioni
	 * specifiche
	 * e chiamare il costruttore corretto di VisitaOrtopedica.
	 * * @param data La data della visita (ereditata da Visita)
	 * 
	 * @param medico             Il cognome del medico curante (ereditato da Visita)
	 * @param diagnosi           La diagnosi iniziale (ereditata da Visita)
	 * @param parametriSpecifici Array varargs dove l'indice [0] deve contenere la
	 *                           parte del corpo
	 * @return Un'istanza pronta di VisitaOrtopedica trattata come tipo astratto
	 *         Visita
	 */
	@Override
	public Visita creaVisita(String data, String medico, String diagnosi, String... parametriSpecifici) {

		// Controllo difensivo: verifichiamo che l'array varargs esista e contenga
		// almeno un elemento.
		// Se l'utente non ha inserito nulla, usiamo un valore di fallback sicuro per
		// evitare eccezioni.
		String parteCorpo = (parametriSpecifici != null && parametriSpecifici.length > 0)
				? parametriSpecifici[0]
				: "Non specificata";

		// Inizializzazione e ritorno dell'oggetto specifico tramite il suo costruttore
		// dedicato
		return new VisitaOrtopedica(parteCorpo, data, medico, diagnosi);
	}
}