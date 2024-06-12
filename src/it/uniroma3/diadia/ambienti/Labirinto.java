package it.uniroma3.diadia.ambienti;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	private Labirinto() {
		this.stanzaIniziale=null;
		this.stanzaVincente=null;
	}
	
	public Labirinto(CaricatoreLabirinto labirinto) {
		this.stanzaIniziale=labirinto.getLabirinto().getLabirinto().getStanzaIniziale();
		this.stanzaVincente=labirinto.getLabirinto().getLabirinto().getStanzaVincente();
	}
	
	/*public Labirinto() {
		this.creaStanze();
	}*/
	
	
	 
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzacorrente) {
		this.stanzaIniziale = stanzacorrente;
	}
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzavincente) {
		this.stanzaVincente = stanzavincente;
	}
	
	
	
	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Map<String,Stanza> stanze;
		
		public static LabirintoBuilder newBuilder(){
			LabirintoBuilder labirinto=new LabirintoBuilder();
			return labirinto;
		}
		
		public LabirintoBuilder() {
			this.stanze=new LinkedHashMap<>();
			this.labirinto=new Labirinto();
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
			if(this.stanze.containsKey(nomeStanzaIniziale)) {
				this.labirinto.setStanzaIniziale(this.stanze.get(nomeStanzaIniziale));
				return this;
			}
			else {
				Stanza stanzaIniziale=new Stanza(nomeStanzaIniziale);
				this.stanze.put(nomeStanzaIniziale,stanzaIniziale);
				this.labirinto.setStanzaIniziale(stanzaIniziale);
				return this;
			}
			
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
			if(this.stanze.containsKey(nomeStanzaVincente)) {
				this.labirinto.setStanzaVincente(this.stanze.get(nomeStanzaVincente));
				return this;
			}
			else {
				Stanza stanzaVincente=new Stanza(nomeStanzaVincente);
				this.stanze.put(nomeStanzaVincente,stanzaVincente);
				this.labirinto.setStanzaVincente(stanzaVincente);
				return this;
			}
		}
		
		public LabirintoBuilder addAdiacenza(String stanza,String adiacente,Direzione direzione) {
			this.stanze.get(stanza).impostaStanzaAdiacente(direzione, this.stanze.get(adiacente));
			return this;
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza=new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza,int soglia) {
			Stanza stanza=new StanzaMagica(nomeStanza,soglia);
			this.stanze.put(nomeStanza, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoNecessario) {
			Stanza stanza=new StanzaBuia(nomeStanza,attrezzoNecessario);
			this.stanze.put(nomeStanza, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanza,Direzione direzioneBloccata,String attrezzoNecessario) {
			Stanza stanza=new StanzaBloccata(nomeStanza,direzioneBloccata,attrezzoNecessario);
			this.stanze.put(nomeStanza, stanza);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			LinkedList<Stanza> listaStanze= new LinkedList<>(this.stanze.values());
			listaStanze.getLast().addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso,String nomeStanza) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			Stanza stanza=this.getStanze().get(nomeStanza);
			stanza.addAttrezzo(attrezzo);
			this.getStanze().put(nomeStanza, stanza);
			return this;
		}
		
		public LabirintoBuilder addMago(String nomeMago,String presentazione,String nomeAttrezzo,int peso) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			AbstractPersonaggio mago=new Mago(nomeMago,presentazione,attrezzo);
			LinkedList<Stanza> listaStanze= new LinkedList<>(this.stanze.values());
			listaStanze.getLast().setPersonaggio(mago);
			return this;
			
		}
		
		public LabirintoBuilder addMago(String nomeMago,String presentazione,String nomeAttrezzo,int peso,String nomeStanza) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			AbstractPersonaggio mago=new Mago(nomeMago,presentazione,attrezzo);
			Stanza stanza=this.getStanze().get(nomeStanza);
			stanza.setPersonaggio(mago);
			return this;
			
		}
		
		public LabirintoBuilder addStrega(String nomeStrega,String presentazione) {
			
			AbstractPersonaggio strega=new Strega(nomeStrega,presentazione);
			LinkedList<Stanza> listaStanze= new LinkedList<>(this.stanze.values());
			listaStanze.getLast().setPersonaggio(strega);
			return this;
			
		}
		
	public LabirintoBuilder addStrega(String nomeStrega,String presentazione,String nomeStanza) {
			
			AbstractPersonaggio strega=new Strega(nomeStrega,presentazione);
			Stanza stanza=this.getStanze().get(nomeStanza);
			stanza.setPersonaggio(strega);
			return this;
			
		}
		
		public LabirintoBuilder addCane(String nomeCane,String presentazione,String ciboPreferito,String nomeAttrezzo, int peso) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			AbstractPersonaggio cane=new Cane(nomeCane,presentazione,ciboPreferito,attrezzo);
			LinkedList<Stanza> listaStanze= new LinkedList<>(this.stanze.values());
			listaStanze.getLast().setPersonaggio(cane);
			return this;
			
		}
		
		public LabirintoBuilder addCane(String nomeCane,String presentazione,String ciboPreferito,String nomeAttrezzo, int peso,String nomeStanza) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
			AbstractPersonaggio cane=new Cane(nomeCane,presentazione,ciboPreferito,attrezzo);
			Stanza stanza=this.getStanze().get(nomeStanza);
			stanza.setPersonaggio(cane);
			return this;
			
		}
		
		
		public List<Stanza> getListaStanze(){
			List<Stanza> listaStanze=new LinkedList<>(this.stanze.values());
			return listaStanze;
		}
		
		public Map<String,Stanza> getStanze(){
			if(this.stanze.isEmpty()) return null;
			
			return this.stanze;
		}

		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}

	}


}
