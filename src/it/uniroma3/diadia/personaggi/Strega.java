package it.uniroma3.diadia.personaggi;

import java.util.Collections;
import java.util.LinkedList;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzaPerAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_BUONO = "Sei gentile ad avermi salutato"+
                                                   " ti mando nella stanza vicina con più attrezzi!";
	private static final String MESSAGGIO_CATTIVO = "La tua maleducazione mi sorprende, ti spedisco nella stanza vicina meno attrezzata!!!";
	
	public Strega(String nome,String presentazione) {
		super(nome,presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		ComparatoreStanzaPerAttrezzi cmp=new ComparatoreStanzaPerAttrezzi();
		LinkedList<Stanza> adiacenti=new LinkedList<>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		Collections.sort(adiacenti,cmp);
		if(haSalutato()==true) {
			partita.setStanzaCorrente(adiacenti.getLast());
			msg=MESSAGGIO_BUONO;
		}else {
			partita.setStanzaCorrente(adiacenti.getFirst());
			msg=MESSAGGIO_CATTIVO;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return "Grazie aahahahahahahaah";
	}

}
