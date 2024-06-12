package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{
private String nomeAttrezzo;
	
	/**
	 * Posa un oggetto dalla borsa nella stanzaCorrente
	 */
	@Override
	public void esegui(Partita partita,IO console) {
		if(this.nomeAttrezzo==null)
			console.mostraMessaggio("Che attrezzo vuoi regalare?");
		Attrezzo attrezzoRegalato=null;
		attrezzoRegalato=partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzoRegalato==null)
			console.mostraMessaggio("Non hai questo attrezzo nella borsa");
		else {
			if(partita.getStanzaCorrente().getPersonaggio()==null) console.mostraMessaggio("Non ci sta nessuno a cui regalare qualcosa");
			else {
				console.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoRegalato, partita));
				console.mostraMessaggio("Hai regalato l'attrezzo a "+partita.getStanzaCorrente().getPersonaggio().getNome());
			}
			
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
