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
	public void testStanzaVincente_Esiste() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testStanzaVincente_StanzaIniziale(){
		assertNotEquals(this.labirinto.getStanzaIniziale(),this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testStanzaVincente_StanzaVincente() {
		assertEquals("Biblioteca",this.labirinto.getStanzaVincente().getNome());
	}
	
	

}
