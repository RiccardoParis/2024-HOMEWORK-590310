package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto() {
		this.creaStanze();
	}
	
	
	 /**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave= new Attrezzo("chiave",1);
    	
		/* crea stanze del labirinto */
		StanzaBloccata atrio = new StanzaBloccata("Atrio","nord","chiave");
		StanzaBuia aulaN11 = new StanzaBuia("Aula N11","lanterna");
		StanzaMagica aulaN10 = new StanzaMagica("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		aulaN11.addAttrezzo(chiave);
		// il gioco comincia nell'atrio
        this.stanzaIniziale = atrio;  
		this.stanzaVincente = biblioteca;

		
    }
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzacorrente) {
		this.stanzaIniziale = stanzacorrente;
	}
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzavincente) {
		this.stanzaVincente = stanzavincente;
	}

}
