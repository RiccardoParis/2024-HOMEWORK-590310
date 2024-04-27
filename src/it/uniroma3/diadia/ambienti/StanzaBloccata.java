package it.uniroma3.diadia.ambienti;



public class StanzaBloccata extends Stanza{
	private String direzioneBloccata;
	private String attrezzoNecessario;
	
	public StanzaBloccata(String nome,String direzioneBloccata,String attrezzoNecessario) {
		super(nome);
		this.attrezzoNecessario=attrezzoNecessario;
		this.direzioneBloccata=direzioneBloccata;
	}
	
	/**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
	@Override
    public String getDescrizione() {
		/*da aggiornare*/
        return this.toString()+"\n"+
        		"Direzione bloccata: "+this.direzioneBloccata+"\n"+
        		"Attrezzo necessario nella stanza: "+this.attrezzoNecessario;
    }
	
	/**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.hasAttrezzo(this.attrezzoNecessario) || !(this.direzioneBloccata.equals(direzione))) {
			Stanza stanza = null;
			for(int i=0; i<super.getNumeroStanzeAdiacenti(); i++)
	        	if (super.getDirezioni()[i].equals(direzione))
	        		stanza = super.getStanzeAdiacenti()[i];
	        return stanza;
		}
		if(this.direzioneBloccata.equals(direzione)) {
			Stanza stanzaCorrente=this;
			return stanzaCorrente;
		}
		return null;
        
	}

}
