import java.util.*;
import java.time.*;

public class Concessionaria {

	public static void main(String[] args) {

		// creo un oggetto della classe archivio
		Archivio archivio = new Archivio();
		// carico archivio con alcune auto già salvate
		archivio.carica();

		String selezione;
		String selezione1;

		// ciclo che avvia il menù almeno una volta e continua ad avviarlo finché
		// l'utente non inserisce "u"
		do {
			// chiede all'utente che azione vuole fare
			System.out.println("Benvenuto nella nostra concessionaria, cosa desideri fare?");
			System.out.println("Digita una delle seguenti opzioni:");
			System.out.println();
			System.out.println("[a]: aggiungere un'auto all'archivio;");
			System.out.println("[r]: rimuovere un'auto dall'archivio;");
			System.out.println("[v]: visualizza l'elenco di tutte le auto in archivio;");
			System.out.println("[vn]: visualizza l'elenco di tutte le auto nuove in archvio;");
			System.out.println("[vu]: visualizza l'elenco di tutte le auto usate in archivio;");
			System.out.println("[m]: modifica la data di consegna di un auto nuova;");
			System.out.println("[rn]: ricerca le auto acquistate da un certo cliente;");
			System.out.println("[rak]: ricerca tutte le auto usate immatricolate dopo un certo anno e con al più un certo numero di chilometri;");
			System.out.println("[rc]: ricerca la prossima auto da consegnare;");
			System.out.println("[s]: salva l'archivio su file;");
			System.out.println("[u]: esci dalla concessionaria.");

			Scanner input = new Scanner(System.in);
			Scanner input1 = new Scanner(System.in);
			Scanner input2 = new Scanner(System.in);
			Scanner input3 = new Scanner(System.in);

			// scelta dell'utente
			selezione = input.next();
			// conversione dell'input in lower case
			selezione1 = selezione.toLowerCase();

			switch (selezione1) {
			// permette all'utente di aggiungere un'auto
			case "a":
				String auto1;
				// contatore del numero di tentativi in cui l'utente prova ad aggiungere un'auto
				int contAggiungi = 0;
				do {
					// l'utente può scegliere se inserire un'auto nuova o un'auto usata
					System.out.println("Vuoi inserire un'auto nuova o un'auto usata?");
					System.out.println(
							"Digita [n] se vuoi aggiungere un'auto nuova, digita [u] se vuoi aggiungere un'auto usata");
					String auto = input.next();
					auto1 = auto.toLowerCase();

					// se l'utente non ha inserito "n" o "u" aumento il contatore di 1
					if ((!auto1.equals("n")) && (!auto1.equals("u"))) {
						contAggiungi++;
						// se il contatore arriva a 4, l'utente viene rimandato al menù principale
						if (contAggiungi == 4) {
							System.out.println("Hai esaurito i tentativi.");
							System.out.println("Verrai indirizzato al menù principale.");
							System.out.println("-------------------");
							System.out.println();
						}
						// se invece il contatore è minore di 4, si avverte l'utente che il valore
						// inserito non è valido
						else {
							System.out.println("Il valore inserito non è valido.");
							System.out.println("Ritenta...");
							System.out.println("-------------------");
							System.out.println();

						}
					}
					// se l'utente sceglie di inserire un'auto nuova
					else if (auto1.equals("n")) {
						// chiedo all'utente di inserire la targa
						String targa;
						String newTarga;
						String targaOK;
						// contatore del numero di tentativi in cui l'utente prova a inserire un numero
						// di targa
						int contTarga = 0;
						do {
							System.out.println("Inserisci il numero di targa nel formato AA000AA");
							targa = input1.nextLine();
							newTarga = targa.toUpperCase();
							// controllo che la targa sia corretta con il metodo targaOK
							// se l'utente non ha inserito una targa corretta aumento il contatore di 1 e
							// inizializzo la targa a null
							if (!archivio.targaOK(newTarga)) {
								contTarga++;
								targaOK = null;
								// se il contatore arriva a 4, l'utente viene rimandato al menù principale
								if (contTarga == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								}
								// se invece il contatore è minore di 4, si avverte l'utente che il valore
								// inserito non è valido
								else {
									System.out.println("La targa inserita non è valida.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}

								// se la targa inserita è corretta assegno il valore dato in input (newTarga)
								// alla variabile targaOK
							} else {
								targaOK = newTarga;
								break;
							}

						} while ((!archivio.targaOK(newTarga)) && (contTarga < 4));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contTarga == 4) {
							break;
						}

						// chiedo all'utente di inserire il modello
						String modello;
						// contatore del numero di tentativi in cui l'utente prova a inserire il modello
						int contModello = 0;
						do {
							System.out.println("Inserisci il modello");
							modello = input1.nextLine();
							// controllo che l'utente non abbia inserito una stringa vuota
							if (modello == "") {
								contModello++;
								if (contModello == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("ATTENZIONE: devi inserire il modello.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
						} while ((modello == "") && (contModello < 4));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contModello == 4) {
							break;
						}

						// chiedo all'utente di inserire la cilindrata
						int cilindrata = 0;
						boolean ok;
						// contatore del numero di tentativi in cui l'utente prova a inserire la
						// cilindrata
						int contCilindrata = 0;
						do {
							do {
								ok = true;
								// controllo che l'utente abbia inserito un intero
								try {
									System.out.println("Inserisci la cilindrata in centrimetri cubi");
									cilindrata = input.nextInt();
									if (cilindrata <= 0) {
										contCilindrata++;
										if (contCilindrata == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}

									// se l'utente non ha inserito un intero il ciclo entra nel gestore delle
									// eccezioni
								} catch (InputMismatchException e) {
									contCilindrata++;
									if (contCilindrata == 4) {
										System.out.println("Hai esaurito i tentativi.");
										System.out.println("Verrai indirizzato al menù principale.");
										System.out.println("-------------------");
										System.out.println();
									} else {
										input.nextLine(); // annulla l’input ricevuto
										System.out.println("Il valore inserito non è valido.");
										System.out.println("Ritenta...");
										System.out.println("-------------------");
										System.out.println();
										ok = false;
									}
								}

							} while ((!ok) && (contCilindrata < 4));
						} while ((cilindrata <= 0) && (contCilindrata < 4));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contCilindrata == 4) {
							break;
						}

						// chiede all'utente di inserire il colore
						String colore;
						// contatore del numero di tentativi in cui l'utente prova a inserire il colore
						int contColore = 0;
						do {
							System.out.println("Inserisci il colore");
							colore = input1.nextLine();
							// controllo che l'utente non abbia inserito una stringa vuota
							if (colore == "") {
								contColore++;
								if (contColore == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("ATTENZIONE: devi inserire il modello.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
						} while ((colore == "") && (contColore < 4));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contColore == 4) {
							break;
						}

						// chiede all'utente di inserire il prezzo
						double prezzo = 0;
						boolean ok1;
						// contatore del numero di tentativi in cui l'utente prova a inserire il prezzo
						int contPrezzo = 0;
						do {
							do {
								ok1 = true;
								// controllo che l'utente abbia un double
								try {
									System.out.println("Inserisci il prezzo di vendita");
									prezzo = input.nextDouble();
									if (prezzo <= 0) {
										contPrezzo++;
										if (contPrezzo == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}
									// se l'utente non ha inserito un double il ciclo entra nel gestore delle
									// eccezioni
								} catch (InputMismatchException e) {
									contPrezzo++;
									if (contPrezzo == 4) {
										System.out.println("Hai esaurito i tentativi.");
										System.out.println("Verrai indirizzato al menù principale.");
										System.out.println("-------------------");
										System.out.println();
									} else {
										input.nextLine(); // annulla l’input ricevuto
										System.out.println("Il valore inserito non è valido.");
										System.out.println("Ritenta...");
										System.out.println("-------------------");
										System.out.println();
										ok1 = false;
									}
								}

							} while ((!ok1) && (contPrezzo < 4));
						} while ((prezzo <= 0) && (contPrezzo < 4));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contPrezzo == 4) {
							break;
						}

						String data1;
						LocalDate data;
						// contatore del numero di tentativi in cui l'utente prova a inserire la data
						int contData = 0;
						do {
							System.out.println("Inserisci la data, in formato YYYY-MM-DD");
							data1 = input.next();
							// si imposta la data usando il metodo setData
							data = archivio.setData(data1);
							if (data == null) {
								contData++;
								data = null;
								if (contData == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("Il valore inserito non è valido.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
							// controllo che la data inserita sia posteriore alla data odierna
							else if (data.isBefore(LocalDate.now())) {
								contData++;
								data = null;
								if (contData == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println(
											"La data che hai inserito deve essere posteriore alla data odierna");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();

								}
							}
						} while (((data == null) && (contData < 4))
								|| ((data.isBefore(LocalDate.now()) && (contData < 4))));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contData == 4) {
							break;
						}

						// chiedo all'utente di inserire il nome del proprietario
						String nome;
						String newNome;
						// contatore del numero di tentativi in cui l'utente prova a inserire il nome
						int contNome = 0;
						do {
							System.out.println("Inserisci il nome e il cognome del proprietario");
							nome = input3.nextLine();
							newNome = nome.toUpperCase();
							// controllo che l'utente non abbia inserito una stringa vuota
							if (newNome == "") {
								contNome++;
								if (contNome == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("ATTENZIONE: devi inserire il nome del proprietario.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
						} while ((newNome == "") && (contNome < 4));

						// se l'utente ha esaurito i tentativi viene rimandato al menù principale
						if (contNome == 4) {
							break;
						}

						// creo oggetto della classe Automobile_nuova
						Automobile a = new Automobile_nuova(targaOK, modello, cilindrata, colore, prezzo, data,
								newNome);
						// controllo che l'auto non sia già presenta in archivio usando la targa. Se
						// presente non aggiungo l'auto.
						if (!archivio.presentePerTarga(a)) {
							archivio.inserisci(a);
							System.out.println("L'auto è stata inserita correttamente.");
							System.out.println();
							archivio.visualizza();
						} else {
							System.out.println("ATTENZIONE: esiste già un'auto con questa targa.");
							System.out.println("Impossibile aggiungere l'automobile all'archivio.");
							System.out.println("-------------------");
							System.out.println();
							archivio.visualizza();
						}

						// se l'utente sceglie di inserire un'auto usata
					} else if (auto1.equals("u")) {
						String targa;
						String newTarga;
						String targaOK;
						int contTarga = 0;
						do {
							System.out.println("Inserisci il numero di targa nel formato AA000AA");
							targa = input1.nextLine();
							newTarga = targa.toUpperCase();
							if (!archivio.targaOK(newTarga)) {
								contTarga++;
								targaOK = null;
								if (contTarga == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("La targa inserita non è valida.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							} else {
								targaOK = newTarga;
								break;
							}

						} while ((!archivio.targaOK(newTarga)) && (contTarga < 4));
						if (contTarga == 4) {
							break;
						}

						String modello;
						int contModello = 0;
						do {
							System.out.println("Inserisci il modello");
							modello = input1.nextLine();
							if (modello == "") {
								contModello++;
								if (contModello == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("ATTENZIONE: devi inserire il colore.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
						} while ((modello == "") && (contModello < 4));

						if (contModello == 4) {
							break;
						}

						int cilindrata = 0;
						boolean ok;
						int contCilindrata = 0;
						do {
							do {
								ok = true;
								try {
									System.out.println("Inserisci la cilindrata in centimetri cubi");
									cilindrata = input.nextInt();
									if (cilindrata <= 0) {
										contCilindrata++;
										if (contCilindrata == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}
								} catch (InputMismatchException e) {
									contCilindrata++;
									if (contCilindrata == 4) {
										System.out.println("Hai esaurito i tentativi.");
										System.out.println("Verrai indirizzato al menù principale.");
										System.out.println("-------------------");
										System.out.println();
									} else {
										input.nextLine(); // annulla l’input ricevuto
										System.out.println("Il valore inserito non è valido.");
										System.out.println("Ritenta...");
										System.out.println("-------------------");
										System.out.println();
										ok = false;
									}
								}
							} while ((!ok) && (contCilindrata < 4));
						} while ((cilindrata <= 0) && (contCilindrata < 4));

						if (contCilindrata == 4) {
							break;
						}

						String colore;
						int contColore = 0;
						do {
							System.out.println("Inserisci il colore");
							colore = input1.nextLine();
							if (colore == "") {
								contColore++;
								if (contColore == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("ATTENZIONE: devi inserire il colore.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
						} while ((colore == "") && (contColore < 4));

						if (contColore == 4) {
							break;
						}

						double prezzo = 0;
						boolean ok1;
						int contPrezzo = 0;
						do {
							do {
								ok1 = true;
								try {
									System.out.println("Inserisci il prezzo di vendita");
									prezzo = input.nextDouble();
									if (prezzo <= 0) {
										contPrezzo++;
										if (contPrezzo == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}
								} catch (InputMismatchException e) {
									contPrezzo++;
									if (contPrezzo == 4) {
										System.out.println("Hai esaurito i tentativi.");
										System.out.println("Verrai indirizzato al menù principale.");
										System.out.println("-------------------");
										System.out.println();
									} else {
										input.nextLine(); // annulla l’input ricevuto
										System.out.println("Il valore inserito non è valido.");
										System.out.println("Ritenta...");
										System.out.println("-------------------");
										System.out.println();
										ok1 = false;
									}
								}
							} while ((!ok1) && (contPrezzo < 4));
						} while ((prezzo <= 0) && (contPrezzo < 4));

						if (contPrezzo == 4) {
							break;
						}

						// chiede all'utente di inserire l'anno di immatricolazione
						int immatricolazione = 0;
						boolean ok2;
						// contatore del numero di tentativi in cui l'utente prova a inserire l'anno di
						// immatricolazione
						int contImmatricolazione = 0;
						do {
							do {
								ok2 = true;
								// controllo che il numero inserito sia un intero
								try {
									System.out.println("Inserisci l'anno di immatricolazione");
									immatricolazione = input.nextInt();
									// controllo che il numero inserito non sia negativo
									if (immatricolazione < 0) {
										contImmatricolazione++;
										if (contImmatricolazione == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}
									// controllo che l'anno inserito sia >= del 1980
									else if (immatricolazione < 1980) {
										contImmatricolazione++;
										if (contImmatricolazione == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("L'anno inserito non è valido.");
											System.out.println("Inserisci un anno posteriore al 1980");
											System.out.println("-------------------");
											System.out.println();
										}
									}
									// controllo che l'anno sia < dell'anno corrente
									else if (immatricolazione > LocalDate.now().getYear()) {
										contImmatricolazione++;
										if (contImmatricolazione == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println(
													"L'anno inserito non può essere posteriore all'anno corrente.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}

									// lancio il gestore dell'eccezione se l'utente non ha inserito un intero
								} catch (InputMismatchException e) {
									contImmatricolazione++;
									if (contImmatricolazione == 4) {
										System.out.println("Hai esaurito i tentativi.");
										System.out.println("Verrai indirizzato al menù principale.");
										System.out.println("-------------------");
										System.out.println();
									} else {
										input.nextLine(); // annulla l’input ricevuto
										System.out.println("Il valore inserito non è valido.");
										System.out.println("Ritenta...");
										System.out.println("-------------------");
										System.out.println();
										ok2 = false;
									}
								}
							} while ((!ok2) && (contImmatricolazione < 4));
						} while (((immatricolazione < 0) && (contImmatricolazione < 4))
								|| ((immatricolazione < 1980) && (contImmatricolazione < 4))
								|| ((immatricolazione > LocalDate.now().getYear() && (contImmatricolazione < 4))));

						if (contImmatricolazione == 4) {
							break;
						}

						// chiede all'utente di inserire il numero di chilometri
						int chilometri = 0;
						boolean ok3;
						// contatore del numero di tentativi in cui l'utente prova a inserire i
						// chilometri
						int contKm = 0;
						do {
							do {
								ok3 = true;
								try {
									System.out.println("Inserisci i chilometri");
									chilometri = input.nextInt();
									// controllo che il numero inserito non sia minore o uguale a 0
									if (chilometri <= 0) {
										contKm++;
										if (contKm == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}
									// controllo che il numero inserito non sia maggiore di 300000
									if (chilometri > 300000) {
										contKm++;
										if (contKm == 4) {
											System.out.println("Hai esaurito i tentativi.");
											System.out.println("Verrai indirizzato al menù principale.");
											System.out.println("-------------------");
											System.out.println();
										} else {
											System.out.println("Il numero inserito non è valido.");
											System.out.println("Ritenta...");
											System.out.println("-------------------");
											System.out.println();
										}
									}
									//// lancio il gestore dell'eccezione se l'utente non ha inserito un intero
								} catch (InputMismatchException e) {
									contKm++;
									if (contKm == 4) {
										System.out.println("Hai esaurito i tentativi.");
										System.out.println("Verrai indirizzato al menù principale.");
										System.out.println("-------------------");
										System.out.println();
									} else {
										input.nextLine(); // annulla l’input ricevuto
										System.out.println("Il valore inserito non è valido.");
										System.out.println("Ritenta...");
										System.out.println("-------------------");
										System.out.println();
										ok3 = false;
									}
								}

							} while ((!ok3) && (contKm < 4));
						} while (((chilometri <= 0) && (contKm < 4)) || ((chilometri > 300000) && (contKm < 4)));

						if (contKm == 4) {
							break;
						}

						// creo oggetto della classe Automobile_usata
						Automobile a = new Automobile_usata(targaOK, modello, cilindrata, colore, prezzo,
								immatricolazione, chilometri);
						if (!archivio.presentePerTarga(a)) {
							archivio.inserisci(a);
							System.out.println("L'auto è stata inserita correttamente.");
							System.out.println();
							archivio.visualizza();
						} else {
							System.out.println("ATTENZIONE: esiste già un'auto con questa targa.");
							System.out.println("Impossibile aggiungere l'automobile all'archivio.");
							System.out.println("-------------------");
							System.out.println();
							archivio.visualizza();
						}
					}
				} while (((!auto1.equals("n")) && (!auto1.equals("u"))) && contAggiungi < 4);

				break;

			// permette all'utente di rimuovere un'auto
			case "r":
				String targa;
				String newTarga;
				String targaOK;
				// contatore del numero di auto presenti in archivio con una data targa
				int contMacchine = 0;
				int contTargaErrata = 0;
				do {
					int contTargaCorretta = 0;
					do {
						System.out.println("Inserisci il numero di targa nel formato AA000AA");
						targa = input1.nextLine();
						newTarga = targa.toUpperCase();
						if (!archivio.targaOK(newTarga)) {
							contTargaCorretta++;
							targaOK = null;
							if (contTargaCorretta == 4) {
								System.out.println("Hai esaurito i tentativi.");
								System.out.println("Verrai indirizzato al menù principale.");
								System.out.println("-------------------");
								System.out.println();
							} else {
								System.out.println("La targa inserita non è valida.");
								System.out.println("Ritenta...");
								System.out.println("-------------------");
								System.out.println();
							}
						} else {
							targaOK = newTarga;
							break;
						}

					} while ((!archivio.targaOK(newTarga)) && (contTargaCorretta < 4));
					if (contTargaCorretta == 4) {
						break;
					}

					// controllo che l'auto inserita (di cui viene inserita solo la targa) sia
					// presente in archivio,
					// e in caso affermativo incremento il contatore di 1
					for (Automobile a : archivio.getArchivio()) {
						if ((a.getTarga()).equals(targaOK)) {
							contMacchine = 1;
						}
					}

					// si avverte l'utente che non sono presenti auto in archivio con il numero di
					// targa inserito
					if (contMacchine == 0) {
						contTargaErrata++;
						if (contTargaErrata == 4) {
							System.out.println("Hai esaurito i tentativi.");
							System.out.println("Verrai indirizzato al menù principale.");
							System.out.println("-------------------");
							System.out.println();
						} else {
							System.out.println("Non ci sono macchine con questo numero di targa.");
							System.out.println("Ritenta...");
							System.out.println("-------------------");
							System.out.println();
						}

					}

					if (contMacchine == 1) {
						// ciclo l'archivio
						Iterator<Automobile> iter = archivio.getArchivio().iterator();
						while (iter.hasNext()) {
							Automobile a = iter.next();
							// trova l'auto con la targa corrispondente a quella inserita dall'utente
							if ((a.getTarga()).equals(targaOK)) {
								// rimuove l'auto
								iter.remove();
								System.out.println("L'auto è stata rimossa correttamente.");
								System.out.println();
								System.out.println("-------------------");

							}
						}

					}
				} while ((contMacchine != 1) && (contTargaErrata < 4));
				break;

			// permette all'utente di visualizzare l'archivio delle auto nuove e usate
			case "v":
				archivio.visualizza();
				break;
			// permette all'utente di visualizzare solo le auto nuove
			case "vn":
				archivio.visualizza_nuovo();
				break;
			// permette all'utente di visualizzare solo le auto usate
			case "vu":
				archivio.visualizza_usato();
				break;
			// permette all'utente di modificare la data di consegna di un'auto nuova
			case "m":
				String targa1;
				String newTarga1;
				String targaOK1;
				// contatore del numero di auto presenti in archivio con una data targa
				int contMacchine1 = 0;
				int contTargaErrata1 = 0;
				do {
					int contTargaCorretta = 0;
					do {
						System.out.println("Inserisci il numero di targa nel formato AA000AA");
						targa1 = input1.nextLine();
						newTarga1 = targa1.toUpperCase();
						if (!archivio.targaOK(newTarga1)) {
							contTargaCorretta++;
							targaOK1 = null;
							if (contTargaCorretta == 4) {
								System.out.println("Hai esaurito i tentativi.");
								System.out.println("Verrai indirizzato al menù principale.");
								System.out.println("-------------------");
								System.out.println();
							} else {
								System.out.println("La targa inserita non è valida.");
								System.out.println("Ritenta...");
								System.out.println("-------------------");
								System.out.println();
							}
						} else {
							targaOK1 = newTarga1;
							break;
						}

					} while ((!archivio.targaOK(newTarga1)) && (contTargaCorretta < 4));
					if (contTargaCorretta == 4) {
						break;
					}

					// controlla che l'auto sia presente in archivio e che sia un'auto nuova
					for (Automobile a : archivio.getArchivio()) {
						if (((a.getTarga()).equals(targaOK1)) && (a instanceof Automobile_nuova)) {
							contMacchine1 = 1;
						}
					}
					// si avverte l'utente che non sono presenti auto in archivio con il numero di
					// targa inserito
					if (contMacchine1 == 0) {
						contTargaErrata1++;
						if (contTargaErrata1 == 4) {
							System.out.println("Hai esaurito i tentativi.");
							System.out.println("Verrai indirizzato al menù principale.");
							System.out.println("-------------------");
							System.out.println();
						} else {
							System.out.println("Non ci sono macchine con questo numero di targa.");
							System.out.println("Ritenta...");
							System.out.println("-------------------");
							System.out.println();
						}

					}
					// se l'auto è presente in archivio
					else if (contMacchine1 == 1) {
						for (Automobile a : archivio.getArchivio()) {
							if ((a.getTarga()).equals(targaOK1)) {
								if (a instanceof Automobile_nuova) {
									// stampa l'auto
									System.out.println(a.toString());
									System.out.println();
									System.out.println("-------------------");
									// reimposta la data
									String data1;
									LocalDate data;
									int contData = 0;
									do {
										System.out.println("Inserisci la data, in formato YYYY-MM-DD");
										data1 = input.next();
										data = ((Automobile_nuova) a).setNewData(data1);
										if (data == null) {
											contData++;
											data = null;
											if (contData == 4) {
												System.out.println("Hai esaurito i tentativi.");
												System.out.println("Verrai indirizzato al menù principale.");
												System.out.println("-------------------");
												System.out.println();
											} else {
												System.out.println("Il valore inserito non è valido.");
												System.out.println("Ritenta...");
												System.out.println("-------------------");
												System.out.println();
											}
										} else if (data.isBefore(LocalDate.now())) {
											contData++;
											data = null;
											if (contData == 4) {
												System.out.println("Hai esaurito i tentativi.");
												System.out.println("Verrai indirizzato al menù principale.");
												System.out.println("-------------------");
												System.out.println();
											} else {
												System.out.println("La data che hai inserito deve essere posteriore alla data odierna");
												System.out.println("Ritenta...");
												System.out.println("-------------------");
												System.out.println();

											}
										}
									} while (((data == null) && (contData < 4))
											|| ((data.isBefore(LocalDate.now()) && (contData < 4))));

									if (contData == 4) {
										break;
									}

									System.out.println("La data è stata cambiata.");
									System.out.println();
									System.out.println("-------------------");

								}

							}
						}
					}

				} while (contMacchine1 != 1);

				break;
			// permette all'utente di ricercare le auto di un certo proprietario con il nome
			// o una parte di esso
			case "rn":
				String nome;
				String newNome;
				// contatore del numero di tentativi in cui l'utente prova a inserire il nome
				int contNome = 0;
				do {
					System.out.println("Inserisci il nome e il cognome del proprietario");
					nome = input3.nextLine();
					newNome = nome.toUpperCase();
					// controllo che l'utente non abbia inserito una stringa vuota
					if (newNome == "") {
						contNome++;
						if (contNome == 4) {
							System.out.println("Hai esaurito i tentativi.");
							System.out.println("Verrai indirizzato al menù principale.");
							System.out.println("-------------------");
							System.out.println();
						} else {
							System.out.println("ATTENZIONE: devi inserire il nome del proprietario.");
							System.out.println("Ritenta...");
							System.out.println("-------------------");
							System.out.println();
						}
					}
				} while ((newNome == "") && (contNome < 4));
				if (contNome == 4) {
					break;
				}
				// lancia il metodo ricerca_per_nome
				archivio.ricerca_per_nome(newNome);

				break;
			// permette di cercare le auto in archivio con un certo anno di
			// immatricolazione, o successivo, e con al più un tot numero di chilometri
			case "rak":
				int immatricolazione = 0;
				boolean ok2;
				int contImmatricolazione = 0;
				do {
					do {
						ok2 = true;
						try {
							System.out.println("Inserisci l'anno di immatricolazione");
							immatricolazione = input.nextInt();
							if (immatricolazione < 0) {
								contImmatricolazione++;
								if (contImmatricolazione == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("Il numero inserito non è valido.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							} else if (immatricolazione < 1980) {
								contImmatricolazione++;
								if (contImmatricolazione == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("L'anno inserito non è valido.");
									System.out.println("Inserisci un anno posteriore al 1980");
									System.out.println("-------------------");
									System.out.println();
								}
							} else if (immatricolazione > LocalDate.now().getYear()) {
								contImmatricolazione++;
								if (contImmatricolazione == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("L'anno inserito non può essere posteriore all'anno corrente.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}

						} catch (InputMismatchException e) {
							contImmatricolazione++;
							if (contImmatricolazione == 4) {
								System.out.println("Hai esaurito i tentativi.");
								System.out.println("Verrai indirizzato al menù principale.");
								System.out.println("-------------------");
								System.out.println();
							} else {
								input.nextLine(); // annulla l’input ricevuto
								System.out.println("Il valore inserito non è valido.");
								System.out.println("Ritenta...");
								System.out.println("-------------------");
								System.out.println();
								ok2 = false;
							}
						}
					} while ((!ok2) && (contImmatricolazione < 4));
				} while (((immatricolazione < 0) && (contImmatricolazione < 4))
						|| ((immatricolazione < 1980) && (contImmatricolazione < 4))
						|| ((immatricolazione > LocalDate.now().getYear() && (contImmatricolazione < 4))));

				if (contImmatricolazione == 4) {
					break;
				}

				int chilometri = 0;
				boolean ok3;
				int contKm = 0;
				do {
					do {
						ok3 = true;
						try {
							System.out.println("Inserisci i chilometri");
							chilometri = input.nextInt();
							if (chilometri <= 0) {
								contKm++;
								if (contKm == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("Il numero inserito non è valido.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
							if (chilometri > 300000) {
								contKm++;
								if (contKm == 4) {
									System.out.println("Hai esaurito i tentativi.");
									System.out.println("Verrai indirizzato al menù principale.");
									System.out.println("-------------------");
									System.out.println();
								} else {
									System.out.println("Il numero inserito non è valido.");
									System.out.println("Ritenta...");
									System.out.println("-------------------");
									System.out.println();
								}
							}
						} catch (InputMismatchException e) {
							contKm++;
							if (contKm == 4) {
								System.out.println("Hai esaurito i tentativi.");
								System.out.println("Verrai indirizzato al menù principale.");
								System.out.println("-------------------");
								System.out.println();
							} else {
								input.nextLine(); // annulla l’input ricevuto
								System.out.println("Il valore inserito non è valido.");
								System.out.println("Ritenta...");
								System.out.println("-------------------");
								System.out.println();
								ok3 = false;
							}
						}

					} while ((!ok3) && (contKm < 4));
				} while (((chilometri <= 0) && (contKm < 4)) || ((chilometri > 300000) && (contKm < 4)));

				if (contKm == 4) {
					break;
				}
				// lancia metodo ricerca_auto_immatricolate
				archivio.ricerca_auto_immatricolate(immatricolazione, chilometri);
				break;
			// permette all'utente di visualizzare la prossima auto da consegnare
			case "rc":
				archivio.ricerca_prossima_consegna();
				break;
			// permette all'utente di salvare l'archvio su file binario
			case "s":
				archivio.salva();
				System.out.println("Salvato!");
				System.out.println("-------------------");
				System.out.println();
				break;
			// permette all'utente di uscire dalla concessionaria
			case "u":
				System.out.println("Grazie per aver usato la nostra concessionaria.");
				System.out.println("-------------------");
				System.out.println();
				break;
			default:
				System.out.println("La lettera inserita non è valida.");
				System.out.println("Ritenta...");
				System.out.println("-------------------");
				System.out.println();

				break;
			}

		} while (!selezione1.equals("u"));

	}
}
