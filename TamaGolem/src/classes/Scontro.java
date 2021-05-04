/**
 * 
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 *
 */
public class Scontro {
	
	public final static int N = 5;
	public final static int P = 3;
	public final static int S = 15;
	public final static int G = 2;
	public final static String GIOCATORE_CASA = "casa";
	public final static String GIOCATORE_OSPITE = "ospite";
	
	private Giocatore giocatoreCasa;
	private Giocatore giocatoreOspite;
	private TamaGolem tamaGolemCasa;
	private TamaGolem tamaGolemOspite;
	private Giocatore vincitore;
	private static HashMap<String, Integer> saccaComune;
	
	public Scontro(Giocatore giocatoreCasa,Giocatore giocatoreOspite) {
		this.giocatoreCasa = giocatoreCasa;
		this.giocatoreOspite  = giocatoreOspite;
		this.giocatoreCasa.addTamagolem(creaTamaGolem(GIOCATORE_CASA));
		this.giocatoreOspite.addTamagolem(creaTamaGolem(GIOCATORE_OSPITE));
		tamaGolemCasa = giocatoreCasa.getTamaGolem().get(0);
		tamaGolemOspite = giocatoreOspite.getTamaGolem().get(0);
		saccaComune = new HashMap<>();
		setSaccaComune();
	}
	
	public boolean evocazioneTamaGolem(String tipoGiocatore) {  //tipoGiocatore sarà "casa" o "ospite" e serve per verificare quale evocazione fare: evocare il tamaGolemCasa oppure il tamaGolemOspite
		if(tipoGiocatore.equalsIgnoreCase(GIOCATORE_CASA)) {
			if(giocatoreCasa.evocazioneTamagolem() != null) {
				tamaGolemCasa = giocatoreCasa.evocazioneTamagolem();
				tamaGolemCasa.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune));
				return true;
			}
			else {
				vincitore = giocatoreOspite;
				return false;
			}
		}
		else {
			if(giocatoreOspite.evocazioneTamagolem() != null) {
				tamaGolemOspite = giocatoreOspite.evocazioneTamagolem();
				tamaGolemOspite.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune));
				return true;
			}
			else {
				vincitore = giocatoreCasa;
				return false;
			}
		}
	}
	
	/**
	 * Metodo per rigenerare la saccaComune ogni volta che inzia un nuovo scontro
	 */
	public void setSaccaComune() {
		for(int i = 0; i < N; i++)
			saccaComune.put(Pietra.NOMI_ELEMENTI[i], P);
	}
	
	public static ArrayList<TamaGolem> creaTamaGolem(String giocatore) {
		ArrayList<TamaGolem> tama = new ArrayList<>();
		if(giocatore.equalsIgnoreCase(GIOCATORE_CASA)) {
			for(int i = 0; i < Scontro.G; i++) {
				TamaGolem tamaGolemCasa = new TamaGolem();
				tamaGolemCasa.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune));
				tama.add(tamaGolemCasa);
			}
			return tama;
		}
		else {
			for(int i = 0; i < Scontro.G; i++) {
				TamaGolem tamaGolemOspite = new TamaGolem();
				tamaGolemOspite.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune));
				tama.add(tamaGolemOspite);
			}
			return tama;
		}
	}
	
}
