/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFactory;

import static DAOFactory.MotCleDao.pool;
import entites.Auteur;
import entites.Livre;
import entites.Tva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author cdi115
 */
public class LivreDao {

    public static final String pool = "jdbc/librairie";
    private static final String reqLivre = "SELECT l.livreISBN, l.livreTitre, l.livreSousTitre, l.livreResume, l.livreQuantiteDisponible, p.imageLivre, l.livreStatut,l.livrePrixHT , l.livreDimensions, l.livreNbrePages, l.livrePoids, e.editeurNom FROM livre AS l, photo AS p, publication AS pu, editeur AS e WHERE l.livreURLImage=p.imageId AND l.livreISBN=pu.livreISBN AND pu.editeurId=e.editeurId AND l.livreISBN=?";
    private static final String reqLivreAuteur = "SELECT a.auteurId, a.auteurPrenom, a.auteurNom FROM auteur AS a, ecriture AS e, livre AS l WHERE a.auteurId=e.auteurId AND e.livreISBN=l.livreISBN AND l.livreISBN=?";
    private static final String reqLivreTva = "SELECT t.tvaId, t.tvaTaux FROM livre AS l, tva AS t WHERE t.tvaId=l.tvaId AND l.livreISBN=?";
    private static final String reqToutLivre = "SELECT l.livreISBN FROM livre AS l";
    private static final String reqTheme = "SELECT themeId FROM THEME WHERE themeLibelle=?";
    private static final String reqSousTheme = "SELECT sousThemeId FROM SOUSTHEME WHERE themeid=?";
    private static final String reqThemeISBN = "SELECT livreISBN FROM Contenance WHERE sousThemeId=?";
    private static final String reqMotCle = "SELECT m.motCleId, m.motCleRecherche FROM motCle AS m WHERE m.motCleRecherche=?";
    private static final String reqMotCleISBN = "SELECT l.livreISBN FROM livre AS l, recherche AS r WHERE l.livreISBN=r.livreISBN AND r.motCleId=?";
    private static final String REQ_PRIX = "SELECT rubriqueReduction from rubrique ru JOIN evenement e ON e.rubriqueId=ru.rubriqueId JOIN livre li ON li.livreISBN=e.livreISBN where li.livreISBN=?";
    private DataSource ds;

    public LivreDao() throws NamingException {
        ds = LibrairieConnection.getDataSource();
    }

    public List<String> rechercheMotCle(String motCle) {
        String motCleID = "";
        List<String> ISBN = new ArrayList<String>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqMotCle);
            pstm.setString(1, motCle);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                motCleID = rs.getString("motCleId");
            }
            if (!motCleID.isEmpty()) {
                pstm = cnt.prepareStatement(reqMotCleISBN);
                pstm.setString(1, motCleID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    ISBN.add(rs.getString("livreISBN"));
                }
                return ISBN;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        return ISBN;
    }

    public List<String> rechercheTheme(String theme) throws SQLException {
        List<Long> sousTheme = new ArrayList<Long>();
        List<String> ISBN = new ArrayList<String>();
        List<Livre> livre = new ArrayList<Livre>();
        String themeId = "";
        //*******************************************************
        //*code sous requete join 

        Connection cnt = ds.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(reqTheme);
        pstm.setString(1, theme);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            themeId = rs.getString("themeId");

        }
        if (!themeId.isEmpty()) {
            pstm = cnt.prepareStatement(reqSousTheme);
            pstm.setString(1, themeId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sousTheme.add(rs.getLong("sousThemeId"));

            }

        }
        if (!sousTheme.isEmpty()) {
            for (Long l : sousTheme) {
                pstm = cnt.prepareStatement(reqThemeISBN);
                pstm.setLong(1, l);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    ISBN.add(rs.getString("livreISBN"));
                }
                return ISBN;
            }
        }
        return ISBN;
    }

    public List<Livre> rechercheLivre(List<String> ISBN) {
        List<Livre> mesLivres = new ArrayList<>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqLivre);
            for (String s : ISBN) {
                Livre monLivre = rechercheLivre(s);
                mesLivres.add(monLivre);
            }
            return mesLivres;
        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        return mesLivres;
    }

    public Livre rechercheLivre(String ISBN) throws SQLException {
        Livre l = new Livre();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqLivre);
            pstm.setString(1, ISBN);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                l.setISBN(rs.getString("livreISBN"));
                l.setTitre(rs.getString("livreTitre"));
                l.setSousTitre(rs.getString("livreSousTitre"));
                l.setResume(rs.getString("livreResume"));
                l.setQuantiteDisponible(rs.getInt("livreQuantiteDisponible"));
                l.setUrlImage(rs.getString("imageLivre"));
                l.setStatut(rs.getBoolean("livreStatut"));
                l.setPrixHT(rs.getFloat("livrePrixHT"));
                l.setDimensions(rs.getString("livreDimensions"));
                l.setEditeur(rs.getString("editeurNom"));
                l.setPoids(rs.getFloat("livrePoids"));
                l.setNbrePages(rs.getInt("livreNbrePages"));
                pstm = cnt.prepareStatement(reqLivreAuteur);
                pstm.setString(1, ISBN);
                rs = pstm.executeQuery();
                List<Auteur> mesAuteurs = new ArrayList<>();
                while (rs.next()) {
                    Auteur a = new Auteur(rs.getString("auteurPrenom"), rs.getString("auteurNom"), rs.getString("auteurId"));
                    mesAuteurs.add(a);
                }
                l.setAuteurs(mesAuteurs);
                pstm = cnt.prepareStatement(reqLivreTva);
                pstm.setString(1, ISBN);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Tva tva = new Tva(rs.getFloat("tvaTaux"), rs.getString("tvaId"));
                    l.setTva(tva);
                }
                return l;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());

        }
        return l;
    }

    public List<Livre> rechercheToutLivre() {
        List<Livre> mesLivres = new ArrayList<>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqToutLivre);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String ISBN = rs.getString("livreISBN");
                Livre monLivre = rechercheLivre(ISBN);
                mesLivres.add(monLivre);
            }
            return mesLivres;
        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        return mesLivres;
    }

    public List<Livre> verifierPrix(List<Livre> livres) {
        try (Connection cnt = ds.getConnection();) {
            List<Livre> maj = new ArrayList<>();
            PreparedStatement ps = cnt.prepareStatement(REQ_PRIX);
            for (Livre l : livres) {
                ps.setString(1, l.getISBN());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    float reduction = 0.0f;
                    reduction = rs.getFloat("rubriqueReduction");
                    if (reduction != 0 && l.getPrixHT() != 0) {
                        l.setPrixHT((l.getPrixHT() * reduction) / 100);
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livres;
    }

    public Livre verifierPrix(Livre livre) {
        Livre maj=new Livre();
        try (Connection cnt = ds.getConnection();) {
           
            PreparedStatement ps = cnt.prepareStatement(REQ_PRIX);
             ps.setString(1, livre.getISBN());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    float reduction = 0.0f;
                    reduction = rs.getFloat("rubriqueReduction");
                    if (reduction != 0 && livre.getPrixHT() != 0) {
                        maj.setPrixHT((livre.getPrixHT() * reduction) / 100);
                    }

                }
        } catch (SQLException ex) {
            Logger.getLogger(LivreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maj;
    }
}
