
import java.text.DecimalFormat;
/**
 * Clase : Impresion.java
 * 
 * Clase que ayuda a imprimir la salida
 * 
 * 
 * @author AlejandroSanchez
 *@date 25/10/2015
 *@version 1.0
 *
 */
//&p-Impresion
//&b=10
public class Impresion {
	/*
	 * Formatear
	 * Formatea los valores de double 
	 * 
	 * @param con un valor <code>String</code> es la representacion que le quieres dar
	 * @param con un valor <code>Double</code> es el valor que quieres convertir en el formato 
	 */
	//&i
	public static String Formatear(String sPatron, Double dValor){
		DecimalFormat format = new DecimalFormat(sPatron);
		String sOutput = format.format(dValor);
		return sOutput;
	}
	/*
	 * Imprimir
	 * Realiza la impresion de los archivos con el formato que se definio
	 * 
	 * @param con un valor <code>Double</code> que representa que es la cantidad de X
	 * @param con un valor <code>Double</code> que representa que es la cantidad de Dof que se tiene
	 * @param con un valor <code>Double</code> que representa la P calculadad 
	 *
	 */
	//&i
	public static void Imprimir(Double x, Double dof, Double p){
		System.out.println("P = " + Formatear("0.00000",p));//&m
		System.out.println("Dof = " + Formatear("##.#####",dof));
		System.out.println("X = " + Formatear("#0.00000",x));//&m
	}
	
}
