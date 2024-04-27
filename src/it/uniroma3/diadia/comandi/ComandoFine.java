package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
    
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		partita.setFinita();
		console.mostraMessaggio("Grazie di aver giocato!"); 
	}

	@Override
	public void setParametro(String parametro) {
		
		
	}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}


}