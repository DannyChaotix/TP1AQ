import static org.junit.Assert.*;

import org.junit.Test;

public class TestVerification {

	@Test
	public void test() {
		Extraction test = new Extraction();
		boolean resultat = test.verification("Dave",1);
		assertEquals(true,resultat);
		
		resultat = test.verification("Spagetti 7.25",2);
		assertEquals(true,resultat);
		
		resultat = test.verification("Dave Spagetti 3",3);
		assertEquals(true,resultat);
	}

}
