package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private String ciboPreferito;
	private Attrezzo attrezzo;

	public Cane(String nome, String presentazione,String ciboPreferito,Attrezzo attrezzo) {
		super(nome, presentazione);
		this.ciboPreferito=ciboPreferito;
		this.attrezzo=attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-3);
		return "WOOF-WOOF....GRRRRRRRR(Il cane ti ha morso via 3 CFU)";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(this.getCiboPreferito())) {
			if(this.getAttrezzo()==null) return "Il cane non ha nulla da lasciare";
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			Attrezzo lasciato=this.getAttrezzo();
			this.setAttrezzo(null);
			return "SLURP...(il cane è felice e ha lasciato "+lasciato.getNome()+" per terra)";
		}
		else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			return "GRRRRRRR(il cane ti ha morso via 1 CFU)";
		}
	}

	public String getCiboPreferito() {
		return ciboPreferito;
	}

	public void setCiboPreferito(String ciboPreferito) {
		this.ciboPreferito = ciboPreferito;
	}

	public Attrezzo getAttrezzo() {
		return attrezzo;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

}
