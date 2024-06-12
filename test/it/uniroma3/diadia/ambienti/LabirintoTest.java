package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;



public class LabirintoTest {
	Labirinto labirinto;
	LabirintoBuilder labirintoBuilder=LabirintoBuilder.newBuilder();
	
	@BeforeEach
	public void setUp() {
		this.labirinto=labirintoBuilder.addStanzaIniziale("stanzaIniziale")
				.addStanzaVincente("stanzaVincente")
				.getLabirinto();
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
		assertNotEquals(this.labirinto.getStanzaIniziale().getNome(),this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testStanzaVincente_StanzaVincente() {
		assertEquals("stanzaVincente",this.labirinto.getStanzaVincente().getNome());
	}
	
	

}
