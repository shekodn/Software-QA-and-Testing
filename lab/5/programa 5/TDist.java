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
	public ArrayList<Double> TDistOne(ArrayList<Double> x,Double dof){
		ArrayList<Double> distOne = new ArrayList<Double>();
		for(int i=0; i< x.size();i++){
			Double dVal = 1 + ((Math.pow(x.get(i), 2)) / dof);
			distOne.add(dVal);
		}
		return distOne;
	}

	//&i
	public ArrayList<Double> TDistTwo(ArrayList<Double> x,Double dof){
		ArrayList<Double> distTwo = new ArrayList<Double>();
		Double dValor = ((dof + 1) / 2.0);
		for(int i=0; i< x.size();i++){
			distTwo.add(Math.pow(x.get(i), - dValor));
		}
		return distTwo;
	}

	//&i
	public Double TDistThree(Double dof){
		Double dValueUp = 0.0;
		Double dValueDownTwo = 0.0;
		Double ivalue = (dof + 1)/ 2.0;
		String sDof = String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValue = Integer.parseInt(sDof);
			dValueUp = (double) Factorial(iValue-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dValueUp = Calculo(dValue-1);
		}
		ivalue = dof/2.0;
		sDof = String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValue = Integer.parseInt(sDof);
			dValueDownTwo = (double) Factorial(iValue-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dValueDownTwo = Calculo(dValue-1);
		}

		Double dValueDownOne = Math.pow((dof * Math.PI), 0.5);
		Double dValueDown = (dValueDownOne * dValueDownTwo);
		return dValueUp / dValueDown;
	}

	//&i
	public ArrayList<Double> TDistFour(ArrayList<Double> TdistTwo, Double TdistThree){
		ArrayList<Double> finalArray = new ArrayList<Double>();
		for(int i= 0; i < TdistTwo.size(); i++){
			finalArray.add(TdistThree * TdistTwo.get(i));
		}
		return finalArray;
	}

}
