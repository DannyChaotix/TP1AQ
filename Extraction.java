import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Extraction {

	private static String fileName = "src/facture.txt";
	private static String erreur = "Le fichier ne respecte pas le format demandé !";

	private static List<String> listClients = new ArrayList<String>();
	private static List<String> listPlats = new ArrayList<String>();
	private static List<String> listCommandes = new ArrayList<String>();

	// Lit les lignes du fichier texte et vérifie le format, un format non conforme
	// sera rejeté avec un message d'erreur.
	public static void main(String[] args) {

		boolean valide = true;
		try {

			BufferedReader buffReader = new BufferedReader(new FileReader(new File(fileName)));

			String ligne;

			boolean fin = false;
			List<String> listTemp = new ArrayList<String>();

			int indVerification = 0;

			while (!fin) {

				ligne = buffReader.readLine();

				switch (ligne) {
				case "Clients :":
					listTemp = listClients;
					indVerification = 1;
					break;
				case "Plats :":
					listTemp = listPlats;
					indVerification = 2;
					break;
				case "Commandes :":
					listTemp = listCommandes;
					indVerification = 3;
					break;
				case "Fin":
					fin = true;
					break;

				default:
					if (verification(ligne, indVerification)) {
						listTemp.add(ligne);
					} else {
						valide = false;
						fin = true;
					}
					break;
				}
			}
			buffReader.close();
		} catch (Exception e) {
			System.out.println(erreur);
		}
		if (valide) {
			String[] tabClients = new String[listClients.size()];
			String[] tabPlats = new String[listPlats.size()];
			String[] tabCommandes = new String[listCommandes.size()];

			tabClients = listClients.toArray(tabClients);
			tabPlats = listPlats.toArray(tabPlats);
			tabCommandes = listCommandes.toArray(tabCommandes);
			// Call la class Facture avec 3 tableau de String.
			Facture facture1 = new Facture(tabClients, tabPlats, tabCommandes);
			ecrireFichier(facture1.calculerFacture(), Facture.getList());
		} else {
			System.out.println(erreur);

		}

	}

	// Switch qui vérifie la String avec un indice qui permet de savoir si nous
	// somme dans la section Clients, Plats, Command ou autre.
	private static boolean verification(String ligne, int ind) {
		boolean verifier = true;
		String[] tabStringTemp;

		switch (ind) {
		case 0:
			verifier = false;
			break;
		case 1:
			if (!verifierMot(ligne)) {
				verifier = false;
			}
			;
			break;
		case 2:
			tabStringTemp = ligne.split(" ");
			if (tabStringTemp.length != 2) {
				verifier = false;
			} else {
				if (!verifierMot(tabStringTemp[0]) || !verifierChiffre(tabStringTemp[1])) {
					verifier = false;
				}
			}
			break;
		case 3:
			tabStringTemp = ligne.split(" ");
			if (tabStringTemp.length != 3) {
				verifier = false;
			} else {
				if (!verifierMot(tabStringTemp[0]) || !verifierMot(tabStringTemp[1])
						|| !verifierUnite(tabStringTemp[2])) {
					verifier = false;
				}
			}
			break;
		default:
			;
			break;
		}

		return verifier;
	}

	// Vérifie un mot
	private static boolean verifierMot(String ligne) {
		boolean verifier = true;
		if (ligne.contains(" ") || Pattern.compile("[0-9]").matcher(ligne).find()) {
			verifier = false;
		}
		return verifier;
	}

	// Vérifie un Chiffre
	private static boolean verifierChiffre(String ligne) {
		boolean verifier = true;
		try {
			Double.parseDouble(ligne);
		} catch (NumberFormatException e) {
			verifier = false;
		}
		return verifier;
	}

	// Vérifie une quantité d'un plat
	private static boolean verifierUnite(String ligne) {
		boolean verifier = true;
		if (ligne.length() != 1) {
			verifier = false;
		} else {
			try {
				Integer.parseInt(ligne);
			} catch (NumberFormatException e) {
				verifier = false;
			}
		}
		return verifier;
	}

	// Crée un fichier texte "ImpFacture.txt" avec un tableau à 2 dimension.
	public static void ecrireFichier(String[][] tabString, String[] tabErreur) {
		PrintWriter writer;
		LocalDateTime temps = LocalDateTime.now();
		try {
			writer = new PrintWriter("Facture-du-" + temps.getDayOfMonth() + "-" + temps.getHour() + "-"
					+ temps.getMinute() + "-" + temps.getSecond() + ".txt", "UTF-8");
			writer.println("Bienvenue chez Barette!");
			writer.println("Factures:");
			// écrie les erreurs
			
			for (int i = 0; i < tabString[0].length; i++) {
				writer.println(tabString[0][i] + " " + tabString[1][i]);
			}
			writer.println("--------------------------------------------------------------------------------------");
			if (tabErreur != null) {
				for (int i = 0; i < tabErreur.length; i++) {
					writer.println(tabErreur[i]);
				}
			}
			
			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Ne peux pas imprimer .");
		}

	}

}
