package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	@Override
	public void esegui(Partita partita,IO console) {
		console.mostraMessaggio("Comando sconosciuto");

	}

	@Override
	public String getNome() {
		
		return null;
	}

	

}
