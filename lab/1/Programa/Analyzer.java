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

    private int iLINES = 0;
    private int iBLANKLINES = 0;


    public int getTotalLines(){
        return iLINES;
    }

    public int getTotalBlankLines(){
        return iBLANKLINES;
    }

    public void setTotalLines(int iN){
        this.iLINES = iN;
    }

    public void setTotalBlankLines(int iN){
        this.iBLANKLINES = iN;
    }

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
                // System.out.println(sCurrentLine.length());

                if(sCurrentLine.length() >= 1){
                    iLineCounter++;
                    //System.out.println(sCurrentLine.length());
                } else{
                    iBlankCounter++;
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



    public Archivo readByLine(String fileName, Archivo archivo) throws FileNotFoundException{

        Scanner linReader = new Scanner(new File(fileName));
        int iBlankCounter = 0;
        int iLineCounter = 0;



        // while (linReader.hasNextLine()){
        while(linReader.hasNext()){

            String sLine = linReader.nextLine();

            if(sLine.length() >= 1){
                iLineCounter++;
                System.out.println(sLine.length());
            } else{
                iBlankCounter++;
                System.out.println(sLine.length());
            }
        }

        archivo.setName(fileName);
        archivo.setBlankLines(iBlankCounter);
        archivo.setLines(iLineCounter);

        linReader.close();

        return archivo;
    }

    public void globalInformation(int iListSize, int blankLines, int iLines){

        System.out.println("TOTALES:");
        System.out.println("Cantidad de archivos " + iListSize);
        System.out.println("Cantidad de líneas en blanco " + blankLines);
        System.out.println("Cantidad de líneas con información " + iLines);

    }

    public int calculateLinesGlobalInfo(LinkedList <Archivo> list, int iLines){

        for(int iI = 0; iI < list.size(); iI++){

            iLines = iLines + list.get(iI).getLines();
        }

        return iLines;
    }

    public int calculateBlankLinesGlobalInfo(LinkedList <Archivo> list, int iBlankLines){

        for(int iI = 0; iI < list.size(); iI++){

            iBlankLines = iBlankLines + list.get(iI).getBlankLines();
        }

        return iBlankLines;
    }

}
