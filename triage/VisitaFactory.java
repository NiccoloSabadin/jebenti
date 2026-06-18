package triage;

public interface VisitaFactory {
	// Metodo factory generico
	Visita creaVisita(String data, String medico, String diagnosi, String... parametriSpecifici);
}