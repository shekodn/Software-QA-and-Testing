//&p-Parte
//&a
import java.io.*;

public class Parte {

    /**
     * private attributes
     */
    //&i
    private String sName;
    private String sTipoDeParte;
    private int iLDC;
    private int iLineasBase;
    private int iLineasBorradas;
    private int iLineasModificadas;
    private int iLineasAgregadas;
    private int iLineasTotales;
    private int iNumberOfItems;

    /**
     * Default costructor
     * @return
     */
    //&i
    public Parte(){
        this.sName = "part with no name";
        sTipoDeParte = "sin tipo";
        this.iLDC = 0;
        this.iLineasBase = 0;
        this.iLineasBorradas = 0;
        this.iLineasModificadas = 0;
        this.iLineasAgregadas = 0;
        this.iLineasTotales = 0;
        this.iNumberOfItems = 0;
    }

    /**
     * Cosctuctor with params
     * @param  String sName
     * @param  int    iLDC
     * @param  int    iLineasBase
     * @param  int    iLineasBorradas
     * @param  int    iLineasModificadas
     * @param  int    iLineasAgregadas
     * @param  int    iLineasTotales
     * @param  int    iNumberOfItems
     * @return
     */
     //&i
    public Parte(String sName, String sTipoDeParte ,int iLDC, int iLineasBase,
                int iLineasBorradas, int iLineasModificadas, int iLineasAgregadas,
                int iLineasTotales, int iNumberOfItems){

        this.sName = sName;
        this.sTipoDeParte = sTipoDeParte;
        this.iLDC = iLDC;
        this.iLineasBase = iLineasBase;
        this.iLineasBorradas = iLineasBorradas;
        this.iLineasModificadas = iLineasModificadas;
        this.iLineasAgregadas = iLineasAgregadas;
        this.iLineasTotales = iLineasTotales;
        this.iNumberOfItems = iNumberOfItems;
    }

    /* Getters */

    public String getTipoDeParte(){
        return sTipoDeParte;
    }

    public String getName(){
        return sName;
    }

    public int getLDC(){
        return iLDC;
    }

    public int getLineasBase(){
        return iLineasBase;
    }

    public int getLineasBorradas(){
        return iLineasBorradas;
    }

    public int getLineasModificadas(){
        return iLineasModificadas;
    }

    // public int getLineasAgregadas(){
    //     return iLineasAgregadas;
    // }

    public int getLineasTotales(){
        return iLineasTotales;
    }

    public int getNumberOfItems(){
        return iNumberOfItems;
    }

    //special getters

    public int getLineasAgregadas(int iT, int iB, int iD){
        return iT - iB + iD;
    }

    public int calculaLineasTotales(int iA, int iB, int iD){
        return  iA + iB - iD;
    }

    /* Setters */

    public void setName(String sN){
        this.sName = sN;
    }

    public void setTipoDeParte(String sN){

        if(sName == "base" || sName == "nueva" ||sName == "reusada"){
            this.sTipoDeParte = sN;
        } else{
            this.sTipoDeParte = "Error";
        }
    }

    public void setLDC(int iN){
        this.iLDC = iN;
    }

    public void setLineasBase(int iN){
        this.iLineasBase = iN;
    }

    public void setLineasBorradas(int iN){
        this.iLineasBorradas = iN;
    }

    public void setLineasModificadas(int iN){
        this.iLineasModificadas = iN;
    }

    public void setLineasAgregadas(int iN){
        this.iLineasAgregadas = iN;
    }

    public void setLineasTotales(int iN){
        this.iLineasTotales = iN;
    }

    public void setNumberOfItems(int iN){
        this.iNumberOfItems = iN;
    }

    /* Other methods */

    public int addItem(){
        iNumberOfItems = iNumberOfItems + 1;
        return iNumberOfItems;
    }

    public int addModified(){
        iLineasModificadas = iLineasModificadas + 1;
        return iLineasModificadas;
    }

    public int addDeleted(){
        iLineasBorradas = iLineasBorradas + 1;
        return iLineasBorradas;
    }

    public int addLineasTotales(){
        iLineasTotales = iLineasTotales + 1;
        return iLineasTotales;
    }




    //&i
    public void tipoDeParte(int iB, int iM, int iD, int iA){

        if (iB > 0 && (iM > 0 || iD > 0 || iA > 0)) {
            this.sTipoDeParte = "base";
            // return "base";
        } else if (iB == 0 && iM == 0 && iD == 0 && iA > 0) {
            this.sTipoDeParte = "nueva";
            // return "nueva";
        } else if (iB > 0 && iM == 0 && iD == 0 && iA == 0) {
            this.sTipoDeParte = "reusada";
            // return "reusada";
        }  else {
            this.sTipoDeParte = "error";
            // return "error";
        }
    }

    /**
     * getLinesOfSpecificID description ex. //&b-42
     * @param  String sName
     * @return  42
     */
    //&i
    public int getLinesOfSpecificID(String sName){

        if(sName.contains("base") || sName.contains("nueva") ||sName.contains("reusada")){

            return Integer.parseInt(sName.substring(5));

        } else {
            System.out.println("Could not get number of lines");
            return 0;
        }
    }


    public void printPartInfo(String sPartName, int iLineasTotales, int iNumberOfItems,
                    int iLineasBase, int iLineasBorradas, int iLineasModificadas,
                    int iLineasAgregadas, String sTipo){

        if(sTipo == "base"){
            System.out.println("\t" + sPartName + ": T=" + iLineasTotales +
                            ", I=" + iNumberOfItems + ", B=" + iLineasBase + ", D=" +
                            iLineasBorradas + ", M=" + iLineasModificadas + ", A=" +
                            iLineasAgregadas);

        } else if (sTipo == "nueva"){
            System.out.println("\t" + sPartName + ": " + "T=" + iLineasTotales +
                            " I=" + iNumberOfItems + " B=" + iLineasBase);

        } else if (sTipo == "reusada"){
            System.out.println("\t" + sPartName + ": " + "T=" + iLineasTotales +
                            " I=" + iNumberOfItems + " B=" + iLineasBase);
        } else {
            System.out.println("ERROR CON EL TIPO DE PARTE");
        }
    }
}
