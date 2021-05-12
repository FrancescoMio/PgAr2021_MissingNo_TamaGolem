/**
 * 
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 *
 */
public class InputOutputGiocatore {
	public static final String PIETRE_DISPONIBILI = "PIETRE DISPONIBILI NELLA SACCA COMUNE:";
	public static final String GOLEM_CASA = "%nGIOCATORE CASA - HAI %d TAMAGOLEM A DISPOSIZIONE ! %n DEVI DARE AL TUO TAMAGOLEM %d PIETRE";
	public static final String GOLEM_OSPITE = "%nGIOCATORE OSPITE - HAI %d TAMAGOLEM A DISPOSIZIONE !%n DEVI DARE AL TUO TAMAGOLEM %d PIETRE";
	public static final String INSERIMENTO_NICKNAME_CASA = "INSERISCI UN NICKNAME PER IL GIOCATORE CASA: ";
	public static final String INSERIMENTO_NICKNAME_OSPITE = "INSERISCI UN NICKNAME PER IL GIOCATORE OSPITE: ";
	public static final String INSERIMENTO_PIETRE = "SELEZIONA IL NUMERO DELLA PIETRA CHE VUOI DARE AL TUO TAMAGOLEM: ";
	public static final String RICHIEDI_N = "INSERISCI IL NUMERO DI ELEMENTI DELL'EQUILIBRIO (compreso tra 3 e 10): ";
	public static final String SACCA_VUOTA = "LE PIETRE DI QUESTO ELEMENTO SONO ESAURITE! INSERISCI UN ALTRO VALORE !";
	public static final String GOLEM_EVOCATO = "DEVI DARE DA MANGIARE AL TAMAGOLEM CHE HAI EVOCATO!";
	public static final String NUMERO_GOLEM = "%nSTAI DANDO DA MANGIARE AL TAMAGOLEM NUMERO %d";
	public static final int MIN_N = 3;
	public static final int MAX_N = 10;
	
	/**
	 * Metodo per seleziomare le pietre da dare al tamagolem
	 * @param saccaComune  contenitore di tutte le pietre che memorizza il loro numero per ogni genere
	 * @return le pietre da dare al tamagolem
	 */
	public static ArrayList<Pietra> selezionaPietre (Map<String, Integer> saccaComune, String giocatore, int numeroTamagolem) {
		ArrayList<Pietra> pietre = new ArrayList<>();
		stampaSaccaComune(saccaComune);
		if(giocatore.equalsIgnoreCase(Scontro.GIOCATORE_CASA) && numeroTamagolem != -1)
			System.out.println("- - - - - - - - - - - - - - - - - - -"+String.format(GOLEM_CASA, Scontro.getG(), Scontro.getP()));
		if(giocatore.equalsIgnoreCase(Scontro.GIOCATORE_OSPITE) && numeroTamagolem != -1)
			System.out.println("- - - - - - - - - - - - - - - - - - -"+String.format(GOLEM_OSPITE, Scontro.getG(), Scontro.getP()));
		if(numeroTamagolem == -1)
			System.out.println("- - - - - - - - - - - - - - - - - - - %n " + GOLEM_EVOCATO);
		else {
			System.out.println(String.format(NUMERO_GOLEM, numeroTamagolem));
		}
		for(int i = 0; i < Scontro.getP(); i++) {
			stampaSaccaComune(saccaComune);
			int scelta = 0;
			boolean empty = true;
			while(empty) {
				scelta = InputDati.leggiInteroCompreso(INSERIMENTO_PIETRE, 1, Scontro.getN());
				if(saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) != 0) {
					empty = false;
				}
				else
					System.err.println(SACCA_VUOTA);
			}
			switch (scelta) { 
			case 1:
				Pietra pietra1 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra1.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				//System.out.println(pietra1.getDanni());
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra1);
				break;
			case 2: 
				Pietra pietra2 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra2.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				//System.out.println(pietra2.getDanni());
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra2);
				break;
			case 3:
				Pietra pietra3 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra3.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra3);
				break;
			case 4:
				Pietra pietra4 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra4.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra4);
				break;
			case 5:
				Pietra pietra5 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra5.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra5);
				break;
			case 6:
				Pietra pietra6 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra6.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra6);
				break;
			case 7:
				Pietra pietra7 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra7.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra7);
				break;
			case 8:
				Pietra pietra8 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra8.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra8);
				break;
			case 9:
				Pietra pietra9 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra9.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra9);
				break;
			case 10:
				Pietra pietra10 = new Pietra(Pietra.getNOMI_ELEMENTI(scelta-1));
				pietra10.setDanniPietra(Scontro.getEquilibrioPartita(), scelta - 1);
				saccaComune.replace(Pietra.getNOMI_ELEMENTI(scelta-1), saccaComune.get(Pietra.getNOMI_ELEMENTI(scelta-1)) - 1);
				pietre.add(pietra10);
				break;
			}
		}
		System.out.println("- - - - - - - - - - - - - - - - - - -");
		return pietre;
	}
	
	public static int richiediN() {
		int n = InputDati.leggiInteroCompreso(RICHIEDI_N, MIN_N, MAX_N);
		return n;
	}
	
	public static int calcolaP(int n) {
		int p = ((n +1) + 3 - 1) / 3;
		p++;
		return p;
	}
	
	public static int calcolaG(int n, int p) { 
		int a = (n - 1) * (n - 2);
		int b = p * 2;
		int g = (a + b - 1) / b;
		return g;
	}
	
	public static int calcolaS(int n, int g, int p) { 
		int a = (2 * g * p);
		int s = (a + n - 1) / n;
		s = s * n;
		return s;
	}
	
	/**
	 * Metodo per creare un giocatore con un proprio nickName richiesto tramite input all'utente
	 * @param giocatore specifica se si tratta di un giocatore casa	o ospite
	 * @return l'istanza del giocatore
	 */
	public static Giocatore creaGiocatore(String giocatore) {

		if(giocatore.equalsIgnoreCase(Scontro.GIOCATORE_CASA)) {
			String nickName = InputDati.leggiSringaNonVuotaSenzaNumeri(INSERIMENTO_NICKNAME_CASA);
			Giocatore giocatoreCasa = new Giocatore(nickName);
			return giocatoreCasa;
		}
		else {
			String nickName = InputDati.leggiSringaNonVuotaSenzaNumeri(INSERIMENTO_NICKNAME_OSPITE);
			Giocatore giocatoreOspite = new Giocatore(nickName);
			return giocatoreOspite;
		}
	}
	
	public static void stampaSaccaComune(Map<String, Integer> saccaComune) {
		System.out.println(PIETRE_DISPONIBILI);
		int k = 1;
		for (Entry<String, Integer> entry : saccaComune.entrySet()) {
			System.out.println(k + " - "+ entry.getKey() + " -> " + entry.getValue());
			k++;
		}
	}

}
