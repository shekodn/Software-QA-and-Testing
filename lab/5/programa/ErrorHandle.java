
/**
 * Clase : ErrorHandle.java
 * 
 * Clase que realiza el manejo de los errores que se tienen 
 * dentro de la clases se llama el error handle cuando se tenga un error dentro del codigo
 *  
 * 
 * @author AlejandroSanchez
 *@date 27/9/2015
 *@version 1.0
 *
 */

//&p-ErrorHandle
//&b=17
public class ErrorHandle {
	/*
	 * archivoNoEncontrado
	 * Imprime que el archivo no fue encontrado 
	 *
	 */
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
