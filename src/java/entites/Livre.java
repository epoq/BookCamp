package entites;

import java.util.List;
import java.util.Objects;

public class Livre {
    private String ISBN;
    private String titre;
    private String sousTitre;
    private String resume;
    private List<Auteur> auteurs;
    private String editeur;
    private List<MotCle> motCles;
    private List<Theme> themes;
    private String urlImage;
    private float prixHT;
    private Tva tva;
    private int nbrePages;
    private int quantiteDisponible;
    private String dimensions;
    private float poids;
    private boolean statut;

    public Livre() {
    }

    public Livre(String ISBN, String titre, List<Auteur> auteurs, String editeur, String urlImage, float prixHT, int nbPages) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.auteurs = auteurs;
        this.editeur = editeur;
        this.urlImage = urlImage;
        this.prixHT = prixHT;
        this.nbrePages = nbPages;
    }

    public Livre(String ISBN, String titre, String sousTitre,String urlImage, String resume, float prixHT) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.urlImage=urlImage;
        this.resume = resume;
        this.prixHT = prixHT;
    }
    

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public List<MotCle> getMotCles() {
        return motCles;
    }

    public void setMotCles(List<MotCle> motCles) {
        this.motCles = motCles;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(float prixHT) {
        this.prixHT = prixHT;
    }

    public Tva getTva() {
        return tva;
    }

    public void setTva(Tva tva) {
        this.tva = tva;
    }

    public int getNbrePages() {
        return nbrePages;
    }

    public void setNbrePages(int nbrePages) {
        this.nbrePages = nbrePages;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Livre{" + "ISBN=" + ISBN + ", titre=" + titre + ", sousTitre=" 
                + sousTitre + ", resume=" + resume + ", auteurs=" + auteurs 
                + ", editeur=" + editeur + ", motCles=" + motCles + ", themes=" 
                + themes + ", urlImage=" + urlImage + ", prixHT=" + prixHT 
                + ", tva=" + tva + ", nbPages=" + nbrePages + ", quantiteDispo=" 
                + quantiteDisponible + ", dimensions=" + dimensions + ", poids=" 
                + poids + ", statut=" + statut + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livre other = (Livre) obj;
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        return true;
    }
    
    
    
}
