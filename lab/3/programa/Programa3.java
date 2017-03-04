//&p-Programa3
//&b=8
import java.io.*;
import java.util.*;

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
    public void analyze(){
        //methods

        init();
        fileName(1);
        addCoordinatesToList();
        performCalculation();
        controlador.printInfo();
    }



    /**
     * [main description]
     * @param String[] args [description]
     */
    //&p-Main
    //&b=3
    public static void main(String[] args) {
        Programa3 program = new Programa3();
        program.analyze();
    }
}
