package it.uniroma3.diadia.giocatore;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Aggiunge un attrezzo alla borsa nella prima posizione libera
	 * @param attrezzo
	 * @return
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	
	/**
	 * Ritorna l'attrezzo,presente nella borsa,col nome inserito come parametro
	 * @param nomeAttrezzo
	 * @return
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	
	/**
	 * ritorna il peso attuale dell borsa
	 * @return
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Verifica se l'attrezzo col nome passato come parametro è nella borsa
	 * @param nomeAttrezzo
	 * @return
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove l'attrezzo dalla borsa in base al nome passato come parametro
	 * @param nomeAttrezzo
	 * @return
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.numeroAttrezzi==0) {
			System.out.println("Non ci sono attrezzi da rimuovere\n");
		}
		else if(this.hasAttrezzo(nomeAttrezzo)){
			for(int i=0;i<this.numeroAttrezzi;i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a=attrezzi[i];
					for(int j=i;j<this.numeroAttrezzi;j++) {
						attrezzi[j]=attrezzi[j+1];
					}
					this.numeroAttrezzi--;
				}
					
			}
		}
		else if(!this.hasAttrezzo(nomeAttrezzo)) {
			System.out.println("Non è presente questo attrezzo nella borsa\n");
		}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
		
