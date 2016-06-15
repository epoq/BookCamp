/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFactory;

import entites.Livre;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import javax.sql.DataSource;

/**
 *
 * @author cdi115
 */
public class ThemeDao implements Serializable {

    private DataSource ds;
    private static final String REQ_TH = "SELECT themeId FROM THEME WHERE themeLibelle=?";
    private static final String REQ_STH = "SELECT sousThemeId FROM SOUSTHEME WHERE themeid=?";
    private static final String REQ_ISBN = "SELECT livreISBN FROM Contenance WHERE sousThemeId=?";
    //private static final String REQ_JOIN = "SELECT * FROM LIVRE JOIN CONTENANCE ON LIVRE.livreISBN=CONTENANCE.livreISBN JOIN SousTHEME on CONTENANCE.SOUSTHEMEID=SOUSTHEME.SOUSTHEMEID JOIN THEME ON SOUSTHEME.THEMEID=THEME.THEMEID WHERE THEMELIBELLE=?";
    private static final String REQ_JOIN = "SELECT l.livreISBN, l.livreTitre, l.livreSousTitre, l.livrePrixHT, l.livreResume, photo.imageLivre FROM livre AS l JOIN contenance AS c ON l.livreISBN=c.livreISBN JOIN soustheme AS st ON c.sousThemeId=st.sousThemeId JOIN theme AS t ON st.themeId=t.themeId JOIN photo ON photo.imageId=l.livreURLImage WHERE t.themeLibelle=?";
    
    
    public ThemeDao() throws NamingException {
        ds = LibrairieConnection.getDataSource();
    }

    public List<Livre> rechercheLivre(String theme) throws SQLException {
        List<Long> sousTheme = new ArrayList<Long>();
        List<String> isbn = new ArrayList<String>();
        List<Livre>livre=new ArrayList<Livre>();
        String themeId = "";
        //*******************************************************
        //*code sous requete join 
        /*
        Connection cnt = ds.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(REQ_TH);
        pstm.setString(1, theme);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            themeId = rs.getString("themeId");

        }
        if (!themeId.isEmpty()) {
            pstm = cnt.prepareStatement(REQ_STH);
            pstm.setString(1, themeId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sousTheme.add(rs.getLong("sousThemeId"));

            }

        }
        if (!sousTheme.isEmpty()) {
            for (Long l : sousTheme) {
                pstm = cnt.prepareStatement(REQ_ISBN);
                pstm.setLong(1, l);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    isbn.add(rs.getString("livreISBN"));
                }
            }
        }
        
        */
        Connection cnt = ds.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(REQ_JOIN);
        pstm.setString(1, theme);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            String isbnlivre=rs.getString("LivreISBN");
            String titre=rs.getString("livreTitre");
            if(rs.getString("LivresousTitre")==null){
                String sousTitre="";
                
            }
            String sousTitre=rs.getString("livresousTitre");
            String url=rs.getString("imageLivre");
            String resume=rs.getString("livreresume");
            float prix=rs.getFloat("livrePrixHT");
            Livre li= new Livre(isbnlivre,titre,sousTitre,url,resume,prix);
            livre.add(li);
        }
        return livre;

    }

}
