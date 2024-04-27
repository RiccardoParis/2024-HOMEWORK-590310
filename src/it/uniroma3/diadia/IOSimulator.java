package it.uniroma3.diadia;

import java.util.Scanner;

public class IOSimulator implements IO{

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee=new Scanner(System.in);
		String riga=scannerDiLinee.nextLine();
		return riga;
	}

}
