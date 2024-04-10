package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class LabirintoTest {
	Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.labirinto=new Labirinto();
	}

	/**
	 * Test per il metodo getStanzaVincente
	 */
	@Test
	public void testStanzaVincenteNotNull() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testStanzaVincenteSbagliata(){
		assertNotEquals("Atrio",this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testStanzaVincenteCorretta() {
		assertEquals("Biblioteca",this.labirinto.getStanzaVincente().getNome());
	}
	
	/**
	 * Test per il metodo getStanzaCorrente
	 */
	@Test
	public void testStanzaCorrenteNotNull() {
		assertNotNull(this.labirinto.getStanzaCorrente());
	}
	
	@Test
	public void testStanzaCorrenteSbagliata(){
		assertNotEquals("Aula N11",this.labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testStanzaCorrenteCorretta() {
		assertEquals("Atrio",this.labirinto.getStanzaCorrente().getNome());
	}
	

}
