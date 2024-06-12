package it.uniroma3.diadia.comandi;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ComandoVaiTest {
	FabbricaDiComandiRiflessiva fabbrica;
	AbstractComando comando;
		
		@BeforeEach
		public void setUp() {
			this.fabbrica =new FabbricaDiComandiRiflessiva();
		}

		@Test
		public void testComandoVai() throws Exception {
			this.comando=this.fabbrica.costruisciComando("vai NORD");
			assertEquals(ComandoVai.class,this.comando.getClass());
		}
		
		@Test
		public void testComandoVai_Nome() throws Exception {
			this.comando=this.fabbrica.costruisciComando("vai NORD");
			assertEquals("vai",this.comando.getNome());
		}
		
		@Test
		public void testComandoVai_Parametro() throws Exception {
			this.comando=this.fabbrica.costruisciComando("vai NORD");
			assertEquals("NORD",this.comando.getParametro());
		}
		
		@Test
		public void testComandoVai_ParametroNullo() throws Exception {
			this.comando=this.fabbrica.costruisciComando("vai");
			assertNull(this.comando.getParametro());
		}
}
