package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ComandoPrendiTest {
	FabbricaDiComandiRiflessiva fabbrica;
	AbstractComando comando;
		
		@BeforeEach
		public void setUp() {
			this.fabbrica =new FabbricaDiComandiRiflessiva();
		}

		@Test
		public void testComandoPrendi() throws Exception {
			this.comando=this.fabbrica.costruisciComando("prendi attrezzo");
			assertEquals(ComandoPrendi.class,this.comando.getClass());
		}
		
		@Test
		public void testComandoPrendi_Nome() throws Exception {
			this.comando=this.fabbrica.costruisciComando("prendi attrezzo");
			assertEquals("prendi",this.comando.getNome());
		}
		
		@Test
		public void testComandoPrendi_Parametro() throws Exception {
			this.comando=this.fabbrica.costruisciComando("prendi attrezzo");
			assertEquals("attrezzo",this.comando.getParametro());
		}
		
		@Test
		public void testComandoPrendi_ParametroNullo() throws Exception {
			this.comando=this.fabbrica.costruisciComando("prendi");
			assertNull(this.comando.getParametro());
		}
}
