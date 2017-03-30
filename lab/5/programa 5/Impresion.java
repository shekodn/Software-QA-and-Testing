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
	public static void Imprimir(Double x, Double dof, Double p){
		System.out.println("P = " + Formatear("0.00000",p));//&m
		System.out.println("Dof = " + Formatear("##.#####",dof));
		System.out.println("X = " + Formatear("#0.00000",x));//&m
	}

}
