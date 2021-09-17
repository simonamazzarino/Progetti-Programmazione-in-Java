//classe che costruisce oggetti del tipo Automobile_usata estendendo la classe Automobile

public class Automobile_usata extends Automobile {
	private int prima_immatricolazione;
	private int chilometri;

	// costruttore della classe Automobile_usata
	public Automobile_usata(String targa, String modello, int cilindrata, String colore, double prezzo_vendita,
			int prima_immatricolazione, int chilometri) {
		super(targa, modello, cilindrata, colore, prezzo_vendita);
		this.prima_immatricolazione = prima_immatricolazione;
		this.chilometri = chilometri;
	}

	// metodo per stampare un'automobile usata che sovrascrive il metodo toString
	// della classe Automobile
	public String toString() {
		return getTarga() + " (modello: " + getModello() + ", cilindrata: " + getCilindrata() + ", colore: "
				+ getColore() + ", prezzo di vendita: " + getPrezzo() + ", anno di prima immatricolazione: "
				+ prima_immatricolazione + ", chilometri: " + chilometri + ")";
	}

	// metodo che restituisce l'anno di prima immatricolazione
	public int getImmatricolazione() {
		return prima_immatricolazione;
	}

	// metodo che restituisce il numero di chilometri percorsi dall'auto
	public int getChilometri() {
		return chilometri;
	}

}
