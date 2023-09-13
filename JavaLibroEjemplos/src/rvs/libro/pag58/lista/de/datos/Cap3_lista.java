package rvs.libro.pag58.lista.de.datos;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Cap3_lista {

//	Cantidad max de elementos
	public static int MAX = 15;

//  Array de elementos de tipo String
	public static String[] lista = new String[MAX];

//	Indice del Array que ira incrementandose
	public static int lista_elem = 0;

	public static void main(String[] args) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		int op = 1; // almacenará la opción de menu que haya escogido el usuario
		while (true) {
			switch (op) {
			case 1:
				System.out.println("Dato:?");
				ingresa(leerLinea(teclado));
				break;
			case 2:
				listar();
				break;
			case 3:
				System.out.println("Item a borrar:?");
				borrar(opcion(teclado));
				break;
			case 0:
				System.out.println("Terminado..");
				System.exit(0);
				break;
			}
			if (op != 1) {
				imprimir_menu();
			}
			System.out.println("Opción?");
			op = opcion(teclado);
		}
	}

	/**
	 * Función auxiliar <br>
	 * <br>
	 * Antes sde aumentar en uno el contador de elementos se utiliza el valor del
	 * indice para almacenar el valor del dato que desea ingresar a la lista
	 * 
	 * @param dato
	 */
	public static void ingresa(String dato) {
		lista[lista_elem++] = dato;
	}

	/**
	 * Función auxiliar
	 * 
	 */
	public static void listar() {
		for (int i = 0; i < lista_elem; i++) {
			System.out.println("Item[" + i + "]:[" + lista[i] + "]");
		}
	}

	/**
	 * 
	 * @param item
	 */
	public static void borrar(int item) {
		lista_elem--;
		for (int i = item; i < lista_elem; i++) {
			lista[i] = lista[i + 1];
		}
	}

	public static void imprimir_menu() {
		System.out.println("SELECCIONE UNA OPCION");
		System.out.println("1) Ingresar un elemento al listado");
		System.out.println("2) Listar los elementos de la lista");
		System.out.println("3) Borrar un elemento de la lista");
		System.out.println("0) Salir");
	}

	/**
	 * 
	 * @param buff
	 * @return
	 */
	public static int opcion(BufferedReader buff) {
		int lee = 0;
		boolean error;
		do {
			error = false;
			try {
				return lee = Integer.parseInt(buff.readLine());
			} catch (NumberFormatException ex) {
				System.err.println("Entrada erronea, repetir:?");
				error = true;
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		} while (error);
		return lee;
	} // final de la funcion leer

	/**
	 * 
	 * @param buff
	 * @return
	 */
	public static String leerLinea(BufferedReader buff) {
		try {
			return buff.readLine();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
		return "";
	} // final de la funcion leer

} // fin de la clase
