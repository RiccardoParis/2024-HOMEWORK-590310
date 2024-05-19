package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	//static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	private Map<String,Attrezzo> attrezzi;
    private Map<String,Stanza> stanzeAdiacenti;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.attrezzi=new HashMap<String,Attrezzo>();
        this.stanzeAdiacenti=new HashMap<String,Stanza>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.size()<NUMERO_MASSIMO_DIREZIONI)
    		this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        if(this.stanzeAdiacenti.containsKey(direzione))
        	return this.stanzeAdiacenti.get(direzione);
        else {
        	return null;
        }
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo > getAttrezzi() {
    	if(this.attrezzi.isEmpty()) return null;
    	
    	ArrayList<Attrezzo> listaAttrezzi=new ArrayList<Attrezzo>(this.attrezzi.values());
    	
       return listaAttrezzi; 
    }
    
    /**
     * 
     * @return la mappa delle stanze adiacenti
     */
    public Map<String,Stanza> getMapStanzeAdiacenti(){
    	if(this.stanzeAdiacenti.isEmpty()) return null;
    	
    	Map<String,Stanza> stanzeAdiacenti=new HashMap<>(this.stanzeAdiacenti);
    	return stanzeAdiacenti;
    }
    

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.attrezzi.containsKey(attrezzo.getNome()))
    		return false;
    	else {
    		this.attrezzi.put(attrezzo.getNome(), attrezzo);
    		return true;
    	}

    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    		Set<String> direzioni=this.stanzeAdiacenti.keySet();
    		for(String direzione:direzioni)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	if(this.attrezzi.isEmpty()) {
    		risultato.append("Non ci sono attrezzi.");
    	}else {
    		List<Attrezzo> listaAttrezzi=new ArrayList<Attrezzo>(this.attrezzi.values());
    		for (Attrezzo attrezzo:listaAttrezzi) {
        		risultato.append(attrezzo.toString()+" ");
        		
        	}
    	}
    	
    	return risultato.toString();
    }

   

	/**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.attrezzi.isEmpty()) return null;
		
		if(this.attrezzi.containsKey(nomeAttrezzo))
			return this.attrezzi.get(nomeAttrezzo);
		else
			return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			this.attrezzi.remove(nomeAttrezzo);
			return true;
		}
		else
			return false;
			
	}
	
	@Override
	public boolean equals(Object obj) {
		Stanza that=(Stanza) obj;
		return this.getNome().equals(that.getNome());
	}
	
	public boolean hasDirezione(String direzione) {
		return this.stanzeAdiacenti.containsKey(direzione);
	}


	public Set<String> getDirezioni() {
		Set<String> direzioni = new HashSet<String>(this.stanzeAdiacenti.keySet());
	    return direzioni;
    }
	
	/**
	 * Setters e getters
	 */
	 public int getNumeroAttrezzi() {
			return this.attrezzi.size();
		}

		public int getNumeroStanzeAdiacenti() {
			return this.stanzeAdiacenti.size();
		}

		public Set<Stanza> getStanzeAdiacenti() {
			Set<Stanza> stanzeAdiacenti=new HashSet<Stanza>(this.stanzeAdiacenti.values());
			return stanzeAdiacenti;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public void setAttrezzi(Map<String, Attrezzo> attrezzi) {
			this.attrezzi = attrezzi;
		}

		public void setStanzeAdiacenti(Map<String, Stanza> stanzeAdiacenti) {
			this.stanzeAdiacenti = stanzeAdiacenti;
		}

		


		
		

}