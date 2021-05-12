package classes;

import java.util.ArrayList;


public class GenerazioneEquilibrio {
	
	public static final String RIVELAZIONE_EQUILIBRIO = "L'EQUILIBRIO DELLA PARTITA E':";
	
	public static int[][] gen(int numElementi, int numVita){
	//System.out.println("programma iniziato");
	//inizializzazione Matrice
	int [][] interazioni = new int[numElementi][numElementi];
	for (int i=0; i<numElementi; i++) {
		for (int j=0; j<numElementi;j++) {
			interazioni[i][j]=0;
		}
	}
	
	//Creazione matrice 3x3
	int numVitaTemp=(int) Math.floor(Math.random()*(numVita)+1);
	
	//Bara se la vita*3<numElementi
	
	if (numVitaTemp*3<numElementi) {
		//System.out.println("sto barando");
		interazioni=GenerazioneEquilibrio.bara(interazioni, numElementi, numVitaTemp);
		return interazioni;
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
		while(!GenerazioneEquilibrio.rigaValida(interazioni, riga)) {
			riga=(int) Math.floor(Math.random()*(i));
		}
		//System.out.println("presa riga " + riga);
		int elementoRiga=(int) Math.floor(Math.random()*(i));
		while(interazioni[riga][elementoRiga]==0||interazioni[riga][elementoRiga]==1) {
			elementoRiga=(int) Math.floor(Math.random()*(i));
		}
		//System.out.println("preso elemento " + elementoRiga);
		int primoSplit=(int) Math.floor(Math.random()*(interazioni[riga][elementoRiga]));
		
		if (primoSplit==0) {
			primoSplit=1;
		}
		
		int	secondoSplit=interazioni[riga][elementoRiga]-primoSplit;
		
		interazioni[riga][elementoRiga]=primoSplit;
		interazioni[riga][i]=secondoSplit;
		interazioni[i][elementoRiga]=secondoSplit;
		/*GenEquilibrio.stampaMatrice(interazioni, numElementi, numVita);
		System.out.println("");*/
		
		
	}
	return interazioni;


	}
	
	public static boolean rigaValida(int riga[][], int pos) {
		boolean check = false;
		for (int i=0; i<riga.length; i++) {
			if (riga[pos][i]!=1&&riga[pos][i]!=0) 
				check=true;
		}
		return check;
	}
	
	
	public static int[][] bara(int interazioni[][], int numElementi, int vita){
		
		for (int i=0; i<numElementi;i++) {
			if (i==(numElementi-1)) 
				interazioni[i][0]=vita;
			else
				interazioni[i][i+1]=vita;
		}
		return interazioni;
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


