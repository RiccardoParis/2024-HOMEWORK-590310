package it.uniroma3.diadia.comandi;

public interface FabbricaDiComandi {
	
	/**
	 * Riceve una stringa istruzione e ritorna la classe Comando corrispondente
	 * @param istruzione
	 * @return
	 * @throws Exception 
	 */
	public AbstractComando costruisciComando(String istruzione) throws Exception;
	
	public String getNome(String istruzione);
	
	public String getParametro(String istruzione);

}
