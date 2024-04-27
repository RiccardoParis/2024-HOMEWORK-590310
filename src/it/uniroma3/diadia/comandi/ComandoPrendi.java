package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;
    
	/**
	 * Prende un oggetto dalla stanza e lo mette nella borsa
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		if(this.nomeAttrezzo==null)
			console.mostraMessaggio("Che attrezzo vuoi prendere?");
		Attrezzo attrezzoPreso=null;
		attrezzoPreso=partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(attrezzoPreso==null)
			console.mostraMessaggio("Attrezzo non presente");
		else {
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPreso);
			partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			console.mostraMessaggio("Attrezzo messo nella borsa");
		}

	}

	@Override
	public void setParametro(String nomeAttrezzo) {
         this.nomeAttrezzo=nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
