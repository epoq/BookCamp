package entites;

public class Tva {
    private float tvaTaux;
    private String tvaId;

    public Tva(float tvaTaux, String tvaId) {
        this.tvaTaux = tvaTaux;
        this.tvaId = tvaId;
    }

    public float getTvaTaux() {
        return tvaTaux;
    }

    public void setTvaTaux(float tvaTaux) {
        this.tvaTaux = tvaTaux;
    }

    public String getTvaId() {
        return tvaId;
    }

    public void setTvaId(String tvaId) {
        this.tvaId = tvaId;
    }
    
    
}
