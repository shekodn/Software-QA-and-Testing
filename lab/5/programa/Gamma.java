import java.io.*;
import java.util.*;


public class Gamma {
	/*
	 * Factorial
	 * Toma un valor Double( x) y le aplica la funcion gamma por medio del
	 * Factorial
	 *
	 * @param con un valor <code>Double</code> es el valor double que le quieres aplicar la funcion gamma
	 */
	//&i
	public static double Factorial(int n){
		 double ifact = 1;
	        for (int i = 1; i <= n; i++) {
	            ifact *= i;
	        }
	        return ifact;
	}
	/*
	 * Calculo
	 * Toma un valor Double( x) y le aplica la funcion gamma por medio del
	 * Un calculo multiplacado por la raiz de PI
	 *
	 * @param con un valor <code>Double</code> es el valor double que le quieres aplicar la funcion gamma
	 */
	//&i
	public static Double Calculo(Double value){
		ArrayList<Double> valores = new ArrayList<Double>();
		Double dTotal = 1.0;
		do{
			valores.add(value);
			value-= 1;
		}while(value > 0);
		for(int i= 0; i < valores.size(); i++){
			dTotal *= valores.get(i);
		}
		return dTotal * Math.sqrt(Math.PI);
	}



}
