package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	static final private String[] elencoComandi = {"Comandi:vai aiuto fine prendi posa guarda interagisci saluta regala"};
    
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]);
		

	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	

}
