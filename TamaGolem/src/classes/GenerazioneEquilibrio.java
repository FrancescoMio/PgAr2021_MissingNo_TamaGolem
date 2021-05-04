/**
 * 
 */
package classes;

import java.util.ArrayList;



/**
 * 
 *
 */
public class GenerazioneEquilibrio {

	public static int[][] gen(int numElementi, int numVita){
		
		int [][] interazioni = new int[numElementi][numElementi];
		
		/*Pietra pietra = new Pietra(interazioni);
		ArrayList<Pietra> pietre=new ArrayList<Pietra>();
		
		for (int i=0; i<numElementi; i++) {
			pietre.add(pietra);
		}*/
		
		ArrayList<Integer> danniRand=new ArrayList<Integer>(); //Assegnazione Danni Random
		for (int i=0; i<numElementi;i++) {
			int randomDanno=(int) Math.floor(Math.random()*(numVita)+1);
			danniRand.add(randomDanno);
		}
		//System.out.println(danniRand);
		
		for (int i=0; i<numElementi; i++) { //Creazione Matrice
			for (int j =0; j<numElementi; j++) {
				interazioni[i][j]=0;
			}
		}
		
		for (int i=0; i<numElementi; i++ ) {
				if (calcolaRiga(interazioni, i)<danniRand.get(i)) {
					//System.out.println("somma riga " + i+ " " +calcolaRiga(interazioni, i));
					ArrayList<Integer> posizionix = new ArrayList<Integer>();	
					for (int j=0; j<numElementi;j++) {
						if ((i!=j)&&interazioni[i][j]==0&&interazioni[j][i]==0) {
							posizionix.add(j);
							
						}
						
					}
					if (posizionix.size()==0) {
						for (int k=0; k<numElementi;k++) {
							if(calcolaColonna(interazioni, k)!=calcolaRiga(interazioni, k)) {
								GenerazioneEquilibrio.gen(numElementi,numVita);
								return null;
							}
						}
						return GenerazioneEquilibrio.stampaMatrice(interazioni, numElementi, numVita);
						
					}
					/*if (posizionix.size()==numElementi-2) {
						posizionix.remove(posizionix.size()-1);
					}*/
					//System.out.println("posx " + posizionix);
					for (int j=0; j<=(danniRand.get(i)-calcolaRiga(interazioni, i)); j++) {
						//System.out.println(danniRand.get(i)-calcolaRiga(interazioni, i));
						int posizioneRand=(int) Math.floor(Math.random()*(posizionix.size()));
						int broken=0;
						while (calcolaColonna(interazioni,posizionix.get(posizioneRand))>=danniRand.get(posizionix.get(posizioneRand))) {
							posizioneRand=(int) Math.floor(Math.random()*(posizionix.size()));
							broken++;
							if (broken==50) {
								GenerazioneEquilibrio.gen(numElementi,numVita);
								return null;
								
							}
						}
						
						interazioni[i][posizionix.get(posizioneRand)]++;
					}
					
				}
				/*System.out.println("");
				for (int b=0; b<numElementi; b++) {
					for (int j =0; j<numElementi; j++) {
						System.out.print(interazioni[b][j]+ " ");
						
					}
					System.out.println("");
				
				}*/
				
				
				if (calcolaColonna(interazioni, i)<danniRand.get(i)) {
					//System.out.println("somma colonna " +i+" " +calcolaColonna(interazioni, i));
					ArrayList<Integer> posizioniy = new ArrayList<Integer>();	
					for (int j=0; j<numElementi;j++) {
						if ((j!=i)&&interazioni[j][i]==0&&interazioni[i][j]==0) {
							posizioniy.add(j);
							
						}
						
					}
					if (posizioniy.size()==0) {
						for (int k=0; k<numElementi;k++) {
							if(calcolaColonna(interazioni, k)!=calcolaRiga(interazioni, k)) {
								GenerazioneEquilibrio.gen(numElementi, numVita);
								return null;
							}
						}
						return GenerazioneEquilibrio.stampaMatrice(interazioni, numElementi, numVita);
						
					}
					/*if (posizioniy.size()==numElementi-2) {
						posizioniy.remove(posizioniy.size()-1);
					}*/
					//System.out.println("posy "+posizioniy);
					for (int j=0; j<=(danniRand.get(i)-calcolaColonna(interazioni, i)); j++) {
						int posizioneRand=(int) Math.floor(Math.random()*(posizioniy.size()));
						int broken=0;
						while (calcolaRiga(interazioni,posizioniy.get(posizioneRand))>=danniRand.get(posizioniy.get(posizioneRand))) {
							posizioneRand=(int) Math.floor(Math.random()*(posizioniy.size()));
							broken++;
							if (broken==50) {
								GenerazioneEquilibrio.gen(numElementi,numVita);
								return null;
							}
						}
						
						interazioni[posizioniy.get(posizioneRand)][i]++;
					}
					
					
				}
				/*System.out.println("");
				for (int b=0; b<numElementi; b++) {
					for (int j =0; j<numElementi; j++) {
						System.out.print(interazioni[b][j]+ " ");
						
					}
					System.out.println("");	
				
				}*/
		}
		
		for (int i=0; i<numElementi;i++) {
			if(calcolaColonna(interazioni, i)!=calcolaRiga(interazioni, i)) {
				GenerazioneEquilibrio.gen(numElementi,numVita);
				return null;
			}
		}
				
		return GenerazioneEquilibrio.stampaMatrice(interazioni, numElementi, numVita);
		
					
		}
		
		public static int calcolaRiga(int riga[][] , int pos) {
			int somma=0;
			for (int i=0; i<riga.length; i++) {
				
				somma=somma+riga [pos][i];
			}
			return somma;
		}
		
		public static int calcolaColonna(int colonna[][] , int pos) {
			int somma=0;
			for (int i=0; i<colonna.length; i++) {
				
				somma=somma+colonna [i][pos];
			}
			return somma;
		}
		
		public static int[][] stampaMatrice(int interazioni[][],int numElementi,int numVita) {
			System.out.println("");
			System.out.println("Dati " +numElementi + " numeri di pietra e " +numVita+ " di vita");
			int sommax=0;
			int sommay=0;
			
			for (int i=0; i<numElementi; i++) {
				for (int j =0; j<numElementi; j++) {
					System.out.print(interazioni[i][j]+ " ");
					sommax=sommax +interazioni[i][j];
				}
				System.out.print(" = " +sommax);
				sommax=0;
				System.out.println("");
			}
			
			for (int i=0; i<numElementi; i++) {
				
				
				System.out.print("= ");
				
			}
			System.out.println("");
			
			for (int i=0; i<numElementi; i++) {
				
				for (int j =0; j<numElementi; j++) {
					sommay=sommay +interazioni[j][i];
				}
				
				System.out.print(sommay+ " ");
				sommay=0;
				
			}
			return interazioni;	
		}
	
}
