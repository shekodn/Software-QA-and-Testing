import java.io.*;
import java.util.*;


public class TDist {

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


	//&i
	public ArrayList<Double> calcula1(ArrayList<Double> x,Double dof){
		ArrayList<Double> calcula1 = new ArrayList<Double>();
		for(int iI=0; iI< x.size(); iI++){
			Double dVal = 1 + ((Math.pow(x.get(iI), 2)) / dof);
			calcula1.add(dVal);
		}
		return calcula1;
	}

	//&i
	public ArrayList<Double> calcula2(ArrayList<Double> x,Double dof){
		ArrayList<Double> calcula2 = new ArrayList<Double>();
		Double dValor = ((dof + 1) / 2.0);
		for(int iI=0; iI< x.size();iI++){
			calcula2.add(Math.pow(x.get(iI), - dValor));
		}
		return calcula2;
	}

	//&i
	public Double calcula3(Double dof){
		Double dArriba = 0.0;
		Double dAbajo2 = 0.0;
		Double ivalue = (dof + 1)/ 2.0;
		String sDof = String.valueOf(ivalue);
		try{
			int iValue = Integer.parseInt(sDof);
			dArriba = (double) Factorial(iValue-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dArriba = Calculo(dValue-1);
		}
		ivalue = dof/2.0;
		sDof = String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValue = Integer.parseInt(sDof);
			dAbajo2 = (double) Factorial(iValue-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dAbajo2 = Calculo(dValue-1);
		}

		Double dAbajo1 = Math.pow((dof * Math.PI), 0.5);
		Double dAbajo = (dAbajo1 * dAbajo2);
		return dArriba / dAbajo;
	}

	//&i
	public ArrayList<Double> calcula4(ArrayList<Double> calcula2, Double calcula3){
		ArrayList<Double> arrAns = new ArrayList<Double>();

		for(int iI= 0; iI < calcula2.size(); iI++){
			arrAns.add(calcula3 * calcula2.get(iI));
		}
		return arrAns;
	}
}
