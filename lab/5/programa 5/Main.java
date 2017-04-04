//&p-Programa5
import java.io.*;
import java.util.*;

public class Main {

	//&i
	public static void main(String [] args){
		//Declaracion
		//inicializacion
		double dX = 1.0;
		double dAux = 1.0;
		double dDof = 0.0;
		double dAns1 = 0.0;
		double dAns2 = 0.0;
		double dNum_seg = 10.0;
		double dNum_seg2 = 20.0;
		double dEpsilon = 0.00000001;
		double dW = 0.0;
		double dW2 = 0.0;
		double dValue = 0.0;
		boolean isSolved = false;
		double dD = 0.5;
		double dP = 0.0;
		int iDireccionPasada = 0;
		int iDireccionActual = 0;
		Simpson simpson = new Simpson();
		Scanner userInput = new Scanner(System.in);

		/**
		 * Asks user for P
		 */
		while(true){ //m
			System.out.println("Inserta el P");//m
			try{
				dP = Double.parseDouble(userInput.nextLine());//m
				if(dP >= 0 && dP <= 0.5){//m
					break;//m
				}
				System.out.println("Error con el valor de P");//m
			}catch(NumberFormatException e){
				System.out.println("Error con el valor de P");
			}
		}

		/**
		 * Asks user for DOF
		 */
		while(true){
			System.out.println("Inserta Dof");//m
			try{
				dDof = Double.parseDouble(userInput.nextLine());//m
				if(dDof > 0){
					break;//m
				}
				System.out.println("Error con el valor de DOF");//m
			}catch(NumberFormatException e){
				System.out.println("Error con el valor de DOF");//m
			}
		}

		do{
			 if(dAns1 != 0.0){
				 dNum_seg = dNum_seg * 2;
				//  System.out.println("dNum_seg  - " + dNum_seg);//m //borrar
				 dW = dX/dNum_seg;
				//  System.out.println("dW - " + dW);//m //borrar
				 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
				//  System.out.println("dAns1 - " + dAns1);//m //borrar
				 dNum_seg2 = dNum_seg * 2;
				//  System.out.println("dNum_seg2 - " + dNum_seg2);//m //borrar
				 dW2  = dX/dNum_seg2;
				//  System.out.println("dW2 - " + dW2);//m //borrar
				 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
				//  System.out.println("dAns2 - " + dAns2);//m //borrar

			 }
			 else{
				 dW = dX/dNum_seg;
				//  System.out.println("dW - " + dW);//m //borrar
				 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
				//  System.out.println("dAns1 - " + dAns1);//m //borrar
				 if(dAns1 != 0.0){
					 dW2  = dX/dNum_seg2;
					//  System.out.println("dW2 - " + dW2);//m //borrar
					 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
					//  System.out.println("dAns2 - " + dAns2);//m //borrar
				 }
			 }
			 dValue = Math.abs(dAns1 - dAns2);
			//  System.out.println("dValue - " + dAns2);//m //borrar
			 if(dValue < dEpsilon){
				 isSolved = true;
				//  System.out.println("isSolved - true");//m //borrar

			 }

		}while(isSolved != true);

		if(dAns2 == dP){

			//prints
			System.out.printf("P = %.05f \n", dP);
			System.out.printf("DOF = %.0f \n", dDof);
			System.out.printf("X = %.05f \n", dX);

		}else if(dAns2 < dP){

			dAux = dX + dD;
			// System.out.println("dAux - " + dAux);//m //borrar
			iDireccionActual = 1;

		}else if(dAns2 > dP){
			dAux = dX - dD;
			// System.out.println("dAux - " + dAux);//m //borrar
			iDireccionActual = -1;
		}

		dAns2 = 0.0;

		while(Math.abs(dAns2- dP) > dEpsilon){
			dX = dAux;
			isSolved = false;
			dW = 0.0;
			dW2 = 0.0;
			dAns1 = 0.0;
			dAns2 = 0.0;

			do{
				 if(dAns1 != 0.0){
					 dNum_seg = dNum_seg * 2;
					 dW = dX/dNum_seg;
					//  System.out.println("dW - " + dW);//m //borrar
					 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
					//  System.out.println("dAns1 - " + dAns1);//m //borrar
					 dNum_seg2 = dNum_seg * 2;
					//  System.out.println("dNum_seg2 - " + dNum_seg2);//m //borrar
					 dW2  = dX/dNum_seg2;
					//  System.out.println("dW2 - " + dW2);//m //borrar
					 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
					//  System.out.println("dAns2 - " + dAns2);//m //borrar

				 }
				 else{
					 dW = dX/dNum_seg;
					//  System.out.println("dW - " + dW);//m //borrar
					 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
					//  System.out.println("dAns1 - " + dAns1);//m //borrar
					 if(dAns1 != 0.0){
						 dW2  = dX/dNum_seg2;
						//  System.out.println("dW2 - " + dW2);//m //borrar
						 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
						//  System.out.println("dAns2 - " + dAns2);//m //borrar
					 }
				 }

				 dValue = Math.abs(dAns1 - dAns2);
				 if(dValue < dEpsilon){
					 isSolved = true;
					//  System.out.println("isSolved - true");//m //borrar
				 }

			} while(!isSolved);

			if(dAns2 == dP){

				//prints
				System.out.printf("P = %.05f \n", dP);
				System.out.printf("DOF = %.0f \n", dDof);
				System.out.printf("X = %.05f \n", dX);

			}else{
				if(dAns2 < dP){
					dAux += dD;
					iDireccionActual = 1;
				} else if(dAns2 > dP){
					dAux -= dD;
					iDireccionActual = -1;
				}
			}

			if(iDireccionPasada !=0 && iDireccionActual != iDireccionPasada){
				dD /= 2.0;
			}
			iDireccionPasada = iDireccionActual;
			dValue = Math.abs(dAns2 - dP);
		}

		//prints
		System.out.printf("P = %.05f \n", dP);
		System.out.printf("DOF = %.0f \n", dDof);
		System.out.printf("X = %.05f \n", dX);

	}
}
