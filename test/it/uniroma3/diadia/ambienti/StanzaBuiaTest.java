package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	StanzaBuia stanza;
	Attrezzo attrezzoGiusto;
	Attrezzo attrezzoSbagliato;
	
	@BeforeEach
	public void setUp() {
		this.stanza=new StanzaBuia("stanza","attrezzoGiusto");
		this.attrezzoGiusto =new Attrezzo("attrezzoGiusto",1);
		this.attrezzoSbagliato =new Attrezzo("attrezzoSbagliato",1);
	}

	@Test
	public void testAttrezzoSbagliato() {
		this.stanza.addAttrezzo(attrezzoSbagliato);
		assertEquals("qui c'è buio pesto, servirebbe posare qui una "+stanza.getAttrezzoNecessario()+" per vederci qualcosa",this.stanza.getDescrizione());
		
	}
	
	@Test
	public void testAttrezzoGiusto() {
		this.stanza.addAttrezzo(attrezzoGiusto);
		assertNotEquals("qui c'è buio pesto",this.stanza.getDescrizione());
		
	}

}
