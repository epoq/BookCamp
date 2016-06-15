package DAOFactory;

import entites.Auteur;
import entites.Livre;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuteurDao implements Serializable {

    private DataSource ds;
    public static final String pool = "jdbc/librairie";
    private static final String reqNomAuteur = "SELECT * FROM Auteur AS a WHERE a.auteurNom=?";
    private static final String reqLivreAuteur = "select l.livreISBN, l.livreTitre, photo.imageLivre from livre AS l JOIN photo ON photo.imageId=l.livreURLImage join ecriture ON ecriture.livreISBN=l.livreISBN join auteur ON ecriture.auteurId=auteur.auteurId where auteur.auteurNom=?";
//    private static final String reqISBN = "SELECT l.livreISBN FROM livre AS l, recherche AS r WHERE l.livreISBN=r.livreISBN AND r.motCleId=?";

    public AuteurDao() throws NamingException {
        InitialContext ic = new InitialContext();
        ds = (DataSource) ic.lookup(pool);
    }

    public List<Livre> rechercheAuteur(String auteur) throws NamingException {
        String nom = "";
        List<Livre> mesLivres = new ArrayList<>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqNomAuteur);
            pstm.setString(1, auteur);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                nom = rs.getString("auteurNom");
            }
            if (!nom.isEmpty()) {
                pstm = cnt.prepareStatement(reqLivreAuteur);
                pstm.setString(1, nom);
                rs = pstm.executeQuery();
                while (rs.next()) {
//                    Livre l = new Livre();
//                    l.setISBN(rs.getString("livreISBN"));
//                    l.setTitre(rs.getString("livreTitre"));
//                    l.setUrlImage(rs.getString("imageLivre"));
//                    mesLivres.add(l);
                    LivreDao ldao = new LivreDao();
                    Livre l = ldao.rechercheLivre(rs.getString("livreISBN"));
                    mesLivres.add(l);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        return mesLivres;
    }

}
