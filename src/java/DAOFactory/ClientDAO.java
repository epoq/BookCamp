package DAOFactory;

import entites.Adresse;
import entites.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClientDAO {

    private static final String reqLogin = "SELECT clientEmail, clientMdp, clientNom, clientPrenom FROM client WHERE clientEmail=? AND clientMdp=?";
    private static final String reqCheckLogin = "SELECT clientEmail FROM client WHERE clientEmail=?";
    private static final String reqInscription = "INSERT INTO client(clientEmail, clientMdp, clientNom, clientPrenom, clientStatut) VALUES (?,?,?,?, '1');";
    private static final String reqAdresse = "SELECT adresseId, adresseNumeroVoie, adresseComplement, adresseCodePostal, adresseVille, adressePays FROM adresse AS a WHERE clientEmail=?";
    private static final String reqUpdate = "UPDATE client SET clientNom=?, clientPrenom=? WHERE clientEmail=?";
    private static final String reqCreationAdresse = "INSERT INTO adresse (clientEmail, numeroBoiteAuxLettres, prenomDestinataire, nomDestinataire, adresseNumeroVoie, adresseComplement, adresseCodePostal, adresseVille, adressePays) VALUES (?,?,?,?,?,?,?,?,?)";
    private DataSource ds;

    public ClientDAO() throws NamingException {
        ds = LibrairieConnection.getDataSource();
    }

    public Client clientLogin(String email, String mdp) {
        Client c = new Client();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqLogin);
            pstm.setString(1, email);
            pstm.setString(2, mdp);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                c = new Client(rs.getString("clientEmail"), rs.getString("clientMdp"), rs.getString("clientNom"), rs.getString("clientPrenom"));
            }
            c.setAdresses(clientAdresse(email));
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public boolean clientInscription(String nom, String prenom, String mdp, String email) {
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqCheckLogin);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return false;
            }
            pstm = cnt.prepareStatement(reqInscription);
            pstm.setString(1, email);
            pstm.setString(2, mdp);
            pstm.setString(3, nom);
            pstm.setString(4, prenom);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public List<Adresse> clientAdresse(String email) {
        List<Adresse> mesAdresses = new ArrayList<Adresse>();
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqAdresse);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Adresse ad = new Adresse(rs.getString("adresseId"), rs.getString("adresseNumeroVoie"), rs.getString("adresseComplement"), rs.getString("adresseCodePostal"), rs.getString("adresseVille"), rs.getString("adressePays"));
                mesAdresses.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesAdresses;
    }

    public boolean clientUpdateNomPrenom(String nom, String prenom, String email) {
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqUpdate);
            pstm.setString(1, nom);
            pstm.setString(2, prenom);
            pstm.setString(3, email);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean clientAdresse(String clientEmail, String numeroBoiteAuxLettres, String prenomDestinataire, String nomDestinataire, String adresseNumeroVoie,String adresseComplement, String adresseCodePostal, String adresseVille, String adressePays){
        try (Connection cnt = ds.getConnection();) {
            PreparedStatement pstm = cnt.prepareStatement(reqCreationAdresse);
            pstm.setString(1, clientEmail);
            pstm.setString(2, numeroBoiteAuxLettres);
            pstm.setString(3, prenomDestinataire);
            pstm.setString(4, nomDestinataire);
            pstm.setString(5, adresseNumeroVoie);
            pstm.setString(6, adresseComplement);
            pstm.setString(7, adresseCodePostal);
            pstm.setString(8, adresseVille);
            pstm.setString(9, adressePays);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
