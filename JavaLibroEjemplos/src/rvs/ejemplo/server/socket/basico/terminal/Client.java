package rvs.ejemplo.server.socket.basico.terminal;

//A Java program for a Client
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <br>
 * Clase Concreta <br>
 * 
 * <br>
 * 1º Abrir la terminal <br>
 * <br>
 * 2º Ejecutar el código de la clase Server - java Server <br>
 * <br>
 * 3º Ejecutar el código de la clase Client - java Client <br>
 * <br>
 * 8 sept 2023 - 23:09:36 <br>
 *
 * @author RVS
 *
 */
public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private BufferedReader input = null;
	private DataOutputStream out = null;

	// constructor to put ip address and port
	public Client(String address, int port) {
		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
			input = new BufferedReader(new InputStreamReader(System.in));

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException u) {
			System.out.println(u);
			return;
		} catch (IOException i) {
			System.out.println(i);
			return;
		}

		// string to read message from input
		String line = "";

		// keep reading until "Over" is input
		while (!line.equals("Over")) {
			try {
				line = input.readLine();
				out.writeUTF(line);
			} catch (IOException i) {
				System.out.println(i);
				System.exit(-1);
			}
		}

		// close the connection
		try {
			input.close();
			out.close();
			socket.close();
		} catch (IOException i) {
			System.out.println(i);
			System.exit(-1);
		}
	}

	public static void main(String args[]) {
		Client client = new Client("127.0.0.1", 5000);
	}
}
