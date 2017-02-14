//&p-Analyzer
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
import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class Analyzer {

    /**
     * Initializes variables
     */

    //&i
    private int iLINES = 0;
    private int iBLANKLINES = 0;
    private boolean isClosed = true; // true comment closed, false, waiting to close comment

    private LinkedList <Parte> lklPartes;
    private LinkedList <Parte> lklPartesBase;
    private LinkedList <Parte> lklPartesNuevas;
    private LinkedList <Parte> lklPartesReusadas;

    private String sActivePart;
    private int iCurrentIndex = 0;


    // public void init(){
    //
    //     lklPartes = new LinkedList<Parte>();
    //     lklPartesBase = new LinkedList<Parte>();
    //     lklPartesNuevas = new LinkedList<Parte>();
    //     lklPartesReusadas = new LinkedList<Parte>();
    // }


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
     * @param  String  fileName
     * @param  Archivo archivo
     * @return a file with updated infomration
     */
    //&i
    public Archivo readByLine2(String fileName, Archivo archivo){

        String FILENAME = fileName;
        BufferedReader br = null;
        FileReader fr = null;
        int iBlankCounter = 0;
        int iLineCounter = 0;
        lklPartes = new LinkedList<Parte>();

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {

                isClosed = checksComment(sCurrentLine);

                if((sCurrentLine.trim().equals("") ||
                                sCurrentLine.trim().equals("\t") ||
                                sCurrentLine.trim().equals(" ")  ||
                                sCurrentLine.trim().equals("}")  ||
                                sCurrentLine.trim().equals("{")  ||
                                sCurrentLine.contains("//")      ||
                                sCurrentLine.contains("/*")      ||
                                sCurrentLine.contains("*/"))){

                    //sCurrentLine = sCurrentLine.strip(' \t\n\r')

                    //String strippedString = sCurrentLine.replace("\t", "");
                    //String strippedString = sCurrentLine.strip("\t\n\r")
                    //String strippedString = sCurrentLine.strip();
                    String strippedString = sCurrentLine.trim();



                    if(sCurrentLine.contains("//")){ //si hay comentario

                        sActivePart = addingParts(sCurrentLine, lklPartes);

                        //checar que no esten despues de una LDC que se debe de contar
                        if(sCurrentLine.contains("//&m") ){
                            iLineCounter++;

                        } else{
                            iBlankCounter++;
                        }

                        //conteo de tags
                        if(sCurrentLine.contains("//&i") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            lklPartes.get(iCurrentIndex).addItem();
                        }

                        //conteo de tags base
                        if(sCurrentLine.contains("//&b=") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            int iLinesBase = getLinesOfSpecificID(sCurrentLine,"//&b=");
                            lklPartes.get(iCurrentIndex).setLineasBase(iLinesBase);
                        }

                        //conteo de tags deleted
                        if(strippedString.contains("//&d=") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            System.out.println("Striped " + strippedString.length());
                            System.out.println("Striped " + strippedString);

                            int iLineasBorradas = getLinesOfSpecificID(strippedString, "//&d=");
                            System.out.println(strippedString);
                            lklPartes.get(iCurrentIndex).setLineasBorradas(lklPartes.get(iCurrentIndex).getLineasBorradas() + iLineasBorradas);
                        }

                        //conteo de tags deleted
                        if(sCurrentLine.contains("//&m") && sActivePart != null && isClosed && !sCurrentLine.contains("\"")){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            //System.out.println(sCurrentLine);
                            lklPartes.get(iCurrentIndex).setLineasModificadas(lklPartes.get(iCurrentIndex).getLineasModificadas() + 1);
                        }


                    } else {
                        //si no contiene "//", sumar lineas blancas
                        iBlankCounter++;
                    }

                } else{
                    //is closed es para no agregar palabras entre comentarios /* */
                    if (isClosed){
                        iLineCounter++;
                    } else{
                        iBlankCounter++;
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

        System.out.println("Partes: " + lklPartes.size());
        for(int iI = 0; iI < lklPartes.size(); iI++){
            System.out.println(lklPartes.get(iI).getName());
            System.out.println(lklPartes.get(iI).getNumberOfItems());
            System.out.println(lklPartes.get(iI).getLineasBase());
            System.out.println(lklPartes.get(iI).getLineasModificadas());
            System.out.println(lklPartes.get(iI).getLineasBorradas() + "\n");


        }

        // lklPartes.get(0).setName("new email");
        // System.out.println(lklPartes.get(0).getName());


        return archivo;
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


    public int isAlreadyAPart(LinkedList <Parte> list, String sName){

        for (int iI = 0; iI < list.size(); iI ++){
            if(sName == list.get(iI).getName()){
                //System.out.println(sName + "already is a part");
                return iI;
            }
        }

        //System.out.println(sName + "is a new part");
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

    //&i
    /**
     * Verfies that LOC is a part //&p-"
     * @param  String sPart
     * @return
     */
    public boolean isAPart(String sPart){
        if(sPart.contains("//&p-")){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Determines if LOC is an item
     * @param  String sPart
     * @return
     */
    public boolean isAnItem(String sPart){
        if(sPart.contains("//&i")){
            return true;
        } else{
            return false;
        }
    }

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

        return true;
    }

    public boolean comment(String sLinea){

        Pattern pattern1 = Pattern.compile("/\\*.*?\\*/");
        Pattern pattern2 = Pattern.compile(".*//.*");

        Matcher matcher1 = pattern1.matcher(sLinea);
        Matcher matcher2 = pattern2.matcher(sLinea);

        if (matcher1.find()) {
            // Indicates match is found. Do further processing
            //System.out.println(matcher1.group());
            return true;
        }
        if(matcher2.find()) {
            // Indicates match is found. Do further processing
            //System.out.println(matcher2.group());
            return true;

        } else{

            return false;
        }
    }

    public boolean isCommentAfterStatement(String sLinea){

        Pattern pattern2 = Pattern.compile(".*//");
        Matcher matcher2 = pattern2.matcher(sLinea);

        if(matcher2.find()) {

            if(matcher2.group().length() == 2){ //this starts with comment
                 return false;

            } else{
                //this has a comment after statements
                return true;
            }

        } else{

            return false;
        }
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
