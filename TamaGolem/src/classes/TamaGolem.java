/**
 * 
 */
package classes;

import java.util.ArrayList;

/**
 * 
 *
 */
public class TamaGolem {
	
	private ArrayList<Pietra> pietreIngurgitate;
	private int vita;
	
	public TamaGolem(int vitaMassima) {
		this.vita = vitaMassima;
		this.pietreIngurgitate = new ArrayList<>();
	}
	
	public void addPietreIngurgitate(ArrayList<Pietra> pietre) {
		pietreIngurgitate = pietre;
	}
	
	public void setVita(int dannoSubito) {
		vita -= dannoSubito;
	}
	
	public int getVita() {
		return vita;
	}
	
	public ArrayList<Pietra> getPietreIngurgitate() {
		return pietreIngurgitate;
	}

	public void setPietreIngurgitate(ArrayList<Pietra> pietreIngurgitate) {
		this.pietreIngurgitate = pietreIngurgitate;
	}

	public boolean ancoraVivo() {
		if(vita <= 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TamaGolem [pietreIngurgitate=" + pietreIngurgitate + ", vita=" + vita + "]";
	}

}
