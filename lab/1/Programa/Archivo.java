public class Archivo implements Comparable <Archivo> {

    private String sName;
    private int iBlankLines;
    private int iLines;


    /**
    * empty constructor for file
    */
    public Archivo(){
        this.sName = "no name";
        this.iBlankLines = 0;
        this.iLines = 0;

    }

    // public Archivo copyArchivo (Archivo archivo){
    //     Archivo a = new Archivo();
    //     //for all properties in FOo
    //     a.set(archivo.get());
    //     return a;
    // }

    /**
    * Copy constructor.
    */
    // public Archivo(Archivo aArchivo) {
    //     this(aArchivo.getName(), aArchivo.getBlankLines(), aArchivo.getLines());
    //     //no defensive copies are created here, since
    //     //there are no mutable object fields (String is immutable)
    // }

    // copy constructor
    Archivo(Archivo a) {
        System.out.println("Copy constructor called");
        sName = a.getName();
        iLines = a.getLines();
        iBlankLines = a.getBlankLines();

    }





    public Archivo(String sName, int iBlankLines, int iLines){
        this.sName = sName;
        this.iBlankLines = iBlankLines;
        this.iLines = iLines;
    }

    /* Getters and setters */

    /**
    * [gets file name]
    * @return [sName]
    */
    public String getName(){
        return sName;
    }

    /**
    * Changes the name of the file
    * @param String sName [variable to change the name of the file]
    */
    public void setName(String sName){
        this.sName = sName;
    }

    /**
    * [getBlankLines of file]
    * @return [number of blank lines]
    */
    public int getBlankLines(){

        return iBlankLines;
    }

    /**
    * [setBlankLines]
    * @param int iN [number of blank lines]
    */
    public void setBlankLines(int iN){
        this.iBlankLines = iN;
    }

    /**
    * [get number of lines with information]
    * @return [number of lines with info]
    */
    public int getLines(){

        return iLines;
    }


    /**
    * sets number of blank lines in file
    * @param int iN [number of blank lines]
    */
    public void setLines(int iN){
        this.iLines = iN;
    }

    /* Special funcitons */


    public void resetValues(){

        this.sName = "reset name";
        this.iBlankLines = 0;
        this.iLines = 0;
    }

    /**
    * [compareTo description]
    * @param  Archivo o [file to compare with]
    * @return This method is to sort according to number of lines with
    * information (min to max)
    */

    //@Override
    public int compareTo(Archivo o) {
        int comparedSize = o.iLines;
        if (this.iLines > comparedSize) {
            return 1;
        } else if (this.iLines == comparedSize) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
    * Prints file description in the desired format
    */
    public void printFileData(){
        System.out.println("");
        System.out.println("Nombre del archivo: " + this.sName);
        System.out.println("Cantidad de líneas en blanco " + this.iBlankLines);
        System.out.println("Cantidad de líneas con información " + this.iLines);
        System.out.println("-------------------------------------------------");
    }
}
