package it.uniroma3.it.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;;


class MagoTest {
	LabirintoBuilder labirintoBuilder;
	String nomeStanzaIniziale = "Atrio";
	String nomeStanzaVincente = "Uscita";

	@BeforeEach
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}
	
	@Test
	public void testMonolocale_magoAssente() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertNull(monolocale.getStanzaIniziale().getPersonaggio());
	}

	@Test
	public void testMonolocale_magoPresente() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.addMago("Mago", "ho un attrezzo per te","pugnale",3)
				.getLabirinto();
	assertEquals("Mago",monolocale.getStanzaIniziale().getPersonaggio().getNome());
	}
	
	@Test
	public void testBilocale_magoStanzaVincente() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente).addMago("Mago", "ho un attrezzo per te","pugnale",3)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, Direzione.NORD)
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, Direzione.SUD)
				.getLabirinto();
		assertEquals("Mago",bilocale.getStanzaVincente().getPersonaggio().getNome());
	}
	
	@Test
	public void testBilocale_magoStanzaIniziale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addMago("Mago", "ho un attrezzo per te","pugnale",3)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, Direzione.NORD)
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, Direzione.SUD)
				.getLabirinto();
		assertEquals("Mago",bilocale.getStanzaIniziale().getPersonaggio().getNome());
	}
	
	@Test
	public void testTrilocale_magoBiblioteca(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca").addMago("Mago", "ho un attrezzo per te","pugnale",3)
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", Direzione.SUD)
				.addAdiacenza("biblioteca", nomeStanzaIniziale, Direzione.NORD)
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, Direzione.EST)
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , Direzione.OVEST)
				.getLabirinto();	
		assertEquals("Mago", trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.SUD).getPersonaggio().getNome());
	}
}

