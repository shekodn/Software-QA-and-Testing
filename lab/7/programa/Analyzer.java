//&p-Anlyzer
//&b=59
import java.util.*;
import java.io.*;


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
     */
     //&i
    public void readByLine2(String fileName, LinkedList<Double> lklDoubles ){

        String FILENAME = fileName;
        BufferedReader br = null;
        FileReader fr = null;
        int iBlankCounter = 0;
        int iLineCounter = 0;
        // LinkedList<Double> lklDoubles = new LinkedList<Double>();
        String ans = "";
        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {

                if(sCurrentLine.trim().equals("") ||
                                sCurrentLine.trim().equals("\t") ||
                                sCurrentLine.trim().equals(" ")){
                    //has no content

                } else{

                    //has content
                    ans = ans+=sCurrentLine;

                }

                ans = ans+=",";

            }
            br.close();

        } catch (IOException e) {

            //e.printStackTrace();
        }

        String[] tokens = ans.split("\\,");

        for (int x=0; x<tokens.length; x++) {
            //System.out.println(tokens[x]);
            String text = tokens[x];
            double value = Double.parseDouble(text);
            lklDoubles.add(value);
            //System.out.println(lklDoubles.get(x));
        }


        //return lklDoubles;
    }

    /**
     * globalInformation description
     * @param int iListSize
     * @param int blankLines
     * @param int iLines
     */
     //&i
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
     //&i
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
     //&i
    public int calculateBlankLinesGlobalInfo(LinkedList <Archivo> list,
            int iBlankLines){

        for(int iI = 0; iI < list.size(); iI++){

            iBlankLines = iBlankLines + list.get(iI).getBlankLines();
        }
        return iBlankLines;
    }
}
