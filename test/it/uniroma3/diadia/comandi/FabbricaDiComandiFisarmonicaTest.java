package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
	FabbricaDiComandiFisarmonica fabbrica;
	
	@BeforeEach
	public void setUp() {
		this.fabbrica =new FabbricaDiComandiFisarmonica();
	}
	
    /**
     * ComandoVai
     */
	@Test
	public void testComandoVaiParametro() {
		Comando comando=this.fabbrica.costruisciComando("vai nord");
		assertEquals("nord",comando.getParametro());
	}
	
	@Test
	public void testComandoVaiNome() {
		Comando comando=this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai",comando.getNome());
	}
	
	/**
     * ComandoPosa
     */
	@Test
	public void testComandoPosaParametro() {
		Comando comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals("attrezzo",comando.getParametro());
	}
	
	@Test
	public void testComandoPosaNome() {
		Comando comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals("posa",comando.getNome());
	}
	
	/**
     * ComandoPrendi
     */
	@Test
	public void testComandoPrendiParametro() {
		Comando comando=this.fabbrica.costruisciComando("prendi attrezzo");
		assertEquals("attrezzo",comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiNome() {
		Comando comando=this.fabbrica.costruisciComando("prendi attrezzo");
		assertEquals("prendi",comando.getNome());
	}
	
	/**
     * ComandoAiuto
     */
	@Test
	public void testComandoAiutoParametro() {
		Comando comando=this.fabbrica.costruisciComando("aiuto");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoAiutoNome() {
		Comando comando=this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto",comando.getNome());
	}
	
	/**
     * ComandoFine
     */
	@Test
	public void testComandoFineParametro() {
		Comando comando=this.fabbrica.costruisciComando("fine");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoFineNome() {
		Comando comando=this.fabbrica.costruisciComando("fine");
		assertEquals("fine",comando.getNome());
	}
	
	/**
     * ComandoNonValido
     */
	@Test
	public void testComandoNonValidoParametro() {
		Comando comando=this.fabbrica.costruisciComando("non valido");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoNonValidoNome() {
		Comando comando=this.fabbrica.costruisciComando("non valido");
		assertNull(comando.getNome());
	}

}
