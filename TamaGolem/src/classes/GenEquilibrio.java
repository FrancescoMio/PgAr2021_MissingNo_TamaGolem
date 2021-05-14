package classes;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author FRANCESCO MIO
 * 
 * PREMESSA: alcune volte viene generata la matrice(corretta) altre si genera un loop infinito nel codice
 * 
 * Idea generale:
 * 1) riempire la diagonale di zeri
 * 2) riempire prima riga e prima colonna con zeri alternati nel senso che se c'è uno zero in poszione 01 non ci può essere anche in 10
 * 3) riempire caselle vuote (nel codice identificate con valore -1) con valori la cui somma deve essere >= al numero di caselle vuote della rispettiva
 *    colonna + eventuali valori presenti sulla colonna stessa
 * 4) proseguire di nuovo con il punto 2 e 3 con seconda riga e seconda colonna e così via
 * 5) Quando si arriva alla penultima riga e quindi anche alla penultima colonna se la somma della riga è > della somma della colonna allora lo zero
 *    andrà messo sulla casella vuota della riga altrimenti sulla casella vuota della colonna
 * 6) inserire infine l'ultimo valore necessario ed è fatta
 *
 */
public class GenEquilibrio {
	public static final String RIVELAZIONE_EQUILIBRIO = "L'EQUILIBRIO DELLA PARTITA E':";
	
	public static void gen(int numElementi, int numVita){
		int [][] matEquilibrio = new int[numElementi][numElementi];
		setCaselleVuote(matEquilibrio, numElementi);
		int numeroGiro= 0,quantiZeri = numElementi - 2;
		for(numeroGiro = 0; numeroGiro < numElementi-1; numeroGiro++) {
			if(numeroGiro == numElementi-2) {
				setValoriPenultimaRigaColonna(matEquilibrio, numeroGiro, numElementi, numVita);
			}
			else {
				setRigaColonnaZeri(matEquilibrio, numeroGiro, quantiZeri, numElementi);
				setValoriRigaColonna(matEquilibrio, numeroGiro, numElementi, numVita);
				quantiZeri--;
			}
		}
		stampaMatrice(matEquilibrio, numElementi);
		//System.out.println("oh cazzo");
		//return matEquilibrio;
	}
	
	public static void setRigaColonnaZeri(int [][] mat , int numeroGiro, int nZeri, int numElementi) {
		int quantiZeri = getRandomNumber(1, nZeri+1); //numeri di zeri che andranno posizionati sulla riga
		ArrayList<Integer> posizioniZeri = new ArrayList<>(); // array che mi tiene memorizzate le posizioni dove mettere gli zeri,
		ArrayList<Integer> posizioniDoppie = new ArrayList<>(); 
		for(int i = 0; i < quantiZeri; i++) {
			int valore = getRandomNumber(numeroGiro+1, numElementi);
			while(posizioniDoppie.contains(valore)) {
				valore = getRandomNumber(numeroGiro+1, numElementi);
				System.out.println("ff");
			}
			posizioniZeri.add(valore);
			posizioniDoppie.add(valore);
		}
		//System.out.println(posizioniZeri);
		//posiziona gli zeri sulla riga
		for(int i = 0; i < posizioniZeri.size(); i++)
			mat[numeroGiro][posizioniZeri.get(i)] = 0;
		//posiziona gli zeri sulla colonna
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(!posizioniZeri.contains(i))
				mat[i][numeroGiro] = 0;
		}
	}
	
	public static void setValoriRigaColonna(int [][] mat , int numeroGiro, int numElementi, int numVita) {
		int sommaColonna = getSommaColonna(mat, numeroGiro, numElementi);
		int quantiNumeriSullaRiga = 0,quantiNumeriSullaColonna = 0;
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(mat[numeroGiro][i] == -1)
				quantiNumeriSullaRiga++;
		}
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(mat[i][numeroGiro] == -1)
				quantiNumeriSullaColonna++;
		}
		/*int sommaTotaleRiga = 0,sommaTotaleColonna = 0;
		for(int i = 0; i < numElementi; i++) {
			if(mat[numeroGiro][i] != -1)
				sommaTotaleRiga += mat[numeroGiro][i];
		}
		for(int i = 0; i < numElementi; i++) {
			if(mat[i][numeroGiro] != -1)
				sommaTotaleColonna += mat[i][numeroGiro];
		}
		ArrayList<Integer> valoriRiga = new ArrayList<>();
		ArrayList<Integer> posizioni = new ArrayList<>();
		ArrayList<Integer> valoriCasualiRiga = new ArrayList<>();
		int sommaRiga = sommaTotaleRiga;
		boolean sommaMaggiore = true;
		for(int i = 1; i <= numVita; i++)
			valoriRiga.add(i);
		System.out.println(valoriRiga);
		while(sommaMaggiore) {
			ArrayList<Integer> appoggioValoriCasualiRiga = new ArrayList<>();
			sommaRiga = sommaTotaleRiga;
			for(int i = 0; i < quantiNumeriSullaRiga; i++) {
				int pos = getRandomNumber(0, numVita);
				sommaRiga += valoriRiga.get(pos);
				appoggioValoriCasualiRiga.add(valoriRiga.get(pos));
			}
			if(sommaRiga >= sommaTotaleColonna && sommaRiga <= numVita) {
				for(int i = 0; i < appoggioValoriCasualiRiga.size(); i++)
					valoriCasualiRiga.add(appoggioValoriCasualiRiga.get(i));
				sommaMaggiore = false;
			}
			System.out.println("MM");
		}
		int k = 0;
		//setta i valori corretti sulla riga
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(mat[numeroGiro][i] == -1) {
				mat[numeroGiro][i] = valoriCasualiRiga.get(k);
				k++;
			}
		}
		
		ArrayList<Integer> valoriCasualiColonna = new ArrayList<>();
		boolean sommaUguale = false;
		int sommaInizialeColonna = sommaTotaleColonna;
		while(!sommaUguale) {
			sommaTotaleColonna = sommaInizialeColonna;
			ArrayList<Integer> appoggioValoriCasualiColonna = new ArrayList<>();
			if(quantiNumeriSullaColonna == 1) {
				valoriCasualiColonna.add(sommaTotaleRiga);
				sommaUguale = true;
			}
			else {
				for(int i = 0; i < quantiNumeriSullaColonna; i++) {
					int pos = getRandomNumber(0, numVita);
					sommaTotaleColonna += valoriRiga.get(pos);
					appoggioValoriCasualiColonna.add(valoriRiga.get(pos));
				}
				if(sommaTotaleColonna == sommaRiga) {
					for(int i = 0; i < appoggioValoriCasualiColonna.size(); i++)
						valoriCasualiColonna.add(appoggioValoriCasualiColonna.get(i));
					sommaUguale = true;
				}
			}
			System.out.println("gg");
		}
		//setta i valori corretti sulla colonna
		int j = 0;
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(mat[i][numeroGiro] == -1) {
				mat[i][numeroGiro] = valoriCasualiColonna.get(j);
				j++;
			}
		}*/
		
		
		ArrayList<Integer> valoriCasualiRiga = new ArrayList<>();
		int sommaTotaleRiga = 0,sommaTotaleColonna = 0;
		for(int i = 0; i < numElementi; i++) {
			if(mat[numeroGiro][i] != -1)
				sommaTotaleRiga += mat[numeroGiro][i];
		}
		for(int i = 0; i < numElementi; i++) {
			if(mat[i][numeroGiro] != -1)
				sommaTotaleColonna += mat[i][numeroGiro];
		}
		//System.out.println(sommaColonna);
		//System.out.println(quantiNumeriSullaRiga);
		boolean sommaMaggiore = true;
		int sommaInizialeRiga = sommaTotaleRiga;
		while(sommaMaggiore) {
			ArrayList<Integer> appoggioValoriCasualiRiga = new ArrayList<>();
			sommaTotaleRiga = sommaInizialeRiga;
			for(int i = 0; i < quantiNumeriSullaRiga; i++) {
				int valore = getRandomNumber(1, numVita+1);
				appoggioValoriCasualiRiga.add(valore);
				sommaTotaleRiga += valore;					
			}
			if(sommaTotaleRiga >= sommaColonna && sommaTotaleRiga < numVita) {
				for(int i = 0; i < appoggioValoriCasualiRiga.size(); i++)
					valoriCasualiRiga.add(appoggioValoriCasualiRiga.get(i));
				sommaMaggiore = false;
			}
			System.out.println("gg");
		}
		
		int k = 0;
		//setta i valori corretti sulla riga
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(mat[numeroGiro][i] == -1) {
				mat[numeroGiro][i] = valoriCasualiRiga.get(k);
				k++;
			}
		}

		ArrayList<Integer> valoriCasualiColonna = new ArrayList<>();
		boolean sommaUguale = false;
		int sommaInizialeColonna = sommaTotaleColonna;
		//float numVitaIniziale = numVita/2;
		//numVitaIniziale = Math.round(numVitaIniziale);
		while(!sommaUguale) {
			if(quantiNumeriSullaColonna == 1) {
				valoriCasualiColonna.add(sommaTotaleRiga);
				sommaUguale = true;
			}
			else {
				sommaTotaleColonna = sommaInizialeColonna;
				ArrayList<Integer> appoggioValoriCasualiColonna = new ArrayList<>();
				for(int i = 0; i < quantiNumeriSullaColonna; i++) {
					int valore = getRandomNumber(1, numVita+1);
					appoggioValoriCasualiColonna.add(valore);
					sommaTotaleColonna += valore;
				}
				if(sommaTotaleColonna == sommaTotaleRiga) {
					for(int i = 0; i < appoggioValoriCasualiColonna.size(); i++)
						valoriCasualiColonna.add(appoggioValoriCasualiColonna.get(i));
					sommaUguale = true;
				}
				System.out.println("hh");				
			}
		}
		
		//setta i valori corretti sulla colonna
		int j = 0;
		for(int i = numeroGiro+1; i < numElementi; i++) {
			if(mat[i][numeroGiro] == -1) {
				mat[i][numeroGiro] = valoriCasualiColonna.get(j);
				j++;
			}
		}
	}
	
	public static void setValoriPenultimaRigaColonna(int [][] mat , int numeroGiro, int numElementi, int numVita) {
		int sommaPenultimaRiga = 0,sommaInizialeriga = 0;
		int sommaPenultimaColonna = 0,sommaInizialeColonna = 0;
		for(int i = 0; i < numElementi; i++) {
			if(mat[numeroGiro][i] != -1)
				sommaPenultimaRiga += mat[numeroGiro][i];
		}
		for(int i = 0; i < numElementi; i++) {
			if(mat[i][numeroGiro] != -1)
				sommaPenultimaColonna += mat[i][numeroGiro];
		}
		if(sommaPenultimaRiga == sommaPenultimaColonna)
			System.out.println("porca troia");
		sommaInizialeColonna = sommaPenultimaColonna;
		sommaInizialeriga = sommaPenultimaRiga;
		if(sommaPenultimaRiga > sommaPenultimaColonna) {
			mat[numeroGiro][numeroGiro+1] = 0;
			boolean sommaUguale = false;
			while(!sommaUguale) {
				sommaPenultimaColonna = sommaInizialeColonna;
				int valore = getRandomNumber(1, numVita+1);
				sommaPenultimaColonna += valore;
				if(sommaPenultimaColonna == sommaPenultimaRiga) {
					mat[numeroGiro+1][numeroGiro] = valore;
					sommaUguale = true;
				}
				System.out.println("ll");
			}
		}
		else {
			mat[numeroGiro+1][numeroGiro] = 0;
			boolean sommaUgualeColonna = false;
			while(!sommaUgualeColonna) {
				sommaPenultimaRiga = sommaInizialeriga;
				int valore = getRandomNumber(1, numVita+1);
				sommaPenultimaRiga += valore;
				if(sommaPenultimaRiga == sommaPenultimaColonna) {
					mat[numeroGiro][numeroGiro+1] = valore;
					sommaUgualeColonna = true;
				}
				System.out.println("qq");
			}
		}
	}
	
	public static int getSommaColonna(int [][] mat , int numeroGiro, int numElementi) {
		int somma = 0;
		for(int i = 0; i < numElementi; i++) {
			if(mat[i][numeroGiro] == -1)
				somma += 1;
			else
				somma += mat[i][numeroGiro];
		}
		return somma;
	}
	
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static void setCaselleVuote(int mat [][],int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i != j)
					mat[i][j] = -1;
			}
		}
	}
	
	public static void stampaMatrice(int [][] mat, int n) {
		System.out.println(RIVELAZIONE_EQUILIBRIO);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println("");
		}
	}

}


