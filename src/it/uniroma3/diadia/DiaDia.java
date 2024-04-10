package it.uniroma3.diadia;




import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	private IOConsole console;
	

	public DiaDia() {
		this.partita = new Partita();
	}

	@SuppressWarnings("resource")
	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione=this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		this.console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Posa un oggetto dalla borsa nella stanzaCorrente
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			this.console.mostraMessaggio("Che attrezzo vuoi posare?");
		Attrezzo attrezzoPosato=null;
		attrezzoPosato=this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzoPosato==null)
			this.console.mostraMessaggio("Non hai questo attrezzo nella borsa");
		else {
			this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoPosato);
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			this.console.mostraMessaggio("Hai posato l'attrezzo nella stanza "+this.partita.getLabirinto().getStanzaCorrente().getNome());
		}
	}
	
	/**
	 * Prende un oggetto dalla stanza e lo mette nella borsa
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			this.console.mostraMessaggio("Che attrezzo vuoi prendere?");
		Attrezzo attrezzoPreso=null;
		attrezzoPreso=this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(attrezzoPreso==null)
			this.console.mostraMessaggio("Attrezzo non presente");
		else {
			this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPreso);
			this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			this.console.mostraMessaggio("Attrezzo messo nella borsa");
		}
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.console.mostraMessaggio(elencoComandi[i]+" ");
	}


	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		IOConsole console=new IOConsole();
		gioco.gioca();
	}
}