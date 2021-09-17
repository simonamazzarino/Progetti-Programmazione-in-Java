import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args) throws IOException {

		// dichiaro IP, socket, varibaile di lettura e scrittura
		InetAddress address = InetAddress.getByName(null);
		Socket socket = null;
		BufferedReader input = null;
		PrintWriter output = null;
		// variabile di scrittura su file per tenere conto delle giocate
		PrintWriter printout = null;

		try {
			// costruisco il socket
			socket = new Socket(address, Server.PORT);
			System.out.println("Client avviato.\nSocket: " + socket);
			System.out.println("");

			// creo stream di lettura e di scrittura
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			// file su cui verrano scritte le varie giocate tra il Client e il Server
			printout = new PrintWriter(new BufferedWriter(new FileWriter("ClientvsServer.txt")));

			String mossaClient = null;
			String mossaServer = null;
			String rispostaServer = null;

			// genero random la mossa del Client
			int mossa = (int) (Math.random() * 3);
			while (true) {
				switch (mossa) {
				case 0:
					mossaClient = "sasso";
					System.out.println("Il Client gioca: " + mossaClient);
					printout.println("Il Client gioca: " + mossaClient);
					// rigenero una mossa per continuare a giocare
					mossa = (int) (Math.random() * 3);
					break;
				case 1:
					mossaClient = "carta";
					System.out.println("Il Client gioca: " + mossaClient);
					printout.println("Il Client gioca: " + mossaClient);
					mossa = (int) (Math.random() * 3);
					break;
				case 2:
					mossaClient = "forbice";
					System.out.println("Il Client gioca: " + mossaClient);
					printout.println("Il Client gioca: " + mossaClient);
					mossa = (int) (Math.random() * 3);
					break;

				}

				// comunico al server la mossa del Client
				output.println(mossaClient);
				// ottengo la mossa del Server
				mossaServer = input.readLine();
				System.out.println(mossaServer);
				printout.println(mossaServer);
				// ottengo la risposta del Server
				rispostaServer = input.readLine();
				printout.println(rispostaServer);
				System.out.println(rispostaServer);

				// OPZIONALE: il codice commentato serve per non far durare le giocate
				// all'infinito.

				/*
				 * if (rispostaServer.equals("Il Server non ha più giocate disponibili.")) {
				 * System.out.println(""); printout.println("");
				 * System.out.println("Gioco terminato."); printout.println("Gioco terminato.");
				 * System.out.println(""); printout.println(""); break; }
				 */

			}

		} catch (UnknownHostException e) {
			System.err.println("Indirizzo sconosciuto: " + address);
		} catch (IOException e) {
			System.err.println("Errore di I/0...");
		}

		// chiudo tutto
		input.close();
		output.close();
		printout.close();
		socket.close();
		System.out.println("Client chiuso.");

	}
}
