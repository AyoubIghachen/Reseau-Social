package ProfilPackage;

import java.util.List;

public class Profil {
	private String Username;
	private String PhotoProfil;
	private String Nom;
	private String Description;
	private String Loisirs;
	private String Ville;
	private List<Publication> ListPublication;
	private boolean followState;
	

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPhotoProfil() {
		return PhotoProfil;
	}

	public void setPhotoProfil(String photoProfil) {
		PhotoProfil = photoProfil;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLoisirs() {
		return Loisirs;
	}

	public void setLoisirs(String loisirs) {
		Loisirs = loisirs;
	}

	public String getVille() {
		return Ville;
	}

	public void setVille(String ville) {
		Ville = ville;
	}

	public List<Publication> getListPublication() {
		return ListPublication;
	}

	public void setListPublication(List<Publication> listPublication) {
		ListPublication = listPublication;
	}
	
	public boolean isFollowState() {
		return followState;
	}

	public void setFollowState(boolean followState) {
		this.followState = followState;
	}
}
