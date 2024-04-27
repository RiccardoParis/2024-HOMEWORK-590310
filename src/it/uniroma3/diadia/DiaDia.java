package it.uniroma3.diadia;




import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;


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
	
	

	private Partita partita;
	private IO console;
	
	

	public DiaDia(IO console) {
		this.console=console;
		Labirinto labirinto=new Labirinto();
		this.partita = new Partita(labirinto);
	}

	
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
	/*private boolean processaIstruzione(String istruzione) {
		if(istruzione.isBlank())
			return false;
		
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
	}   */
	
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isBlank())
			return false;
		
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita,this.console);
		if (this.partita.vinta())

		System.out.println("Hai vinto!");
		if (this.partita.getGiocatore().getCfu()==0)

		System.out.println("Hai esaurito i CFU...");

		return this.partita.isFinita();
		}

	// implementazioni dei comandi dell'utente:
	


	public static void main(String[] argc) {
		IO io=new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}