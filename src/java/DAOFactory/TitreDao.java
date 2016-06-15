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

public class TitreDao implements Serializable {

    private DataSource ds;

    public static final String pool = "jdbc/librairie";
    private static final String reqIsbnLivre = "SELECT * FROM Livre WHERE livreTitre=?";
    private static final String reqTitreLivre = "SELECT LivreTitre FROM Livre WHERE livreisbn=?";
    private static final String reqLivreAuteur = "select l.livreISBN, l.livreTitre, photo.livreImage from livre AS l JOIN photo ON photo.imageId=l.livreURLImage join ecriture ON ecriture.livreISBN=l.livreISBN join auteur ON ecriture.auteurId=auteur.auteurId where l.livreTitre=?";

    public TitreDao() throws NamingException {
        InitialContext ic = new InitialContext();
        ds = (DataSource) ic.lookup(pool);
    }

    public List<Livre> rechercheIsbnLivre(String titre) throws NamingException {
        List<Livre> mesLivres = new ArrayList<>();
        try (Connection cnt = ds.getConnection();) {

            if (!titre.isEmpty()) {
                PreparedStatement pstm = cnt.prepareStatement(reqIsbnLivre);
                pstm.setString(1, titre);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
//                    Livre l = new Livre();
//                    l.setISBN(rs.getString("livreISBN"));
//                    l.setTitre(rs.getString("livreTitre"));
//                    mesLivres.add(l);
//                    System.out.println("++++++++++++");
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
    
    public List<Livre> rechercheTitreLivre(String isbn) {
        String titre = "";
        List<Livre> mesLivres = new ArrayList<>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqIsbnLivre);
            pstm.setString(1, isbn);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                titre = rs.getString("livreTitre");
            }
            if (!titre.isEmpty()) {
                pstm = cnt.prepareStatement(reqLivreAuteur);
                pstm.setString(1, titre);
                rs = pstm.executeQuery();
                while (rs.next()) {
                     Livre l = new Livre();
                    l.setISBN(rs.getString("livreISBN"));
                    l.setTitre(rs.getString("livreTitre"));
                    l.setUrlImage(rs.getString("imageLivre"));
                    mesLivres.add(l);
                }
//                return titre;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        return mesLivres;
    }
}
