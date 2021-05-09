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
	
	public Giocatore(String nickName) {
		this.nickName = nickName;
		tamaGolem = new ArrayList<>();
	}
	
	public void addTamagolem(ArrayList<TamaGolem> tamaGolem) {
		this.tamaGolem = tamaGolem;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public ArrayList<TamaGolem> getTamaGolem(){
		return this.tamaGolem;
	}
	
	public TamaGolem evocazioneTamagolem() {
		if(tamaGolem.size() != 0)
			return tamaGolem.get(0);
		return null;
	}

	@Override
	public String toString() {
		return "Giocatore [nickName=" + nickName + ", tamaGolem=" + tamaGolem + "]";
	}
	
}
