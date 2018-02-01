public class CV {
private String nom;
private String prenom;
private String formation;
private int experiences;
private String competences;
private String attentes;


public static void main(String[] args){
	System.out.println("Bienvenue chez barette!");
	
	CV cvP = new CV("Papineau", "Patrick", "Informatique de gestion", 3, "Java", "Assurer la qualité d un logiciel." );
	CV cvD = new CV("Provost", "David", "Informatique de gastion", 2, "HTML", "Programmer");
	
	cvP.affiche();
	cvD.affiche();
}
	
	public CV(String nom, String prenom, String formation, int experiences,
			String competences, String attentes) {
			this.nom = nom;
			this.prenom = prenom;
			this.formation = formation;
			this.experiences = experiences;
			this.competences = competences;
			this.attentes = attentes;
		}
	public void affiche(){
		System.out.println("\nNom : "+this.nom+"\nPrénom : "+this.prenom+"\nFormation : "
					+this.formation+"\nExpérience : "+this.experiences+"\nCompétences : "+this.competences
					+"Attentes : "+this.attentes);
	}
		
}

