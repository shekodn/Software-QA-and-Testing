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
	private ArrayList <Double> finalArr;
	private Double dAns;

	//&i
	public Double Iniciar(Double x, Double dof, Double num_seg, Double error, Double w){
 		segmentos = new ArrayList<Double>();
		segmentos = Segmentos(num_seg, w, x);
		patron = new ArrayList<Double>();
		patron = Patron(num_seg);
		tDist = new TDist();
		calcula1 = new ArrayList<Double>();
		calcula1 = tDist.calcula1(segmentos, dof);
		calcula2 = new ArrayList<Double>();
		calcula2 = tDist.calcula2(calcula1, dof);
		calcula3 = 0.0;
		calcula3 = tDist.calcula3(dof);
		calcula4 = new ArrayList<Double>();
		calcula4 = tDist.calcula4(calcula2, calcula3);
		finalArr = new ArrayList<Double>();
		finalArr = Final(w,patron,calcula4);
		dAns = Sumatoria(finalArr);
		return dAns;
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
	private ArrayList<Double> Final(Double w, ArrayList<Double> patron, ArrayList<Double> calcula4) {
		ArrayList<Double> finalArr = new ArrayList<Double>();
		for(int i= 0; i< calcula4.size();i++){
			finalArr.add((w/3.0) * patron.get(i) * calcula4.get(i));
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
