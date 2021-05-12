package classes;

import java.util.ArrayList;

public class Nome {
	
	public static String getNomi(int pos){
		ArrayList<String> nomi = new ArrayList<String>();
		nomi.add("Normale");
		nomi.add("Fuoco");
		nomi.add("Lotta");
		nomi.add("Acqua");
		nomi.add("Volante");
		nomi.add("Erba");
		nomi.add("Veleno");
		nomi.add("Elettro");
		nomi.add("Terra");
		nomi.add("Psico");
		nomi.add("Roccia");
		nomi.add("Ghiaccio");
		nomi.add("Coleottero");
		nomi.add("Drago");
		nomi.add("Spettro");
		nomi.add("Buio");
		nomi.add("Acciaio");
		nomi.add("Folletto");
		return nomi.get(pos);
	}
}
