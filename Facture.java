import java.text.DecimalFormat;

public class Facture {

	private String[] tabClients;
	private String[] tabPlats;
	private double[] tabPrix;
	private String[] tabCommandes;

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

				System.out.println("commande = " + commande[0] + ", client = " + tabClients[i] + ".");
				if (commande[0].equals(tabClients[i])) {
					System.out.println("commande == client");

					// La boucle trouve le plat
					for (int k = 0; k < tabPlats.length; k++) {
						double prix = 0;
						System.out.println("\t commande = " + commande[1] + ", plat = "
								+ Double.parseDouble(commande[2]) + tabPlats[k] + "(s).");
						if (commande[1].equals(tabPlats[k])) {
							prix = (tabPrix[k] * Double.parseDouble(commande[2]));
						}
						System.out.println("Prix a ajouter= " + prix);
						tabCout[i] += prix;
						System.out.println("Prix totale pour " + tabClients[i] + " = " + tabCout[i]);
					}
				}
			}
			for(int q = 0; q<tabCout.length;q++) {
				reponce[1][q] = format.format(tabCout[q]);
			}
		}
		return reponce;
	}
}
