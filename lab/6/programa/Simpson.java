//&p-Simpson
//&b=88
import java.io.*;
import java.util.*;

public class Simpson {

	private double dX;
	private double iDof;
	private double num_seg;
	private double dW;
	//
	private ArrayList<Double> segmentos;
	private ArrayList<Double> patron;
	private DistT tDist;
	private ArrayList<Double> calcula1;
	private ArrayList<Double> calcula2;
	private Double calcula3;
	private ArrayList<Double> calcula4;
	private ArrayList <Double> arrAns;
	private Double dFinalAns;


	//&i
	public Simpson(double dX, double iDof, double iNumberOfSegments){
		this.dX = dX;
		this.iDof = iDof;
		this.num_seg = iNumberOfSegments;
		this.dW = dX / iNumberOfSegments;
	}

	//&i
	public double calculaP(){
		double dXi = 0;
		int iMultiplier;
		double dAnswer = 0;
		DistT distT;

		for (int iI = 0; iI <= num_seg; iI++){

			distT = new DistT(dXi, iDof);

			if (iI == 0 || iI == num_seg){
			 iMultiplier = 1;
			} else if (iI % 2 == 0){
			 iMultiplier = 2;
		 	} else {
			 iMultiplier = 4;
			}

			dAnswer = dAnswer + ((dW/3) * iMultiplier * distT.calculaF());

			dXi = dXi + dW;
		}

		return dAnswer;
	}


	//&i
	public Double Iniciar(Double x, Double dof, Double num_seg, Double error, Double w){
		segmentos = new ArrayList<Double>();
		segmentos = Segmentos(num_seg, w, x);
		patron = new ArrayList<Double>();
		patron = Patron(num_seg);
		tDist = new DistT(x,dof);
		calcula1 = new ArrayList<Double>();
		calcula1 = tDist.uno(segmentos, dof);
		calcula2 = new ArrayList<Double>();
		calcula2 = tDist.dos(calcula1, dof);
		calcula3 = 0.0;
		calcula3 = tDist.tres(dof);
		calcula4 = new ArrayList<Double>();
		calcula4 = tDist.cuatro(calcula2, calcula3);
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
