import java.io.*;
import java.util.*;


//&p-Programa5
public class Main {

	//&i
	public static void main(String [] args){
		//Declaracion
		//&i
		Double dx;
		Double dxAux;
		Double dDof;
		Double dCalculo1;
		Double dCalculo2;
		Double dNum_seg;
		Double dNum_seg2;
		Double dError;
		Double dW;
		Double dW2;
		Double dValue;
		boolean bOut;
		Double dD;
		Double dP;
		int iDireccionPasada;
		int iDireccionActual;
		Simpson simpson;
		Scanner scan;


		//inicializacion
		//&i
		dx = 1.0;
		dxAux = 1.0;
		dDof = 0.0;
		dCalculo1 = 0.0;
		dCalculo2 = 0.0;
		dNum_seg = 10.0;
		dNum_seg2 = 20.0;
		dError = 0.00000001;
		dW = 0.0;
		dW2 = 0.0;
		dValue = 0.0;
		bOut = false;
		dD = 0.5;
		dP = 0.0;
		iDireccionPasada = 0;
		iDireccionActual = 0;
		simpson = new Simpson();
		scan = new Scanner(System.in);

		/**
		 * Asks user for P
		 */
		while(true){ //&m
			System.out.println("Inserta el P");//&m
			try{
				dP = Double.parseDouble(scan.nextLine());//&m
				if(dP >= 0 && dP <= 0.5){//&m
					break;//&m
				}
				System.out.println("Error con el valor de P");//&m
			}catch(NumberFormatException e){
				System.out.println("Error con el valor de P");
			}
		}

		/**
		 * Asks user for DOF
		 */
		while(true){
			System.out.println("Inserta Dof");//&m
			try{
				dDof = Double.parseDouble(scan.nextLine());//&m
				if(dDof > 0){
					break;//&m
				}
				System.out.println("Error con el valor de DOF");//&m
			}catch(NumberFormatException e){
				System.out.println("Error con el valor de DOF");//&m
			}
		}

		do{
			 if(dCalculo1 != 0.0){
				 dNum_seg = dNum_seg * 2;
				 dNum_seg = Double.parseDouble(Impresion.Formatear("00.00000", dNum_seg));
				 dW = dx/dNum_seg;
				 dCalculo1 = simpson.Iniciar(dx, dDof, dNum_seg, dError, dW);
				 dNum_seg2 = dNum_seg * 2;
				 dNum_seg2 = Double.parseDouble(Impresion.Formatear("00.00000", dNum_seg2));
				 dW2  = dx/dNum_seg2;
				 dCalculo2 = simpson.Iniciar(dx, dDof, dNum_seg2, dError, dW2);

			 }
			 else{
				 dW = dx/dNum_seg;
				 dCalculo1 = simpson.Iniciar(dx, dDof, dNum_seg, dError, dW);
				 if(dCalculo1 != 0.0){
					 dW2  = dx/dNum_seg2;
					 dCalculo2 = simpson.Iniciar(dx, dDof, dNum_seg2, dError, dW2);
				 }
			 }
			 dValue = Math.abs(dCalculo1 - dCalculo2);
			 if(dValue < dError){
				 bOut = true;
			 }

		}while(bOut != true);

		if(dCalculo2 == dP){
			System.out.printf("P = %.05f \n", dP);
			System.out.printf("DOF = %.0f \n", dDof);
			System.out.printf("X = %.05f \n", dx);
		}else if(dCalculo2 < dP){
			dxAux = dx + dD;
			iDireccionActual = 1;
		}else if(dCalculo2 > dP){
			dxAux = dx - dD;
			iDireccionActual = -1;
		}
		dCalculo2 = 0.0;
		while(Math.abs(dCalculo2- dP) > dError){
			dx = dxAux;
			bOut = false;
			dW = 0.0;
			dW2 = 0.0;
			dCalculo1 = 0.0;
			dCalculo2 = 0.0;
			do{
				 if(dCalculo1 != 0.0){
					 dNum_seg = dNum_seg * 2;
					 dW = dx/dNum_seg;
					 dCalculo1 = simpson.Iniciar(dx, dDof, dNum_seg, dError, dW);
					 dNum_seg2 = dNum_seg * 2;
					 dW2  = dx/dNum_seg2;
					 dCalculo2 = simpson.Iniciar(dx, dDof, dNum_seg2, dError, dW2);

				 }
				 else{
					 dW = dx/dNum_seg;
					 dCalculo1 = simpson.Iniciar(dx, dDof, dNum_seg, dError, dW);
					 if(dCalculo1 != 0.0){
						 dW2  = dx/dNum_seg2;
						 dCalculo2 = simpson.Iniciar(dx, dDof, dNum_seg2, dError, dW2);
					 }
				 }
				 dValue = Math.abs(dCalculo1 - dCalculo2);
				 if(dValue < dError){
					 bOut = true;
				 }

			}while(bOut != true);
			if(dCalculo2 == dP){
				System.out.printf("P = %.05f \n", dP);
				System.out.printf("DOF = %.0f \n", dDof);
				System.out.printf("X = %.05f \n", dx);

			}else{
				if(dCalculo2 < dP){
					dxAux += dD;
					iDireccionActual = 1;
				}else if(dCalculo2 > dP){
					dxAux -= dD;
					iDireccionActual = -1;
				}
			}

			if(iDireccionPasada !=0 && iDireccionActual != iDireccionPasada){
				dD /= 2.0;
			}
			iDireccionPasada = iDireccionActual;
			dValue = Math.abs(dCalculo2 - dP);
		}
		System.out.printf("P = %.05f \n", dP);
		System.out.printf("DOF = %.0f \n", dDof);
		System.out.printf("X = %.05f \n", dx);

	}
}
