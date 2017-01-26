import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;



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

    public int howManyFiles(){

        int iNumberOfFiles = 0;
        System.out.println("How many files do you want to analyze? -> ");
        iNumberOfFiles = scUserInput.nextInt();

        //checks if it is a number
        if (iNumberOfFiles > 0){

            return iNumberOfFiles;
        }

        return iNumberOfFiles;
    }

    void fileName(LinkedList <Archivo> list, int iNumberOfFiles){

        String sName;
        int iCounter = 1;
        Archivo temporalFile = new Archivo();
        Analyzer analyzer = new Analyzer();

        for(int iI = 0; iI < iNumberOfFiles; iI++){

            System.out.print("Name for file " + iCounter + " out of " +
            iNumberOfFiles + " ->");
            System.out.println();

            sName = scFileName.nextLine();

            if(analyzer.isAFile(sName)){
                temporalFile.setName(sName);
                list.add(temporalFile);

            } else{

                System.out.println(sName + " is not a file");
            }
        }
    }

    public void scan(LinkedList <Archivo> list){

        Analyzer analyzer = new Analyzer();
        Archivo temporalFile = new Archivo();

        for(int iI = 0; iI < list.size(); iI++){

            try{

                temporalFile = analyzer.readByLine(list.get(iI).getName(), temporalFile);
                list.get(iI).setBlankLines(temporalFile.getBlankLines());
                list.get(iI).setLines(temporalFile.getLines());
                temporalFile.resetValues();

            }
            catch (FileNotFoundException ex){
                System.out.println("Error reading the file in scan method");
            }
        }
    }

    public void individualData(LinkedList <Archivo> list){

        for(int iI = 0; iI < list.size(); iI++){

            list.get(iI).printFileData();
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
        fileName(lklFiles, iNumberOfFiles);
        scan(lklFiles);
        // sort in ascending order
        Collections.sort(lklFiles);
        individualData(lklFiles);
        printGlobalData();
    }


    public static void main(String[] args) {
        FileAnalyzer program = new FileAnalyzer();
        program.analyze();

    }
}
