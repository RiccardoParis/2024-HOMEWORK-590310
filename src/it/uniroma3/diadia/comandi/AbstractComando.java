package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	private String parametro;

	/**
	 * Esecuzione del comando
	 */
	public abstract void esegui(Partita partita,IO console);
	
	/**
	 * Parametro del comando
	 */
	
	
	public  abstract String getNome();
	
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}

}
