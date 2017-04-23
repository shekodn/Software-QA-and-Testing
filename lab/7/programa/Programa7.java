import java.util.*;
import java.io.*;

public class Programa7 {

    private Scanner scFileName; //scanner with user input to name files
    private LinkedList <Archivo> lklFiles; //Lista de archivo
    private LinkedList <Double> lklDoubles; //Lista de archivo
    private double N;
    private double wk;
    private double xk;
    private double yk;
    private double b0;
    private double b1;
    private double b2;
    private double b3;
    private double zk;

    /**
     * Initializes variables
     */
    public void init(){
        lklFiles = new LinkedList<Archivo>();
        scFileName = new Scanner(System.in);
        lklDoubles = new LinkedList<Double>();

        N  = 0;
        wk = 0;
        xk = 0;
        yk = 0;
        b0 = 0;
        b1 = 0;
        b2 = 0;
        b3 = 0;
        zk = 0;
    }


    /**
     * The user put the name of the files he is going to scan
     * @param int iNumberOfFiles
     */
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
                iCounter++;

            } else{
                System.out.println("PLEASE TRY AGAIN!" + "\n");
                iI-=1;
            }
        }
    }


    public void scan(){
        String hola="";
        for(int iI = 0; iI < lklFiles.size(); iI++){
            Analyzer analyzer = new Analyzer();
            analyzer.readByLine2(lklFiles.get(iI).getName(), lklDoubles);
            System.out.println(lklDoubles);
        }

        Double auxN = lklDoubles.size() - 3.0;
        N = auxN / 4;
        wk = lklDoubles.get(0);
        xk = lklDoubles.get(1);
        yk = lklDoubles.get(2);
    }



    public void analyze(){
        //methods
        init();
        fileName(1); //1 file
        scan();
        print();
    }

    public void print(){

        System.out.printf(" N = %.0f \n", N);
        System.out.printf("wk = %.05f \n", wk);
        System.out.printf("xk = %.05f \n", xk);
        System.out.printf("yk = %.05f \n", yk);
        System.out.print("------------\n");
        System.out.printf("P = %.05f \n", b0);
        System.out.printf("P = %.05f \n", b1);
        System.out.printf("P = %.05f \n", b2);
        System.out.printf("P = %.05f \n", b3);
        System.out.print("------------\n");
        System.out.printf("P = %.05f \n", zk);


    }

    public static void main(String[] args) {
        Programa7 p7 = new Programa7();
        p7.analyze();


    }
}
