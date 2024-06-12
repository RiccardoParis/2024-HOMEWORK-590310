package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;



public class PartitaTest {
	Partita partita;
	
	/**
	 * Inizializzo una partita
	 * @throws FormatoFileNonValidoException 
	 * @throws FileNotFoundException 
	 */
	@BeforeEach
	public void SetUp() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto labirinto=LabirintoBuilder.newBuilder().addStanzaIniziale("stanzaIniziale")
				.addStanzaVincente("stanzaVincente")
				.getLabirinto();
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
