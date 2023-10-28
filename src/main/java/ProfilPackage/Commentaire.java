package ProfilPackage;

public class Commentaire {
	private String ID_Commentaire;
	private String ID_Publication;
	private String Username;
	private String Nom;
	private String Commentaire;
	private String DateTime;
	
	
	public String getID_Commentaire() {
		return ID_Commentaire;
	}

	public void setID_Commentaire(String iD_Commentaire) {
		ID_Commentaire = iD_Commentaire;
	}

	public String getID_Publication() {
		return ID_Publication;
	}

	public void setID_Publication(String iD_Publication) {
		ID_Publication = iD_Publication;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getCommentaire() {
		return Commentaire;
	}

	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	
}
