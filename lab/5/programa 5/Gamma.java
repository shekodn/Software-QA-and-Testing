import java.io.*;
import java.util.*;


public class Gamma {

	//&i
	public static double Factorial(int n){
		double ifact = 1;
		for (int i = 1; i <= n; i++) {
			ifact *= i;
		}
		return ifact;
	}

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

	//&i
	//&b=9
	public double calculaGama(double x){
		if (x == 0){
			return -1;
		}
		if (x == 1.0){
			return 1;
		}
		if (x == 0.5){

			return Math.sqrt(Math.PI);
		}
		else{
			return (x - 1) * calculaGama(x - 1);
		}
	}
}
