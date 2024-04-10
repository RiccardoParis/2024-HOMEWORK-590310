package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	Stanza mensa;
	Stanza bar;
	Stanza ufficio;
	Attrezzo osso;
	Attrezzo lancia;
	Attrezzo pistola;
	
	/**
	 * Creo due stanze connesse tra loro tramite il metodo impostaStanzaAdiacente
	 * con i relativi attrezzi per testare il metodo getAttrezzi
	 */
	@BeforeEach
	public void setUp() {
		this.mensa=new Stanza("mensa");
		this.bar=new Stanza("bar");
		this.ufficio=new Stanza("ufficio");
		this.osso=new Attrezzo("osso",3);
		this.lancia=new Attrezzo("lancia",2);
		this.pistola=new Attrezzo("pistola",3);
		
		this.mensa.impostaStanzaAdiacente("nord", bar);
		this.bar.impostaStanzaAdiacente("sud", mensa);
		this.bar.addAttrezzo(osso);
	}
	
	/**
	 * Test del metodo GetStanzaAdiacente
	 */
	@Test
	public void testStanzaAdiacenteNotNull(){
		assertNotNull(this.bar.getStanzaAdiacente("sud"));
	}

	@Test
	public void testGetStanzaAdiacente() {
		assertEquals(bar,this.mensa.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetNuovaStanzaAdiacente() {
		this.mensa.impostaStanzaAdiacente("nord", ufficio);
		assertEquals(ufficio,this.mensa.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStanzaAdiacenteNull() {
		assertNull(this.bar.getStanzaAdiacente("est"));
	}
	
	/**
	 * Test del metodo getAttrezzo
	 */
	@Test
	public void testGetAttrezzoSbagliato() {
		assertNotEquals(osso,this.bar.getAttrezzo("spada"));
	}
	
	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertNull(this.mensa.getAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzoGiusto() {
		assertEquals(osso,this.bar.getAttrezzo("osso"));
	}
	
	@Test
	public void testGetDopoRemoveAttrezzo() {
		this.bar.addAttrezzo(lancia);
		this.bar.addAttrezzo(pistola);
		this.bar.removeAttrezzo("lancia");
		assertEquals(pistola,this.bar.getAttrezzo("pistola"));
	}
	
	
	
	
	/**
	 * Test del metodo removeAttrezzo
	 */
	@Test 
	public void testRemoveAttrezzoFalse() {
		assertFalse(this.bar.removeAttrezzo("spada"));
	}
	
	@Test
	public void testRemoveAttrezzoPrimo() {
		assertTrue(this.bar.removeAttrezzo("osso"));
	}
	
	@Test
	public void testRemoveAttrezzoUltimo() {
		this.bar.addAttrezzo(lancia);
		this.bar.addAttrezzo(pistola);
		assertTrue(this.bar.removeAttrezzo("pistola"));
	}
	
	@Test
	public void testRemoveAttrezzoAlCentro() {
		this.bar.addAttrezzo(lancia);
		this.bar.addAttrezzo(pistola);
		assertTrue(this.bar.removeAttrezzo("lancia"));
	}
	
	
	
	
	
	
}
   

