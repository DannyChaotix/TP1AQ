import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JUnit_Test_Case_Facture.class, TestVerifierChiffre.class, TestVerifierMot.class,
		TestVetrifierUnite.class, TestVerification.class })
public class AllTests {

}
