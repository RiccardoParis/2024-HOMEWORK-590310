package it.uniroma3.it.personaggi;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;;


class StregaTest {
	LabirintoBuilder labirintoBuilder;
	String nomeStanzaIniziale = "Atrio";
	String nomeStanzaVincente = "Uscita";

	@BeforeEach
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}
	
	@Test
	public void testMonolocale_stregaAssente() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertNull(monolocale.getStanzaIniziale().getPersonaggio());
	}

	@Test
	public void testMonolocale_stregaPresente() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.addStrega("Strega", "sono Strega piacere")
				.getLabirinto();
	assertEquals("Strega",monolocale.getStanzaIniziale().getPersonaggio().getNome());
	}
	
	@Test
	public void testBilocale_stregaStanzaVincente() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente).addStrega("Strega", "sono Strega piacere")
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, Direzione.NORD)
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, Direzione.SUD)
				.getLabirinto();
		assertEquals("Strega",bilocale.getStanzaVincente().getPersonaggio().getNome());
	}
}
