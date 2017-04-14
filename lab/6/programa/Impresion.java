//&p-Impresion
//&b=8
import java.io.*;
import java.util.*;
import java.text.*;


public class Impresion {

	//&i
	public static String Formatear(String sPatron, Double dValor){
		DecimalFormat format = new DecimalFormat(sPatron);
		String sOutput = format.format(dValor);
		return sOutput;
	}

}
