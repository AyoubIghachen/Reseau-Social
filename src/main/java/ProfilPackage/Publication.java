package ProfilPackage;

import java.util.List;

public class Publication {
	private String ID_Publication;
	private String Username;
	private String Nom;
	private String DateTime;
	private String Texte;
	private String Photo;
	private List<String> ListAimer;
	private List<Commentaire> ListCommentaire;
	private String PhotoProfil;
	
	
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

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public String getTexte() {
		return Texte;
	}

	public void setTexte(String texte) {
		Texte = texte;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public List<String> getListAimer() {
		return ListAimer;
	}

	public void setListAimer(List<String> listAimer) {
		ListAimer = listAimer;
	}

	public List<Commentaire> getListCommentaire() {
		return ListCommentaire;
	}

	public void setListCommentaire(List<Commentaire> listCommentaire) {
		ListCommentaire = listCommentaire;
	}
	
	public String getPhotoProfil() {
		return PhotoProfil;
	}

	public void setPhotoProfil(String photoProfil) {
		PhotoProfil = photoProfil;
	}
	
}
