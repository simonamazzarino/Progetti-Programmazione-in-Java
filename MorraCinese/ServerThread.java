import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;

		// crea stream di input e di output
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

		// lancia il thread
		start();

	}

	// ovverride di run()
	public void run() {
		System.out.println("Connessione al client riuscita"); // comunico l'avvenuta connessione con il client
		System.out.println("");
		String mossaClient;
		String mossaServer = null;
		// OPZIONALE: contatore usato per fermare il gioco dopo 100 giocate
		int contatore = 0;

		// il Server genera random la sua mossa
		int mossa = (int) (Math.random() * 3);

		try {
			while (true) {

				switch (mossa) {
				case 0:
					mossaServer = "sasso";
					System.out.println("Il Server gioca: " + mossaServer);
					// comunico al Client la mossa del Server
					output.println("Il Server gioca: " + mossaServer);
					break;
				case 1:
					mossaServer = "carta";
					System.out.println("Il Server gioca: " + mossaServer);
					output.println("Il Server gioca: " + mossaServer);
					break;
				case 2:
					mossaServer = "forbice";
					System.out.println("Il Server gioca: " + mossaServer);
					output.println("Il Server gioca: " + mossaServer);
					break;
				}

				// legge la mossa generata dal Client
				mossaClient = input.readLine();

				// confronta la mossa del Client e del Server e genera l'esito della giocata
				if (mossaClient.equals("sasso") && mossaServer.equals("carta")) {
					// comunica l'esito della giocata al Client
					output.println("Congratulazioni! Ha vinto il Server!");
					// creo una nuova giocata
					mossa = (int) (Math.random() * 3);
					// OPZIONALE: incremento il contatore usato per fermare il gioco dopo 100
					// giocate
					contatore++;
				}

				else if (mossaClient.equals("sasso") && mossaServer.equals("forbice")) {
					output.println("Congratulazioni! Ha vinto il Client!");
					mossa = (int) (Math.random() * 3);
					contatore++;
				}

				else if (mossaClient.equals("sasso") && mossaServer.equals("sasso")) {
					output.println("Pari! Non ha vinto nessuno.");
					mossa = (int) (Math.random() * 3);
					contatore++;
				}

				else if (mossaClient.equals("carta") && mossaServer.equals("carta")) {
					output.println("Pari! Non ha vinto nessuno.");
					mossa = (int) (Math.random() * 3);
					contatore++;
				}

				else if (mossaClient.equals("carta") && mossaServer.equals("forbice")) {
					output.println("Congratulazioni! Ha vinto il Server!");
					mossa = (int) (Math.random() * 3);
					contatore++;
				}

				else if (mossaClient.equals("carta") && mossaServer.equals("sasso")) {
					output.println("Congratulazioni! Ha vinto il Client!");
					mossa = (int) (Math.random() * 3);
					contatore++;

				}

				else if (mossaClient.equals("forbice") && mossaServer.equals("carta")) {
					output.println("Congratulazioni! Ha vinto il Client!");
					mossa = (int) (Math.random() * 3);
					contatore++;
				}

				else if (mossaClient.equals("forbice") && mossaServer.equals("forbice")) {
					output.println("Pari! Non ha vinto nessuno.");
					mossa = (int) (Math.random() * 3);
					contatore++;
				}

				else if (mossaClient.equals("forbice") && mossaServer.equals("sasso")) {
					output.println("Congratulazioni! Ha vinto il Server!");
					mossa = (int) (Math.random() * 3);
					contatore++;

				}

				// OPZIONALE: il codice sottostante serve per non far durare le giocate
				// all'infinito.

				// Se le giocate sono uguali a 100, il Server smette di giocare
				/*
				 * if (contatore == 100) { output.println("ATTENZIONE!");
				 * output.println("Il Server non ha più giocate disponibili."); break;
				 * 
				 * }
				 */

			}

		} catch (IOException e) {
			System.err.println("Errore di I/O...");
		}

		// chiude socket
		try {
			socket.close();

		}

		catch (IOException e) {
			System.err.println("Errore di I/O...");
		}

	}

}
