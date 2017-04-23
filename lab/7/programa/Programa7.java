import java.util.*;
import java.io.*;

public class Programa7 {

    private Scanner scFileName; //scanner with user input to name files
    private LinkedList <Archivo> lklFiles; //Lista de archivo
    private LinkedList <Double> lklDoubles;

    private LinkedList <Double> lklW;
    private LinkedList <Double> lklX;
    private LinkedList <Double> lklY;
    private LinkedList <Double> lklZ;

    private double sumatoriaX;
    private double sumatoriaY;
    private double sumatoriaZ;
    private double sumatoriaW;

    private double X2;
    private double Y2;
    private double W2;

    private double WX;
    private double WY;
    private double XY;

    private double ZX;
    private double ZY;
    private double ZW;


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

        lklW = new LinkedList<Double>();
        lklX = new LinkedList<Double>();
        lklY = new LinkedList<Double>();
        lklZ = new LinkedList<Double>();

        N  = 0;
        wk = 0;
        xk = 0;
        yk = 0;
        b0 = 0;
        b1 = 0;
        b2 = 0;
        b3 = 0;
        zk = 0;


        sumatoriaX = 0;
        sumatoriaY = 0;
        sumatoriaZ = 0;
        sumatoriaW = 0;

        X2 = 0;
        Y2 = 0;
        W2 = 0;

        WX = 0;
        WY = 0;
        XY = 0;

        ZX = 0;
        ZY = 0;
        ZW = 0;


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
            //System.out.println(lklDoubles);
        }

        Double auxN = lklDoubles.size() - 3.0;
        N = auxN / 4;
        wk = lklDoubles.get(0);
        xk = lklDoubles.get(1);
        yk = lklDoubles.get(2);


    }

    public void buildLists(LinkedList<Double> list){

        for(int iI = 3; iI < list.size(); iI++){

            if(iI % 4 == 0){
                lklX.add(list.get(iI));
            }

            if(iI % 4 == 1){
                lklY.add(list.get(iI));
            }

            if(iI % 4 == 2){
                lklZ.add(list.get(iI));
            }

            if(iI % 4 == 3){
                lklW.add(list.get(iI));
            }
        }

        // System.out.println("X" +lklX);
        // System.out.println("Y" + lklY);
        // System.out.println("Z" + lklZ);
        // System.out.println("W" + lklW);
    }


    //&i
    public double calculaCuadrado(double dNum){
        return dNum * dNum;
    }


    //&i
    public double sumatoria(LinkedList <Double> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dN = list.get(iI);
            dSum = dSum + dN;
        }

        return dSum;
    }

    //&i
    public double sumatoriaAB(LinkedList <Double> listA, LinkedList <Double> listB){

        double dSum = 0;

        for(int iI = 0; iI < listA.size(); iI++){

            double dA = listA.get(iI);
            double dB = listB.get(iI);
            double dN = dA*dB;

            dSum = dSum + dN;
        }

        return dSum;
    }

    public double sumatoriaCuadrado(LinkedList <Double> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dN = list.get(iI);
            dN = calculaCuadrado(dN);
            dSum = dSum + dN;
        }

        return dSum;
    }


    public void analyze(){
        //methods
        init();
        fileName(1); //1 file
        scan();
        buildLists(lklDoubles);

        //sumatorias
        sumatoriaX = sumatoria(lklX);
        sumatoriaY = sumatoria(lklY);
        sumatoriaZ = sumatoria(lklZ);
        sumatoriaW = sumatoria(lklW);

        X2 = sumatoriaCuadrado(lklX);
        Y2 = sumatoriaCuadrado(lklY);
        W2 = sumatoriaCuadrado(lklW);

        WX = sumatoriaAB(lklW, lklX);
        WY = sumatoriaAB(lklW, lklY);
        XY = sumatoriaAB(lklX, lklY);

        ZX = sumatoriaAB(lklZ, lklX);
        ZY = sumatoriaAB(lklZ, lklY);
        ZW = sumatoriaAB(lklZ, lklW);

        // System.out.println("sumatoriaX); " + sumatoriaX);
        // System.out.println("sumatoriaY); " + sumatoriaY);
        // System.out.println("sumatoriaZ); " + sumatoriaZ);
        // System.out.println("sumatoriaW); " + sumatoriaW);
        //
        // System.out.println("X2); " + X2);
        // System.out.println("Y2); " + Y2);
        // System.out.println("W2); " + W2);
        //
        // System.out.println("WX); " + WX);
        // System.out.println("WY); " + WY);
        // System.out.println("XY); " + XY);
        //
        // System.out.println("ZX); " + ZX);
        // System.out.println("ZY); " + ZY);
        // System.out.println("ZW); " + ZW);



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
