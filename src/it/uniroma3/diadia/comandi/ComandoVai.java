package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	private String direzione;
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		if(this.direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		else {
			Stanza prossimaStanza = null;
			try {
				Direzione direzioneEnum=Direzione.valueOf(direzione);
				prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzioneEnum);
			}catch(IllegalArgumentException e) {
				
			}
			
			if (prossimaStanza == null)
				console.mostraMessaggio("Direzione inesistente");
			else {
				partita.setStanzaCorrente(prossimaStanza);
				int cfu = partita.getGiocatore().getCfu();
				partita.getGiocatore().setCfu(cfu--);
			}
		}
		
		
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	public ComandoVai() {
		this.setParametro(direzione);
	}
	
	@Override
	public void setParametro(String direzione) {
		this.direzione=direzione;
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
	

}
