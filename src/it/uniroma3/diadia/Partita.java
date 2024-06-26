package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Stanza stanzaCorrente;
    private Giocatore giocatore;
	private Labirinto labirinto;
	private boolean finita;
	
	
	public Partita(Labirinto labirinto) {
		this.labirinto=labirinto;
		this.giocatore=new Giocatore();
		this.finita = false;
		this.stanzaCorrente=this.labirinto.getStanzaIniziale();
	}
	
	

   
	public Partita(LabirintoBuilder labirinto) {
		this.labirinto=labirinto.getLabirinto();
		this.giocatore=new Giocatore();
		this.finita = false;
		this.stanzaCorrente=this.labirinto.getStanzaIniziale();
	}




	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}




	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}




	public Labirinto getLabirinto() {
		return labirinto;
	}




	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}


	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente== this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}




	public Giocatore getGiocatore() {
		return giocatore;
	}




	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	
}
