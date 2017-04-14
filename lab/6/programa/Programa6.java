//&p-Programa3
//&b=6
import java.io.*;
import java.util.*;
import java.math.*;


public class Programa6 {

    private LinkedList <Coordenada> lklCoordenadas; //Lista de coordenadas
    private LinkedList <Archivo> lklFiles; //Lista de archivo
    private int iXK; //first number of file
    private Controlador controlador; //controlador principal
    private Scanner scFileName; //scanner with user input to name files

    /**
     * Initializes variables
     */
     //&i
     //&b=7
    public void init(){

        lklCoordenadas = new LinkedList();
        lklFiles = new LinkedList();
        iXK = 0;
        controlador = new Controlador();
        scFileName = new Scanner(System.in);

    }

    /**
     * The user put the name of the files he is goint to scan
     * @param int iNumberOfFiles
     */
     //&i
     //&b=18
    void fileName(int iNumberOfFiles){

        String sName;
        int iCounter = 1;
        Analyzer analyzer = new Analyzer();

        for(int iI = 0; iI < iNumberOfFiles; iI++){

            System.out.print("Name for file " + iCounter + " out of " +
            iNumberOfFiles + " ->");
            System.out.println();
            sName = scFileName.nextLine();

            Archivo temporalFile = new Archivo();

            if(analyzer.isAFile(sName)){
                temporalFile.setName(sName);
                lklFiles.add(temporalFile);
                System.out.println(temporalFile.getName() + " is a file!" + "\n");
                iCounter++;

            } else{
                System.out.println("PLEASE TRY AGAIN!" + "\n");
                iI-=1;
            }
        }
    }


    //&i
    public void addCoordinatesToList(){

        Analyzer fileAnalyzer = new Analyzer();
        iXK = fileAnalyzer.readByLine(lklFiles.get(0).getName(), lklCoordenadas);
    }

    //&i
    public void performCalculation(){

        Controlador auxiliar = new Controlador();

        double r = auxiliar.calculaR(lklCoordenadas);
        double r2 = auxiliar.calculaR2(r);
        double b0 = auxiliar.calculaB0(lklCoordenadas);
        double b1 = auxiliar.calculaB1(lklCoordenadas);
        double yk = auxiliar.calculaYK(lklCoordenadas, iXK);
        double x = auxiliar.calculaX(r, lklCoordenadas.size());
        double xiaux = auxiliar.sumatoriaXi(lklCoordenadas);

        double dX = programa5();
        System.out.println("dx "+ dX );
        double dStdDev = controlador.calculaStdDev(lklCoordenadas, b0, b1);
        double auxRangeSumatoria = controlador.sumatoriaXiMinusXavg(lklCoordenadas);
        double calculaRange = controlador.calculaRange(dX, dStdDev, iXK, xiaux/lklCoordenadas.size(), lklCoordenadas.size(), auxRangeSumatoria);

        controlador.dSig = programa4(x,lklCoordenadas.size());
        controlador.setiN(lklCoordenadas.size());
        controlador.setXK(iXK);
        controlador.setR(r);
        controlador.setR2(r2);
        controlador.setB0(b0);
        controlador.setB1(b1);
        controlador.setYK(yk);
        controlador.dRange = calculaRange;
        controlador.dUPI = calculaRange + yk;

        if(yk - calculaRange < 0){
            controlador.dLPI = 0.0;
        } else{
            controlador.dLPI = yk - calculaRange;
        }
    }

    /**
     * Performs the overall analysis of files
     */
     //&i
     //&b=9
     //&d=4
    public void analyze(){

        init();
        fileName(1);//&m
        addCoordinatesToList();
        performCalculation();

        controlador.printInfo();
    }

    public double programa4(double dX, int iN){//&m

        double x = dX; //&m
        int dof = iN - 2;//&m
        double eps = 0.0000000000001;
        int num_seg = 100;
        double absSubstraction;


        /**
         * Calculates P
         */

        //&i
        Simpson aux = new Simpson(x, dof, num_seg);
        num_seg*=2;
        Simpson resultado = new Simpson(x,dof, num_seg);

        absSubstraction = aux.calculaP() - resultado.calculaP();

        while (Math.abs(absSubstraction) >= eps){
            //System.out.println(aux.calculaP() + " -- " + resultado.calculaP());
            aux = resultado;
            num_seg*=2;
            resultado = new Simpson(x,dof, num_seg);
            absSubstraction = aux.calculaP() - resultado.calculaP();
        }

        return (1 - (2 * resultado.calculaP()));//&m
    }

    public double programa5(){

        //&i
        double dX = 1.0;
		double dAux = 1.0;
		double dDof = lklCoordenadas.size() - 2;
		double dAns1 = 0.0;
		double dAns2 = 0.0;
		double dNum_seg = 10.0;
		double dNum_seg2 = 20.0;
		double dEpsilon = 0.0000000000001;
		double dW = 0.0;
		double dW2 = 0.0;
		double dValue = 0.0;
		boolean isSolved = false;
		double dD = 0.5;
		double dP = 0.35;
		int iDireccionPasada = 0;
		int iDireccionActual = 0;
		Simpson5 simpson = new Simpson5();

		do{
			 if(dAns1 != 0.0){
				 dNum_seg = dNum_seg * 2;
				//  System.out.println("dNum_seg  - " + dNum_seg);//m //borrar
				 dW = dX/dNum_seg;
				//  System.out.println("dW - " + dW);//m //borrar
				 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
				//  System.out.println("dAns1 - " + dAns1);//m //borrar
				 dNum_seg2 = dNum_seg * 2;
				//  System.out.println("dNum_seg2 - " + dNum_seg2);//m //borrar
				 dW2  = dX/dNum_seg2;
				//  System.out.println("dW2 - " + dW2);//m //borrar
				 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
				//  System.out.println("dAns2 - " + dAns2);//m //borrar

			 }
			 else{
				 dW = dX/dNum_seg;
				//  System.out.println("dW - " + dW);//m //borrar
				 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
				//  System.out.println("dAns1 - " + dAns1);//m //borrar
				 if(dAns1 != 0.0){
					 dW2  = dX/dNum_seg2;
					//  System.out.println("dW2 - " + dW2);//m //borrar
					 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
					//  System.out.println("dAns2 - " + dAns2);//m //borrar
				 }
			 }
			 dValue = Math.abs(dAns1 - dAns2);
			//  System.out.println("dValue - " + dAns2);//m //borrar
			 if(dValue < dEpsilon){
				 isSolved = true;
				//  System.out.println("isSolved - true");//m //borrar

			 }

		} while(isSolved != true);

		if(dAns2 == dP){

			//prints

		}else if(dAns2 < dP){

			dAux = dX + dD;
			// System.out.println("dAux - " + dAux);//m //borrar
			iDireccionActual = 1;

		}else if(dAns2 > dP){
			dAux = dX - dD;
			// System.out.println("dAux - " + dAux);//m //borrar
			iDireccionActual = -1;
		}

		dAns2 = 0.0;

		while(Math.abs(dAns2- dP) > dEpsilon){
			dX = dAux;
			isSolved = false;
			dW = 0.0;
			dW2 = 0.0;
			dAns1 = 0.0;
			dAns2 = 0.0;

			do{
				 if(dAns1 != 0.0){
					 dNum_seg = dNum_seg * 2;
					 dW = dX/dNum_seg;
					//  System.out.println("dW - " + dW);//m //borrar
					 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
					//  System.out.println("dAns1 - " + dAns1);//m //borrar
					 dNum_seg2 = dNum_seg * 2;
					//  System.out.println("dNum_seg2 - " + dNum_seg2);//m //borrar
					 dW2  = dX/dNum_seg2;
					//  System.out.println("dW2 - " + dW2);//m //borrar
					 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
					//  System.out.println("dAns2 - " + dAns2);//m //borrar

				 }
				 else{
					 dW = dX/dNum_seg;
					//  System.out.println("dW - " + dW);//m //borrar
					 dAns1 = simpson.Iniciar(dX, dDof, dNum_seg, dEpsilon, dW);
					//  System.out.println("dAns1 - " + dAns1);//m //borrar
					 if(dAns1 != 0.0){
						 dW2  = dX/dNum_seg2;
						//  System.out.println("dW2 - " + dW2);//m //borrar
						 dAns2 = simpson.Iniciar(dX, dDof, dNum_seg2, dEpsilon, dW2);
						//  System.out.println("dAns2 - " + dAns2);//m //borrar
					 }
				 }

				 dValue = Math.abs(dAns1 - dAns2);
				 if(dValue < dEpsilon){
					 isSolved = true;
					//  System.out.println("isSolved - true");//m //borrar
				 }

			} while(!isSolved);

			if(dAns2 == dP){


			}else{
				if(dAns2 < dP){
					dAux += dD;
					iDireccionActual = 1;
				} else if(dAns2 > dP){
					dAux -= dD;
					iDireccionActual = -1;
				}
			}

			if(iDireccionPasada !=0 && iDireccionActual != iDireccionPasada){
				dD /= 2.0;
			}
			iDireccionPasada = iDireccionActual;
			dValue = Math.abs(dAns2 - dP);
		}


        return dX;
	}

    /**
     * [main description]
     * @param String[] args [description]
     */
    //&p-Main
    //&b=3
    //&i
    public static void main(String[] args) {
        Programa6 program = new Programa6();//&m
        program.analyze();
    }
}
