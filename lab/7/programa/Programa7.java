import java.util.*;
import java.io.*;

public class Programa7 {

    /* Variable declaration */
    private Scanner scFileName; //scanner with user input to name files

    /* Aux lists*/
    private LinkedList <Double> lklW;
    private LinkedList <Double> lklX;
    private LinkedList <Double> lklY;
    private LinkedList <Double> lklZ;
    private LinkedList <Double> lklDoubles;
    private LinkedList <Double> lklGaussInput;
    private LinkedList <Archivo> lklFiles;

    /* Aux */
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

    /* OUTPUT */
    private double N;
    private double wk;
    private double xk;
    private double yk;
    private double b0;
    private double b1;
    private double b2;
    private double b3;
    private double zk;

    /* GAUSS variables*/
    private Gauss gauss;
    private int iNum = 4;
    private double[] B;
    private double[][] A;

    /**
     * Initializes variables
     */
     //&i
    public void init(){

        lklFiles = new LinkedList<Archivo>();
        scFileName = new Scanner(System.in);
        lklDoubles = new LinkedList<Double>();
        lklGaussInput = new LinkedList<Double>();

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

        gauss = new Gauss();
        iNum = 4; //here goes 4
        B = new double[iNum];
        A = new double[iNum][iNum];

    }


    /**
     * The user put the name of the files he is going to scan
     * @param int iNumberOfFiles = 1
     */
     //&i
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

    /**
     * [scan the file provided by the user]
     */
     //&i
    public void scan(){

        for(int iI = 0; iI < lklFiles.size(); iI++){
            Analyzer analyzer = new Analyzer();
            analyzer.readByLine2(lklFiles.get(iI).getName(), lklDoubles);
        }

        Double auxN = lklDoubles.size() - 3.0; //&m
        N = auxN / 4; //&m
        wk = lklDoubles.get(0); //&m
        xk = lklDoubles.get(1); //&m
        yk = lklDoubles.get(2); //&m

    }
    /**
     * Fills 4 lists: lkl X,lkl Y,lkl Z, lkl W
     * @param LinkedList<Double> list [description]
     */
    //&i
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
    }


    /**
     * [calculaCuadrado]
     * @param  double dNum
     * @return  dNum * dNum
     */
     //&i
    public double calculaCuadrado(double dNum){
        return dNum * dNum;
    }


    //&i
    /**
     * [sumatoria]
     * @param  LinkedList list
     * @return the sum of all the elements of the list
     */
    public double sumatoria(LinkedList <Double> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dN = list.get(iI);
            dSum = dSum + dN;
        }

        return dSum;
    }

    //&i
    /**
     * [sumatoriaAB]
     * @param  LinkedList <Double> listA [list A]
     * @param  LinkedList <Double> listB [list B]
     * @return the sum of all the elements of both lists
     */
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

    //&i
    /**
     * [sumatoriaAB]
     * @param  LinkedList <Double> list
     * @return the sum to the 2nd power of all the elements of list
     */
    public double sumatoriaCuadrado(LinkedList <Double> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dN = list.get(iI);
            dN = calculaCuadrado(dN);
            dSum = dSum + dN;
        }

        return dSum;
    }


    /**
     * Performs the necesary methods to perform calculations.
     */
    //&i
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

        prepareForSolving();
        print();
    }

    /**
     * Fills matrix so gauss can perform calculations
     */
    public void prepareForSolving(){

        A[0][0] = N;
        A[0][1] = sumatoriaW;
        A[0][2] = sumatoriaX;
        A[0][3] = sumatoriaY;

        A[1][0] = sumatoriaW;
        A[1][1] = W2;
        A[1][2] = WX;
        A[1][3] = WY;

        A[2][0] = sumatoriaX;
        A[2][1] = WX;
        A[2][2] = X2;
        A[2][3] = XY;

        A[3][0] = sumatoriaY;
        A[3][1] = WY;
        A[3][2] = XY;
        A[3][3] = Y2;

        B[0] = sumatoriaZ;
        B[1] = ZW;
        B[2] = ZX;
        B[3] = ZY;

        gauss.calcula(A,B,4);

        b0 = gauss.getB0();
        b1 = gauss.getB1();
        b2 = gauss.getB2();
        b3 = gauss.getB3();
        zk = b0 + b1*wk + b2*xk + b3*yk;

    }

    /**
     * [prints answer]
     */
    //&i
    public void print(){

        System.out.printf(" N = %.0f \n", N);
        System.out.printf("wk = %.05f \n", wk);
        System.out.printf("xk = %.05f \n", xk);
        System.out.printf("yk = %.05f \n", yk);
        System.out.print("------------\n");
        System.out.printf("b0 = %.05f \n", b0);
        System.out.printf("b1 = %.05f \n", b1);
        System.out.printf("b2 = %.05f \n", b2);
        System.out.printf("b3 = %.05f \n", b3);
        System.out.print("------------\n");
        System.out.printf("zk = %.05f \n", zk);
    }

    /**
     * main
     */
    //&i
    public static void main(String[] args) {
        Programa7 p7 = new Programa7();
        p7.analyze();
    }
}
