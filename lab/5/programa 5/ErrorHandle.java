import java.io.*;
import java.util.*;

public class ErrorHandle {

	//&i
	public static void archivoNoEncontrado(){
		System.out.println("Archivo no fue encontrado");
	}
	
	//&i
	public static void extensionIncorrecta(){
		System.out.println("Extension Incorrecta");
	}

	//&i
	public static void lineasNoExistentes(){
		System.out.println("No existen lineas dentro de este archivo");
	}

	//&i
	public static void CuantosArchivos(){
		System.out.println("Cuantos Archivos se van a leer, esto no es correcto");
	}

	//&i
	public static void errorEnLinea(){
		System.out.println("Esta linea tiene algun defecto");
	}

	//&i
	public static void errorEnOutput(){
		System.out.println("No se puede escribir en el archivo");
	}

	//&i
	public static void incorrectDocFormat(){
		System.out.println("Este no es el formato del documento");
	}

}
