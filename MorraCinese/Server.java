import java.net.*;
import java.io.*;

public class Server {

	// Il Server rende disponibile la porta 8070
	public final static int PORT = 8070;

	public static void main(String[] args) throws IOException {

		// creo socket Server e socket Client
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket clientSocket = null;
		try {
			while (true) {
				// il Server è in attesa e accetta una richiesta dal client (si stabilisce la
				// connessione tra client e server)
				clientSocket = serverSocket.accept();
				System.out.println("Connesione stabilita: " + clientSocket);

				// crea un oggetto Server Thread
				try {

					new ServerThread(clientSocket);
					// start nel costruttore
				} catch (IOException e) {
					clientSocket.close();
				}
			}

		} catch (IOException e) {
			System.err.println("Errore di I/O...");
		}

		// chiudo socket
		clientSocket.close();
		serverSocket.close();
		System.out.println("Server chiuso.");

	}
}
