package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class BorsaTest {
   Borsa borsa;
   Attrezzo attrezzo;
   Attrezzo attrezzoPesante;
   Attrezzo attrezzoSecondo;
   Attrezzo attrezzoLeggero;
   Attrezzo attrezzoStessoPeso;
   Attrezzo attrezzoStessoNome;
   
   
   @BeforeEach
   public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
	   this.borsa=new Borsa();
	   this.attrezzo=new Attrezzo("attrezzo",1);
	   this.attrezzoStessoPeso= new Attrezzo("attrezzoStessoPeso",1);
	   this.attrezzoPesante=new Attrezzo("attrezzoPesante",11);
	   this.attrezzoLeggero=new Attrezzo("attrezzoLeggero",5);
	   this.attrezzoStessoNome=new Attrezzo("attrezzo",2);
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
   
   /**
    * Test ContenutoOrdinatoPerPeso
    */
   @Test
   public void testContenutoOrdinatoPerPeso_DueAttrezziPesoDiverso() {
	   this.borsa.addAttrezzo(attrezzoLeggero);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(List.of(attrezzo,attrezzoLeggero),new ArrayList<>(this.borsa.getContenutoOrdinatoPerPeso()));
   }
   
   @Test
   public void testContenutoOrdinatoPerPeso_DueAttrezziStessoPeso() {
	   this.borsa.addAttrezzo(attrezzoStessoPeso);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(List.of(attrezzo,attrezzoStessoPeso),new ArrayList<>(this.borsa.getContenutoOrdinatoPerPeso()));
   }
   
   @Test
   public void testContenutoOrdinatoPerPeso_TreAttrezzi() {
	   this.borsa.addAttrezzo(attrezzoLeggero);
	   this.borsa.addAttrezzo(attrezzo);
	   this.borsa.addAttrezzo(attrezzoStessoPeso);
	   assertEquals(List.of(attrezzo,attrezzoStessoPeso,attrezzoLeggero),new ArrayList<>(this.borsa.getContenutoOrdinatoPerPeso()));
   }
   
   @Test
   public void testContenutoOrdinatoPerPeso_QuattroAttrezzi() {
	   Attrezzo piuma=new Attrezzo("piuma",1);
	   Attrezzo libro=new Attrezzo("libro",2);
	   Attrezzo ps=new Attrezzo("ps",2);
	   Attrezzo piombo=new Attrezzo("piombo",5);
	   this.borsa.addAttrezzo(piuma);
	   this.borsa.addAttrezzo(libro);
	   this.borsa.addAttrezzo(ps);
	   this.borsa.addAttrezzo(piombo);
	   assertEquals(List.of(piuma,libro,ps,piombo),this.borsa.getContenutoOrdinatoPerPeso());
   }
   
   @Test
   public void testContenutoOrdinatoPerPeso_BorsaVuota() {
	   assertNull(this.borsa.getContenutoOrdinatoPerPeso());
   }
   
   
   /**
    * Test sortedSetOrdinatoPerPeso
    */
   @Test
   public void testSortedSetOrdinatoPerPeso_DiversoPeso() {
	   this.borsa.addAttrezzo(attrezzoLeggero);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(Set.of(attrezzo,attrezzoLeggero),this.borsa.getSortedSetOrdinatoPerPeso());
   }
   
   @Test
   public void testSortedSetOrdinatoPerPeso_TreAttrezzi() {
	   this.borsa.addAttrezzo(attrezzoStessoPeso);
	   this.borsa.addAttrezzo(attrezzoLeggero);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(Set.of(attrezzo,attrezzoStessoPeso,attrezzoLeggero),this.borsa.getSortedSetOrdinatoPerPeso());
   }
   
   @Test
   public void testSortedSetOrdinatoPerPeso_StessoPeso() {
	   this.borsa.addAttrezzo(attrezzoStessoPeso);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(Set.of(attrezzo,attrezzoStessoPeso),this.borsa.getSortedSetOrdinatoPerPeso());
   }
   
   @Test
   public void testSortedSetOrdinatoPerPeso_BorsaVuota() {
	   assertNull(this.borsa.getSortedSetOrdinatoPerPeso());
   }
   
   @Test
   public void testSortedSetOrdinatoPerPeso_QuattroAttrezzi() {
	   Attrezzo piuma=new Attrezzo("piuma",1);
	   Attrezzo libro=new Attrezzo("libro",2);
	   Attrezzo ps=new Attrezzo("ps",2);
	   Attrezzo piombo=new Attrezzo("piombo",5);
	   this.borsa.addAttrezzo(piuma);
	   this.borsa.addAttrezzo(libro);
	   this.borsa.addAttrezzo(ps);
	   this.borsa.addAttrezzo(piombo);
	   assertEquals(Set.of(piuma,libro,ps,piombo),this.borsa.getSortedSetOrdinatoPerPeso());
   }
   
   
   /**
    * Test ContenutoOrdinatoPerNome
    */
   @Test
   public void testContenutoOrdinatoPerNome_DueAttrezzi() {
	   Attrezzo binocolo=new Attrezzo("binocolo",2);
	   this.borsa.addAttrezzo(binocolo);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(List.of(attrezzo,binocolo),new ArrayList<>(this.borsa.getContenutoOrdinatoPerNome()));
   }
   
   @Test
   public void testContenutoOrdinatoPerNome_BorsaVuota() {
	   assertNull(this.borsa.getContenutoOrdinatoPerNome());
   }
   
   @Test
   public void testContenutoOrdinatoPerNome_DueAttrezziStessoNome() {
	   this.borsa.addAttrezzo(attrezzoStessoNome);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(List.of(attrezzo,attrezzoStessoNome),new ArrayList<>(this.borsa.getContenutoOrdinatoPerNome()));
   }
   
   @Test
   public void testContenutoOrdinatoPerNome_TreAttrezzi() {
	   Attrezzo binocolo=new Attrezzo("binocolo",2);
	   this.borsa.addAttrezzo(binocolo);
	   this.borsa.addAttrezzo(attrezzoStessoNome);
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(List.of(attrezzo,attrezzoStessoNome,binocolo),new ArrayList<>(this.borsa.getContenutoOrdinatoPerNome()));
   }
   
   @Test
   public void testContenutoOrdinatoPerNome_QuattroAttrezzi() {
	   Attrezzo piuma=new Attrezzo("piuma",1);
	   Attrezzo libro=new Attrezzo("libro",2);
	   Attrezzo ps=new Attrezzo("ps",2);
	   Attrezzo piombo=new Attrezzo("piombo",5);
	   this.borsa.addAttrezzo(piuma);
	   this.borsa.addAttrezzo(libro);
	   this.borsa.addAttrezzo(ps);
	   this.borsa.addAttrezzo(piombo);
	   assertEquals(Set.of(libro,piombo,piuma,ps),this.borsa.getContenutoOrdinatoPerNome());
   }
   
   
   /**
    * Test ContenutoRaggruppatoPerPeso
    */
   @Test
   public void testContenutoRaggruppatoPerPeso_TreAttrezzi() {
	   this.borsa.addAttrezzo(attrezzo);
	   Attrezzo stessoPeso=new Attrezzo("stessoPeso",1);
	   this.borsa.addAttrezzo(stessoPeso);
	   this.borsa.addAttrezzo(attrezzoLeggero);
	assertEquals(Set.of(attrezzo,stessoPeso),this.borsa.getContenutoRaggruppatoPerPeso().get(1));
	 assertEquals(Set.of(attrezzoLeggero),this.borsa.getContenutoRaggruppatoPerPeso().get(5));
   }
   
   @Test
   public void testContenutoRaggruppatoPerPeso_BorsaVuota() {
	   assertNull(this.borsa.getContenutoRaggruppatoPerPeso());
   }
   
   @Test
   public void testContenutoRaggruppatoPerPeso_BorsaUnAttrezzo() {
	   this.borsa.addAttrezzo(attrezzo);
	   assertEquals(Set.of(attrezzo),this.borsa.getContenutoRaggruppatoPerPeso().get(1));
   }
   
   @Test
   public void testContenutoRaggruppatoPerPeso_BorsaDueAttrezzi() {
	   this.borsa.addAttrezzo(attrezzo);
	   this.borsa.addAttrezzo(attrezzoLeggero);
	   assertEquals(Set.of(attrezzo),this.borsa.getContenutoRaggruppatoPerPeso().get(1));
	   assertEquals(Set.of(attrezzoLeggero),this.borsa.getContenutoRaggruppatoPerPeso().get(5));
   }
   
   @Test
   public void testContenutoRaggruppatoPerPeso_BorsaDueAttrezziStessoPeso() {
	   this.borsa.addAttrezzo(attrezzo);
	   Attrezzo stessoPeso=new Attrezzo("stessoPeso",1);
	   this.borsa.addAttrezzo(stessoPeso);
	assertEquals(Set.of(attrezzo,stessoPeso),this.borsa.getContenutoRaggruppatoPerPeso().get(1));
	   
   }
   
   
   
	
}
