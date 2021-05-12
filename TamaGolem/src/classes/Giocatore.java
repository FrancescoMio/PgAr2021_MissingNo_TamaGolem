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

	private String nickName;
	private ArrayList<TamaGolem> tamaGolem;
	private int numeroTamagolem;
	
	public Giocatore(String nickName) {
		this.nickName = nickName;
		tamaGolem = new ArrayList<>();
		numeroTamagolem = 0;
	}
	
	public void addTamagolem(TamaGolem tamaGolem) {
		this.tamaGolem.add(tamaGolem);
	}
	
	public void removeTamaGolem(int i) {
		tamaGolem.remove(i);
	}
	
	public TamaGolem evocazioneTamagolem() {
			return tamaGolem.get(0);
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getNumeroTamagolem() {
		return numeroTamagolem;
	}

	public void setNumeroTamagolem(int numeroTamagolem) {
		this.numeroTamagolem = numeroTamagolem;
	}

	public void setTamaGolem(ArrayList<TamaGolem> tamaGolem) {
		this.tamaGolem = tamaGolem;
	}

	public ArrayList<TamaGolem> getTamaGolem(){
		return this.tamaGolem;
	}
	
	

	@Override
	public String toString() {
		return "Giocatore [nickName=" + nickName + ", tamaGolem=" + tamaGolem + "]";
	}
	
}
