package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class BorsaTest {
   Borsa borsa;
   Attrezzo spada;
   Attrezzo spadone;
   Attrezzo mazza;
   
   @BeforeEach
   public void setUp() {
	   this.borsa=new Borsa();
	   this.spada=new Attrezzo("spada",4);
	   this.spadone=new Attrezzo("spadone",11);
	   this.mazza=new Attrezzo("mazza",5);
   }
   
   /**
    * Test per il metodo addAttrezzo
    */
   @Test
   public void testAddAttrezzoFalse() {
	   assertFalse(this.borsa.addAttrezzo(spadone));
   }
   
   @Test
   public void testAddAttrezzoTrue() {
	   assertTrue(this.borsa.addAttrezzo(spada));
   }
   
   /**
    * Test per il metodo getAttrezzo
    */
   @Test
   public void testGetAttrezzoGiusto(){
	   this.borsa.addAttrezzo(spada);
	   assertEquals(spada,this.borsa.getAttrezzo("spada"));
   }
   
   @Test
   public void testGetAttrezzoBorsaVuota() {
	   assertNull(this.borsa.getAttrezzo("spada"));
   }
   
   @Test
   public void testGetAttrezzoSbagliato() {
	   this.borsa.addAttrezzo(spada);
	   assertNotEquals(spadone,this.borsa.getAttrezzo("spada"));
   }
   
   /**
    * Test per il metodo getPeso
    */
   @Test
   public void testGetPesoBorsaVuota() {
	   assertEquals(0,this.borsa.getPeso());
   }
   
   @Test
   public void testGetPesoUnOggetto() {
	   this.borsa.addAttrezzo(spada);
	   assertEquals(4,this.borsa.getPeso());
   }
   
   @Test
   public void testGetPesoDueOggetti() {
	   this.borsa.addAttrezzo(mazza);
	   this.borsa.addAttrezzo(spada);
	   assertEquals(9,this.borsa.getPeso());
   }
   
   /**
    * Test per il metodo removeAttrezzo
    */
   @Test
   public void testRemoveAttrezzoBorsaVuota() {
	   assertNull(this.borsa.removeAttrezzo("spada"));
   }
   
   @Test
   public void testRemovePrimoAttrezzo() {
	   this.borsa.addAttrezzo(spada);
	   assertEquals(spada,this.borsa.removeAttrezzo("spada"));
   }
   
   @Test
   public void testRemoveUltimoAttrezzo() {
	   this.borsa.addAttrezzo(spada);
	   this.borsa.addAttrezzo(mazza);
	   assertEquals(mazza,this.borsa.removeAttrezzo("mazza"));
   }
   
   @Test
   public void testRemoveAttrezzoInesistente() {
	   this.borsa.addAttrezzo(spada);
	   assertNull(this.borsa.removeAttrezzo("mazza"));
   }
	
}
