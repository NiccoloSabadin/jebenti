package triage;

/**
 * Sotto-Factory specializzato nella creazione di oggetti di tipo
 * {@link VisitaCardiologica}.
 * Gestisce l'estrazione dei parametri vitali (frequenza e pressione) necessari
 * per il modello.
 */
public class VisitaCardiologicaFactory implements VisitaFactory {

	/**
	 * Crea una visita cardiologica validando la presenza dei parametri specifici
	 * necessari.
	 *
	 * @param data               Data della visita.
	 * @param medico             Medico curante.
	 * @param diagnosi           Diagnosi rilevata.
	 * @param parametriSpecifici Array atteso: [0] Frequenza, [1] Pressione
	 *                           Sistolica, [2] Pressione Diastolica.
	 * @return Un'istanza di {@link VisitaCardiologica}.
	 * @throws IllegalArgumentException Se i parametri specifici forniti sono meno
	 *                                  di 3.
	 */
	@Override
	public Visita creaVisita(String data, String medico, String diagnosi, String... parametriSpecifici) {
		if (parametriSpecifici.length < 3) {
			throw new IllegalArgumentException(
					"Parametri insufficienti per Visita Cardiologica. Richiesti: Frequenza, PresSist, PresDiast.");
		}
		String frequenza = parametriSpecifici[0];
		String presSist = parametriSpecifici[1];
		String presDiast = parametriSpecifici[2];

		return new VisitaCardiologica(frequenza, presSist, presDiast, data, medico, diagnosi);
	}
}
