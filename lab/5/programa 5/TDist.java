//&p-TDist
//&b=66

import java.io.*;
import java.util.*;


public class TDist {


	//&i
	public static double Factorial(int n){
		double factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
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


	//&i
	public ArrayList<Double> uno(ArrayList<Double> x,Double dof){
		ArrayList<Double> distOne = new ArrayList<Double>();
		for(int iI=0; iI< x.size(); iI++){
			Double dVal = 1 + ((Math.pow(x.get(iI), 2)) / dof);
			distOne.add(dVal);
		}
		return distOne;
	}

	//&i
	public ArrayList<Double> dos(ArrayList<Double> x,Double dof){
		ArrayList<Double> distTwo = new ArrayList<Double>();
		Double dValor = ((dof + 1) / 2.0);
		for(int iI=0; iI< x.size();iI++){
			distTwo.add(Math.pow(x.get(iI),
						- dValor));
		}
		return distTwo;
	}

	//&i
	public Double tres(Double dof){
		Double dArriba = 0.0;
		Double dAbajo2 = 0.0;
		Double ivalue = (dof + 1)/ 2.0;
		String sDof =
				String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValor = Integer.parseInt(sDof);
			dArriba = (double) Factorial(iValor-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dArriba = Calculo(dValue-1);
		}
		ivalue = dof/2.0;
		sDof = String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValor = Integer.parseInt(sDof);
			dAbajo2 = (double) Factorial(iValor-1);
		}catch(NumberFormatException e){
			double dValue =
				Double.parseDouble(sDof);
			dAbajo2 =
				Calculo(dValue-1);
		}

		Double dAbajo1 = Math.pow((dof * Math.PI), 0.5);
		Double dAbajo = (dAbajo1 * dAbajo2);
		return dArriba / dAbajo;
	}

	//&i
	public ArrayList<Double> cuatro(ArrayList<Double> arrDos, Double dTres){
		ArrayList<Double> finalArray = new ArrayList<Double>();

		for(int iI= 0; iI < arrDos.size(); iI++){
			finalArray.add(dTres * arrDos.get(iI));
		}
		return finalArray;
	}
}
