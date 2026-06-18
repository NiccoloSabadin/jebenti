package triage;

public class VisitaCardiologicaFactory implements VisitaFactory {
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
