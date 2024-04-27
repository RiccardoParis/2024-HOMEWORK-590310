package it.uniroma3.diadia.comandi;

public interface FabbricaDiComandi {
	
	/**
	 * Riceve una stringa istruzione e ritorna la classe Comando corrispondente
	 * @param istruzione
	 * @return
	 */
	public Comando costruisciComando(String istruzione);
	
	public String getNome(String istruzione);
	
	public String getParametro(String istruzione);

}
