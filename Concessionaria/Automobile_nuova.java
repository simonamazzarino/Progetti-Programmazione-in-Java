
//classe che costruisce oggetti del tipo Automobile_nuova estendendo la classe Automobile
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Automobile_nuova extends Automobile {

	private LocalDate data_consegna;
	private String nuovo_proprietario;

	// costruttore della classe Automobile_nuova
	public Automobile_nuova(String targa, String modello, int cilindrata, String colore, double prezzo_vendita,
			LocalDate data_consegna, String nuovo_proprietario) {
		super(targa, modello, cilindrata, colore, prezzo_vendita);
		this.data_consegna = data_consegna;
		this.nuovo_proprietario = nuovo_proprietario;
	}

	// metodo per stampare un'automobile nuova che sovrascrive il metodo toString
	// della classe Automobile
	public String toString() {
		return getTarga() + " (modello: " + getModello() + ", cilindrata: " + getCilindrata() + ", colore: "
				+ getColore() + ", prezzo di vendita: " + getPrezzo() + ", data di consegna: " + data_consegna
				+ ", nuovo proprietario: " + nuovo_proprietario + ")";
	}

	// metodo per ottenere la data di consegna dell'Automobile_nuova
	public LocalDate getData() {
		return data_consegna;
	}

	// metodo per ottenere il nome del nuovo proprietario
	public String getProprietario() {
		return nuovo_proprietario;
	}

	// metodo usato per cambiare la data di consegna di un'automobile che prende
	// come parametro una nuova data di tipo stringa e la converte in tipo LocalDate
	public LocalDate setNewData(String nuova_data_consegna) {
		LocalDate data1 = null;
		try {
			// formatta la data in anno-mese-giorno
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			data1 = LocalDate.parse(nuova_data_consegna, formatter);
			this.data_consegna = data1;
			return data1;
			// restituisce null se la data non è conforme al formato stabilito
		} catch (DateTimeParseException e) {
			return null;
		}
	}


}
