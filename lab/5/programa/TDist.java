
import java.util.ArrayList;
/**
 * Clase : Tdist.java
 * 
 * Clase que ayuda a hacer los calculos que se llevan a cabo con la 
 * distribucion T
 * 
 * @author AlejandroSanchez
 *@date 25/10/2015
 *@version 1.0
 *
 */
//&p-TDist
//&b=40
public class TDist {
	/*
	 * TDistOne
	 * Hace el calculo de la primer parte de TDist
	 *  @param <code>ArrayList</code> toma los valores que se hicieron   
	 *  @param <code>Double</code> toma el Dof 
	 */
	//&i
	public ArrayList<Double> TDistOne(ArrayList<Double> x,Double dof){
		ArrayList<Double> distOne = new ArrayList<Double>();
		for(int i=0; i< x.size();i++){
			Double dVal = 1 + ((Math.pow(x.get(i), 2)) / dof);
			distOne.add(dVal);
		}
		return distOne;
	}
	/*
	 * TDistTwo
	 * hace el calculo de la segunda parte del TDist
	 *  @param <code>ArrayList</code> toma los valores que se hicieron del paso anterior   
	 *  @param <code>Double</code> toma el Dof 
	 */
	//&i
	public ArrayList<Double> TDistTwo(ArrayList<Double> x,Double dof){
		ArrayList<Double> distTwo = new ArrayList<Double>();
		Double dValor = ((dof + 1) / 2.0);
		for(int i=0; i< x.size();i++){
			distTwo.add(Math.pow(x.get(i), - dValor));
		}
		return distTwo;
	}
	/*
	 * TDistThree
	 *
	 *  Hace el calculo de la tercera parte de Tdist
	 *  @param <code>Double</code> toma el Dof 
	 *  
	 *  @return regresa el valor calculado
	 */
	//&i
	public Double TDistThree(Double dof){
		Double dValueUp = 0.0;
		Double dValueDownTwo = 0.0;
		Double ivalue = (dof + 1)/ 2.0;
		String sDof = String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValue = Integer.parseInt(sDof);
			dValueUp = (double) Gamma.Factorial(iValue-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dValueUp = Gamma.Calculo(dValue-1);
		}
		ivalue = dof/2.0;
		sDof = String.valueOf(Impresion.Formatear("##.#####", ivalue));
		try{
			int iValue = Integer.parseInt(sDof);
			dValueDownTwo = (double) Gamma.Factorial(iValue-1);
		}catch(NumberFormatException e){
			double dValue = Double.parseDouble(sDof);
			dValueDownTwo = Gamma.Calculo(dValue-1);
		}
		
		Double dValueDownOne = Math.pow((dof * Math.PI), 0.5);
		Double dValueDown = (dValueDownOne * dValueDownTwo);
		return dValueUp / dValueDown;
	}
	/*
	 * TDistFour
	 * 
	 *  @param <code>ArrayList</code> toma los valores que se hicieron en la segunda parte  
	 *  @param <code>Double</code> El valor que se calculo en la tercera parte 
	 */
	//&i
	public ArrayList<Double> TDistFour(ArrayList<Double> TdistTwo, Double TdistThree){
		ArrayList<Double> finalArray = new ArrayList<Double>();
		for(int i= 0; i < TdistTwo.size(); i++){
			finalArray.add(TdistThree * TdistTwo.get(i)); 
		}
		return finalArray;
	}
	
}
