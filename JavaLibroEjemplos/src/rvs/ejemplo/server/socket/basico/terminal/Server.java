package rvs.ejemplo.server.socket.basico.terminal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase Concreta<br>
 * <br>
 * Para poder ejecutar la prueba se necesita usar el cmd o terminal de Linux
 * <br>
 * 1- Ejecutar el script del Servidor <br>
 * <br>
 * 2- Ejecutar el script del Cliente <br>
 * <br>
 * 3- Escribir mensajes hasta escribir "Over" para terminar el envio de datos
 * <br>
 * 
 * 
 * 8 sept 2023 - 22:36:07
 *
 * @author RVS
 *
 */
public class Server {

	// initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;

	// constructor with port
	public Server(int port) {
		// starts server and waits for a connection
		try {
			server = new ServerSocket(port);
			System.out.println("Server started - Servidor Comenzado");

			System.out.println("Waiting for a client - Esperando a un cliente");

			socket = server.accept();

			System.out.println("Client accepted - Cliente aceptado");

			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over")) {
				try {
					line = in.readUTF();
					System.out.println(line);

				} catch (IOException i) {
					System.out.println(i);
					System.exit(-1);
				}
			}
			
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		} catch (IOException i) {
			System.out.println(i);
			System.exit(-1);
		}
	}

	public static void main(String args[]) {
		Server server = new Server(5000);
	}
}
