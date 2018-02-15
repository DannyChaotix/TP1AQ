
public class Facture {
	
	public Facture (String [] tabClients,String [] tabPlats, String [] tabCommandes) {

		for (int i = 0; i < tabClients.length; i++) {
			System.out.println(tabClients[i]);
		}
		
		for (int i = 0; i < tabPlats.length; i++) {
			System.out.println(tabPlats[i]);
		}
		
		for (int i = 0; i < tabCommandes.length; i++) {
			System.out.println(tabCommandes[i]);
		}
		
		System.out.println("Test d'impression");
		String[][] tabTest = {{"pat","marc","john"},{"10","21","32"}};
		Extraction.ecrireFichier(tabTest);
			
		
	}
}
