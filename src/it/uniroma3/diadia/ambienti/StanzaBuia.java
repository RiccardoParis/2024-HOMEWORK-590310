package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String attrezzoNecessario;
	
	public StanzaBuia(String nome,String attrezzoNecessario) {
		super(nome);
		this.attrezzoNecessario =attrezzoNecessario;
	}
	
	 /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
	@Override
    public String getDescrizione() {

		if(super.hasAttrezzo(this.attrezzoNecessario))
			return this.toString();
		return "qui c'è buio pesto, servirebbe posare qui una "+this.attrezzoNecessario+" per vederci qualcosa";
	}

	public String getAttrezzoNecessario() {
		return attrezzoNecessario;
	}

	public void setAttrezzoNecessario(String attrezzoNecessario) {
		this.attrezzoNecessario = attrezzoNecessario;
	}
	

}
