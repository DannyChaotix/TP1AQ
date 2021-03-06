import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class EcrireFichier {


	public EcrireFichier(String ligne, String[] tabErreur) {
		PrintWriter writer;
		LocalDateTime temps = LocalDateTime.now();
		String[] ligneMot = ligne.split(";");
		try {
			writer = new PrintWriter("Facture-du-" + temps.getDayOfMonth() + "-" + temps.getHour() + "-"
					+ temps.getMinute() + "-" + temps.getSecond() + ".txt", "UTF-8");
			writer.println("Bienvenue chez Barette!");
			writer.println("Factures:");
			// �crie les erreurs
			
			/*for (int i = 0; i < tabString[0].length; i++) {
			*	writer.println(tabString[0][i] + " " + tabString[1][i]);
			*}
			*/
			if (tabErreur != null) {
				for (int i = 0; i < tabErreur.length; i++) {
					writer.println(tabErreur[i]+" non valide.");
				}
				writer.println("--------------------------------------------------------------------------------------");
			}
			for (int i = 0; i < ligneMot.length; i++) {
				writer.println(ligneMot[i]);	
			}

			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Ne peux pas imprimer .");
		}
		
	}
}