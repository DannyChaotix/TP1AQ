import static org.junit.Assert.*;

import org.junit.Test;

public class TestVetrifierUnite {

	@Test
	public void test() {
		Extraction test = new Extraction();
		boolean resultat = test.verifierUnite("9");
		assertEquals(true,resultat);
	}

}
