import static org.junit.Assert.*;

import org.junit.Test;

public class TestVerifierChiffre {

	@Test
	public void test() {
		Extraction test = new Extraction();
		boolean resultat = test.verifierChiffre("64");
		assertEquals(true,resultat);
	}

}
