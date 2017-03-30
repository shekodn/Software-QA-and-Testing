import java.io.*;
import java.util.*;

//&p-Programa 4
public class Programa4 {
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double x = 0;
		int dof = 0;
		double eps = 0.0001;
		int num_seg = 10;
		double absSubstraction;


		/**
		 * Checks for a valid X
		 */
		//&i
		String sInputString = br.readLine();
		try {
			x = Double.parseDouble(sInputString);
		}
		catch(NumberFormatException error){
			System.out.println("Dato no valido");
			System.exit(0);

		}

		if (x < 0){
			System.out.println("Dato no valido");
			System.exit(0);

		}

		/**
		 * Checks for a valid DOF
		 */
		 //&i
		sInputString = br.readLine();
		try {
			dof = Integer.parseInt(sInputString);
		}
		catch(NumberFormatException error){
			System.out.println("Dato no valido");
			System.exit(0);
		}

		if (dof <= 0){
			System.out.println("Dato no valido");
			System.exit(0);
		}

		/**
		 * Calculates P
		 */

		//&i
		Simpson aux = new Simpson(x,dof, num_seg);
		num_seg*=2;
		Simpson resultado = new Simpson(x,dof, num_seg);

		absSubstraction = aux.calculaP() - resultado.calculaP();

		while (Math.abs(absSubstraction) >= eps){
			System.out.println(aux.calculaP() + " -- " + resultado.calculaP());
			aux = resultado;
			num_seg+=10;
			resultado = new Simpson(x,dof, num_seg);
			absSubstraction = aux.calculaP() - resultado.calculaP();
		}

		/**
		 * Prints results
		 */
		//&i
		System.out.println("X   = " + x);
		System.out.println("DOF = " + dof);
		System.out.printf("P = %.05f \n", resultado.calculaP());

	}
}
