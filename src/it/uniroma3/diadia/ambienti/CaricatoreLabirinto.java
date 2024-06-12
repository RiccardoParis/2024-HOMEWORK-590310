package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: "; 
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche: "; 
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate: "; 
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_BUIE_MARKER = "StanzeBuie: "; 

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";
	
	/* prefisso della riga contenente le specifiche dei maghi da collocare nel formato <nomeMago> <presentazione> <nomeStanza> */
	private static final String MAGHI_MARKER = "Maghi: ";
	
	/* prefisso della riga contenente le specifiche delle streghe da collocare nel formato <nomeStrega> <presentazione> <nomeStanza> */
	private static final String STREGHE_MARKER = "Streghe: ";
	
	/* prefisso della riga contenente le specifiche dei cani da collocare nel formato <nomeCane> <presentazione> <nomeStanza> */
	private static final String CANI_MARKER = "Cani: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private LabirintoBuilder labirinto;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.labirinto=LabirintoBuilder.newBuilder();
		this.carica();
	}
	
	public CaricatoreLabirinto(StringReader labirinto) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(labirinto);
		this.labirinto=new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzaMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaMaghi();
			this.leggiECollocaStreghe();
			this.leggiECollocaCani();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
	
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String cani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		
		if(cani==null) return;
		
		for(String cane:separaStringheAlleVirgole(cani)) {
			String nomeCane=null;
			String presentazione=null;
			String ciboPreferito=null;
			String nomeAttrezzo=null;
			String pesoAttrezzo=null;
			String nomeStanza=null;
			try(Scanner scannerLinea=new Scanner(cane)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeCane=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeCane));
				presentazione=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito di "+nomeCane));
				ciboPreferito=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo posseduto da "+nomeCane));
				nomeAttrezzo=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo));
				pesoAttrezzo=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomeCane));
				nomeStanza=scannerLinea.next();
			}
			int peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			Cane nuovoCane=new Cane(nomeCane,presentazione,ciboPreferito,attrezzo);
			Stanza stanza=this.nome2stanza.get(nomeStanza);
			stanza.setPersonaggio(nuovoCane);
			this.labirinto.addCane(nomeCane, presentazione,ciboPreferito, nomeAttrezzo, peso,nomeStanza);
			
		}
		
	}

	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String maghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		
		if(maghi==null) return;
		
		for(String mago:separaStringheAlleVirgole(maghi)) {
			String nomeMago=null;
			String presentazione=null;
			String nomeAttrezzo=null;
			String pesoAttrezzo=null;
			String nomeStanza=null;
			try(Scanner scannerLinea=new Scanner(mago)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeMago));
				presentazione=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo posseduto da "+nomeMago));
				nomeAttrezzo=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo));
				pesoAttrezzo=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomeMago));
				nomeStanza=scannerLinea.next();
			}
			int peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			Mago nuovoMago=new Mago(nomeMago,presentazione,attrezzo);
			Stanza stanza=this.nome2stanza.get(nomeStanza);
			stanza.setPersonaggio(nuovoMago);
			this.labirinto.addMago(nomeMago, presentazione, nomeAttrezzo, peso,nomeStanza);
			
		}
		
	}
	
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String streghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		
		if(streghe==null) return;
		
		for(String strega:separaStringheAlleVirgole(streghe)) {
			String nomeStrega=null;
			String presentazione=null;
			String nomeStanza=null;
			try(Scanner scannerLinea=new Scanner(strega)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeStrega=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeStrega));
				presentazione=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomeStrega));
				nomeStanza=scannerLinea.next();
			}
			Strega nuovaStrega=new Strega(nomeStrega,presentazione);
			Stanza stanza=this.nome2stanza.get(nomeStanza);
			stanza.setPersonaggio(nuovaStrega);
			this.labirinto.addStrega(nomeStrega, presentazione,nomeStanza);
			
		}
		
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		
		if(stanzeBuie==null) return;
		
		for(String stanzaBuia : separaStringheAlleVirgole(stanzeBuie)) {
			String nomeStanza=null;
			String attrezzoNecessario=null;
			try (Scanner scannerLinea = new Scanner(stanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo necessario per vedere in "+nomeStanza+"."));
				attrezzoNecessario = scannerLinea.next();
			}
			StanzaBuia stanza = new StanzaBuia(nomeStanza,attrezzoNecessario);
			this.nome2stanza.put(nomeStanza, stanza);
			this.labirinto.addStanzaBuia(nomeStanza,attrezzoNecessario);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		
        if(stanzeBloccate==null) return;
		
		for(String stanzaBloccata : separaStringheAlleVirgole(stanzeBloccate)) {
			String nomeStanza=null;
			String direzioneBloccata=null;
			String attrezzoNecessario=null;
			try (Scanner scannerLinea = new Scanner(stanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata di"+nomeStanza));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo necessario per sbloccare l'uscita "+direzioneBloccata+" di "+nomeStanza+"."));
				attrezzoNecessario = scannerLinea.next();
			}
			Direzione dir=Direzione.valueOf(direzioneBloccata);
			StanzaBloccata stanza = new StanzaBloccata(nomeStanza,dir,attrezzoNecessario);
			this.nome2stanza.put(nomeStanza, stanza);
			this.labirinto.addStanzaBloccata(nomeStanza,dir,attrezzoNecessario);
		}
	}

	private void leggiECreaStanzaMagiche() throws FormatoFileNonValidoException {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		
        if(stanzeMagiche==null) return;
		
		for(String stanzaMagica : separaStringheAlleVirgole(stanzeMagiche)) {
			String nomeStanza=null;
			String soglia=null;
			try (Scanner scannerLinea = new Scanner(stanzaMagica)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo necessario per vedere in "+nomeStanza+"."));
				soglia = scannerLinea.next();
			}
			int sogliaStanza=Integer.parseInt(soglia);
			StanzaMagica stanza = new StanzaMagica(nomeStanza,sogliaStanza);
			this.nome2stanza.put(nomeStanza, stanza);
			this.labirinto.addStanzaMagica(nomeStanza,sogliaStanza);
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
			this.labirinto.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while(scanner.hasNext()) {
			result.add(scanner.next());
		}
		/*try (Scanner scannerDiParole = scanner) {
			result.add(scannerDiParole.next());
		}*/
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.labirinto.addStanzaIniziale(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
		this.labirinto.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
			
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
			this.labirinto.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			String dir = null;
			String stanzaDestinazione = null; 
			try (Scanner scannerLinea = new Scanner(specificaUscita)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				dir = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				stanzaDestinazione = scannerLinea.next();
			}	
			Direzione direzione=Direzione.valueOf(dir);
			impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
			
		}
		
		/*try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();
				
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				
			}
		} */
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+stanzaDa);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ nomeA);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		this.labirinto.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public LabirintoBuilder getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(LabirintoBuilder labirinto) {
		this.labirinto = labirinto;
	}

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}

	public void setNome2stanza(Map<String, Stanza> nome2stanza) {
		this.nome2stanza = nome2stanza;
	}
}
