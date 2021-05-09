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
	
	public final static int VITA_MASSIMA = 20;
	
	private ArrayList<Pietra> pietreIngurgitate;
	private int vita;
	
	public TamaGolem() {
		this.vita = VITA_MASSIMA;
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
