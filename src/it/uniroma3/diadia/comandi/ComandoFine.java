package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
    
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		partita.setFinita();
		console.mostraMessaggio("Grazie di aver giocato!"); 
	}

	

	@Override
	public String getNome() {
		return "fine";
	}

	


}
