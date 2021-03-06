import java.io.*;
import java.util.*;


public class Calculos {

	//&i
	public Double Iniciar(Double x, Double dof, Double num_seg, Double error, Double w){
		ArrayList<Double> segmentos = new ArrayList<Double>();
		segmentos = Segmentos(num_seg, w, x);
		ArrayList<Double> patron = new ArrayList<Double>();
		patron = Patron(num_seg);
		TDist tDist = new TDist();
		ArrayList<Double> tdistOne = new ArrayList<Double>();
		tdistOne = tDist.TDistOne(segmentos, dof);
		ArrayList<Double> tdistTwo = new ArrayList<Double>();
		tdistTwo = tDist.TDistTwo(tdistOne, dof);
		Double dtdistThree = 0.0;
		dtdistThree = tDist.TDistThree(dof);
		ArrayList<Double> tdistFour = new ArrayList<Double>();
		tdistFour = tDist.TDistFour(tdistTwo, dtdistThree);
		ArrayList <Double> finalArr = new ArrayList<Double>();
		finalArr = Final(w,patron,tdistFour);
		Double dRetorno = Sumatoria(finalArr);
		return dRetorno;
	}



	//&i
	private Double Sumatoria(ArrayList<Double> finalArr){
		Double dtotal = 0.0;
		for(int i = 0; i < finalArr.size(); i++){
			dtotal += finalArr.get(i);
		}
		return dtotal;
	}


	//&i
	private ArrayList<Double> Final(Double w, ArrayList<Double> patron, ArrayList<Double> tdistFour) {
		ArrayList<Double> finalArr = new ArrayList<Double>();
		for(int i= 0; i< tdistFour.size();i++){
			finalArr.add((w/3.0) * patron.get(i) * tdistFour.get(i));
		}
		return finalArr;
	}

	//&i
	private ArrayList<Double> Segmentos(Double num_seg, Double w, Double x){
		ArrayList<Double> segementada = new ArrayList<Double>();
		segementada.add((double) 0);

		Double dsumaTotal = 0.0;
		dsumaTotal += w;
		for(int i = 0; i < num_seg -1 ; i++){
			segementada.add(dsumaTotal);
			dsumaTotal += w;
		}
		segementada.add(x);
	    return segementada;
	}


	//&i
	private ArrayList<Double> Patron(Double num_seg){
		ArrayList<Double> patron = new ArrayList<Double>();
		patron.add((double) 1);
		boolean bValue = true;
		for(int i = 0; i < num_seg - 1 ; i++){
			if(bValue){
				patron.add((double) 4);
				bValue = false;
			}
			else{
				patron.add((double) 2);
				bValue = true;
			}
		}
		patron.add((double) 1);
	    return patron;
	}

}
