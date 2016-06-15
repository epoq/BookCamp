package entites;

import java.util.List;

public class Client {
    private String email;
    private String mdp;
    private String nom;
    private String prenom;
    private List<Adresse> adresses;

    public Client() {
    }

    
    public Client(String email, String mdp, String nom, String prenom) {
        this.email = email;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }
    
    
}
