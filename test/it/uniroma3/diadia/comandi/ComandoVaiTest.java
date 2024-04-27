package it.uniroma3.diadia.comandi;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoVaiTest {
	ComandoVai comando;
	Partita partita;
	Labirinto labirinto;
	IO console;
	Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.console=new IOConsole();
		this.labirinto=new Labirinto();
		this.partita =new Partita(labirinto);
		this.comando =new ComandoVai();
	}

	@Test
	public void testComandoVai() {
		this.comando.setParametro("nord");
		this.comando.esegui(partita, console);
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord"),this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testComandoVai_SenzaParametro() {
		this.comando.esegui(partita, console);
		assertEquals(this.labirinto.getStanzaIniziale(),this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testComandoVai_DirezioneSbagliata() {
		this.comando.setParametro("direzioneSbagliata");
		this.comando.esegui(partita, console);
		assertEquals(this.labirinto.getStanzaIniziale(),this.partita.getStanzaCorrente());
	}

}
