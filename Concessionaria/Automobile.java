import java.io.Serializable;

//classe che costruisce oggetti di tipo Automobile e implementa la classe Serializable

public class Automobile implements Serializable {
	// variabile richiesta da Serializable
	private static final long serialVersionUID = 1L;
	private String targa;
	private String modello;
	private int cilindrata;
	private String colore;
	private double prezzo_vendita;

//costruttore della classe Automobile	
	public Automobile(String targa, String modello, int cilindrata, String colore, double prezzo_vendita) {
		this.targa = targa;
		this.modello = modello;
		this.cilindrata = cilindrata;
		this.colore = colore;
		this.prezzo_vendita = prezzo_vendita;

	}

	// metodo per stampare un'automobile
	public String toString() {
		return targa + " (modello: " + modello + ", cilindrata: " + cilindrata + ", colore: " + colore
				+ ", prezzo di vendita: " + prezzo_vendita + ")";
	}

	// metodo che restituisce la targa
	public String getTarga() {
		return targa;
	}

	// metodo che restituisce il modello
	public String getModello() {
		return modello;
	}

	// metodo che restituisce la cilindrata
	public int getCilindrata() {
		return cilindrata;
	}

	// metodo che restituisce il colore
	public String getColore() {
		return colore;
	}

	// metodo che restituisce il prezzo
	public double getPrezzo() {
		return prezzo_vendita;
	}

	// metodo usato per controllare se una data targa di input è uguale a una targa
	// in archivio
	public boolean equals(Object o) {
		if (o instanceof Automobile) {
			Automobile a = (Automobile) o;
			return targa.equals(a.targa);
		} else {
			return false;
		}

	}

}
