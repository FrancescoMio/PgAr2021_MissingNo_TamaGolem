/**
 * 
 */
package classes;

import java.util.HashMap;

/**
 *
 */
public class Pietra {
	
	public static final String[] NOMI_ELEMENTI = {"FUOCO", "ACQUA" , "ETERE" , "TERRA", "ARIA"};
	public static final int DIM_MAT = 5;
	
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
	
}
