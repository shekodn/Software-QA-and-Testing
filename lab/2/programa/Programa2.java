//&p-Programa2
import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;



public class Programa2 {

    //&i
    private LinkedList <Archivo> lklFiles; //LinkedList of Archivo object

    private int iBLANKLINES; //global number of blank lines
    private int iLINES; //global number of lines with info.
    private Scanner scUserInput; //scanner with user input (how many files)
    private Scanner scFileName; //scanner with user input to name files
    private int iNumberOfFiles; //number of files to analyze
    private boolean isACorrectNumberInput;

    private LinkedList <Parte> lklPartes;
    private LinkedList <Parte> lklPartesBase;
    private LinkedList <Parte> lklPartesNuevas;
    private LinkedList <Parte> lklPartesReusadas;


    /**
     * Initializes variables
     */
     //&i
    public void init(){
        lklFiles = new LinkedList<Archivo>();
        iBLANKLINES = 0;
        iLINES = 0;
        scUserInput = new Scanner(System.in);
        scFileName = new Scanner(System.in);
        iNumberOfFiles = 0;
        isACorrectNumberInput = false;

        //listas
        lklPartes = new LinkedList<Parte>();
        lklPartesBase = new LinkedList<Parte>();
        lklPartesNuevas = new LinkedList<Parte>();
        lklPartesReusadas = new LinkedList<Parte>();



    }

    /**
     * This functions is for slowing down the output in the screen
     * @param int ms [number of miliseconds]
     */
     //&i
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks the user how many files he is going to scan
     * @return [number o files]
     */
     //&i
    public int howManyFiles(){

        int iNumberOfFiles = 0;

        try{

            System.out.println("How many files do you want to analyze? -> ");
            iNumberOfFiles = scUserInput.nextInt();
            System.out.println("");
            isACorrectNumberInput = true;

        return iNumberOfFiles;

        } catch(Exception e){

            System.out.println("You need to put a number!" + "\n");
        }

        return 0;
    }

    /**
     * The user put the name of the files he is goint to scan
     * @param int iNumberOfFiles
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
                System.out.println(temporalFile.getName() + " is a file!" +
                        "\n");
                iCounter++;

            } else{

                sleep(500);
                System.out.println("PLEASE TRY AGAIN!" + "\n");
                iI-=1;
            }

        }
    }
    /**
     * Scans and count the number of blank and normal lines per file
     */
     //&i
    public void scan(){
        for(int iI = 0; iI < lklFiles.size(); iI++){
            Analyzer analyzer = new Analyzer();
            Archivo temporalFile = new Archivo();
            lklPartes = (LinkedList<Parte>) analyzer.readByLine2(lklFiles.get(iI).getName(), temporalFile).clone();
            lklFiles.set(iI,temporalFile);
        }
    }

    public void printParts(){

        String sPartName = "";
        int iNumberOfItems = 0;
        int iLineasBase = 0;
        int iLineasBorradas = 0;
        int iLineasModificadas = 0;
        int iLineasTotales = 0;
        int iLineasAgregadas = 0;


        for(int iI = 0; iI < lklPartes.size(); iI++){

            sPartName = lklPartes.get(iI).getName();
            iNumberOfItems = lklPartes.get(iI).getNumberOfItems();
            iLineasBase = lklPartes.get(iI).getLineasBase();
            iLineasBorradas = lklPartes.get(iI).getLineasBorradas();
            iLineasModificadas = lklPartes.get(iI).getLineasModificadas();
            iLineasTotales = lklPartes.get(iI).getLineasTotales();
            iLineasAgregadas = lklPartes.get(iI).getLineasAgregadas();
            lklPartes.get(iI).setLineasAgregadas(iLineasAgregadas);
            lklPartes.get(iI).tipoDeParte(iLineasBase, iLineasModificadas , iLineasBorradas, iLineasAgregadas);

            if(lklPartes.get(iI).getTipoDeParte() == "base"){
                lklPartesBase.add(lklPartes.get(iI));
            }
        }


        for(int iI = 0; iI < lklPartes.size(); iI++){

            sPartName = lklPartes.get(iI).getName();
            iNumberOfItems = lklPartes.get(iI).getNumberOfItems();
            iLineasBase = lklPartes.get(iI).getLineasBase();
            iLineasBorradas = lklPartes.get(iI).getLineasBorradas();
            iLineasModificadas = lklPartes.get(iI).getLineasModificadas();
            iLineasTotales = lklPartes.get(iI).getLineasTotales();
            iLineasAgregadas = lklPartes.get(iI).getLineasAgregadas();
            lklPartes.get(iI).setLineasAgregadas(iLineasAgregadas);
            lklPartes.get(iI).tipoDeParte(iLineasBase, iLineasModificadas , iLineasBorradas, iLineasAgregadas);

            if(lklPartes.get(iI).getTipoDeParte() == "nueva"){
                lklPartesNuevas.add(lklPartes.get(iI));
            }
        }


        for(int iI = 0; iI < lklPartes.size(); iI++){

            sPartName = lklPartes.get(iI).getName();
            iNumberOfItems = lklPartes.get(iI).getNumberOfItems();
            iLineasBase = lklPartes.get(iI).getLineasBase();
            iLineasBorradas = lklPartes.get(iI).getLineasBorradas();
            iLineasModificadas = lklPartes.get(iI).getLineasModificadas();
            iLineasTotales = lklPartes.get(iI).getLineasTotales();
            iLineasAgregadas = lklPartes.get(iI).getLineasAgregadas();
            lklPartes.get(iI).setLineasAgregadas(iLineasAgregadas);
            lklPartes.get(iI).tipoDeParte(iLineasBase, iLineasModificadas , iLineasBorradas, iLineasAgregadas);

            if(lklPartes.get(iI).getTipoDeParte() == "reusada"){
                lklPartesReusadas.add(lklPartes.get(iI));
            }
        }

        try{

            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("ConteoLDC.txt"), true));

            System.out.println("PARTES BASE:");
            writer.println("PARTES BASE:");
            for (int i = 0; i<lklPartesBase.size();i++) {
                System.out.println(lklPartesBase.get(i).getName() + ": "
                + "T=" + lklPartesBase.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesBase.get(i).getNumberOfItems() + ", "
                + "B=" + lklPartesBase.get(i).getLineasBase() + ", "
                + "D=" + lklPartesBase.get(i).getLineasBorradas() + ", "
                + "M=" + lklPartesBase.get(i).getLineasModificadas() + ", "
                + "A=" + lklPartesBase.get(i).getLineasAgregadas());
                writer.println(lklPartesBase.get(i).getName() + ": "
                + "T=" + lklPartesBase.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesBase.get(i).getNumberOfItems() + ", "
                + "B=" + lklPartesBase.get(i).getLineasBase() + ", "
                + "D=" + lklPartesBase.get(i).getLineasBorradas() + ", "
                + "M=" + lklPartesBase.get(i).getLineasModificadas() + ", "
                + "A=" + lklPartesBase.get(i).getLineasAgregadas());

            }

            System.out.println("--------------------------------------------");
            writer.println("--------------------------------------------");
            System.out.println("PARTES NUEVAS:");
            writer.println("PARTES NUEVAS");
            for (int r = 0; r<lklPartesNuevas.size();r++){
                System.out.println(lklPartesNuevas.get(r).getName() + ": "
                + "T=" + lklPartesNuevas.get(r).getLineasTotales() + ", "
                + "I=" + lklPartesNuevas.get(r).getNumberOfItems());
                writer.println(lklPartesNuevas.get(r).getName() + "; "
                + "T=" + lklPartesNuevas.get(r).getLineasTotales() + ", "
                + "I=" + lklPartesNuevas.get(r).getNumberOfItems());

            }

            System.out.println("--------------------------------------------");
            writer.println("--------------------------------------------");
            System.out.println("PARTES REUSADAS:");
            writer.println("PARTES REUSADAS:");
            for (int i = 0;i<lklPartesReusadas.size();i++){
                System.out.println(lklPartesReusadas.get(i).getName() + ": "
                + "T=" + lklPartesReusadas.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesReusadas.get(i).getNumberOfItems() + ", "
                + "B=" + lklPartesReusadas.get(i).getLineasBase());
                writer.println(lklPartesReusadas.get(i).getName() + ": "
                + "T=" + lklPartesReusadas.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesReusadas.get(i).getNumberOfItems());
            }

            //System.out.println("Total LDC: " + iLineCounter);
            // writer.println("Total LDC: " +  iLineCounter);
            writer.close();


        } catch(IOException e){

        }

    }
    /**
     * prints data per file
     */
     //&i
    public void individualData(){

        for(int iI = 0; iI < lklFiles.size(); iI++){
            lklFiles.get(iI).printFileData();
        }
    }

    /**
     * prints total quantities of lines, blank lines and files
     */
     //&i
    public void printGlobalData(){
        Analyzer analyzer = new Analyzer();
        // iBLANKLINES = analyzer.calculateBlankLinesGlobalInfo(lklFiles,
        //         iBLANKLINES);
        iLINES = analyzer.calculateLinesGlobalInfo(lklFiles, iLINES);
        //analyzer.globalInformation(lklFiles.size(), iBLANKLINES ,iLINES);

        try{
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("ConteoLDC.txt"), true));
            System.out.println("--------------------------------------------");
            writer.println("--------------------------------------------");
            System.out.println("Total LDC: " + iLINES);
            writer.println("Total LDC: " +  iLINES);
            writer.close();

        } catch(IOException e){

        }
    }

    /**
     * Performs the overall analysis of files
     */
     //&i
    public void analyze(){
        //methods
        clearTheFile();
        init();
        iNumberOfFiles = howManyFiles();
        fileName(iNumberOfFiles);
        scan();
        printParts();
        //sort in ascending order
        Collections.sort(lklFiles);
        printGlobalData();

    }

    //&b=12
    public static void clearTheFile() {
        try{
            FileWriter fwOb = new FileWriter("ConteoLDC.txt", false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch(IOException e){

        }

    }

    //&i
    public static void main(String[] args) {
        Programa2 program = new Programa2();
        program.analyze();
    }

}
