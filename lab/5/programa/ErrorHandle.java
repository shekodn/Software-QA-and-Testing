import java.io.*;
import java.util.*;

public class ErrorHandle {

	//&i
	public static void archivoNoEncontrado(){
		System.out.println("Archivo no fue encontrado");
	}
	/*
	 * extensionIncorrecta
	 * Imprime que la extension es incorrecta
	 *
	 */
	//&i
	public static void extensionIncorrecta(){
		System.out.println("Extension Incorrecta");
	}

	/*
	 * LineasNoExistentes
	 * Imprime que las lineas del archivo no existen
	 *
	 */
	//&i
	public static void lineasNoExistentes(){
		System.out.println("No existen lineas dentro de este archivo");
	}
	/*
	 * CuantosArchivos
	 * Imprime la cantidad de archivos que intentas meter no son adecuados
	 *
	 */
	//&i
	public static void CuantosArchivos(){
		System.out.println("Cuantos Archivos se van a leer, esto no es correcto");
	}
	/*
	 * errorEnLinea
	 * Imprime que hay error en la linea
	 *
	 */
	//&i
	public static void errorEnLinea(){
		System.out.println("Esta linea tiene algun defecto");
	}
	/*
	 * errorEnOutput
	 * Error cuando quieres escribir
	 */
	//&i
	public static void errorEnOutput(){
		System.out.println("No se puede escribir en el archivo");
	}

	//&i
	public static void incorrectDocFormat(){
		System.out.println("Este no es el formato del documento");
	}

}
