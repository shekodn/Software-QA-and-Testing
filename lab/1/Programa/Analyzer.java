import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Analyzer {

    /**
     * Initializes variables
     */
    private int iLINES = 0;
    private int iBLANKLINES = 0;

    /**
     * gets total lines
     * @return gets total lines
     */
    public int getTotalLines(){
        return iLINES;
    }

    /**
     * getTotalBlankLines
     * @return [number of blank lines]
     */
    public int getTotalBlankLines(){
        return iBLANKLINES;
    }

    /**
     * setTotalLines
     * @param int iN [set total lines]
     */
    public void setTotalLines(int iN){
        this.iLINES = iN;
    }

    public void setTotalBlankLines(int iN){
        this.iBLANKLINES = iN;
    }

    /**
     * Checks if given file name it's actually a file
     * @param  String fileName
     * @return  true if it is a file
     */
    public boolean isAFile(String fileName){

        File f = new File(fileName);

        if (f.isFile() && f.canRead()) {

            //System.out.println(fileName + " is a FILE ");
            return true;

        } else{
            System.out.println(fileName + " is not FILE ");
            return false;
        }
    }

    /**
     * [readByLine2 description]
     * @param  String  fileName
     * @param  Archivo archivo
     * @return a file with updated infomration
     */
    public Archivo readByLine2(String fileName, Archivo archivo){

        String FILENAME = fileName;
        BufferedReader br = null;
        FileReader fr = null;
        int iBlankCounter = 0;
        int iLineCounter = 0;

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {

                if(sCurrentLine.trim().equals("") ||
                                sCurrentLine.trim().equals("\t") ||
                                sCurrentLine.trim().equals(" ")){

                    iBlankCounter++;
                    //System.out.println(sCurrentLine.length());
                } else{
                    iLineCounter++;
                    //System.out.println(sCurrentLine.length());
                }
            }
            archivo.setName(fileName);
            archivo.setBlankLines(iBlankCounter);
            archivo.setLines(iLineCounter);
            br.close();

        } catch (IOException e) {

            //e.printStackTrace();
        }

        return archivo;
    }

    /**
     * globalInformation description
     * @param int iListSize
     * @param int blankLines
     * @param int iLines
     */
    public void globalInformation(int iListSize, int blankLines, int iLines){
        System.out.println("TOTALES:");
        System.out.println("Cantidad de archivos " + iListSize);
        System.out.println("Cantidad de líneas en blanco " + blankLines);
        System.out.println("Cantidad de líneas con información " + iLines);
    }

    /**
     * calculateLinesGlobalInfo description
     * @param  LinkedList <Archivo> linked list of files
     * @param  int iLines = number of lines with info
     * @return number of lines with info
     */
    public int calculateLinesGlobalInfo(LinkedList <Archivo> list, int iLines){

        for(int iI = 0; iI < list.size(); iI++){

            iLines = iLines + list.get(iI).getLines();
        }

        return iLines;
    }
    /**
     * calculateBlankLinesGlobalInfo description
     * @param  LinkedList <Archivo>     list          [linked list with files]
     * @param  int        iBlankLines   Total number of blank lines
     * @return Total number of blank lines
     */
    public int calculateBlankLinesGlobalInfo(LinkedList <Archivo> list,
            int iBlankLines){

        for(int iI = 0; iI < list.size(); iI++){

            iBlankLines = iBlankLines + list.get(iI).getBlankLines();
        }
        return iBlankLines;
    }
}
