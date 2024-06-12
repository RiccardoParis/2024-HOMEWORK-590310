package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	private String nomeAttrezzo;
	
	/**
	 * Posa un oggetto dalla borsa nella stanzaCorrente
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		if(this.nomeAttrezzo==null)
			console.mostraMessaggio("Che attrezzo vuoi posare?");
		Attrezzo attrezzoPosato=null;
		attrezzoPosato=partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzoPosato==null)
			console.mostraMessaggio("Non hai questo attrezzo nella borsa");
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzoPosato);
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			console.mostraMessaggio("Hai posato l'attrezzo nella stanza "+partita.getStanzaCorrente().getNome());
		}

	}

	@Override
	public void setParametro(String nomeAttrezzo) {
            this.nomeAttrezzo=nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
