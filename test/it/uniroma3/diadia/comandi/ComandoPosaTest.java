package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	ComandoPosa comando;
	Partita partita;
	Labirinto labirinto;
	IO console;
	Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.console=new IOConsole();
		this.labirinto=new Labirinto();
		this.partita =new Partita(labirinto);
		this.attrezzo =new Attrezzo("attrezzo",1);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.comando =new ComandoPosa();
	}

	@Test
	public void testComandoPosa() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, console);
		assertEquals(attrezzo,this.partita.getStanzaCorrente().getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testComandoPosa_SenzaParametro() {
		this.comando.esegui(partita, console);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testComandoPosa_AttrezzoSbagliato() {
		this.comando.setParametro("attrezzoSbagliato");
		this.comando.esegui(partita, console);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("attrezzo"));
	}

}
