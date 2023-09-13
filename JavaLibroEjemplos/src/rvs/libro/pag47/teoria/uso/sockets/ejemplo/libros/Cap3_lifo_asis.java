package rvs.libro.pag47.teoria.uso.sockets.ejemplo.libros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
* <p>Título: Aprendiendo Java</p>
* <p>Descripción: Ejemplos del Libro Aprendiendo Java de Compunauta</p>
* <p>Copyright: Copyright (c) 2006 www.compunauta.com</p>
* <p>Empresa: COMPUNAUTA</p>
* @author Gustavo Guillermo Pérez
* @version 2006.01.01
*/

/**
 * LIFO<br>
 * <br>
 * Last In First Out - STACK<br>
 * <br>
 * 18 ago 2023 - 19:03:32 <br>
 * 30 ago 2023 - 23:24:02
 *
 * @author RVS
 *
 */
public class Cap3_lifo_asis {
//	Declaramos unas variables globales a este tipo de datos
	public static int PORT = 4567;
	public static int BUFF_SIZE = 24;
	public static int TIMER_SLEEP = 60 * 1000; // 60x1000ms
	public static int buff_elem = 0;
	public static String[] buffer = new String[BUFF_SIZE];

	public static void main(String[] args) {
// Declaramos la variable socket ( sera un puntero a objeto)
		Socket skt = (Socket) null;
// Declaramos vacio el servidor de sockets para inicializarlo
		ServerSocket Ss = (ServerSocket) null;
// Tratamos de escuchar el puerto definido por la variable PORT - Abrimos el puerto para escuchar conexiones
		System.err.println("Escuchando el puerto :" + PORT);
		try {
			Ss = new ServerSocket(PORT);
		} catch (IOException ioe) {
			System.out.println("El sistema no permite el puerto");
			System.exit(-1); // Salir de la aplicación
//			Si no ocurrio error arriba entonces esperamos a la secretaria
		}

		System.err.println("Esperando conexión");
		
		try {
			skt = Ss.accept();
		} catch (IOException ex1) {
			ex1.printStackTrace(System.err);
			System.exit(-1); // Salir de la aplicación
		}

//		Si no ocurrio error arriba la secretaria esta lista para enviar
		System.out.println("Conectado... Esperando titulos");

		try {
//			
			BufferedReader datos = new BufferedReader(new InputStreamReader((skt.getInputStream())));
			
// Crear temporizador de 1 minuto			
			long timer = 0;
			boolean timer_on = false;
			boolean ultimo = false; // cuando los libros son todos

			while (true) {
				if (!ultimo && (skt.isClosed()
						|| (buff_elem > 0) && buffer[buff_elem - 1] != null && buffer[buff_elem - 1].equals("fin"))) {
//					Terminamos el programa si la bibliotecaria terminó
					System.err.println("No hay mas, nos vamos cuando terminemos...");
//					el libro fin no se debe guardar es el aviso
					buff_elem--;
					ultimo = true;
				}
//				Si hay titulos los guardamos
				if (!ultimo && datos.ready()) {
					put_tit(datos.readLine());
				}
				if (timer_on) {
//					si el timer funciona no hacer nada , si se pasó pararlo
					if ((timer + TIMER_SLEEP) < System.currentTimeMillis()) {
						timer_on = false;
					} else {
//						Si el timer esta apagado , mostramos un titulo si es que hay 
						if (buff_elem > 0) {
							System.out.println("Libro :" + get_tit());
							timer_on = true;
							timer = System.currentTimeMillis();
						}
					}
				}
				try {
//					Pausamos 100ms para no sobrecargar el procesador
					Thread.sleep(100);
				} catch (InterruptedException ex3) {
				}
			} // fin del bloque eterno
		} catch (IOException ex2) {
			ex2.printStackTrace(System.err);
			System.exit(-1);
		}
	} // fin del metodo principal

	/**
	 * Funciones o metodos auxiliares
	 * 
	 * @param tit 
	 */
	public static void put_tit(String tit) {
// Si se supera el espacio producir un error
		if (BUFF_SIZE < (buff_elem + 1)) {
			System.err.println("Buffer overrun: El buffer se llenó demasiado rápido");
			System.exit(-1);
		}
//		Guardamos el tit y aumentamos en uno el contador
		buffer[buff_elem++] = tit;
	}

	/**
	 * Quitamos uno al contador de elementos
	 * 
	 * @return String - devolvemos el último libro
	 */
	public static String get_tit() {
//		quitamos uno al contador de elementos
//		devolvemos el último libro
		return buffer[--buff_elem];
	}

}