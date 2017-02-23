//&p-Analyzer
//&b=
import java.io.*;
import java.util.*;

public class Analyzer {
    /**
    * Initializes variables
    */
    //&i
    private int iLINES = 0;
    private int iBLANKLINES = 0;
    private boolean isClosed = true; // true comment closed, false, waiting to close comment
    protected LinkedList <Parte> lklPartes;
    protected LinkedList <Parte> lklPartesBase;
    protected LinkedList <Parte> lklPartesNuevas;
    protected LinkedList <Parte> lklPartesReusadas;
    private String sActivePart;
    private int iCurrentIndex = 0;

    String sPartName = "";
    int iNumberOfItems = 0;
    int iLineasBase = 0;
    int iLineasBorradas = 0;
    int iLineasModificadas = 0;
    int iLineasTotales = 0;
    int iLineasAgregadas = 0;

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
    public LinkedList<Parte> readByLine2(String fileName, Archivo archivo){

        String FILENAME = fileName;
        BufferedReader br = null;
        FileReader fr = null;
        int iBlankCounter = 0;
        int iLineCounter = 0;
        lklPartes = new LinkedList<Parte>();
        lklPartesBase = new LinkedList<Parte>();
        lklPartesNuevas = new LinkedList<Parte>();
        lklPartesReusadas = new LinkedList<Parte>();
        int pendingLines = 0;


        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {


                String strippedString = sCurrentLine.trim();
                isClosed = checksComment(sCurrentLine);
                sActivePart = addingParts(strippedString, lklPartes);

                if((sCurrentLine.trim().equals("") ||
                sCurrentLine.trim().equals("\t") ||
                sCurrentLine.trim().equals(" ")  ||
                sCurrentLine.trim().equals("}")  ||
                sCurrentLine.trim().equals("{")  ||
                sCurrentLine.trim().equals("*") ||
                strippedString.indexOf("*") == 0 ||
                sCurrentLine.contains("//")      ||
                sCurrentLine.contains("/*")      ||
                sCurrentLine.contains("*/"))){

                    if(strippedString.contains("//")){ //si hay comentario

                        //conteo de tags
                        if(strippedString.contains("//&i") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            lklPartes.get(iCurrentIndex).addItem();
                        }


                        //conteo de urls
                        if(strippedString.contains("www") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            iLineCounter++;
                            lklPartes.get(iCurrentIndex).setLineasTotales(1 + lklPartes.get(iCurrentIndex).getLineasTotales());

                        }

                        //conteo de tags dentro de strings
                        if(strippedString.contains("\"") && strippedString.contains("//&m") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            iLineCounter++;
                            lklPartes.get(iCurrentIndex).setLineasTotales(1 + lklPartes.get(iCurrentIndex).getLineasTotales());

                        }

                        //conteo de tags base
                        if(strippedString.contains("//&b=") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            int iLinesBase = getLinesOfSpecificID(strippedString,"//&b=");
                            lklPartes.get(iCurrentIndex).setLineasBase(iLinesBase + lklPartes.get(iCurrentIndex).getLineasBase());
                        }

                        //conteo de tags deleted
                        if(strippedString.contains("//&d=") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            int iLineasBorradas = getLinesOfSpecificID(strippedString, "//&d=");
                            lklPartes.get(iCurrentIndex).setLineasBorradas(lklPartes.get(iCurrentIndex).getLineasBorradas() + iLineasBorradas);
                        }

                        //conteo de tags modified
                        if(strippedString.contains("//&m") && sActivePart != null && isClosed && !strippedString.contains("\"")){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);

                            if(strippedString.charAt(0) == '/'){

                                iBlankCounter++;
                                lklPartes.get(iCurrentIndex).setLineasModificadas(lklPartes.get(iCurrentIndex).getLineasModificadas() + 1);

                            } else{
                                lklPartes.get(iCurrentIndex).setLineasTotales(lklPartes.get(iCurrentIndex).getLineasTotales() + 1);
                                lklPartes.get(iCurrentIndex).setLineasModificadas(lklPartes.get(iCurrentIndex).getLineasModificadas() + 1);
                            }

                            iLineCounter++;
                            System.out.println(strippedString);
                        }

                    } else {

                        iBlankCounter++;
                    }

                } else{

                    //is closed es para no agregar palabras entre comentarios /* */
                    if( sActivePart != null){
                        iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                        lklPartes.get(iCurrentIndex).addLineasTotales();
                        iLineCounter++;
                        System.out.println(strippedString);


                    } else{

                        if(isClosed && strippedString.contains(";")){
                            iLineCounter++;
                            System.out.println(strippedString);

                        }
                    }
                }
            }

            archivo.setName(fileName);
            archivo.setBlankLines(iBlankCounter);
            archivo.setLines(iLineCounter);
            br.close();

        } catch (IOException e) {

            //e.printStackTrace();
        }

        return lklPartes;
    }

    /* PART HELPER */

    public int getLinesOfSpecificID(String sName, String sTag){

        int indexOf = sTag.indexOf("=");


        if(sName.contains(sTag)){

            return Integer.parseInt(sName.substring(indexOf + 1));

        } else {
            System.out.println("Could not get number of lines");
            return 0;
        }
    }

    /**
    * Precondition: this methood should be inside is a part
    * @param String     sPartName [description]
    * @param LinkedList <Parte>   list          [description]
    */
    public void currentPartName(String sPartName, LinkedList <Parte> list){

        //si se usa la parte actual
        if(getPartName(sPartName) == sActivePart){


        } else{

            //busca parte
            for(int iI = 0; iI < lklPartes.size(); iI++){

                if (getPartName(sPartName) == sActivePart){
                    sActivePart = list.get(iI).getName();
                    //agregar items aqui
                }
            }
        }
    }


    public String addingParts(String sCurrentLine, LinkedList <Parte> list){
        //hacer funcion
        //Agrgar parte si es que hay
        if (isAPart(sCurrentLine)){// checa si tiene el tag de parte

            if(isAlreadyAPart(list, getPartName(sCurrentLine)) >= 0){ //checa si la parte ya esta en la lista

                sActivePart = getPartName(sCurrentLine);

            } else {
                //Si no esta, creala, ponle la info y metela a la lista
                Parte parParte = new Parte();
                parParte.setName(getPartName(sCurrentLine));
                sActivePart = getPartName(sCurrentLine);
                list.add(parParte);
            }
        }

        return sActivePart;
    }


    public int getCurrentPartIndex(String sPartName, LinkedList <Parte> list){

        int iIndex = 0;

        for(int iI = 0; iI < lklPartes.size(); iI++){

            if (sPartName.equals(list.get(iI).getName())){
                return iI;
            }
        }

        return 0;
    }

    /**
     * [isAlreadyAPart description]
     * @param  LinkedList <Parte>
     * @param  String     sName
     * @return
     */
    //&i
    public int isAlreadyAPart(LinkedList <Parte> list, String sName){

        for (int iI = 0; iI < list.size(); iI ++){
            if(sName == list.get(iI).getName()){
                return iI;
            }
        }
        return -1;
    }

    /**
    * Preconditions: Use is a part method (true)
    * gets the name of a part ex. "//&p-NNN"
    * @param  String sLine
    * @return NNN
    */
    //&i
    public String getPartName(String sLine){
        return sLine.substring(5);
    }

    /**
    * Verfies that LOC is a part //&p-"
    * @param  String sPart
    * @return
    */
    //&i
    public boolean isAPart(String sPart){
        if(sPart.contains("//&p-")){
            return true;
        } else{
            return false;
        }
    }
    /**
     * Checks if there is a comment
     * @param  String sCurrentLine
     * @return
     */
    //&i
    private boolean checksComment(String sCurrentLine){

        if (sCurrentLine.contains("/*") && sCurrentLine.contains("*/")){
            return true;
        }

        if (sCurrentLine.contains("*/")){
            return true;
        }

        if(sCurrentLine.contains("/*")){
            return false;
        }

        if(!sCurrentLine.contains(";")){
            return false;
        }

        return true;
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
}
