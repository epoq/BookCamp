package entites;

public class Adresse {

    private String adresseId;
    private String adresseNumeroVoie;
    private String adresseComplement;
    private String adresseCodePostal;
    private String adresseVille;
    private String adressePays;

    public Adresse(String adresseId, String adresseNumeroVoie, String adresseComplement, String adresseCodePostal, String adresseVille, String adressePays) {
        this.adresseId = adresseId;
        this.adresseNumeroVoie = adresseNumeroVoie;
        this.adresseComplement = adresseComplement;
        this.adresseCodePostal = adresseCodePostal;
        this.adresseVille = adresseVille;
        this.adressePays = adressePays;
    }

    public String getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(String adresseId) {
        this.adresseId = adresseId;
    }

    public String getAdresseNumeroVoie() {
        return adresseNumeroVoie;
    }

    public void setAdresseNumeroVoie(String adresseNumeroVoie) {
        this.adresseNumeroVoie = adresseNumeroVoie;
    }

    public String getAdresseComplement() {
        return adresseComplement;
    }

    public void setAdresseComplement(String adresseComplement) {
        this.adresseComplement = adresseComplement;
    }

    public String getAdresseCodePostal() {
        return adresseCodePostal;
    }

    public void setAdresseCodePostal(String adresseCodePostal) {
        this.adresseCodePostal = adresseCodePostal;
    }

    public String getAdresseVille() {
        return adresseVille;
    }

    public void setAdresseVille(String adresseVille) {
        this.adresseVille = adresseVille;
    }

    public String getAdressePays() {
        return adressePays;
    }

    public void setAdressePays(String adressePays) {
        this.adressePays = adressePays;
    }

    @Override
    public String toString() {
        return adresseNumeroVoie + " " + adresseComplement + " " + adresseCodePostal + " " + adresseVille + " " + adressePays;
    }
    
    
    
}
