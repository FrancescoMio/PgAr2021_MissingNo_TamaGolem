package classes;

import java.util.ArrayList;

/*
LEGGIMI ATTENTAMENTE PRIMA

ho finito di fare questa classe alle 23:36, e non ho avuto tempo di commentare tutto

onestamente sono stanco

Se sono necessarie spiegazioni, contattatemi su Telegram (sono Giovanni Ballini)

Magari domani faccio un video su youtube

Grazie per l'attenzione

*/

public class GenerazioneEquilibrio {
	
public static final String RIVELAZIONE_EQUILIBRIO = "L'EQUILIBRIO DELLA PARTITA E':";
	
	public static int[][] gen(int numElementi){
	//System.out.println("programma iniziato");
	//inizializzazione Matrice
	int [][] interazioni = new int[numElementi][numElementi];
	for (int i=0; i<numElementi; i++) {
		for (int j=0; j<numElementi;j++) {
			interazioni[i][j]=0;
		}
	}
	
	//Scelta random Vita
	int numVitaTemp=(int) Math.floor(Math.random()*(10)+1);
	
	if (numVitaTemp*3<numElementi) {
		numVitaTemp=(int) Math.floor(Math.random()*(10)+1);
	}
	//Crea matrice iniziale 3x3
	int randomTemp=-1;
	for(int i=0; i<3;i++) {
		int random=(int) Math.floor(Math.random()*(3));
		
		while (i==random||interazioni[random][i]!=0||random==randomTemp) {
			random=(int) Math.floor(Math.random()*(3));
		}
		
		interazioni[i][random]=numVitaTemp;
		randomTemp=random;
	}

	//Costruzione del resto della matrice
	for (int i=3; i<numElementi; i++) {
		int riga=(int) Math.floor(Math.random()*(i));
		while(!rigaValida(interazioni, riga)) {
			riga=(int) Math.floor(Math.random()*(i));
		}
	
		int elementoRiga=(int) Math.floor(Math.random()*(i));
		while(interazioni[riga][elementoRiga]==0||interazioni[riga][elementoRiga]==1) {
			elementoRiga=(int) Math.floor(Math.random()*(i));
		}
		
		int primoSplit=(int) Math.floor(Math.random()*(interazioni[riga][elementoRiga]));
		
		if (primoSplit==0) {
			primoSplit=1;
		}
		
		int	secondoSplit=interazioni[riga][elementoRiga]-primoSplit;
		
		interazioni[riga][elementoRiga]=primoSplit;
		interazioni[riga][i]=secondoSplit;
		interazioni[i][elementoRiga]=secondoSplit;
		//stampaMatrice(interazioni, numElementi, numVitaTemp);
		interazioni=matrixFix(interazioni, i);
		
		
	}
	
	return shuffler (interazioni,numElementi);


	}
	
	public static int[][] matrixFix(int interazioni[][], int pos){
		/*Questo metodo riempie gli spazi (affinchè ogni pietra "interagisca" con un'altra)
		 * 
		 * 
		 */
		for (int i=0; i<pos; i++) {
			int random=(int) Math.floor(Math.random()*(10)+1);
			if (interazioni[pos][i]==0&&interazioni[i][pos]==0) {
				//System.out.println(pos + " "+ i);
				ArrayList<Integer> pathing =pathing(interazioni, i, pos, i);
				//System.out.println(pathing);
				for (int j=0; j<pathing.size()-1;j++) {
					interazioni[pathing.get(j)][pathing.get(j+1)]+=random;
				}
				/*interazioni[pos][i]+=random;
				int temp=valoreValido(interazioni,i);
				interazioni[i][temp]+=random;
				interazioni[temp][pos]+=random;*/
			}
		}
		return interazioni;
	}
	
	
	public static int[][] shuffler(int interazioni[][], int numElementi){
		//Metodo necessario per randomizzare ulteriormente le cose
		int shuffles=(int) Math.floor(Math.random()*(numElementi));
		for (int i=0; i<shuffles;i++) {
			//Scelgo due righe casuali non uguali
			int randomRiga=(int) Math.floor(Math.random()*(numElementi));
			int randomRigaDue=(int) Math.floor(Math.random()*(numElementi));
			while (randomRigaDue==randomRiga) {
				randomRigaDue=(int) Math.floor(Math.random()*(numElementi));
			}
			//randomRiga diventa randomRigaDue e viceversa
			int temp;
			int tempDue;
			for (int j=0; j<numElementi; j++) {
				temp=interazioni[randomRiga][j];
				tempDue=interazioni[randomRigaDue][j];
				interazioni[randomRiga][j]=tempDue;
				interazioni[randomRigaDue][j]=temp;
				
			}
			//Scambio i valori in tutte le righe
			for (int j=0; j<numElementi; j++) {
				temp=interazioni[j][randomRiga];
				tempDue=interazioni[j][randomRigaDue];
				interazioni[j][randomRiga]=tempDue;
				interazioni[j][randomRigaDue]=temp;
			}
		}
		return interazioni;
	}
	
	
	public static boolean rigaValida(int riga[][], int pos) {
		//Se la riga non contiene 0 e 1 allora è valida
		boolean check = false;
		for (int i=0; i<riga.length; i++) {
			if (riga[pos][i]!=1&&riga[pos][i]!=0) 
				check=true;
		}
		return check;
	}
	
	public static ArrayList<Integer> posizioniValide(int matrice[][], int pos) {

		ArrayList<Integer> posizioni  = new ArrayList<Integer>();
		for (int i=0; i<matrice.length; i++) {
			if (matrice[pos][i]!=0) 
				posizioni.add(i);
		}
		return posizioni;
	}
	
	public static ArrayList<Integer> pathing(int matrice[][], int rigaPartenza, int rigaArrivo, int colonnaArrivo){
		ArrayList<Integer> pathing = new ArrayList<>();
		ArrayList<Integer> lastI = new ArrayList<>();
		pathing.add(rigaArrivo);
		pathing.add(colonnaArrivo);
		ArrayList<Integer> posizioniValide  = posizioniValide(matrice, rigaPartenza);
		//System.out.println(posizioniValide);
		boolean check=false;
		boolean check2=true;
		boolean banana=true;
		int i=0;
		while(banana) {
			
			if (check)
				i=0;
			check=false;
			
			for (int j=0; j<posizioniValide.size();j++) {
				if (posizioniValide.get(j)==rigaArrivo) {
					pathing.add(posizioniValide.get(j));
					return pathing;
				}
			}
			
			while(i>(posizioniValide.size())-1) {
				pathing.remove(pathing.get(pathing.size()-1));
				//System.out.println("questo è il pathing dopo la rimozione " + pathing);
				rigaPartenza=pathing.get(pathing.size()-1);
				i= lastI.get(lastI.size()-1)+1;
				lastI.remove(lastI.size()-1);
				posizioniValide  = posizioniValide(matrice, rigaPartenza);
			}
			
			
			if(rigaPartenza!=posizioniValide.get(i)) {
				for (int k=0; k<pathing.size();k++) {
					if (pathing.get(k)==posizioniValide.get(i))
							check2=false;
				}
				if (check2) {
				lastI.add(i);
				//System.out.println("questa è la riga partenza 1 :"+posizioniValide.get(i));
				//System.out.println("questa è la riga partenza 2 :"+ rigaPartenza);
				pathing.add(posizioniValide.get(i));
				//System.out.println("questo è il pathing " + pathing);
				
				rigaPartenza=posizioniValide.get(i);
				posizioniValide  = posizioniValide(matrice, rigaPartenza);
				//System.out.println("posizioni valide " +posizioniValide);
				check=true;
				}
			}
			
			check2=true;
			i++;
			
			}
		//System.out.println("non dovrebbe succedere");
		return pathing;
			
		}
		
	
	/*public static int[][] bara(int interazioni[][], int numElementi, int vita){
		
		for (int i=0; i<numElementi;i++) {
			if (i==(numElementi-1)) 
				interazioni[i][0]=vita;
			else
				interazioni[i][i+1]=vita;
		}
		return interazioni;
	}*/
	
	public static void stampaMatrice(int [][] mat, int n) {
		System.out.println(RIVELAZIONE_EQUILIBRIO);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
/*	public static void stampaMatrice(int interazioni[][],int numElementi,int numVita) {
		System.out.println("");
		System.out.println("Dati " +numElementi + " numeri di pietra e " +numVita+ " di vita");
		int sommax=0;
		int sommay=0;
		
		for (int i=0; i<numElementi; i++) {
			for (int j =0; j<numElementi; j++) {
				if (interazioni[i][j]>=0) {
					System.out.print(" "+interazioni[i][j]);
					sommax=sommax +interazioni[i][j];
				}
				else
					System.out.print(" " +0);
				
			}
			System.out.print(" = " +sommax);
			sommax=0;
			System.out.println("");
		}
		
		for (int i=0; i<numElementi; i++) {
			
			
			System.out.print(" =");
			
		}
		System.out.println("");
		
		for (int i=0; i<numElementi; i++) {
			
			for (int j =0; j<numElementi; j++) {
				if (interazioni[j][i]>=0)
					sommay=sommay +interazioni[j][i];
			}
			
			System.out.print(" "+sommay);
			sommay=0;
			
		}
		return;
		
		
		
			
		
	}*/
	
	
	
	
	

}


