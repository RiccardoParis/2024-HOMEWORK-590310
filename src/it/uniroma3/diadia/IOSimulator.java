package it.uniroma3.diadia;

import java.util.List;

public class IOSimulator implements IO{
	
	private List<String> righeLette;
	int indiceRigheLette;
	
	private List<String> messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;
	
	

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiProdotti.add(this.indiceMessaggiProdotti, msg);
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		String riga=null;
		riga=this.righeLette.get(this.indiceRigheLette);
		this.indiceRigheLette++;
		return riga;
	}

}
