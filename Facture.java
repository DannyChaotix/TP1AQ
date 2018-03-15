import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Facture {

	private String[] tabClients;
	private String[] tabPlats;
	private double[] tabPrix;
	private String[] tabCommandes;
	private static List<String> erreures = new ArrayList<String>();

	public Facture(String[] tabClients, String[] tabPlats, String[] tabCommandes) {
		super();
		this.tabClients = tabClients;
		this.tabCommandes = tabCommandes;
		this.tabPrix = new double[tabPlats.length];
		this.tabPlats = new String[tabPlats.length];

		for (int i = 0; i < tabPlats.length; i++) {
			String[] temp = tabPlats[i].split(" ");
			this.tabPlats[i] = temp[0];
			this.tabPrix[i] = Double.parseDouble(temp[1]);
		}
	}

	public String[][] calculerFacture() {

		System.out.println("Factures:");
		DecimalFormat format = new DecimalFormat("0.00$");

		double[] tabCout = new double[tabClients.length];
		String[][] reponce = new String[2][tabClients.length];

		reponce[0] = tabClients;
		for (int i = 0; i < tabClients.length; i++) {

			for (int j = 0; j < tabCommandes.length; j++) {
				// commande contient les aspects de la commande (0=Client, 1=Plat, 2=Quantitée)
				String[] commande = tabCommandes[j].split(" ");

				if (commande[0].equals(tabClients[i])) {
					System.out.println("commande == client");

					// La boucle trouve le plat
					boolean tester = false;
					for (int k = 0; k < tabPlats.length; k++) {
						double prix = 0;
						if (commande[1].equals(tabPlats[k])) {
							prix = (tabPrix[k] * Double.parseDouble(commande[2]));
							System.out.println("Prix a ajouter= " + prix);
							tabCout[i] += prix;
							double prixTPS = tabCout[i] * 0.05;
							System.out.println("TPS: " + prixTPS);
							double prixTVQ = (prix + prixTPS) * 0.10;
							System.out.println("TVQ: " + prixTVQ);
							tabCout[i] = (prix + prixTPS + prixTVQ);

							System.out.println("Prix totale pour " + tabClients[i] + " = " + tabCout[i]);
							tester = true;
						}

						

					}
					if (!tester) {
						erreures.add(commande[1]);
					}
				}

			}
			for (int q = 0; q < tabCout.length; q++) {
				reponce[1][q] = format.format(tabCout[q]);
			}
		}
		return reponce;
	}

	public static String[] getList() {
		// tabPlats = listPlats.toArray(tabPlats); String[] tabClients = new
		// String[listClients.size()];
		if (erreures.size() == 0) {
			String[] tabErreures = new String[0];
			return tabErreures;
		} else {
			String[] tabErreures = new String[erreures.size()];
			tabErreures = erreures.toArray(tabErreures);
			return tabErreures;
		}
	}
}
