package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Map<String,Stanza> stanze;
	
	public LabirintoBuilder() {
		this.stanze=new HashMap<>();
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
	
	public LabirintoBuilder addAdiacenza(String stanza,String adiacente,String direzione) {
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
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza,String direzioneBloccata,String attrezzoNecessario) {
		Stanza stanza=new StanzaBloccata(nomeStanza,direzioneBloccata,attrezzoNecessario);
		this.stanze.put(nomeStanza, stanza);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
		Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,peso);
		LinkedList<Stanza> listaStanze= new LinkedList<>(this.stanze.values());
		listaStanze.getFirst().addAttrezzo(attrezzo);
		return this;
	}
	
	public List<Stanza> getListaStanze(){
		List<Stanza> listaStanze=new ArrayList<>(this.stanze.values());
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
