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
	
	public final static String TAMAGOLEM_CASA_SCHIERATO = "TAMAGOLEM CASA ----- > SCHIERATO ";
	public final static String TAMAGOLEM_OSPITE_SCHIERATO = "TAMAGOLEM OSPITE ----- > SCHIERATO";
	public final static String TAMAGOLEM_OSPITE_MORTO = "- - - IL TAMAGOLEM OSPITE E' MORTO! - - - ";
	public final static String TAMAGOLEM_CASA_MORTO = "- - - IL TAMAGOLEM CASA E' MORTO! - - - ";
	public final static String GIOCATORE_CASA_VINCE = "------ %s HA VINTO LA PARTITA!--------";
	public final static String GIOCATORE_OSPITE_VINCE = "------ %s HA VINTO LA PARTITA!--------";
	public final static String DANNI_INFLITTI_A_OSPITE = "<<<<IL TAMAGOLEM DI %s HA SUBITO %d DANNI!>>>>";
	public final static String DANNI_INFLITTI_A_CASA = "<<<<IL TAMAGOLEM DI %s HA SUBITO %d DANNI!>>>>";
	public final static String NUOVA_PARTITA = "|---- > VUOI GIOCARE UN'ALTRA PARTITA?  Y/N";
	public final static String FINE_PARTITA = "------------------&n GRAZIE PER AVER GIOCATO!%n----------------";
	
	public final static String GIOCATORE_CASA = "casa";
	public final static String GIOCATORE_OSPITE = "ospite";
	public static final int N_NOMI = 18;
	
	private Giocatore giocatoreCasa;
	private Giocatore giocatoreOspite;
	private TamaGolem tamaGolemCasa;
	private TamaGolem tamaGolemOspite;
	private Giocatore vincitore;
	private static int [][] equilibrioPartita;
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
		equilibrioPartita = GenerazioneEquilibrio.gen(N, TamaGolem.VITA_MASSIMA);
		Pietra.setNomiElementi(randomNumbers(), N);
		this.giocatoreCasa = InputOutputGiocatore.creaGiocatore(GIOCATORE_CASA);
		this.giocatoreOspite  = InputOutputGiocatore.creaGiocatore(GIOCATORE_OSPITE);
		saccaComune = new HashMap<>();
		setSaccaComune();
		setNomiElementi(saccaComune);
		this.giocatoreCasa.addTamagolem(creaTamaGolem(GIOCATORE_CASA));
		this.giocatoreOspite.addTamagolem(creaTamaGolem(GIOCATORE_OSPITE));
		tamaGolemCasa = giocatoreCasa.getTamaGolem().get(0);
		tamaGolemOspite = giocatoreOspite.getTamaGolem().get(0);
		battaglia();
	}
	
	public void battaglia() {
		System.out.println(TAMAGOLEM_CASA_SCHIERATO);
		System.out.println(TAMAGOLEM_OSPITE_SCHIERATO);
		boolean scontroFinito = false;
		int contaPietreUtilizzate = 0;
		int tamaGolemOspiteMorto = 0;
		int tamaGolemCasaMorto = 0;
		while(!scontroFinito) {
			if(contaPietreUtilizzate == P)
				contaPietreUtilizzate = 0;
			Pietra pietraCasa = tamaGolemCasa.getPietreIngurgitate().get(contaPietreUtilizzate);
			Pietra pietraOspite = tamaGolemOspite.getPietreIngurgitate().get(contaPietreUtilizzate);
			String nomePietraCasa = pietraCasa.getNome();
			String nomePietraOspite = pietraOspite.getNome();
			int dannoPietraCasa = pietraCasa.getDanno(nomePietraOspite);
			int dannoPietraOspite = pietraOspite.getDanno(nomePietraCasa);
			int vitaPrimaCasa = tamaGolemCasa.getVita();
			int vitaPrimaOspite = tamaGolemOspite.getVita();
			tamaGolemOspite.setVita(dannoPietraCasa);
			tamaGolemCasa.setVita(dannoPietraOspite);
			if(tamaGolemOspite.getVita() <= 0) {
				System.out.println(TAMAGOLEM_OSPITE_MORTO);
				giocatoreOspite.removeTamaGolem(tamaGolemOspiteMorto);
				if(!evocazioneTamaGolem(GIOCATORE_OSPITE)) {
					scontroFinito = true;
					System.out.println(String.format(GIOCATORE_CASA_VINCE, giocatoreCasa.getNickName()));
					//QUA CI ANDRà IL METODO CHE STAMPA L'EQUILIBRIO
				}	
			}
			if(tamaGolemCasa.getVita() <= 0) {
				System.out.println(TAMAGOLEM_CASA_MORTO);
				giocatoreCasa.removeTamaGolem(tamaGolemCasaMorto);
				if(!evocazioneTamaGolem(GIOCATORE_CASA)) {
					scontroFinito = true;
					System.out.println(String.format(GIOCATORE_OSPITE_VINCE, giocatoreOspite.getNickName()));
					//QUA CI ANDRà IL METODO CHE STAMPA L'EQUILIBRIO
				}	
			}
			if(tamaGolemOspite.getVita() < vitaPrimaOspite)
				System.out.println(String.format(DANNI_INFLITTI_A_OSPITE, giocatoreOspite.getNickName(),vitaPrimaOspite-tamaGolemOspite.getVita()));
			if(tamaGolemCasa.getVita() < vitaPrimaCasa)
				System.out.println(String.format(DANNI_INFLITTI_A_CASA, giocatoreCasa.getNickName(),vitaPrimaCasa-tamaGolemCasa.getVita()));
			contaPietreUtilizzate++;
		}
		String scelta = InputDati.leggiStringaNonVuota(NUOVA_PARTITA);
		if(scelta.equalsIgnoreCase("Y"))
			nuovaPartita();
		else
			System.out.println(FINE_PARTITA);
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
				tamaGolemCasa.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,tipoGiocatore,-1));
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
				tamaGolemOspite.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,tipoGiocatore,-1));
				return true;
			}
			else {
				vincitore = giocatoreCasa;
				return false;
			}
		}
	}
	
	public static int[][] getEquilibrioPartita() {
		return equilibrioPartita;
	}

	public static void setEquilibrioPartita(int[][] equilibrioPartita) {
		Scontro.equilibrioPartita = equilibrioPartita;
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
				tamaGolemCasa.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,GIOCATORE_CASA,i+1));
				tama.add(tamaGolemCasa);
			}
			return tama;
		}
		else {
			for(int i = 0; i < Scontro.G; i++) {
				TamaGolem tamaGolemOspite = new TamaGolem();
				tamaGolemOspite.addPietreIngurgitate(InputOutputGiocatore.selezionaPietre(saccaComune,GIOCATORE_OSPITE,i+1));
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
