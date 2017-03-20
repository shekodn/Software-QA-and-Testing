import java.io.*;
import java.util.*;

//&p-Programa4
public class Programa4 {

	private BufferedReader br;
	private double x;
	private int iDof;
	private double eps;
	private int num_seg;
	private double resta;
	private Simpson aux;
	private Simpson resultado;

	//&i
	public void init(){

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //scanner
		double x = 0; //dX value from user input
		int iDof = 0; //Degrees of freedom
		double eps = 0.0001; //epsilon
		int num_seg = 10;
		double resta = 0.0;
		Simpson aux = new Simpson(x, iDof, num_seg);
		Simpson resultado = new Simpson(x,iDof, num_seg);

	}

	//&i
	public void verificaX() throws IOException{

		String sInputString = br.readLine();

		try {
			x = Double.parseDouble(sInputString);
		}
		catch(NumberFormatException error){
			System.out.println("Dato invalido");
		}

		if (x < 0){
			System.out.println("Dato invalido");
		}
	}

	//&i
	public void verificaDOF() throws IOException {

		String sInputString = br.readLine();

		sInputString = br.readLine();
		try {
			iDof = Integer.parseInt(sInputString);
		}
		catch(NumberFormatException nfe){
			System.out.println("Dato invalido");
		}

		if (iDof <= 0){
			System.out.println("Dato invalido");
		}
	}



	public void calculaRespuesta(){

		num_seg+=10;

		resta = aux.getP() - resultado.getP();

		while (Math.abs(resta) >= eps){
			System.out.println(aux.getP() + " -- " + resultado.getP());
			aux = resultado;
			num_seg+=10;
			resultado = new Simpson(x,iDof, num_seg);
			resta = aux.getP() - resultado.getP();

		}

	}

	/**
	* [rounds to the fifth decimal place]
	* @param  double dNum          [description]
	* @return        [description]
	*/
	//&i
	public double roundNumber(double dNum){

		double factor = 1e5; // = 1 * 10^5 = 100000.
		double result = Math.round(dNum * factor) / factor;

		return result;
	}

	//&i
	public void printInfo(){

		System.out.println("X   = " + x);
		System.out.println("DOF = " + iDof);
		System.out.println("P   = " + roundNumber(resultado.getP()));
	}


	//&i
	public static void main(String[] args) throws IOException{

		Programa4 p4 = new Programa4();
		p4.verificaX();
		p4.verificaDOF();
		p4.calculaRespuesta();
		p4.printInfo();
	}
}
