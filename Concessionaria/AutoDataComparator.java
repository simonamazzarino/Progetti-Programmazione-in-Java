//classe utlizzata per comparare le date di consegna delle auto nuove 

import java.util.Comparator;

public class AutoDataComparator implements Comparator<Automobile> {
	public int compare(Automobile a1, Automobile a2) {
		return ((Automobile_nuova) a1).getData().compareTo(((Automobile_nuova) a2).getData());

	}
}
