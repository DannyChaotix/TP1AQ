import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class EcrireFichier {


	public EcrireFichier(String[][] tabString, String[] tabErreur) {
		PrintWriter writer;
		LocalDateTime temps = LocalDateTime.now();
		try {
			writer = new PrintWriter("Facture-du-" + temps.getDayOfMonth() + "-" + temps.getHour() + "-"
					+ temps.getMinute() + "-" + temps.getSecond() + ".txt", "UTF-8");
			writer.println("Bienvenue chez Barette!");
			writer.println("Factures:");
			// écrie les erreurs
			
			for (int i = 0; i < tabString[0].length; i++) {
				writer.println(tabString[0][i] + " " + tabString[1][i]);
			}
			writer.println("--------------------------------------------------------------------------------------");
			if (tabErreur != null) {
				for (int i = 0; i < tabErreur.length; i++) {
					writer.println(tabErreur[i]+" non valide.");
				}
			}
			
			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Ne peux pas imprimer .");
		}
		
	}
}