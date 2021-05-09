/**
 * 
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che rappresenta una pietra (elemento) dell'equilibrio del mondo
 */
public class Pietra {
	
	private static String[] NOMI_ELEMENTI;
	public static final int DIM_MAT = Scontro.getN();
	
	private String nome;
	private HashMap<String, Integer> danni;
	
	public Pietra(String nome) {
		this.nome = nome.toUpperCase();
		danni = new HashMap<>();
	}
	
	public void setDanniPietra (int matDanni[][],int rigaPietra) {
		for(int i = 0; i < DIM_MAT; i++) 
			danni.put(NOMI_ELEMENTI[i], matDanni[rigaPietra][i]);
	}
	
	public static void setNomiElementi(ArrayList<Integer> numbers, int n) {
		NOMI_ELEMENTI = new String[n];
		for(int i = 0; i < n; i++) {
			NOMI_ELEMENTI[i] = Nome.getNomi(numbers.get(i));
			//System.out.println(NOMI_ELEMENTI[i]);			
		}
		
	}
	
	public static void setNomi(String name, int i) {
		NOMI_ELEMENTI[i] = name;
	}

	public static String getNOMI_ELEMENTI(int pos) {
		return NOMI_ELEMENTI[pos];
	}
	
	public static String[] getNOMI_ELEMENTI() {
		return NOMI_ELEMENTI;
	}

	@Override
	public String toString() {
		return "Pietra [nome=" + nome + ", danni=" + danni + "]";
	}
	
}
