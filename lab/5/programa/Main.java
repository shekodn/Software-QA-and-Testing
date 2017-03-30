

import java.util.Scanner;

/**
 * Clase : Main.java
 * 
 * Clase que ayuda a compilar el proyecto. Dentro de esta clase se junta todo 
 * Se debe de fusionar todos los elementos que se han creado para que se compile bien 
 * y se tenga de una forma cero errores
 * 
 * @author AlejandroSanchez
 *@date 27/9/2015
 *@version 1.0
 *
 */
//&p-Main
//&b=55
public class Main {
	/*
	 * main
	 * Realiza la ejecucion del programa dentro de este se realizan las llamadas
	 * para que se ejecuten los metodos de las otras clases 
	 * 
	 * @param args es un conjunto de argumentos tipo <code>String</code> los cuales se pueden
	 * meter por medio de la terminal  
	 */
	//&i
	public static void main(String [ ] args){	
		Double dx = 1.0;
		Double dxAux = 1.0;
		Double dDof = 0.0;
		Double dCalculo1 = 0.0;
		Double dCalculo2 = 0.0;
		Double dNum_seg = 10.0;
		Double dNum_seg2 = 20.0;
		Double dError = 0.00000001;
		Double dW = 0.0;
		Double dW2 = 0.0;
		Double dValue = 0.0;
		boolean bOut = false;
		Double dD = 0.5;
		Double dP = 0.0;
		int iDireccionPasada = 0;
		int iDireccionActual = 0;
		
		Calculos calculos = new Calculos();
		
		Scanner scan = new Scanner(System.in);
		
		while(true){
			System.out.println("Inserta el P");//&m
			try{
				dP = Double.parseDouble(scan.nextLine());//&m
				if(dP >= 0 && dP <= 0.5){
					break;
				}
				ErrorHandle.errorEnLinea();
			}catch(NumberFormatException e){
				ErrorHandle.errorEnLinea();
			}
		}
		
		while(true){
			System.out.println("Inserta Dof");
			try{
				dDof = Double.parseDouble(scan.nextLine());
				if(dDof > 0){
					break;
				}
				ErrorHandle.errorEnLinea();
			}catch(NumberFormatException e){
				ErrorHandle.errorEnLinea();
			}
		}
		
		do{
			 if(dCalculo1 != 0.0){
				 dNum_seg = dNum_seg * 2;
				 dNum_seg = Double.parseDouble(Impresion.Formatear("00.00000", dNum_seg));
				 dW = dx/dNum_seg;
				 dCalculo1 = calculos.Iniciar(dx, dDof, dNum_seg, dError, dW);
				 dNum_seg2 = dNum_seg * 2;
				 dNum_seg2 = Double.parseDouble(Impresion.Formatear("00.00000", dNum_seg2));
				 dW2  = dx/dNum_seg2;
				 dCalculo2 = calculos.Iniciar(dx, dDof, dNum_seg2, dError, dW2);
				 
			 }
			 else{
				 dW = dx/dNum_seg;
				 dCalculo1 = calculos.Iniciar(dx, dDof, dNum_seg, dError, dW);
				 if(dCalculo1 != 0.0){
					 dW2  = dx/dNum_seg2;
					 dCalculo2 = calculos.Iniciar(dx, dDof, dNum_seg2, dError, dW2);
				 }
			 }
			 dValue = Math.abs(dCalculo1 - dCalculo2);
			 if(dValue < dError){
				 bOut = true;
			 }
			 
		}while(bOut != true);
		
		if(dCalculo2 == dP){
			Impresion.Imprimir(dx, dDof, dP);
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
					 dCalculo1 = calculos.Iniciar(dx, dDof, dNum_seg, dError, dW);
					 dNum_seg2 = dNum_seg * 2;
					 dW2  = dx/dNum_seg2;
					 dCalculo2 = calculos.Iniciar(dx, dDof, dNum_seg2, dError, dW2);
					 
				 }
				 else{
					 dW = dx/dNum_seg;
					 dCalculo1 = calculos.Iniciar(dx, dDof, dNum_seg, dError, dW);
					 if(dCalculo1 != 0.0){
						 dW2  = dx/dNum_seg2;
						 dCalculo2 = calculos.Iniciar(dx, dDof, dNum_seg2, dError, dW2);
					 }
				 }
				 dValue = Math.abs(dCalculo1 - dCalculo2);
				 if(dValue < dError){
					 bOut = true;
				 }
				 
			}while(bOut != true);
			if(dCalculo2 == dP){
				Impresion.Imprimir(dx, dDof, dP);
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
		Impresion.Imprimir(dx, dDof, dP);
		scan.close();
	}
}