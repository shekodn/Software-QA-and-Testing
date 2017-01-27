public class Articulo implements Comparable <Articulo> { 

   private String nombre;
   private int costo;


   public Articulo(){

       this.nombre = "Silla";
       this.costo = 0;
   }


   @Override
   public int compareTo(Articulo o) {
       int comparedSize = o.costo;
       if (this.costo > comparedSize) {
           return 1;
       } else if (this.costo == comparedSize) {
           return 0;
       } else {
           return -1;
       }
   }


   public String getNombre(){

       return nombre;
   }

   public void setNombre(String n){

       this.nombre = n;
   }

   public int getCosto(){
       return costo;
   }

   public void setCosto(int n){

       this.costo = n;

   }


}
