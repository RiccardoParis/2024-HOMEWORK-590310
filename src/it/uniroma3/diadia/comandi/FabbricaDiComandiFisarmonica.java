package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	/*public Comando costruisciComando(String istruzione) {
		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null)
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else comando = new ComandoNonValido();
		comando.setParametro(parametro);
		return comando;
	}*/

	@Override
	public String getNome(String istruzione) {
		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		return nomeComando;
	}

	@Override
	public String getParametro(String istruzione) {
		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione);
		@SuppressWarnings("unused")
		String nomeComando = null;
		String parametro = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		return parametro;
	}

	@Override
	public AbstractComando costruisciComando(String istruzione) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
