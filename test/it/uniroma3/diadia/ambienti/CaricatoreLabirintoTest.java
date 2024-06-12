package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;


class CaricatoreLabirintoTest {
	CaricatoreLabirinto caricatore;
	Labirinto labirintoCaricato;
	LabirintoBuilder labirintoBuilder;
	StringReader labirinto;
	Partita partita;
	
	@BeforeEach
	public void SetUp() {
		
	}

	@Test
	void testTrilocaleDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: "
				+ "\nStanzeMagiche: "
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: "
				+ "\nStreghe: "
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
	}
	
	@Test
	void testTrilocaleDaStringa_Labirinto() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: "
				+ "\nStanzeBloccate: "
				+ "\nStanzeMagiche: "
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: "
				+ "\nStreghe: "
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoCaricato=this.caricatore.getLabirinto().getLabirinto();
		labirintoBuilder=this.caricatore.getLabirinto();
		
		assertEquals("stanzaIniziale",labirintoCaricato.getStanzaIniziale().getNome());
		assertEquals(List.of(new Stanza("stanzaIniziale"),new Stanza("biblioteca"),new Stanza("N10")),labirintoBuilder.getListaStanze());
		assertEquals("N10",labirintoBuilder.getStanze().get("N10").getNome());
	}
	
	@Test
	void testStanzaBuiaDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: "
				+ "\nStanzeMagiche: "
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: "
				+ "\nStreghe: "
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoBuilder=this.caricatore.getLabirinto();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("stanzaBuia",this.caricatore.getNome2stanza().get("stanzaBuia").getNome());
		assertEquals(new StanzaBuia("stanzaBuia","lanterna"),labirintoBuilder.getStanze().get("stanzaBuia"));
	}
	
	@Test
	void testStanzaBloccataDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: stanzaBloccata NORD chiave"
				+ "\nStanzeMagiche: "
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: "
				+ "\nStreghe: "
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoBuilder=this.caricatore.getLabirinto();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("stanzaBloccata",this.caricatore.getNome2stanza().get("stanzaBloccata").getNome());
		assertEquals(new StanzaBloccata("stanzaBloccata",Direzione.NORD,"chiave"),labirintoBuilder.getStanze().get("stanzaBloccata"));
	}
	
	@Test
	void testStanzaMagicaDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: stanzaBloccata NORD chiave"
				+ "\nStanzeMagiche: stanzaMagica 2"
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: "
				+ "\nStreghe: "
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoBuilder=this.caricatore.getLabirinto();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("stanzaMagica",this.caricatore.getNome2stanza().get("stanzaMagica").getNome());
		assertEquals(new StanzaMagica("stanzaMagica",2),labirintoBuilder.getStanze().get("stanzaMagica"));
	}
	
	@Test
	void testMagoDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: stanzaBloccata NORD chiave"
				+ "\nStanzeMagiche: stanzaMagica 2"
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: merlino piacere anello 1 stanzaIniziale"
				+ "\nStreghe: "
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoBuilder=this.caricatore.getLabirinto();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("merlino",this.caricatore.getNome2stanza().get("stanzaIniziale").getPersonaggio().getNome());
		assertEquals("piacere",labirintoBuilder.getStanze().get("stanzaIniziale").getPersonaggio().getPresentazione());
	}
	
	@Test
	void testStregaDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: stanzaBloccata NORD chiave"
				+ "\nStanzeMagiche: stanzaMagica 2"
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: merlino piacere anello 1 stanzaIniziale"
				+ "\nStreghe: sabrina hihihihi stanzaBuia"
				+ "\nCani: "
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoBuilder=this.caricatore.getLabirinto();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("sabrina",this.caricatore.getNome2stanza().get("stanzaBuia").getPersonaggio().getNome());
		assertEquals("hihihihi",labirintoBuilder.getStanze().get("stanzaBuia").getPersonaggio().getPresentazione());
	}
	
	@Test
	void testCaneDaStringa_Caricatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=new StringReader("Stanze: stanzaIniziale,biblioteca,N10"
				+ "\nStanzeBuie: stanzaBuia lanterna"
				+ "\nStanzeBloccate: stanzaBloccata NORD chiave"
				+ "\nStanzeMagiche: stanzaMagica 2"
				+ "\nInizio: stanzaIniziale"
				+ "\nVincente: biblioteca"
				+ "\nAttrezzi: chiave 1 biblioteca"
				+ "\nMaghi: merlino piacere anello 1 stanzaIniziale"
				+ "\nStreghe: sabrina hihihihi stanzaBuia"
				+ "\nCani: fido baubau osso regalo 2 stanzaMagica"
				+ "\nUscite: stanzaIniziale NORD N10");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		this.caricatore.carica();
		labirintoBuilder=this.caricatore.getLabirinto();
		assertEquals("stanzaIniziale",this.caricatore.getStanzaIniziale().getNome());
		assertEquals("chiave",this.caricatore.getNome2stanza().get("biblioteca").getAttrezzo("chiave").getNome());
		assertEquals("N10",this.caricatore.getNome2stanza().get("stanzaIniziale").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("fido",this.caricatore.getNome2stanza().get("stanzaMagica").getPersonaggio().getNome());
		assertEquals("baubau",labirintoBuilder.getStanze().get("stanzaMagica").getPersonaggio().getPresentazione());
	}
	
	/*@Test 
	void testScanner() throws FormatoFileNonValidoException, FileNotFoundException {
		this.labirinto=new StringReader("Stanze: monolocale,biblioteca,N10"
				+ "\nInizio: monolocale"
				+ "\nVincente: monolocale");
		this.caricatore=new CaricatoreLabirinto(this.labirinto);
		assertEquals("monolocale,biblioteca,N10",this.caricatore.leggiRigaCheCominciaPer("Stanze: "));
		assertEquals(List.of("monolocale","biblioteca","N10"),this.caricatore.separaStringheAlleVirgole("monolocale,biblioteca,N10"));
	}*/
	

}
