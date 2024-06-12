package it.uniroma3.diadia.giocatore;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;



public class GiocatoreTest {
         Giocatore giocatore;
         
         @BeforeEach
         public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        	 this.giocatore=new Giocatore();
         }
         
         /*Non ci sono metodi particolari da testare*/
	

}
