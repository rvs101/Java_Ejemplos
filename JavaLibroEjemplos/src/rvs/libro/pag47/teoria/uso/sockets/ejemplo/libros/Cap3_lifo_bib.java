package rvs.libro.pag47.teoria.uso.sockets.ejemplo.libros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * 13 sept 2023 - 11:30:47
 *
 * @author RVS
 *
 */

public class Cap3_lifo_bib {
// Declaramos una variables globales a este tipo de datos
	public static int PORT = 4567;
	public static String HOST = "127.0.0.1";

	public static void main(String[] args) {
		System.err.println("Intentando conectar con la asistente");
		Socket skt = (Socket) null;
		try {
			skt = new Socket(HOST, PORT);
		} catch (Exception ex) {
			System.err.println("La asistente no esta en linea");
			System.exit(-1);
		}

		String titulo;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			PrintWriter datos = new PrintWriter(skt.getOutputStream());
			System.err.println("Nos conectamos con la asistente:" + HOST + ":" + PORT);
			System.err.println("Ingrese Títulos (linea vacia termina)");
			while (true) {
				if ((titulo = leerLinea(teclado)).length() == 0) {
					System.err.println("Programa terminado");
					datos.println("fin");
					datos.flush();
					datos.close();
					System.exit(0);
				} else {
					datos.println(titulo);
					datos.flush();
				}
			}
		} catch (IOException ex1) {
			ex1.printStackTrace(System.err);
		}

	} // fin del metodo principal

	/**
	 * Funciones o metodos auxiliares
	 * 
	 * @param teclado
	 * @return
	 */
	private static String leerLinea(BufferedReader buff) {
		try {
			return buff.readLine();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
		return "";
	} // final de la funcion leer

} // final de la clase
