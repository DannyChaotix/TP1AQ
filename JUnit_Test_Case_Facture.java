import static org.junit.Assert.*;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;


public class JUnit_Test_Case_Facture {

	Facture factureTestVal, factureTestErrClient, factureTestErrPlats, factureTestErrQuantite;
	
	String tabClient[] = {"Roger", "Céline", "Steeve"}; 
	String tabPlats[] = {"Poutine 10.5", "Frites 2.5", "Repas_Poulet 15.75"};
	String tabCommandes[] = {"Roger Poutine 1", "Céline Frites 2", "Céline Repas_Poulet 1"};
	
	String tabCommandesErreurClient[] = {"Patrick Poutine 1", "Céline Frites 2", "Céline Repas_Poulet 1"};
	String tabCommandesErreurPlats[] = {"Roger Poutine 1", "Céline BigMac 2", "Céline Repas_Poulet 1"};
	String tabCommandesErreurQuantite[] = {"Roger Poutine 1", "Céline Frites 2", "Céline Repas_Poulet -2"};

	@Before
	public void testCreationDeFactures() {
		factureTestVal = new Facture(tabClient, tabPlats, tabCommandes);	
		factureTestErrClient = new Facture(tabClient, tabPlats, tabCommandesErreurClient);
		factureTestErrPlats = new Facture(tabClient, tabPlats, tabCommandesErreurPlats);
		factureTestErrQuantite = new Facture(tabClient, tabPlats, tabCommandesErreurQuantite);
		
	}
	
	@Test
	public void testFactureDonneesValide() {
		String[] tabTest;
		
		tabTest = factureTestVal.getList();
		assertEquals(0, tabTest.length);
		
		tabTest = factureTestErrClient.getList();
		assertEquals("Patrick", tabTest[0]);
		
		tabTest = factureTestErrPlats.getList();
		assertEquals("BigMac", tabTest[0]);
		
		tabTest = factureTestErrQuantite.getList();
		assertEquals("-2", tabTest[0]);
		
	}
}
