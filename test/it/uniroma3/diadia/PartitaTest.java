package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class PartitaTest {
	Partita partita;
	
	/**
	 * Inizializzo una partita
	 */
	@BeforeEach
	public void SetUp() {
		this.partita=new Partita();
	}
	
	
	
	
	/**
	 * Test per il metodo vinta
	 */
	@Test
	public void testVintaFalse() {
		assertFalse(this.partita.vinta());
	}
	
	/**
	 * Test per il metodo IsFinita
	 */
	@Test
	public void testIsFinitaFalse() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaTrue() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	
	
	

	

}
