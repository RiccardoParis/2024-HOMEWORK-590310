package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	ComandoPrendi comando;
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
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.comando =new ComandoPrendi();
	}

	@Test
	public void testComandoPrendi() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, console);
		assertEquals(attrezzo,this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testComandoPrendi_SenzaParametro() {
		this.comando.esegui(partita, console);
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testComandoPrendi_AttrezzoSbagliato() {
		this.comando.setParametro("attrezzoSbagliato");
		this.comando.esegui(partita, console);
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo"));
	}

}
