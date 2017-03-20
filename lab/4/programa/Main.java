import java.io.*;
import java.util.*;

//&p-Main
//&b=9
public class Main {
	//&i
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double x = 0;
		int dof = 0;
		double E = 0.0000001;

		//&d=3


		// X
		String sInputString = br.readLine();
		try {
			x = Double.parseDouble(sInputString);
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe de escribir un dato valido.");
			System.out.println("Terminando Programa...");
			System.exit(0);
		}

		if (x < 0){
			System.out.println("Debe de escribir un dato valido.");
			System.out.println("Terminando Programa...");
			System.exit(0);
		}



		// DOF
		sInputString = br.readLine();
		try {
			dof = Integer.parseInt(sInputString);
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe de escribir un dato valido.");
			System.out.println("Terminando Programa...");
			System.exit(0);
		}

		if (dof <= 0){
			System.out.println("Debe de escribir un dato valido.");
			System.out.println("Terminando Programa...");
			System.exit(0);
		}



		// MANDA CALCULAR P
		int num_seg = 10;
		double resta;

		Simpson aux = new Simpson(x,dof, num_seg);
		num_seg+=10;
		Simpson resultado = new Simpson(x,dof, num_seg);

		resta = aux.getP() - resultado.getP();

		while (Math.abs(resta) >= E){
			System.out.println(aux.getP() + " -- " + resultado.getP());
			aux = resultado;
			num_seg+=10;
			resultado = new Simpson(x,dof, num_seg);
			resta = aux.getP() - resultado.getP();

		}



		// DESPLIEGA RESULTADOS
		System.out.println("\nx = " + x);
		System.out.println("dof = " + dof);
		System.out.printf("p = %.05f \n", resultado.getP());
	}


}
