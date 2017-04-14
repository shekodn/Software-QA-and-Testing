//&p-Analyzer
//&b=36
import java.io.*;
import java.util.*;

public class Analyzer {

    /**
     * Checks if given file name it's actually a file
     * @param  String fileName
     * @return  true if it is a file
     */
     //&i
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
     //&i
    public int readByLine(String fileName, LinkedList<Coordenada> list){

        String FILENAME = fileName;
        BufferedReader br = null;
        FileReader fr = null;
        int iN = 0;

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {

                if(sCurrentLine.contains(",")){
                    int iComa = sCurrentLine.indexOf(',');
                    String sX = sCurrentLine.substring(0, iComa);
                    String sY = sCurrentLine.substring(iComa + 1);
                    double dX = Double.parseDouble(sX);
                    double dY = Double.parseDouble(sY);

                    Coordenada aux = new Coordenada();

                    aux.setX(dX);
                    aux.setY(dY);
                    list.add(aux);

                } else{

                    iN = Integer.parseInt(sCurrentLine);
                }
            }

            br.close();

        } catch (IOException e) {

        }

        return iN;
    }
}
