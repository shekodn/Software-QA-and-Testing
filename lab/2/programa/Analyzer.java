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
    public Archivo readByLine2(String fileName, Archivo archivo, LinkedList <Parte> lklPartesNuevas,
                LinkedList <Parte> lklPartesBase, LinkedList <Parte> lklPartesReusadas){



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
                sCurrentLine.contains("//")      ||
                sCurrentLine.contains("/*")      ||
                sCurrentLine.contains("*/"))){


                    if(strippedString.contains("//")){ //si hay comentario

                        //checar que no esten despues de una LDC que se debe de contar
                        // if(strippedString.contains("//&m")){
                        //     iLineCounter++;
                        // } else{
                        //     iBlankCounter++;
                        // }

                        //conteo de tags
                        if(strippedString.contains("//&i") && sActivePart != null){
                            iCurrentIndex = getCurrentPartIndex(sActivePart, lklPartes);
                            lklPartes.get(iCurrentIndex).addItem();
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
                            // System.out.println(lklPartes.get(iCurrentIndex).getName());
                            // System.out.print(" " + strippedString);

                            if(strippedString.charAt(0) == '/'){
                                iBlankCounter++;
                                // System.out.println(lklPartes.get(iCurrentIndex).getName() + "ES COMENTARIO");
                                lklPartes.get(iCurrentIndex).setLineasModificadas(lklPartes.get(iCurrentIndex).getLineasModificadas() + 1);

                            } else{
                                iLineCounter++;
                                // System.out.println(lklPartes.get(iCurrentIndex).getName() + "ES LDC");
                                lklPartes.get(iCurrentIndex).setLineasTotales(lklPartes.get(iCurrentIndex).getLineasTotales() + 1);
                                lklPartes.get(iCurrentIndex).setLineasModificadas(lklPartes.get(iCurrentIndex).getLineasModificadas() + 1);
                            }
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

                    } else if (isClosed){
                        iLineCounter++;
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

        for(int iI = 0; iI < lklPartes.size(); iI++){

            sPartName = lklPartes.get(iI).getName();
            iNumberOfItems = lklPartes.get(iI).getNumberOfItems();
            iLineasBase = lklPartes.get(iI).getLineasBase();
            iLineasBorradas = lklPartes.get(iI).getLineasBorradas();
            iLineasModificadas = lklPartes.get(iI).getLineasModificadas();
            iLineasTotales = lklPartes.get(iI).getLineasTotales();
            iLineasAgregadas = lklPartes.get(iI).getLineasAgregadas();
            lklPartes.get(iI).setLineasAgregadas(iLineasAgregadas);
            lklPartes.get(iI).tipoDeParte(iLineasBase, iLineasModificadas , iLineasBorradas, iLineasAgregadas);

            if(lklPartes.get(iI).getTipoDeParte() == "base"){
                lklPartesBase.add(lklPartes.get(iI));
            }
        }


        for(int iI = 0; iI < lklPartes.size(); iI++){

            sPartName = lklPartes.get(iI).getName();
            iNumberOfItems = lklPartes.get(iI).getNumberOfItems();
            iLineasBase = lklPartes.get(iI).getLineasBase();
            iLineasBorradas = lklPartes.get(iI).getLineasBorradas();
            iLineasModificadas = lklPartes.get(iI).getLineasModificadas();
            iLineasTotales = lklPartes.get(iI).getLineasTotales();
            iLineasAgregadas = lklPartes.get(iI).getLineasAgregadas();
            lklPartes.get(iI).setLineasAgregadas(iLineasAgregadas);
            lklPartes.get(iI).tipoDeParte(iLineasBase, iLineasModificadas , iLineasBorradas, iLineasAgregadas);

            if(lklPartes.get(iI).getTipoDeParte() == "nueva"){
                lklPartesNuevas.add(lklPartes.get(iI));
            }
        }


        for(int iI = 0; iI < lklPartes.size(); iI++){

            sPartName = lklPartes.get(iI).getName();
            iNumberOfItems = lklPartes.get(iI).getNumberOfItems();
            iLineasBase = lklPartes.get(iI).getLineasBase();
            iLineasBorradas = lklPartes.get(iI).getLineasBorradas();
            iLineasModificadas = lklPartes.get(iI).getLineasModificadas();
            iLineasTotales = lklPartes.get(iI).getLineasTotales();
            iLineasAgregadas = lklPartes.get(iI).getLineasAgregadas();
            lklPartes.get(iI).setLineasAgregadas(iLineasAgregadas);
            lklPartes.get(iI).tipoDeParte(iLineasBase, iLineasModificadas , iLineasBorradas, iLineasAgregadas);

            if(lklPartes.get(iI).getTipoDeParte() == "reusada"){
                lklPartesReusadas.add(lklPartes.get(iI));
            }
        }

        // System.out.println("PARTES BASE:");
        // for(int iI = 0; iI < lklPartesBase.size(); iI++){
        //     lklPartesBase.get(iI).imprimirBase(lklPartesBase, iI);
        // }
        //
        // System.out.println("-------------------------------------");
        // System.out.println("PARTES NUEVAS:");
        // for(int iI = 0; iI < lklPartesNuevas.size(); iI++){
        //     lklPartesNuevas.get(iI).imprimirNueva(lklPartesNuevas, iI);
        // }
        //
        // System.out.println("-------------------------------------");
        // System.out.println("PARTES REUSADAS:");
        // for(int iI = 0; iI < lklPartesReusadas.size(); iI++){
        //     lklPartesReusadas.get(iI).imprimirReusado(lklPartesReusadas, iI);
        // }
        //
        //


        try{

            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("ConteoLDC.txt"), true));

            System.out.println("PARTES BASE:");
            writer.println("PARTES BASE:");
            for (int i = 0; i<lklPartesBase.size();i++) {
                System.out.println(lklPartesBase.get(i).getName() + ": "
                + "T=" + lklPartesBase.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesBase.get(i).getNumberOfItems() + ", "
                + "B=" + lklPartesBase.get(i).getLineasBase() + ", "
                + "D=" + lklPartesBase.get(i).getLineasBorradas() + ", "
                + "M=" + lklPartesBase.get(i).getLineasModificadas() + ", "
                + "A=" + lklPartesBase.get(i).getLineasAgregadas());
                writer.println(lklPartesBase.get(i).getName() + ": "
                + "T=" + lklPartesBase.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesBase.get(i).getNumberOfItems() + ", "
                + "B=" + lklPartesBase.get(i).getLineasBase() + ", "
                + "D=" + lklPartesBase.get(i).getLineasBorradas() + ", "
                + "M=" + lklPartesBase.get(i).getLineasModificadas() + ", "
                + "A=" + lklPartesBase.get(i).getLineasAgregadas());

            }

            System.out.println("--------------------------------------------");
            writer.println("--------------------------------------------");
            System.out.println("PARTES NUEVAS:");
            writer.println("PARTES NUEVAS");
            for (int r = 0; r<lklPartesNuevas.size();r++){
                System.out.println(lklPartesNuevas.get(r).getName() + ": "
                + "T=" + lklPartesNuevas.get(r).getLineasTotales() + ", "
                + "I=" + lklPartesNuevas.get(r).getNumberOfItems());
                writer.println(lklPartesNuevas.get(r).getName() + "; "
                + "T=" + lklPartesNuevas.get(r).getLineasTotales() + ", "
                + "I=" + lklPartesNuevas.get(r).getNumberOfItems());

            }

            System.out.println("--------------------------------------------");
            writer.println("--------------------------------------------");
            System.out.println("PARTES REUSADAS:");
            writer.println("PARTES REUSADAS:");
            for (int i = 0;i<lklPartesReusadas.size();i++){
                System.out.println(lklPartesReusadas.get(i).getName() + ": "
                + "T=" + lklPartesReusadas.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesReusadas.get(i).getNumberOfItems() + ", "
                + "B=" + lklPartesReusadas.get(i).getLineasBase());
                writer.println(lklPartesReusadas.get(i).getName() + ": "
                + "T=" + lklPartesReusadas.get(i).getLineasTotales() + ", "
                + "I=" + lklPartesReusadas.get(i).getNumberOfItems());
            }

            System.out.println("--------------------------------------------");
            writer.println("--------------------------------------------");
            //System.out.println("Total LDC: " + iTotalesLC);
            //writer.println("Total LDC: " +  iTotalesLC);

            writer.close();
        } catch(IOException e){
            System.out.println("writing error");
        }
        
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


    /**
    * globalInformation description
    * @param int iListSize
    * @param int blankLines
    * @param int iLines
    */
    //&i
    public void globalInformation(int iListSize, int blankLines, int iLines){
        System.out.println("-------------------------------------");
        System.out.println("Total de LDC: "  + iLines);
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
