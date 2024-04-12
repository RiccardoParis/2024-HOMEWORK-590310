package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;



public class PartitaTest {
	Partita partita;
	
	/**
	 * Inizializzo una partita
	 */
	@BeforeEach
	public void SetUp() {
		Labirinto labirinto=new Labirinto();
		this.partita=new Partita(labirinto);
	}
	
	
	
	
	/**
	 * Test per il metodo vinta
	 */
	@Test
	public void testVinta_StanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testVinta_InizioPartita() {
		assertFalse(this.partita.vinta());
	}
	
	/**
	 * Test per il metodo IsFinita
	 */
	@Test
	public void testIsFinita_PartitaInCorso() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_0Cfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_PartitaFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	
	
	

	

}
