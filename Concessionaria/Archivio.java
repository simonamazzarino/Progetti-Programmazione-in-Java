//classe che include tutti i metodi utili per la gestione dell'archivio

import java.util.Vector;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;

//Archivio è un "contenitore" di diversi archivi
public class Archivio {
	// archivio delle auto nuove e usate
	private Vector<Automobile> archivio;
	// archivio delle auto usate
	private Vector<Automobile> archivio_usate;
	// archivio delle auto nuove
	private Vector<Automobile> archivio_nuove;
	// archivio delle auto usate immatricolate dopo un anno dato deciso dall'utente
	// e con più di tot chilometri
	private Vector<Automobile> usato_anno_km;
	// archivio delle auto di un proprietario
	private Vector<Automobile> proprietario_auto;

//costruttore della classe Archivio	
	public Archivio() {
		this.archivio = new Vector<Automobile>();
		this.archivio_usate = new Vector<Automobile>();
		this.archivio_nuove = new Vector<Automobile>();
		this.usato_anno_km = new Vector<Automobile>();
		this.proprietario_auto = new Vector<Automobile>();

	}

	// metodo che restituisce l'archivio delle auto nuove e usate
	public Vector<Automobile> getArchivio() {
		return archivio;
	}

//metodo per inserire una nuova automobile	
	public void inserisci(Automobile a) {
		// controlla che l'auto non sia già presente in archivio
		if (!presentePerTarga(a))
			archivio.add(a);

	}

//metodo per vedere se un'automobile è già presente in archivio	confrontando le targhe usando il metodo equals della classe Automobile
	public boolean presentePerTarga(Automobile a) {
		return archivio.contains(a);

	}

//metodo per visualizzare le auto nuove e quelle usate	
	public void visualizza() {
		// comparatore che organizza le auto in archivio secondo il numero di targa (per
		// ordine alfanumerico)
		Comparator<Automobile> comparator = new AutoTargaComparator();
		Collections.sort(archivio, comparator);
		for (Automobile a : archivio) {
			System.out.println(a);
			System.out.println();
			System.out.println("-------------------");
		}
	}

// metodo per visualizzare sole le auto usate	
	public void visualizza_usato() {
		for (Automobile a : archivio) {
			// controllo che a sia un oggetto di tipo Automobile_usata
			if (a instanceof Automobile_usata) {
				// aggiungo l'auto all'archivio delle auto usate
				archivio_usate.addElement(a);
			}
		}
		if (archivio_usate.isEmpty()) {
			System.out.println("Non ci sono macchine usate");
			System.out.println("-------------------");
			System.out.println();
		} else {
			for (Automobile a : archivio_usate) {
				System.out.println(a);
				System.out.println();
				System.out.println("-------------------");
			}
			// elimino tutte le auto in archivio_usate
			archivio_usate.clear();
		}
	}

//	metodo per visualizzare solo le auto nuove
	public void visualizza_nuovo() {
		for (Automobile a : archivio) {
			// controllo che a sia un oggetto di tipo Automobile_nuova
			if (a instanceof Automobile_nuova) {
				// aggiungo l'auto all'archivio delle auto nuove
				archivio_nuove.addElement(a);
			}
		}
		if (archivio_nuove.isEmpty()) {
			System.out.println("Non ci sono macchine nuove");
			System.out.println();
			System.out.println("-------------------");
		} else {
			for (Automobile a : archivio_nuove) {
				System.out.println(a);
				System.out.println();
				System.out.println("-------------------");
			}
			// elimino tutte le auto in archivio_nuove
			archivio_nuove.clear();
		}
	}

//metodo per ricercare le auto di un proprietario (con nome e cognome)
	public void ricerca_per_nome(String n) {
		for (Automobile a : archivio) {
			// controllo che sia un'auto nuova
			if (a instanceof Automobile_nuova) {
				// controllo che il nome del proprietario dell'auto, o una porzione di esso,
				// dato in input dall'utente corrisponda a un proprietario in archivio
				if (((Automobile_nuova) a).getProprietario() == n
						|| ((Automobile_nuova) a).getProprietario().contains(n)) {
					// aggiungo l'auto all'archivio delle auto appartenenti ad un dato proprietario
					proprietario_auto.addElement(a);
				}
			}

		}
		if (proprietario_auto.isEmpty()) {
			System.out.println("Nessuna macchina appartiene a questo proprietario.");
			System.out.println();
			System.out.println("-------------------");
		} else {
			for (Automobile a : proprietario_auto) {
				System.out.println(a);
				System.out.println();
				System.out.println("-------------------");
			}
			// elimino tutte le auto in proprietario_auto
			proprietario_auto.clear();
		}
	}

//metodo per ricercare e visualizzare tutte le auto immatricolate il o dopo un anno e al più un tot di chilometri	
	public void ricerca_auto_immatricolate(int anno, int km) {
		for (Automobile a : archivio) {
			// controllo che sia un'auto usata
			if (a instanceof Automobile_usata) {
				// controllo se ci sono auto in archivio auto con l'anno di immatricolazione
				// dato come input (o posteriore) e con tot chilometri dati in input (o più)
				if ((((Automobile_usata) a).getImmatricolazione() >= anno)
						&& ((Automobile_usata) a).getChilometri() <= km) {
					// aggiungo le auto all'archivio delle auto usate immatricolate dopo un anno
					// dato deciso dall'utente e con più di tot chilometri
					usato_anno_km.addElement(a);
				}
			}

		}

		if (usato_anno_km.isEmpty()) {
			System.out.println(
					"Non ci sono macchine immatricolate il o dopo il " + anno + " e con " + km + " o più chilometri.");
			System.out.println();
			System.out.println("-------------------");
		} else {
			for (Automobile a : usato_anno_km) {
				System.out.println(a);
				System.out.println();
				System.out.println("-------------------");
			}
			// elimino tutte le auto in usato_anno_km
			usato_anno_km.clear();
		}

	}

//metodo per ricercare e visualizzare la prossima auto nuova da consegnare (quella con la data di consegna minore)	
	public void ricerca_prossima_consegna() {
		for (Automobile a : archivio) {
			// controllo che sia un'automobile nuova
			if (a instanceof Automobile_nuova) {
				// aggiungo l'auto all'archivio delle auto nuove
				archivio_nuove.addElement(a);
			}
		}

		if (archivio_nuove.isEmpty()) {
			System.out.println("Non ci sono macchine nuove da consegnare.");
			System.out.println("-------------------");
			System.out.println();
		}

		else {
			// organizzo le auto nuove in archivio_nuove secondo la loro data di consegna
			Comparator<Automobile> comparator = new AutoDataComparator();
			Collections.sort(archivio_nuove, comparator);
			// restituisco il primo elemento dell'archivio ordinato
			System.out.println(archivio_nuove.firstElement());
			System.out.println();
			System.out.println("-------------------");
		}
		// elimino tutte le auto in archivio_nuove
		archivio_nuove.clear();

	}

//metodo per controllare che la targa inserita dall'utente sia formata da due lettere, tre numeri e due lettere (formato italiano)
	public boolean targaOK(String str) {
		boolean charOK = false;
		boolean numOK = false;
		char[] caratteri = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'J', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] numeri = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		if (str.length() == 7) {
			for (char a : caratteri) {
				if (str.charAt(0) == a) {
					for (char b : caratteri) {
						if (str.charAt(1) == b) {
							for (char c : caratteri) {
								if (str.charAt(5) == c) {
									for (char d : caratteri) {
										if (str.charAt(6) == d) {
											charOK = true;
										}
									}
								}
							}
						}
					}
				}
			}
			for (char x : numeri) {
				if (str.charAt(2) == x) {
					for (char y : numeri) {
						if (str.charAt(3) == y) {
							for (char z : numeri) {
								if (str.charAt(4) == z) {
									numOK = true;
								}
							}
						}
					}
				}
			}

			if (charOK && numOK) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	// metodo per salvare l'archivio delle auto nuove e usate su file binario con il
	// metodo della serializzazione a oggetti
	public void salva() {
		try {
			FileOutputStream out = new FileOutputStream("Archivio");
			ObjectOutputStream s = new ObjectOutputStream(out);
			s.writeObject(archivio);
			s.close();
			out.close();
		} catch (IOException e) {
			System.out.println("ERRORE di I/O: impossibile salvare il file.");
		}
	}

	// metodo per caricare l'archivio delle auto nuove e usate da file binario
	public void carica() {
		try {
			FileInputStream in = new FileInputStream("Archivio");
			ObjectInputStream s = new ObjectInputStream(in);
			archivio = (Vector<Automobile>) s.readObject();
			s.close();
			in.close();

		} catch (FileNotFoundException e) {
			// gestisce il caso in cui il file non sia presente (sarà creato poi...)
			System.out.println("ATTENZIONE: Il file non esiste");
			System.out.println("Sarà creato al primo salvataggio");
			System.out.println();
		} catch (ClassNotFoundException e) {
			// gestisce il caso in cui il file non contenga un oggetto
			System.out.println("ERRORE di lettura");
			System.out.println(e);
		} catch (IOException e) {
			// gestisce altri errori di input/output
			System.out.println("ERRORE di I/O");
			System.out.println(e);
		}
	}

	// metodo usato per impostare la data di consegna di un'automobile nuova che
	// prende come parametro una data di tipo stringa e la converte in tipo
	// LocalDate
	public LocalDate setData(String data) {
		LocalDate data1 = null;
		try {
			// formatta la data in anno-mese-giorno
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			data1 = LocalDate.parse(data, formatter);
			return data1;
			// se la data inserita non è corretta viene inizializzata a null
		} catch (DateTimeParseException e) {
			return null;
		}
	}

}
