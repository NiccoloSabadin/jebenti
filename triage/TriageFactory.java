package triage;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory Centrale (Pattern Factory Method combinato con un Registro statico).
 * Svolge il ruolo di unico punto di ingresso per la creazione di qualsiasi
 * istanza di Visita.
 * Disaccoppia completamente i Manager o i Controller del sistema dalle classi
 * concrete
 * (VisitaCardiologica, VisitaOrtopedica, VisitaGenerica).
 */
public class TriageFactory {

	/**
	 * Enumerazione fortemente tipizzata per definire i tipi di visite supportati.
	 * Impedisce errori di battitura tipici delle stringhe grezze (es. "cardiologia"
	 * vs "CARDIOLOGICA").
	 */
	public enum TipoVisita {
		GENERICA,
		ORTOPEDICA,
		CARDIOLOGICA
	}

	/**
	 * Registro dei sotto-factory. Associa ogni costante dell'Enum alla propria
	 * factory dedicata.
	 * Questo approccio evita l'uso di catene di 'if-else' o 'switch' pesanti,
	 * sfruttando
	 * il polimorfismo per recuperare l'istanza corretta in tempo costante O(1).
	 */
	private static final Map<TipoVisita, VisitaFactory> factoryMap = new HashMap<>();

	// Blocco di inizializzazione statica: viene eseguito una sola volta al
	// caricamento della classe in memoria
	static {
		factoryMap.put(TipoVisita.GENERICA, new VisitaGenericaFactory());
		factoryMap.put(TipoVisita.ORTOPEDICA, new VisitaOrtopedicaFactory());
		factoryMap.put(TipoVisita.CARDIOLOGICA, new VisitaCardiologicaFactory());
	}

	/**
	 * Metodo di fabbricazione centralizzato.
	 * * @param tipo Il tipo di visita da istanziare (Enum)
	 * 
	 * @param data               La data della visita (Parametro comune)
	 * @param medico             Il cognome del medico curante (Parametro comune)
	 * @param diagnosi           La diagnosi rilevata (Parametro comune)
	 * @param parametriSpecifici Array varargs di stringhe contenente i dati extra
	 *                           necessari
	 *                           esclusivamente al sotto-factory di destinazione.
	 * @return Un'istanza della sottoclasse specifica di Visita
	 * @throws IllegalArgumentException Se viene richiesto un tipo di visita non
	 *                                  configurato nella mappa
	 */
	public static Visita generaVisita(TipoVisita tipo, String data, String medico, String diagnosi,
			String... parametriSpecifici) {

		// 1. Recupera il sotto-factory specializzato dalla mappa di registro
		VisitaFactory factory = factoryMap.get(tipo);

		// 2. Controllo di sicurezza: se per assurdo l'enum esiste ma non è mappato,
		// solleva un'eccezione
		if (factory == null) {
			throw new IllegalArgumentException("⚠️ Errore di configurazione interna: Tipo di visita non supportato.");
		}

		// 3. DELEGAZIONE: Il factory principale passa i dati al sotto-factory e
		// restituisce il prodotto finito
		return factory.creaVisita(data, medico, diagnosi, parametriSpecifici);
	}
}