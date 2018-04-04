import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Facture {

	private String[] tabClients;
	private int[] tabTables;

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
	private int top;
	private static List<String> erreures = new ArrayList<String>();

	public Facture(String[] tabClients, String[] tabPlats, String[] tabCommandes) {
		super();
		this.tabTables = new int[tabClients.length];
		this.tabClients = new String[tabTables.length];
		for (int i = 0; i < tabClients.length; i++) {
			String[] temp = tabClients[i].split(" ");
			this.tabClients[i] = temp[0];
			tabTables[i] = Integer.parseInt(temp[1]);
			int tempTop = tabTables[i];
			if (tempTop > top) {
				top = tempTop;

			}

		}

		this.tabCommandes = tabCommandes;
		this.tabPrix = new double[tabPlats.length];
		this.tabPlats = new String[tabPlats.length];

		for (int i = 0; i < tabPlats.length; i++) {
			String[] temp = tabPlats[i].split(" ");
			this.tabPlats[i] = temp[0];
			this.tabPrix[i] = Double.parseDouble(temp[1]);
		}

	}

	public String calculerFacture() {

		Double[] tabPrixTables = new Double[top];
		String[] tabAjout = new String[top];
		DecimalFormat format = new DecimalFormat("0.00$");

		for (int i = 0; i < top; i++) {// Les Tables "i"
			tabAjout[i] += "\tPlats:\n";
			for (int j = 0; j < tabCommandes.length; j++) {// Les Commandes "j"
				String[] commande = tabCommandes[j].split(" ");

				boolean testUser = false;
				for (int k = 0; k < tabClients.length; k++) {// Les Clients "k"
					if (commande[0].equals(tabClients[k])) {
						testUser = true;

						if (i + 1 == tabTables[k]) {// Si la commande fait
													// partie de la table

							boolean testPlat = false;
							for (int l = 0; l < tabPlats.length; l++) {// Les
																		// Plats
																		// "l"

								if (tabPlats[l].equals(commande[1])) {// Trouve
																		// le
																		// plat
									testPlat = true;
									boolean testNum = false;
									if (commande.length == 3 || Integer.parseInt(commande[2]) >= 1) {// Si
																										// la
																										// quantitée
										if (tabPrixTables[i] == null) { // est
																		// valide
											tabPrixTables[i] = 0.0;
										}
										testNum = true;
										tabAjout[i] = "\t" + commande[1] + " * " + commande[2] + "= "
												+ format.format((tabPrix[l] * Integer.parseInt(commande[2]))) + "\n";
										tabPrixTables[i] += (tabPrix[l] * Integer.parseInt(commande[2]));// Ajoute
																											// le
										// prix
										// a
										// la
										// table
									}
									if (!testNum) {// La quantitée est invalide
										erreures.add("La quantitée de " + commande[2]);
									}
								}

							}
							if (!testPlat) {// Le Plat n'existe pas
								erreures.add("Le plat " + commande[1]);
							}
						}

					}
					if (!testUser) {// L'usager n'existe pas
						erreures.add("L'usager " + commande[0]);
					}

				}
			}

		}
		int testQuant = 0;
		for (int m = 0; m < top; m++) {// Les tables (Encore) "m"

			for (int n = 0; n > tabTables.length; n++) {// les Clients (encore)
														// "n"
				if (tabTables[n] == m + 1) {
					testQuant++;
				}
			}
			if (testQuant > 0) {
				affichage += "Table " + (m + 1) + "\n\n";
				affichage += tabAjout[m];
			}

			if (tabPrixTables[m] != null) {

				if (tabPrixTables[m] >= 100 || testQuant >= 3) {// le prix est
																// au dessu de
																// 100$/plus de
																// 3 personnes a
																// la
																// table
					tabPrixTables[m] += (tabPrixTables[m] * 0.15);
				}
				affichage += "Table" + (m + 1) + "\n\n;";
				affichage += "Prix Brut = " + format.format(tabPrixTables[m]) + "\n;";
				tabPrixTables[m] += tabPrixTables[m] * 0.15;// Taxes
				affichage += "Ajout des taxes: " + format.format(tabPrixTables[m] * 0.15) + "\n;";
				affichage += "Total: " + format.format(tabPrixTables[m])+";";

			}
		}

		return affichage;
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

}
