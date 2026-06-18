package triage;

/**
 * La classe TriageManager funge da Controller (nell'architettura MVC).
 * Gestisce il flusso dei dati, coordina l'input dell'utente tramite il 'reader'
 * e delega la creazione delle entità alla Factory e ai costruttori.
 */
public class TriageManager {
	// Dipendenze final: una volta assegnate nel costruttore non cambiano più
	// (immutabilità)
	private final InputTriageReader reader; // Componente per la lettura e validazione dell'input da tastiera
	private final TriageView view; // Componente per la gestione dell'output visivo e degli errori

	/**
	 * Costruttore con Dependency Injection (Iniezione delle dipendenze).
	 * Riceve i componenti di I/O dall'esterno per garantire modularità e
	 * testabilità.
	 */
	public TriageManager(InputTriageReader reader, TriageView view) {
		this.reader = reader;
		this.view = view;
	}

	/**
	 * Gestisce il processo di input e creazione di un nuovo oggetto Paziente.
	 * 
	 * @return un'istanza della classe Paziente con dati validati.
	 */
	public Paziente creaNuovoPaziente() {
		String cF;
		// Ciclo infinito di validazione: si interrompe solo quando il Codice Fiscale è
		// corretto
		while (true) {
			// Legge la stringa di input e la converte immediatamente in maiuscolo
			cF = reader.leggiStringa("Inserire Codice Fiscale: ").toUpperCase();
			// Controllo di consistenza: il Codice Fiscale italiano deve essere di 16
			// caratteri
			if (cF.length() == 16)
				break; // Condizione soddisfatta, esce dal ciclo while
			// Se la lunghezza è errata, mostra l'errore tramite la View e ripete il ciclo
			view.mostraErrore("⚠️ Errore di inserimento, inserire Codice Fiscale valido (16 caratteri)! ");
		}

		// Acquisisce nome e cognome convertendoli in maiuscolo per standardizzazione
		// nel DB/Sistema
		String nome = reader.leggiStringa("Inserire nome: ").toUpperCase();
		String cognome = reader.leggiStringa("Inserire cognome: ").toUpperCase();

		// Acquisisce il colore del triage sfruttando la logica di controllo interna del
		// reader
		String colore = reader.leggiColoreTriage();

		// Ritorna l'oggetto Paziente istanziato con i dati appena validati
		return new Paziente(cF, nome, cognome, colore);
	}

	/**
	 * Coordina la scelta e la creazione di una Visita medica specifica.
	 * 
	 * @return un'istanza di Visita (o una sua sottoclasse) oppure null in caso di
	 *         errore.
	 */
	public Visita gestisciRegistrazioneVisita() {
		// Mostra a schermo il sotto-menù contenente le tipologie di visite disponibili
		view.stampaMenuVisite();
		// Legge la scelta dell'utente dall'input tipizzato
		String sceltaVisita = reader.leggiStringa("");

		// Dichiara la variabile per il TipoVisita (Enum definito all'interno della
		// Factory)
		TriageFactory.TipoVisita tipo;
		// Switch-case per mappare l'input testuale dell'utente ("1", "2", "3") sul
		// corrispettivo valore Enum
		switch (sceltaVisita) {
			case "1":
				tipo = TriageFactory.TipoVisita.GENERICA; // Opzione 1: Visita Generica
				break;
			case "2":
				tipo = TriageFactory.TipoVisita.ORTOPEDICA; // Opzione 2: Visita Ortopedica
				break;
			case "3":
				tipo = TriageFactory.TipoVisita.CARDIOLOGICA; // Opzione 3: Visita Cardiologica
				break;
			default:
				// Se l'input non corrisponde a nessun case, mostra l'errore e interrompe il
				// metodo ritornando null
				view.mostraErrore("⚠️ Scelta visita non valida.");
				return null;
		}

		// Acquisisce i dati comuni a tutte le visite usando metodi robusti del reader
		String data = reader.leggiDataObbligatoria(); // Forza l'utente a inserire una data sintatticamente corretta
		String medico = reader.leggiTestoValidato("Inserire il cognome del medico curante:", 50); // Limita l'input a 50
																									// caratteri
		String diagnosi = reader.leggiTestoValidato("Inserire la diagnosi:", 50); // Limita l'input a 50 caratteri

		// Invoca il metodo helper privato per raccogliere i parametri unici richiesti
		// da quella specifica visita
		String[] parametriSpecifici = acquisisciParametriSpecifici(tipo);

		// DISACCOPPIAMENTO (Factory Pattern): Delega la reale istanziazione
		// dell'oggetto alla Factory.
		// Il TriageManager non sa (e non gli interessa sapere) quale classe concreta
		// verrà creata.
		return TriageFactory.generaVisita(tipo, data, medico, diagnosi, parametriSpecifici);
	}

	/**
	 * Metodo di supporto privato per isolare l'acquisizione dei parametri specifici
	 * delle sottoclassi.
	 * 
	 * @param tipo il tipo di visita selezionato (Enum)
	 * @return un array di stringhe contenente i parametri specifici pronti per la
	 *         Factory
	 */
	private String[] acquisisciParametriSpecifici(TriageFactory.TipoVisita tipo) {
		// Seleziona i controlli di input basandosi sul tipo di Enum
		switch (tipo) {
			case GENERICA:
				// Richiede il reparto specifico per la visita generica
				String rep = reader.leggiStringa("Inserire il reparto:");
				// Se l'input è vuoto assegna un valore di default, altrimenti capitalizza la
				// stringa (es. "medicina" -> "Medicina")
				return new String[] { rep.isEmpty() ? "Generico" : ValidatoreDati.capitalizza(rep) };

			case ORTOPEDICA:
				// Richiede la regione anatomica per la visita ortopedica
				String parteCorpo = reader.leggiStringa("Inserire la zona del corpo interessata:");
				// Gestisce il valore vuoto di default o applica la capitalizzazione tramite la
				// classe di validazione
				return new String[] {
						parteCorpo.isEmpty() ? "Non Specificata" : ValidatoreDati.capitalizza(parteCorpo) };

			case CARDIOLOGICA:
				// La visita cardiologica richiede 3 parametri vitali distinti
				String freq = reader.leggiStringa("Inserire la frequenza cardiaca registrata:");
				String sist = reader.leggiStringa("Inserire la pressione sistolica registrata:");
				String diast = reader.leggiStringa("Inserire la pressione diastolica registrata:");
				// Impacchetta i 3 parametri testuali dentro un array ordinato da passare ai
				// Varargs della Factory
				return new String[] { freq, sist, diast };

			default:
				// Caso di sicurezza: restituisce un array vuoto a zero elementi se non trova
				// corrispondenze
				return new String[0];
		}
	}
}