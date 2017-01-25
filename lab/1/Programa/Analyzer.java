import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;


public class Analyzer {

    // public boolean isAFile(String fileName){
    //
    //     File f = new File(fileName);
    //
    //     if (f.isFile() && f.canRead()) {
    //         return true;
    //     } else{
    //
    //         return false;
    //     }
    //
    // }

    public void readByLine(String fileName, Archivo archivo) throws FileNotFoundException{

        Scanner linReader = new Scanner(new File(fileName));
        int iBlankCounter = 0;
        int iLineCounter = 0;
        String sLine;

        while (linReader.hasNext()){

            sLine = linReader.nextLine();

            if(sLine.length() > 0){
                iLineCounter++;
            } else{

                iBlankCounter++;
            }
        }

        archivo.setName(fileName);
        archivo.setBlankLines(iBlankCounter);
        archivo.setLines(iLineCounter);

        linReader.close();

    }

    public void globalInformation(int iListSize, int blankLines, int iLines){

        System.out.println("TOTALES:");
        System.out.println("Cantidad de archivos " + iListSize);
        System.out.println("Cantidad de líneas en blanco " + blankLines);
        System.out.println("Cantidad de líneas con información " + iLines);

    }

    public void calculateLinesGlobalInfo(LinkedList <Archivo> list, int iBlankLines, int iLines){

        for(int iI = 0; iI < list.size(); iI++){

            iLines = iLines + list.get(iI).getLines();
            iBlankLines = iBlankLines + list.get(iI).getBlankLines();
        }
    }
}



    // public static void main(String args[])  throws FileNotFoundException{
    //     new readByLine();
    // }




    // public boolean isAFile(String fileName){
    //
    //     File f = new File(fileName);
    //     if (f.isFile() && f.canRead()) {
    //         try {
    //             // Open the stream.
    //             FileInputStream in = new FileInputStream(f);
    //             // To read chars from it, use new InputStreamReader
    //             // and specify the encoding.
    //             try {
    //                 // Do something with in.
    //             } finally {
    //
    //                 in.close();
    //             }
    //         } catch (IOException ex) {
    //             // Appropriate error handling here.
    //         }
    //     }
    // }
