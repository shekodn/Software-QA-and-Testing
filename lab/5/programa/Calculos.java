import java.io.*;
import java.util.*;


public class Calculos {
	/*
	 * Iniciar
	 * Hace el inicio de todos los calculos que se deben de llevar a cabo
	 *  @param X<code>Double</code> es el valor del X
	 *  @param Dof<code>Double</code> toma el Dof
	 *  @param num_seg<code>Double</code> tiene el valor de numero de segementos que quieres manejar
	 *  @param error <code>Double</code> tiene el valor del error de diferencia
	 *  @param w <code>Double</code> es la distancia entre cada segmento
	 *
	 *  @return retorno <code>Double</code> es el valor que se realiza del calculo total
	 */
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

	/*
	 * Sumatoria
	 * Hace la sumatoria del ultimo paso
	 *  @param finalArr <code>ArrayList</code> son los valores calculados al final
	 *  @return total <code>Double</code> es la sumatoria
	 */

	//&i
	private Double Sumatoria(ArrayList<Double> finalArr){
		Double dtotal = 0.0;
		for(int i = 0; i < finalArr.size(); i++){
			dtotal += finalArr.get(i);
		}
		return dtotal;
	}
	/*
	 * Final
	 * Hace la multiplicacion de los valores calculados
	 *  @param patron <code>ArrayList</code> es el patron que se usa 1..2..4..1
	 *  @param tDistFour <code>ArrayList</code> es el calculo realizado por la 4ta parte de la clase Tdist
	 *  @return finalArr <code>ArrayList</code> que tiene los valores que se sumaran
	 */

	//&i
	private ArrayList<Double> Final(Double w, ArrayList<Double> patron, ArrayList<Double> tdistFour) {
		ArrayList<Double> finalArr = new ArrayList<Double>();
		for(int i= 0; i< tdistFour.size();i++){
			finalArr.add((w/3.0) * patron.get(i) * tdistFour.get(i));
		}
		return finalArr;
	}
	/*
	 * Segementos
	 * Hace la segementacion de los valores
	 *  @param num_seg <code>Double</code> son los valores calculados al final
	 *  @param w <code>Double</code> es lo dispersos que estan los valores de x
	 *  @param x <code>Dobule</code> valor final
	 *  @return segementada <code>ArrayList</code> el arrayList segementado
	 */
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
	/*
	 * Patron
	 * Hace el patron que se usara al final
	 *  @param num_seg <code>Double</code> son los numeros de segmentos
	 *  @return patron <code>ArrayList</code> es la sumatoria
	 */

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
