//&p-Programa3
//&b=6
import java.io.*;
import java.util.*;
import java.math.*;


public class Programa3 {

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

        programa4(x,lklCoordenadas.size());

        controlador.setiN(lklCoordenadas.size());
        controlador.setXK(iXK);
        controlador.setR(r);
        controlador.setR2(r2);
        controlador.setB0(b0);
        controlador.setB1(b1);
        controlador.setB1(b1);
        controlador.setYK(yk);

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

    public void programa4(double dX, int iN){//&m

        double x = dX; //&m
        int dof = iN - 2;//&m
        double eps = 0.0001;
        int num_seg = 10;
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
            System.out.println(aux.calculaP() + " -- " + resultado.calculaP());
            aux = resultado;
            num_seg+=10;
            resultado = new Simpson(x,dof, num_seg);
            absSubstraction = aux.calculaP() - resultado.calculaP();
        }

        controlador.dSig = 1 - (2 * resultado.calculaP());//&m
    }


    /**
     * [main description]
     * @param String[] args [description]
     */
    //&p-Main
    //&b=3
    //&i
    public static void main(String[] args) {
        Programa3 program = new Programa3();//&m
        program.analyze();
    }
}
