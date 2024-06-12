package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiRiflessivaTest {

FabbricaDiComandiRiflessiva fabbrica;
	
	@BeforeEach
	public void setUp() {
		this.fabbrica =new FabbricaDiComandiRiflessiva();
	}
	
	/**
     * ComandoVai
     * @throws Exception 
     */
	@Test
	public void testComandoSalutaParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("saluta");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoSalutaNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("saluta");
		assertEquals("saluta",comando.getNome());
	}
	
    /**
     * ComandoVai
     * @throws Exception 
     */
	@Test
	public void testComandoVaiParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("vai nord");
		assertEquals("nord",comando.getParametro());
	}
	
	@Test
	public void testComandoVaiNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai",comando.getNome());
	}
	
	/**
     * ComandoPosa
	 * @throws Exception 
     */
	@Test
	public void testComandoPosaParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals("attrezzo",comando.getParametro());
	}
	
	@Test
	public void testComandoPosaNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals("posa",comando.getNome());
	}
	
	/**
     * ComandoPrendi
	 * @throws Exception 
     */
	@Test
	public void testComandoPrendiParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("prendi attrezzo");
		assertEquals("attrezzo",comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("prendi attrezzo");
		assertEquals("prendi",comando.getNome());
	}
	
	/**
     * ComandoAiuto
	 * @throws Exception 
     */
	@Test
	public void testComandoAiutoParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("aiuto");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoAiutoNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto",comando.getNome());
	}
	
	/**
     * ComandoFine
	 * @throws Exception 
     */
	@Test
	public void testComandoFineParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("fine");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoFineNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("fine");
		assertEquals("fine",comando.getNome());
	}
	
	/**
     * ComandoNonValido
	 * @throws Exception 
     */
	@Test
	public void testComandoNonValidoParametro() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("invalido");
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoNonValidoNome() throws Exception {
		AbstractComando comando=this.fabbrica.costruisciComando("non valido");
		assertNull(comando.getNome());
	}


}
