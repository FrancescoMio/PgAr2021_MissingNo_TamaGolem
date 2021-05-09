/**
 * 
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

/**
 * Classe che gestisce la parte del programma relativa allo scontro
 *
 */
public class Scontro {
	
	
	public final static String GIOCATORE_CASA = "casa";
	public final static String GIOCATORE_OSPITE = "ospite";
	public static final int N_NOMI = 18;
	
	private Giocatore giocatoreCasa;
	private Giocatore giocatoreOspite;
	private TamaGolem tamaGolemCasa;
	private TamaGolem tamaGolemOspite;
	private Giocatore vincitore;
	private static Map<String, Integer> saccaComune;
	private static int N;
	private static int P;
	private static int S;
	private static int G;
	
	/**
	 * Costruttore della classe Scontro, implementa la costruzione degli oggetti giocatoreCasa/Ospite e i rispettivi tamaGolem
	 */
	public Scontro() {
		nuovaPartita();
	}
	
	public void nuovaPartita() {
		this.N = InputOutputGiocatore.richiediN();
		this.P  = InputOutputGiocatore.calcolaP(N);
		this.G = InputOutputGiocatore.calcolaG(N, P);
		this.S = InputOutputGiocatore.calcolaS(N, G, P);
		Pietra.setNomiElementi(randomNumbers(), N);
		this.giocatoreCasa = InputOutputGiocatore.creaGiocatore(GIOCATORE_CASA);
		this.giocatoreOspite  = InputOutputGiocatore.creaGiocatore(GIOCATORE_OSPITE);
		saccaComune = new HashMap<>();
		setSaccaComune();
		setNomiElementi(saccaComune);
		/*for(int i = 0; i < N; i++)
			System.out.print(Pietra.getNOMI_ELEMENTI(i) + " ");
		System.out.println(saccaComune);*/
		this.giocatoreCasa.addTamagolem(creaTamaGolem(GIOCATORE_CASA));
		this.giocatoreOspite.addTamagolem(creaTamaGolem(GIOCATORE_OSPITE));
		tamaGolemCasa = giocatoreCasa.getTamaGolem().get(0);
		tamaGolemOspite = giocatoreOspite.getTamaGolem().get(0);
	}
	
	/**
	 * Evocazione di un tamaGolem durante lo scontro e controllo di vittoria
	 * @param tipoGiocatore sarà "casa" o "ospite" e serve per verificare quale evocazione fare: evocare il tamaGolemCasa oppure il tamaGolemOspite
	 * @return
	 */
	public boolean evocazioneTamaGolem(String tipoGiocatore) {
		if(tipoGiocatore.equalsIgnoreCase(GIOCATORE_CASA)) {
			if(giocatoreCasa.evocazioneTamagolem() != null) {
				tamaGolemCasa = giocatoreCasa.evocazioneTamagolem();
				tamaGolemCasa.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,tipoGiocatore));
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
				tamaGolemOspite.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,tipoGiocatore));
				return true;
			}
			else {
				vincitore = giocatoreCasa;
				return false;
			}
		}
	}
	
	public static ArrayList<Integer> randomNumbers() {
		ArrayList<Integer> numbers = new ArrayList<>();
		Random generatore = new Random();
		int d = 1 + generatore.nextInt(N_NOMI);
		numbers.add(d);
		for(int i = 0; i < N; i++) {
			boolean flag = true;
			while(flag) {
				int count = 0;
				int b = 1 + generatore.nextInt(N+1);
				for(int number : numbers) {
					if (b != number)
						count++;
				}
				if(count == numbers.size()) {
					numbers.add(b);
					flag = false;
				}
			}
		}
		return numbers;
	}
	
	/**
	 * Metodo per rigenerare la saccaComune ogni volta che inzia un nuovo scontro
	 */
	public void setSaccaComune() {
		String []names = Pietra.getNOMI_ELEMENTI();
		/*for(String name : names)
			System.out.println(name  + " ");*/
		for(int i = 0; i < N; i++)
			saccaComune.put(names[i], P);
	}
	
	public void setNomiElementi(Map<String, Integer> saccaComune) {
		int i = 0;
		for (Entry<String, Integer> entry : saccaComune.entrySet()) {
			Pietra.setNomi(entry.getKey(), i);
			i++;
		}
	}
	
	/**
	 * 
	 * @param giocatore
	 * @return
	 */
	public static ArrayList<TamaGolem> creaTamaGolem(String giocatore) {
		ArrayList<TamaGolem> tama = new ArrayList<>();
		if(giocatore.equalsIgnoreCase(GIOCATORE_CASA)) {
			for(int i = 0; i < Scontro.G; i++) {
				TamaGolem tamaGolemCasa = new TamaGolem();
				tamaGolemCasa.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,GIOCATORE_CASA));
				tama.add(tamaGolemCasa);
			}
			return tama;
		}
		else {
			for(int i = 0; i < Scontro.G; i++) {
				TamaGolem tamaGolemOspite = new TamaGolem();
				tamaGolemOspite.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,GIOCATORE_OSPITE));
				tama.add(tamaGolemOspite);
			}
			return tama;
		}
	}
	
	public Giocatore getVincitore() {
		return vincitore;
	}

	public void setVincitore(Giocatore vincitore) {
		this.vincitore = vincitore;
	}

	public static int getN() {
		return N;
	}

	public static void setN(int n) {
		N = n;
	}

	public static int getP() {
		return P;
	}

	public static void setP(int p) {
		P = p;
	}

	public static int getS() {
		return S;
	}

	public static void setS(int s) {
		S = s;
	}

	public static int getG() {
		return G;
	}

	public static void setG(int g) {
		G = g;
	}

}
