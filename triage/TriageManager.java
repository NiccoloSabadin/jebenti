package triage;

public class TriageManager {
	private final InputTriageReader reader;
	private final TriageView view;

	public TriageManager(InputTriageReader reader, TriageView view) {
		this.reader = reader;
		this.view = view;
	}

	public Paziente creaNuovoPaziente() {
		String cF;
		while (true) {
			cF = reader.leggiStringa("Inserire Codice Fiscale: ").toUpperCase();
			if (cF.length() == 16)
				break;
			view.mostraErrore("⚠️ Errore di inserimento, inserire Codice Fiscale valido (16 caratteri)! ");
		}

		String nome = reader.leggiStringa("Inserire nome: ").toUpperCase();
		String cognome = reader.leggiStringa("Inserire cognome: ").toUpperCase();
		String colore = reader.leggiColoreTriage();

		return new Paziente(cF, nome, cognome, colore);
	}

	public Visita gestisciRegistrazioneVisita() {
		view.stampaMenuVisite();
		String sceltaVisita = reader.leggiStringa("");

		TriageFactory.TipoVisita tipo;
		switch (sceltaVisita) {
			case "1":
				tipo = TriageFactory.TipoVisita.GENERICA;
				break;
			case "2":
				tipo = TriageFactory.TipoVisita.ORTOPEDICA;
				break;
			case "3":
				tipo = TriageFactory.TipoVisita.CARDIOLOGICA;
				break;
			default:
				view.mostraErrore("⚠️ Scelta visita non valida.");
				return null;
		}

		String data = reader.leggiDataObbligatoria();
		String medico = reader.leggiTestoValidato("Inserire il cognome del medico curante:", 50);
		String diagnosi = reader.leggiTestoValidato("Inserire la diagnosi:", 50);

		String[] parametriSpecifici = acquisisciParametriSpecifici(tipo);
		return TriageFactory.generaVisita(tipo, data, medico, diagnosi, parametriSpecifici);
	}

	private String[] acquisisciParametriSpecifici(TriageFactory.TipoVisita tipo) {
		switch (tipo) {
			case GENERICA:
				String rep = reader.leggiStringa("Inserire il reparto:");
				return new String[] { rep.isEmpty() ? "Generico" : ValidatoreDati.capitalizza(rep) };
			case ORTOPEDICA:
				String parteCorpo = reader.leggiStringa("Inserire la zona del corpo interessata:");
				return new String[] { parteCorpo.isEmpty() ? "Non Specificata" : ValidatoreDati.capitalizza(parteCorpo) };
			case CARDIOLOGICA:
				String freq = reader.leggiStringa("Inserire la frequenza cardiaca registrata:");
				String sist = reader.leggiStringa("Inserire la pressione sistolica registrata:");
				String diast = reader.leggiStringa("Inserire la pressione diastolica registrata:");
				return new String[] { freq, sist, diast };
			default:
				return new String[0];
		}
	}
}