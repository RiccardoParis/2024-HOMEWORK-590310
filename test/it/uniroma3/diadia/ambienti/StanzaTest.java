package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	Stanza stanza;
	Stanza stanzaAdiacente;
	Attrezzo attrezzo;
	Attrezzo attrezzoInMezzo;
	Attrezzo attrezzoUltimo;
	
	
	/**
	 * Creo due stanze connesse tra loro tramite il metodo impostaStanzaAdiacente
	 * con i relativi attrezzi per testare il metodo getAttrezzi
	 */
	@BeforeEach
	public void setUp() {
		this.stanza=new Stanza("stanza");
		this.stanzaAdiacente=new Stanza("stanzaAdiacente");
		this.attrezzo=new Attrezzo("attrezzo",1);
		this.attrezzoInMezzo=new Attrezzo("attrezzoInMezzo",2);
		this.attrezzoUltimo=new Attrezzo("attrezzoUltimo",3);
	}
	
	/**
	 * Test del metodo GetStanzaAdiacente
	 */
	@Test
	public void testStanzaSenzaStanzeAdiacenti(){
		assertNull(this.stanza.getStanzaAdiacente("sud"));
	}

	@Test
	public void testSingolaStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente("sud", stanzaAdiacente);
		assertEquals(stanzaAdiacente,this.stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacente_DirezioneErrata() {
		this.stanza.impostaStanzaAdiacente("sud", stanzaAdiacente);
		assertNull(this.stanza.getStanzaAdiacente("nord"));
	}
	
	
	/**
	 * Test del metodo getAttrezzo
	 */
	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertNull(this.stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testGetAttrezzo_SingoloAttrezzo() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo,this.stanza.getAttrezzo("attrezzo"));
	}	
	
	@Test
	public void testStanzaPiena() {
		this.stanza.setNumeroAttrezzi(10);
		this.stanza.addAttrezzo(attrezzo);
		assertNull(this.stanza.getAttrezzo("attrezzo"));
	}
	
	
	
	/**
	 * Test del metodo removeAttrezzo
	 */
	@Test 
	public void testRemoveAttrezzo_StanzaVuota() {
		assertFalse(this.stanza.removeAttrezzo("attrezzo"));
	}
	
	@Test
	public void testRemoveAttrezzo_SingoloAttrezzo() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.removeAttrezzo("attrezzo"));
	}
	
	@Test
	public void testRemoveAttrezzo_AttrezzoInMezzo() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzoInMezzo);
		this.stanza.addAttrezzo(attrezzoUltimo);
		this.stanza.removeAttrezzo("attrezzoInMezzo");
		assertNull(this.stanza.getAttrezzo("attrezzoInMezzo"));
	}
	
	@Test
	public void testRemoveAttrezzo_AttrezzoUltimo() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzoInMezzo);
		this.stanza.addAttrezzo(attrezzoUltimo);
		this.stanza.removeAttrezzo("attrezzoUltimo");
		assertNull(this.stanza.getAttrezzo("attrezzoUltimo"));
	}
	
	
	
	
	
	
}
   

