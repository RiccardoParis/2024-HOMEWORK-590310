package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class BorsaTest {
   Borsa borsa;
   Attrezzo attrezzo;
   Attrezzo attrezzoPesante;
   Attrezzo attrezzoSecondo;
   
   
   @BeforeEach
   public void setUp() {
	   this.borsa=new Borsa();
	   this.attrezzo=new Attrezzo("attrezzo",1);
	   this.attrezzoPesante=new Attrezzo("attrezzoPesante",11);
   }
   

   
   /**
    * Test per il metodo getAttrezzo
    */
   @Test
   public void testGetAttrezzo_BorsaVuota(){
	   assertNull(this.borsa.getAttrezzo("attrezzo"));
   }
   
   @Test
   public void testGetAttrezzo_SingoloAttrezzo() {
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(attrezzo,this.borsa.getAttrezzo("attrezzo"));
   }
   
   @Test
   public void testGetAttrezzoPesante() {
	   this.borsa.addAttrezzo(attrezzoPesante);
	   assertNull(this.borsa.getAttrezzo("attrezzoPesante"));
   }
   
   /**
    * Test per il metodo getPeso
    */
   @Test
   public void testGetPesoBorsaVuota() {
	   assertEquals(0,this.borsa.getPeso());
   }
   
   @Test
   public void testGetPeso_SingoloAttrezzo() {
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(1,this.borsa.getPeso());
   }
   
   @Test
   public void testGetPeso_DueAttrezzi() {
	   
	   this.attrezzoSecondo=new Attrezzo("attrezzoSecondo",5);
	   this.borsa.addAttrezzo(attrezzoSecondo);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(6,this.borsa.getPeso());
   }
   
   /**
    * Test per il metodo removeAttrezzo
    */
   @Test
   public void testRemoveAttrezzo_BorsaVuota() {
	   assertNull(this.borsa.removeAttrezzo("attrezzo"));
   }
   
   @Test
   public void testRemoveAttrezzo_SingoloAttrezzo() {
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(attrezzo,this.borsa.removeAttrezzo("attrezzo"));
   }
   
   @Test
   public void testRemoveAttrezzo_UltimoAttrezzo() {
	   this.borsa.addAttrezzo(attrezzo);
	   this.attrezzoSecondo=new Attrezzo("attrezzoSecondo",5);
	   this.borsa.addAttrezzo(attrezzoSecondo);
	   assertEquals(attrezzoSecondo,this.borsa.removeAttrezzo("attrezzoSecondo"));
   }
   
	
}
