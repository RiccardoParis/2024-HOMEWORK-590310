package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ComandoPosaTest {
FabbricaDiComandiRiflessiva fabbrica;
AbstractComando comando;
	
	@BeforeEach
	public void setUp() {
		this.fabbrica =new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testComandoPosa() throws Exception {
		this.comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals(ComandoPosa.class,this.comando.getClass());
	}
	
	@Test
	public void testComandoPosa_Nome() throws Exception {
		this.comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals("posa",this.comando.getNome());
	}
	
	@Test
	public void testComandoPosa_Parametro() throws Exception {
		this.comando=this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals("attrezzo",this.comando.getParametro());
	}
	
	@Test
	public void testComandoPosa_ParametroNullo() throws Exception {
		this.comando=this.fabbrica.costruisciComando("posa");
		assertNull(this.comando.getParametro());
	}

}
