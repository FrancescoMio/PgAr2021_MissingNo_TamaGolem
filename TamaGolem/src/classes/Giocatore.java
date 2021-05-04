/**
 * 
 */
package classes;

import java.util.ArrayList;

/**
 * 
 *
 */
public class Giocatore {

	private String nome;
	private ArrayList<TamaGolem> tamaGolem;
	
	public Giocatore(String nome) {
		this.nome = nome;
		tamaGolem = new ArrayList<>();
	}
	
	public void addTamagolem(ArrayList<TamaGolem> tamaGolem) {
		this.tamaGolem = tamaGolem;
	}
	
	public ArrayList<TamaGolem> getTamaGolem(){
		return this.tamaGolem;
	}
	
	public TamaGolem evocazioneTamagolem() {
		if(tamaGolem.size() != 0)
			return tamaGolem.get(0);
		return null;
	}
	
}
