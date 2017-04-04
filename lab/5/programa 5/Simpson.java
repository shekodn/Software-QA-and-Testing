//&p-Simpson
//&b=63

import java.io.*;
import java.util.*;


public class Simpson {

	//&i
	private ArrayList<Double> segmentos;
	private ArrayList<Double> patron;
	private TDist tDist;
	private ArrayList<Double> calcula1;
	private ArrayList<Double> calcula2;
	private Double calcula3;
	private ArrayList<Double> calcula4;
	private ArrayList <Double> arrAns;
	private Double dFinalAns;

	//&i
	public Double Iniciar(Double x, Double dof, Double num_seg, Double error, Double w){
 		segmentos = new ArrayList<Double>();
		segmentos = Segmentos(num_seg, w, x);
		patron = new ArrayList<Double>();
		patron = Patron(num_seg);
		tDist = new TDist();
		calcula1 = new ArrayList<Double>();
		calcula1 = tDist.TDistOne(segmentos, dof);
		calcula2 = new ArrayList<Double>();
		calcula2 = tDist.TDistTwo(calcula1, dof);
		calcula3 = 0.0;
		calcula3 = tDist.TDistThree(dof);
		calcula4 = new ArrayList<Double>();
		calcula4 = tDist.TDistFour(calcula2, calcula3);
		arrAns = new ArrayList<Double>();
		arrAns = Final(w,patron,calcula4);
		dFinalAns = Sumatoria(arrAns);
		return dFinalAns;
	}



	//&i
	private Double Sumatoria(ArrayList<Double> arrAns){
		Double dtotal = 0.0;
		for(int i = 0; i < arrAns.size(); i++){
			dtotal += arrAns.get(i);
		}
		return dtotal;
	}


	//&i
	private ArrayList<Double> Final(Double w, ArrayList<Double> patron, ArrayList<Double> calcula4) {
		ArrayList<Double> arrAns = new ArrayList<Double>();
		for(int i= 0; i< calcula4.size();i++){
			arrAns.add((w/3.0) * patron.get(i) * calcula4.get(i));
		}
		return arrAns;
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
