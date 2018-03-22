import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Facture {

	private String[] tabClients;

	public String[] getTabClients() {
		return tabClients;
	}

	public String[] getTabPlats() {
		return tabPlats;
	}

	public double[] getTabPrix() {
		return tabPrix;
	}

	public String[] getTabCommandes() {
		return tabCommandes;
	}

	private String[] tabErreures = new String[0];
	private String[] tabPlats;
	private double[] tabPrix;
	private String[] tabCommandes;
	private String affichage = "";
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

		TrouverErreures();

		affichage += "Factures: \n";
		DecimalFormat format = new DecimalFormat("0.00$");

		double[] tabCout = new double[tabClients.length];
		String[][] reponce = new String[2][tabClients.length];

		reponce[0] = tabClients;

		for (int i = 0; i < tabClients.length; i++) {

			for (int j = 0; j < tabCommandes.length; j++) {
				// commande contient les aspects de la commande (0=Client, 1=Plat, 2=Quantitée)
				String[] commande = tabCommandes[j].split(" ");

				if (commande[0].equals(tabClients[i])) {

					// La boucle trouve le plat
					for (int k = 0; k < tabPlats.length; k++) {
						double prix = 0;
						if (commande[1].equals(tabPlats[k])) {
							prix = (tabPrix[k] * Double.parseDouble(commande[2]));
							affichage += ("Repas: " + tabPlats[k] + "\n" + "Prix a ajouter= " + format.format(prix)
									+ "\n");
							double prixTPS = prix * 0.05;
							affichage += ("TPS: " + format.format(prixTPS) + "\n");
							double prixTVQ = (prix + prixTPS) * 0.10;
							affichage += ("TVQ: " + format.format(prixTVQ) + "\n");
							double save = (prix + prixTPS + prixTVQ);
							tabCout[i] += save;

							affichage += ("Prix du repas: " + tabPlats[k] + " = " + format.format(save) + "\n"
									+ "====================\n");

							try {
								String[] commandeTemp = tabCommandes[j + 1].split(" ");
								if (!tabClients[i].equals(commandeTemp[0])) {
									affichage += "Prix pour " + tabClients[i] + ": " + format.format(tabCout[i]) + "\n"
											+ "====================\n\n";
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								affichage += "Prix pour " + tabClients[i] + ": " + format.format(tabCout[i]) + "\n"
										+ "====================\n\n";
							}
						}

					}
					

				}

			}
			for (int q = 0; q < tabCout.length; q++) {
				reponce[1][q] = format.format(tabCout[q]);
			}
		}

		this.setList();

		for (int r = 0; r < this.tabErreures.length; r++) {
			System.out.println("Erreur #" + (r + 1) + ": " + tabErreures[r] + " est invalide.");
		}

		System.out.println(affichage);
		return reponce;
	}

	public String[] getList() {
		return this.tabErreures;
	}

	public void setList() {
		// tabPlats = listPlats.toArray(tabPlats); String[] tabClients = new
		// String[listClients.size()];
		if (erreures.size() == 0) {
			String[] tabErreures = new String[0];
			this.tabErreures = tabErreures;
		} else {
			String[] tabErreures = new String[erreures.size()];
			tabErreures = erreures.toArray(tabErreures);
			this.tabErreures = tabErreures;
		}
	}

	public void TrouverErreures() {
		boolean tester = false, tester2 = false;

		for (int i = 0; i < tabCommandes.length; i++) {
			String[] com = tabCommandes[i].split(" ");

			for (int j = 0; j < tabClients.length; j++) {
				if (tabClients[j].equals(com[0])) {
					tester = true;
				}
				for (int k = 0; k < tabPlats.length; k++) {
					if (com[1].equals(tabPlats[k])) {
						tester2 = true;
					}
				}


			}
			
			if (!tester) {
				erreures.add(com[0]);
			}
			if (!tester2) {
				erreures.add(com[1]);}
			

		}
	}
}
