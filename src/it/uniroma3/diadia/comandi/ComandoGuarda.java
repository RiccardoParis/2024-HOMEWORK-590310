package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita,IO console) {
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		console.mostraMessaggio("Al momento possiedi "+partita.getGiocatore().getCfu()+" CFU");
	}

	

	@Override
	public String getNome() {
		
		return "guarda";
	}

	

}
