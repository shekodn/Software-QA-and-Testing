import java.io.*;
import java.util.*;
import java.text.*;


public class Impresion {

	//&i
	public static String Formatear(String sPatron, Double dValor){
		DecimalFormat format = new DecimalFormat(sPatron);
		String sOutput = format.format(dValor);
		return sOutput;
	}

	
	//&i
	public static void Imprimir(Double dX, Double dDof, Double dP){
		System.out.printf("P = %.05f \n", dP);
		System.out.printf("DOF = %.0f \n", dDof);
		System.out.printf("X = %.05f \n", dX);
	}

}
