package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	StanzaMagica stanzaMagica;
	Attrezzo attrezzo;
	
	@BeforeEach
	private void setUp(){
		this.stanzaMagica=new StanzaMagica("stanzaMagica");
		this.attrezzo=new Attrezzo("attrezzo",1);
	}
	
    /**
     * test per il metodo addAttrezzo
     */
	@Test
	public void testComportamentoNormale() {
		this.stanzaMagica.addAttrezzo(attrezzo);
		assertTrue(this.stanzaMagica.hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testComportamentoStanzaMagica(){
		this.stanzaMagica.setContatoreAttrezziPosati(3);
		this.stanzaMagica.addAttrezzo(attrezzo);
		assertFalse(this.stanzaMagica.hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testComportamentoStanzaMagicaTrue(){
		this.stanzaMagica.setContatoreAttrezziPosati(3);
		this.stanzaMagica.addAttrezzo(attrezzo);
		assertTrue(this.stanzaMagica.hasAttrezzo("ozzertta"));
	}
	
	
	
	
	

}
