package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	
	@Test
	public void testOrdinamento_Singleton(){
		this.stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		LinkedList<Stanza> ordinati=new LinkedList<>(this.stanza.getMapStanzeAdiacenti().values());
		assertEquals(1,ordinati.size());
	}
	
	@Test
	public void testOrdinamento_DueAdiacenze_StessoNumeroAttrezzi(){
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, new Stanza("Adiacenza"));
		this.stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		LinkedList<Stanza> ordinati=new LinkedList<>(this.stanza.getMapStanzeAdiacenti().values());
		assertEquals(2,ordinati.size());
	}
	
	@Test
	public void testOrdinamento_DueAdiacenze_DiversoNumeroAttrezzi(){
		
		this.stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, new Stanza("Adiacenza"));
		this.stanzaAdiacente.addAttrezzo(attrezzo);
		ComparatoreStanzaPerAttrezzi cmp=new ComparatoreStanzaPerAttrezzi();
		LinkedList<Stanza> ordinati=new LinkedList<>(this.stanza.getMapStanzeAdiacenti().values());
		Collections.sort(ordinati,cmp);
		assertEquals(new Stanza("Adiacenza"),ordinati.getFirst());
		assertEquals(stanzaAdiacente,ordinati.getLast());
	}
	
	/**
	 * Test del metodo GetStanzaAdiacente
	 */
	@Test
	public void testStanzaSenzaStanzeAdiacenti(){
		assertNull(this.stanza.getStanzaAdiacente(Direzione.SUD));
	}

	@Test
	public void testSingolaStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, stanzaAdiacente);
		assertEquals(stanzaAdiacente,this.stanza.getStanzaAdiacente(Direzione.SUD));
	}
	
	@Test
	public void testGetStanzaAdiacente_DirezioneErrata() {
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, stanzaAdiacente);
		assertNull(this.stanza.getStanzaAdiacente(Direzione.NORD));
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
	
	/**
	 * Test getAttrezzi
	 */
	@Test
	public void testGetAttrezzi_StanzaVuota() {
		assertNull(this.stanza.getAttrezzi());
	}
	
	@Test
	public void testGetAttrezzi_SingoloAttrezzo() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(List.of(attrezzo),this.stanza.getAttrezzi());
	}
	
	@Test
	public void testGetAttrezzi_DueAttrezzi() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzoInMezzo);
		assertEquals(List.of(attrezzo,attrezzoInMezzo),this.stanza.getAttrezzi());
	}
	
	@Test
	public void testGetAttrezzi_DueAttrezziUguali() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(List.of(attrezzo),this.stanza.getAttrezzi());
	}
	
	/**
	 * Test GetMapStanzeAdiacenti
	 */
	@Test
	public void testGetMapStanzeAdiacenti_StanzaIsolata() {
		assertNull(this.stanza.getMapStanzeAdiacenti());
	}
	
	@Test
	public void testGetMapStanzeAdiacenti_StanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		assertEquals(stanzaAdiacente,this.stanza.getMapStanzeAdiacenti().get(Direzione.NORD));
	}
	
	@Test
	public void testGetMapStanzeAdiacenti_DueStanzeAdiacente() {
		Stanza stanzaAdiacente_bis=new Stanza("Stanza Adiacente");
		this.stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, stanzaAdiacente_bis);
		assertEquals(stanzaAdiacente,this.stanza.getMapStanzeAdiacenti().get(Direzione.NORD));
		assertEquals(stanzaAdiacente_bis,this.stanza.getMapStanzeAdiacenti().get(Direzione.SUD));
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
	public void testRemoveAttrezzo_AttrezzoUltimoScalato() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzoInMezzo);
		this.stanza.addAttrezzo(attrezzoUltimo);
		this.stanza.removeAttrezzo("attrezzoInMezzo");
		assertEquals(attrezzoUltimo,this.stanza.getAttrezzo("attrezzoUltimo"));
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
   

