import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Principal {

    private Articulo celular;
    private Articulo ipad;
    private Articulo carro;
    private LinkedList <Articulo> lklArticulos;



    public void init(){

        lklArticulos = new LinkedList<Articulo>();

        celular = new Articulo();
        ipad = new Articulo();
        carro = new Articulo();


        lklArticulos.add(celular);
        lklArticulos.add(ipad);
        lklArticulos.add(carro);

        celular.setCosto(999);
        ipad.setCosto(199);
        carro.setCosto(500);


        // sort in ascending order
        Collections.sort(lklArticulos);
        System.out.println(lklArticulos);

        // sort in descending order
        Collections.sort(lklArticulos, Collections.reverseOrder());
        System.out.println(lklArticulos);

    }


    public void caulcula(){
        init();


    }


    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.caulcula();

    }
}


// public static void main(String[] args) {
//     Tetris tetris = new Tetris();
//     tetris.startGame();
// }
