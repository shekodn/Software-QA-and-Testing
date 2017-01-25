import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class FileAnalyzer {

    private LinkedList <Archivo> lklFiles;
    private int iBLANKLINES;
    private int iLINES;
    private Scanner scUserInput;
    private Scanner scFileName;
    private int iNumberOfFiles;

    public void init(){

        lklFiles = new LinkedList<Archivo>();
        iBLANKLINES = 0;
        iLINES = 0;
        scUserInput = new Scanner(System.in);
        scFileName = new Scanner(System.in);

        iNumberOfFiles = 0;

    }

    public void howManyFiles(){

        int iNumberOfFiles;
        System.out.println("How many files do you want to analyze? -> ");
        iNumberOfFiles = scUserInput.nextInt();

        //checks if it is a number
        if (iNumberOfFiles > 0){

            return iNumberOfFiles;
        }

        return 0;
    }

    void fileName(LinkedList <Archivo> list, int iNumberOfFiles){

        String sName;
        int iCounter = 1;
        File temporalFile = new File();
        Analyzer analyzer = new Analyzer();

        for(int iI = 0; iI < iNumberOfFiles; iI++){

            System.out.println("Name for file " + iCounter + "/" +
                    iNumberOfFiles + " ->");

            sName = scFileName.nextLine();

            // if(analyzer.isAFile(sName){
            if(true){
                temporalFile.setName(sName);
                list.add(temporalFile);

            } else{

                System.out.println(sName + " is not a file");
            }
        }
    }

    public void scan(LinkedList <Archivo> list){

        Analyzer analyzer = new Analyzer();
        File temporalFile = new File();

        for(int iI = 0; iI < list.size(), iI++){
            analyzer.readByLine(list.get(iI).getName(), temporalFile);
            list.add(temporalFile);
            temporalFile.resetValues();
        }
    }

    public void individualData(LinkedList <Archivo> list){

        for(int iI = 0; iI < list.size(); iI++){

            list.get(iI).printFileData();
        }
    }

    public void printGlobalData(){

        Analyzer analyzer = new Analyzer();
        analyzer.calculateLinesGlobalInfo(lklFiles, iBLANKLINES, iLINES){
        analyzer.globalInformation(lklFiles.size(),iBLANKLINES, iLINES);
    }

    public void analyze(){

        //methods
        init();
        iNumberOfFiles = howManyFiles();
        fileName(lklFiles, iNumberOfFiles);
        scan();
        // sort in ascending order
        Collections.sort(lklFiles);
        printIndividualData();
        printGlobalData();
    }


    public static void main(String[] args) {
        FileAnalyzer program = new FileAnalyzer();
        program.analyze();

    }
}
