//classe utilizzata per comparare le targhe e stampare l'archivio delle auto in ordine di targa

import java.util.Comparator;

public class AutoTargaComparator implements Comparator<Automobile> {
	public int compare(Automobile a1, Automobile a2) {
		return a1.getTarga().compareTo(a2.getTarga());
	}
}
