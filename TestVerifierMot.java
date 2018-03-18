import static org.junit.Assert.*;

import org.junit.Test;

public class TestVerifierMot {

	@Test
	public void test() {
		Extraction test = new Extraction();
		boolean resultat = test.verifierMot("Poutine");
		assertEquals(true,resultat);
	}

}
