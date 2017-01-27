import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileAnalyzer {

    private LinkedList <Archivo> lklFiles;
    private int iBLANKLINES;
    private int iLINES;
    private Scanner scUserInput;
    private Scanner scFileName;
    private int iNumberOfFiles;

    public void init(){
        lklFiles = new LinkedList<Archivo>();
//        lklFiles = new LinkedList<Archivo>();
        iBLANKLINES = 0;
        iLINES = 0;
        scUserInput = new Scanner(System.in);
        scFileName = new Scanner(System.in);

        iNumberOfFiles = 0;
    }

    public int howManyFiles(){

        int iNumberOfFiles = 0;
        System.out.println("How many files do you want to analyze? -> ");
        iNumberOfFiles = scUserInput.nextInt();
        System.out.println("");


        //checks if it is a number
        if (iNumberOfFiles > 0){

            return iNumberOfFiles;
        }

        return iNumberOfFiles;
    }

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
                System.out.println(temporalFile.getName() + " is a file!");

                System.out.println("");

            } else{
                System.out.println(temporalFile.getName() + " was not added bc is not a file");
                System.out.println("");
            }

            iCounter++;
        }
    }

    public void scan(){

        for(int iI = 0; iI < lklFiles.size(); iI++){
            Analyzer analyzer = new Analyzer();
            Archivo temporalFile = new Archivo();
            analyzer.readByLine2(lklFiles.get(iI).getName(), temporalFile);
            lklFiles.set(iI,temporalFile);
        }

        System.out.println("FINISHES SCAN METHOD");
    }

    public void individualData(){

        for(int iI = 0; iI < lklFiles.size(); iI++){
            lklFiles.get(iI).printFileData();
        }
    }

    public void printGlobalData(){

        Analyzer analyzer = new Analyzer();
        iBLANKLINES = analyzer.calculateBlankLinesGlobalInfo(lklFiles, iBLANKLINES);
        iLINES = analyzer.calculateLinesGlobalInfo(lklFiles, iLINES);
        analyzer.globalInformation(lklFiles.size(), iBLANKLINES ,iLINES);
    }

    public void analyze(){
        //methods
        init();
        iNumberOfFiles = howManyFiles();
        fileName(iNumberOfFiles);
        scan();
        //sort in ascending order
        Collections.sort(lklFiles);
        individualData();
        printGlobalData();
    }


    public static void main(String[] args) {
        FileAnalyzer program = new FileAnalyzer();
        program.analyze();
    }
}
