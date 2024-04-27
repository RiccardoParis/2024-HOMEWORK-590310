package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	StanzaBloccata stanza;
	Stanza stanzaAdiacente;
	Attrezzo passpartout;
	
	@BeforeEach
	public void setUp() {
		this.stanza =new StanzaBloccata("stanza","nord","passpartout");
		this.stanzaAdiacente =new Stanza("stanzaAdiacente");
		this.passpartout=new Attrezzo("passpartout",1);
	}

	@Test
	public void testGetStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		this.stanza.addAttrezzo(passpartout);
		assertEquals(stanzaAdiacente,this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteChiusa() {
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanza,this.stanza.getStanzaAdiacente("nord"));
	}

}
