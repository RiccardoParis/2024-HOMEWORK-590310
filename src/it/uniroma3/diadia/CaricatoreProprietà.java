package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;




public class CaricatoreProprietà {
	
	/* prefisso di una singola riga di testo contenente i CFU iniziali */
	private static final String CFU_INIZIALI_MARKER = "CfuIniziali: ";
	
	/* prefisso di una singola riga di testo contenente i CFU iniziali */
	private static final String PESO_MAX_MARKER = "PesoMaxBorsa: ";
	
	private LineNumberReader reader;
	
	public CaricatoreProprietà(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public void carica() throws FormatoFileNonValidoException {
		try {
			final int CFU_INIZIALI=this.leggiECreaVariabileCFU();
			final int DEFAULT_PESO_MAX_BORSA=this.leggiECreaVariabileBorsa();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	public int leggiECreaVariabileBorsa() throws FormatoFileNonValidoException {
		String variabileBorsa=this.leggiRigaCheCominciaPer(CFU_INIZIALI_MARKER);
		int peso= Integer.parseInt(variabileBorsa);
		
		return peso;
	}

	public int leggiECreaVariabileCFU() throws FormatoFileNonValidoException {
		String variabileCFU=this.leggiRigaCheCominciaPer(PESO_MAX_MARKER);
		int Cfu= Integer.parseInt(variabileCFU);

		return Cfu;
		
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}
	
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
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

}
