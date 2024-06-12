package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 20;
	private List<Attrezzo> attrezzi;
	//private Map<String,Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>(); 
		//this.attrezzi=new HashMap<>();
	}
	
	/**
	 * Aggiunge un attrezzo alla borsa nella prima posizione libera
	 * @param attrezzo
	 * @return
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if(this.getPeso()+attrezzo.getPeso()>this.pesoMax)
			return false;
		return this.attrezzi.add(attrezzo); //return this.attrezzi.put(attrezzo.getNome(),attrezzo);
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		if(this.attrezzi.isEmpty()) return null;
		
		final List<Attrezzo> ordinata= new ArrayList<>(this.attrezzi); //List<Attrezzo> ordinata=new ArrayList<>(this.attrezzi.values());
		final ComparatoreAttrezzoPerPeso cmp=new ComparatoreAttrezzoPerPeso();
		Collections.sort(ordinata, cmp);
		return ordinata;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		if(this.attrezzi.isEmpty()) return null;
		
		ComparatoreAttrezzoPerPeso cmp=new ComparatoreAttrezzoPerPeso();
		final SortedSet<Attrezzo> ordinata=new TreeSet<>(cmp);
		ordinata.addAll(this.attrezzi);
		
		return ordinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		if(this.attrezzi.isEmpty()) return null;
		
		final SortedSet<Attrezzo> ordinata=new TreeSet<>(this.attrezzi); //SortedSet<Attrezzo> ordinata=new TreeSet<>(this.attrezzi.values());
		
		return ordinata;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		if(this.attrezzi.isEmpty()) return null;
			
			
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		//final List<Attrezzo> attrezzi=new ArrayList<>(this.attrezzi.values());
		for(Attrezzo attrezzo:this.attrezzi) {
			if(peso2attrezzi.containsKey(attrezzo.getPeso())) {
				//questo attrezzo ha un peso già visto in precedenza
				//pesco il vecchio Set con il medesimo peso e aggiungo il nuovo arrivato
				final Set<Attrezzo> stessoPeso=peso2attrezzi.get(attrezzo.getPeso());
				stessoPeso.add(attrezzo);
			}
			else {
				//questo attrezzo ha un peso mai visto prima
				//creo un nuovo Set per ospitare gli attrezzi correnti e futuri con questo peso
				final Set<Attrezzo> nuovoSet=new HashSet<>();
				nuovoSet.add(attrezzo);
				peso2attrezzi.put(attrezzo.getPeso(), nuovoSet);
			}
		}
		return peso2attrezzi;
	}
	
	
	/**
	 * Ritorna l'attrezzo,presente nella borsa,col nome inserito come parametro
	 * @param nomeAttrezzo
	 * @return
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (Attrezzo attrezzo:attrezzi)
			if (attrezzo.getNome().equals(nomeAttrezzo))
				a = attrezzo;

		return a;
	}
	
	/**
	 * ritorna il peso attuale dell borsa
	 * @return
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo:attrezzi)
			peso += attrezzo.getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
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
		if(this.attrezzi.isEmpty()) {
			System.out.println("Non ci sono attrezzi da rimuovere\n");
		}
		else if(this.hasAttrezzo(nomeAttrezzo)){
			Attrezzo a = null;
			Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
			while (iteratore.hasNext()) {
				a = iteratore.next();
				if (a.getNome().equals(nomeAttrezzo)) {
					iteratore.remove();
					return a;
				}

			}
		}
		else if(!this.hasAttrezzo(nomeAttrezzo)) {
			System.out.println("Non è presente questo attrezzo nella borsa\n");
		}
		return null;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo:this.getContenutoOrdinatoPerPeso())
				s.append(attrezzo.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

}
		
