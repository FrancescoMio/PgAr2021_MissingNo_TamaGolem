/**
 * 
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 *
 */
public class InputOutputGiocatore {
	//public static final String[] NOMI_ELEMENTI = {"FUOCO", "ACQUA" , "ETERE" , "TERRA", "ARIA"};
	public static final String PIETRE_DISPONIBILI = "PIETRE DISPONIBILI NELLA SACCA COMUNE:";
	public static final String INSERIMENTO_PIETRE = "ISERISCI TRE NUMERI REALATIVI ALLE TRE PIETRE CHE VUOI DARE AL TUO TAMAGOLEM: ";
	
	public static ArrayList<Pietra> selezionaPietre (HashMap<String, Integer> saccaComune) {
		ArrayList<Pietra> pietre = new ArrayList<>();
		System.out.println(PIETRE_DISPONIBILI);
		for (Entry<String, Integer> entry : saccaComune.entrySet()) {
			int i = 1;
			System.out.println(i + " - "+ entry.getKey() + " -> " + entry.getValue());
			i++;
		}
		for(int i = 0; i < Scontro.P; i++) {
			int scelta = InputDati.leggiInteroConMinimo(INSERIMENTO_PIETRE, 1);
			switch (scelta) { 
			case 1: 
				Pietra pietra1 = new Pietra(Pietra.NOMI_ELEMENTI[scelta-1]);
				pietra1.setDanniPietra(GenerazioneEquilibrio.gen(Scontro.N, TamaGolem.VITA_MASSIMA), scelta - 1);
				saccaComune.replace(Pietra.NOMI_ELEMENTI[scelta-1], saccaComune.get(Pietra.NOMI_ELEMENTI[scelta-1]) - 1);
				pietre.add(pietra1);
			case 2: 
				Pietra pietra2 = new Pietra(Pietra.NOMI_ELEMENTI[scelta - 1]);  
				pietra2.setDanniPietra(GenerazioneEquilibrio.gen(Scontro.N, TamaGolem.VITA_MASSIMA), scelta - 1);
				saccaComune.replace(Pietra.NOMI_ELEMENTI[scelta-1], saccaComune.get(Pietra.NOMI_ELEMENTI[scelta-1]) - 1);
				pietre.add(pietra2);
			case 3:
				Pietra pietra3 = new Pietra(Pietra.NOMI_ELEMENTI[scelta - 1]);
				pietra3.setDanniPietra(GenerazioneEquilibrio.gen(Scontro.N, TamaGolem.VITA_MASSIMA), scelta - 1);
				saccaComune.replace(Pietra.NOMI_ELEMENTI[scelta-1], saccaComune.get(Pietra.NOMI_ELEMENTI[scelta-1]) - 1);
				pietre.add(pietra3);
			case 4:
				Pietra pietra4 = new Pietra(Pietra.NOMI_ELEMENTI[scelta - 1]);
				pietra4.setDanniPietra(GenerazioneEquilibrio.gen(Scontro.N, TamaGolem.VITA_MASSIMA), scelta - 1);
				saccaComune.replace(Pietra.NOMI_ELEMENTI[scelta-1], saccaComune.get(Pietra.NOMI_ELEMENTI[scelta-1]) - 1);
				pietre.add(pietra4);
			case 5:
				Pietra pietra5 = new Pietra(Pietra.NOMI_ELEMENTI[scelta - 1]);
				pietra5.setDanniPietra(GenerazioneEquilibrio.gen(Scontro.N, TamaGolem.VITA_MASSIMA), scelta - 1);
				saccaComune.replace(Pietra.NOMI_ELEMENTI[scelta-1], saccaComune.get(Pietra.NOMI_ELEMENTI[scelta-1]) - 1);
				pietre.add(pietra5);
			}
		}
		return pietre;
	}

}
