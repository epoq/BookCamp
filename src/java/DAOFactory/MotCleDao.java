package DAOFactory;

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

public class MotCleDao implements Serializable {

    private DataSource ds;
    public static final String pool = "jdbc/librairie";
    private static final String reqMC = "SELECT m.motCleId, m.motCleRecherche FROM motCle AS m WHERE m.motCleRecherche=?";
    private static final String reqISBN = "SELECT l.livreISBN FROM livre AS l, recherche AS r WHERE l.livreISBN=r.livreISBN AND r.motCleId=?";

    public MotCleDao() throws NamingException {
        InitialContext ic = new InitialContext();
        ds = (DataSource) ic.lookup(pool);
    }

    public void rechercheMotCle(String motCle) {
        String motCleID = "";
       List <String> ISBN =new ArrayList<String>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqMC);
            pstm.setString(1, motCle);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                motCleID = rs.getString("motCleId");
            }
            if (!motCleID.isEmpty()) {
                pstm = cnt.prepareStatement(reqISBN);
                pstm.setString(1, motCleID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    ISBN.add(rs.getString("livreISBN")) ;
                }
            }
            for(String s: ISBN){
            }
        } catch (SQLException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
    }
}
