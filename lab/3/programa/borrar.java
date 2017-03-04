import java.io.*;
import java.util.*;

class borrar{

    public static void main(String[] args) {

        String coord = "48,91.8";

        int iComa = coord.indexOf(',');
        String sX = coord.substring(0, iComa);
        String sY = coord.substring(iComa + 1);

        double dX = Double.parseDouble(sX);
        double dY = Double.parseDouble(sY);

        System.out.println(iComa);
        System.out.println(sX);
        System.out.println(sY);
        System.out.println(dX + dY);
    }
}
