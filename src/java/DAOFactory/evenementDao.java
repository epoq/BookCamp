package DAOFactory;

import entites.Livre;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author yoann g
 */
public class evenementDao implements Serializable {

    private DataSource ds;
    private static final String REQ_NOMRUBRIQUE = "Select  distinct rubriqueIntitule From rubrique";
    private static final String REQ_ISBNRUBRIQUE = "Select livreISBN from evenement JOIN rubrique on rubrique.rubriqueId=evenement.rubriqueId where rubriqueIntitule=?";

    public evenementDao() throws NamingException {
        ds = LibrairieConnection.getDataSource();

    }

    public HashMap<String, ArrayList<Livre>> allEvenement() throws NamingException {
        HashMap<String, ArrayList<Livre>> map = new HashMap<String, ArrayList<Livre>>();
        LivreDao ldao=new LivreDao();                
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement ps = cnt.prepareStatement(REQ_NOMRUBRIQUE);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> rubrique = new ArrayList();
            while (rs.next()) {
                rubrique.add(rs.getString("rubriqueIntitule"));
            }
            for (String ru : rubrique) {
                ps = cnt.prepareStatement(REQ_ISBNRUBRIQUE);
                ps.setString(1, ru);
                rs = ps.executeQuery();
                ArrayList<String> isbn = new ArrayList<String>();
                while (rs.next()) {
                    isbn.add(rs.getString("livreISBN"));
                }
                
                map.put(ru, (ArrayList<Livre>) ldao.rechercheLivre(isbn));
            }

        } catch (SQLException ex) {
            Logger.getLogger(evenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

}
