package it.uniroma3.diadia.ambienti;



public class StanzaBloccata extends Stanza{
	private Direzione direzioneBloccata;
	private String attrezzoNecessario;
	
	public StanzaBloccata(String nome,Direzione direzioneBloccata,String attrezzoNecessario) {
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
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(this.hasAttrezzo(this.attrezzoNecessario) || !(this.direzioneBloccata.equals(direzione))) {
			Stanza stanza = super.getStanzaAdiacente(direzione);
	        return stanza;
		}
		if(this.direzioneBloccata.equals(direzione)) {
			Stanza stanzaCorrente=this;
			return stanzaCorrente;
		}
		return null;
        
	}

}
