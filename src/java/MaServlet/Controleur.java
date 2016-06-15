package MaServlet;

import DAOFactory.AuteurDao;
import DAOFactory.ClientDAO;
import DAOFactory.LivreDao;
import DAOFactory.ThemeDao;
import DAOFactory.TitreDao;
import DAOFactory.evenementDao;
import entites.Auteur;
import entites.Client;
import entites.Livre;
import entites.Panier;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String prefixe = "/WEB-INF/";
        String suffixe = ".jsp";
        String url = "accueil";
        String section = request.getParameter("section");
        String rechercher = request.getParameter("rechercher");
        String demande = request.getParameter("demande");
        String selection = request.getParameter("selection");
        String modifier = request.getParameter("Modifier");
        String ajouter = request.getParameter("Ajouter");
        String creerAdresse = request.getParameter("creerAdresse");
        String email = "Email";
        String nom = "Nom";
        String prenom = "Prenom";
        session.setAttribute("prenomSaisi", prenom);
        session.setAttribute("nomSaisi", nom);
        session.setAttribute("emailSaisi", email);
        email = request.getParameter("Email");
        String mdp = request.getParameter("mdp");
        prenom = request.getParameter("prenom");
        nom = request.getParameter("nom");
        String logout = request.getParameter("logout");
        String inscription = request.getParameter("inscription");
        String choix = request.getParameter("choix");
        LivreDao ldao = null;
        AuteurDao adao = null;
        TitreDao trdao = null;
        Client c = null;
        ClientDAO cDAO = null;
        Panier monPanier = null;

        //* Premiere section Menu Main
        if ("menuMain".equalsIgnoreCase(section)) {
            url = "fragments/menuMain";
        }
        // Fin de premiere section Menu Main */

        //* 2eme section Carousel
        if ("carousel".equalsIgnoreCase(section)) {
            url = "fragments/carousel";
        }
        // Fin de 2eme section Carousel */

        //* 3eme section Nos Selections
        if ("nosSelections".equalsIgnoreCase(section)) {
            List<Livre> mesLivres = new ArrayList<>();
            if ("rechercher".equalsIgnoreCase(rechercher)) {
                if ("Theme".equalsIgnoreCase(selection)) {
                    try {
//                        ThemeDao tdao = new ThemeDao();
//                        mesLivres = new ArrayList<Livre>();
//                        //on appelle la methode rechercheLivre en fct du theme saisi par l'utilisateur
//                        mesLivres = (ArrayList<Livre>) tdao.rechercheLivre(demande);
//                        //on envoi à la request une ArrayList de Livre pour la JSP 
//                        session.setAttribute("mesLivres", mesLivres);
                        ldao = new LivreDao();
                        List<String> mesISBN = ldao.rechercheTheme(demande);
                        mesLivres = ldao.rechercheLivre(mesISBN);
                        session.setAttribute("mesLivres", mesLivres);
                    } catch (NamingException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if ("MotCle".equals(selection)) {
                    try {
                        ldao = new LivreDao();
                        List<String> mesISBN = ldao.rechercheMotCle(demande);
                        mesLivres = ldao.rechercheLivre(mesISBN);
                        session.setAttribute("mesLivres", mesLivres);
                    } catch (NamingException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ("Auteur".equals(selection)) {
                    try {
                        adao = new AuteurDao();
                        mesLivres = adao.rechercheAuteur(demande);
                        session.setAttribute("mesLivres", mesLivres);
                    } catch (NamingException ex) {
                        System.out.println("Erreur " + ex.getMessage());
                    }
                }
                if ("ISBN".equalsIgnoreCase(selection)) {
                    try {
                        ldao = new LivreDao();
                        mesLivres = new ArrayList<Livre>();
                        //on appelle la methode rechercheLivre en fct de l'isbn saisi par l'utilisateur
                        Livre monLivre = ldao.rechercheLivre(demande);
                        mesLivres.add(monLivre);
                        Auteur a = monLivre.getAuteurs().get(0);
                        //on envoi à la request une ArrayList de Livre pour la JSP
                        session.setAttribute("mesLivres", mesLivres);
                    } catch (NamingException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ("Titre".equals(selection)) {
                    try {
                        trdao = new TitreDao();
                        mesLivres = trdao.rechercheIsbnLivre(demande);
                        session.setAttribute("mesLivres", mesLivres);
                    } catch (NamingException ex) {
                        System.out.println("Erreur " + ex.getMessage());
                    }
                }
            }
            url = "nosSelections";
        }
        // Fin de 3eme section Nos Selections */

        //* 4eme section Affichage Livres
        if ("affichageLivre".equalsIgnoreCase(section)) {
            List<Livre> mesLivres = new ArrayList<>();
            List<Livre> mesLivresEvenement=new ArrayList<>();
            if ("rechercher".equals(rechercher)) {
                mesLivres = (List<Livre>) session.getAttribute("mesLivres");
            }
            if (mesLivres.isEmpty()) {
                try {
                    ldao = new LivreDao();
                    mesLivres = ldao.rechercheToutLivre();
                   // mesLivresEvenement=ldao.verifierPrix(mesLivres);
                    
                    session.setAttribute("mesLivres", mesLivres);
                    //session.setAttribute("LivresEvenement",mesLivresEvenement);
                } catch (Exception ex) {
                    System.out.println("Erreur " + ex.getMessage());
                }
            }
            url = "fragments/affichageLivre";
        }
        // Fin de 4eme section Affichage Livres */

        //* 5eme section Login
        if ("loginInscription".equalsIgnoreCase(section)) {
            if (inscription != null) {
                url = "loginInscription";
                session.setAttribute("inscription", "inscription");
                if ("nouveau".equals(inscription)) {
                    session.removeAttribute("errprenom");
                    session.removeAttribute("errnom");
                    session.removeAttribute("erremail");
                    session.removeAttribute("errmdp");
                    boolean erreur = false;
                    if (prenom.equals("")) {
                        session.setAttribute("errprenom", "Prenom est un champ obligatoire");
                        prenom = "Prenom";
                        erreur = true;
                    }
                    if (nom.equals("")) {
                        session.setAttribute("errnom", "nom est un champ obligatoire");
                        nom = "Nom";
                        erreur = true;
                    }
                    if (email.equals("")) {
                        session.setAttribute("erremail", "email est un champ obligatoire");
                        email = "Email";
                        erreur = true;
                    }
                    if (mdp.equals("")) {
                        session.setAttribute("errmdp", "Mot de Passe est un champ obligatoire");
                        erreur = true;
                    }
                    session.setAttribute("prenomSaisi", prenom);
                    session.setAttribute("nomSaisi", nom);
                    session.setAttribute("emailSaisi", email);
                    if (!erreur) {
                        try {
                            cDAO = new ClientDAO();
                            boolean test = cDAO.clientInscription(nom, prenom, mdp, email);
                            if (!test) {
                                session.setAttribute("inscription", "inscription");
                                session.setAttribute("loginExiste", "Cet Email existe déjà ");
                            } else {
                                c = new Client(email, mdp, nom, prenom);
                                session.setAttribute("user", c);
                                url = "accueil";
                                session.removeAttribute("inscription");
                            }
                        } catch (NamingException ex) {
                            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                if (logout != null) {
                    session.removeAttribute("user");
                    session.removeAttribute("erreurLogin");
                    url = "accueil";
                } else {
                    c = (Client) session.getAttribute("user");
                    if (c == null) {
                        url = "loginInscription";
                        try {
                            ClientDAO cdao = new ClientDAO();
                            c = cdao.clientLogin(email, mdp);
                            if (c.getNom() != null) {
                                session.setAttribute("user", c);
                                url = "accueil";
                            } else {
                                if (email != null) {
                                    session.setAttribute("erreurLogin", "Login ou Mot de passe incorrect");
                                }
                            }
                        } catch (NamingException ex) {
                            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        url = "accueil";
                    }
                }
            }

        }
        // Fin de 5eme section Login */

        //* 6eme Section pour affichage des vignettes des livres 
        if("vignetteLivre".equals(section)){
            Livre livre=null;
            Livre livreEvent=new Livre();
            livre=(Livre) request.getAttribute("livre");
            request.setAttribute("livreAffichable", livre);
            
            try{
                ldao=new LivreDao();
            livreEvent=ldao.verifierPrix(livre);
            request.setAttribute("livreEv",livreEvent);
           
            url="fragments/vignetteLivre";
            }
            catch(NamingException ex){
                System.out.println("erreur sql :"+ex.getMessage());
            }
                    
        }
        // fin de section pur affichage des vignettes des livres */

        //* 7eme Section Espace Client
        if ("client".equals(section)) {
            url = "client";
            if ("Ajouter l'adresse".equals(creerAdresse)) {
                String numeroBoiteAuxLettres = request.getParameter("numeroBoiteAuxLettres");
                String adresseNumeroVoie = request.getParameter("adresseNumeroVoie");
                String adresseComplement = request.getParameter("adresseComplement");
                String adresseCodePostal = request.getParameter("adresseCodePostal");
                String adresseVille = request.getParameter("adresseVille");
                String adressePays = request.getParameter("adressePays");
                boolean erreur = false;
                if ("".equals(numeroBoiteAuxLettres)) {
                    erreur = true;
                }
                if ("".equals(adresseNumeroVoie)) {
                    erreur = true;
                }
                if ("".equals(adresseComplement)) {
                    erreur = true;
                }
                if ("".equals(adresseCodePostal)) {
                    erreur = true;
                }
                if ("".equals(adresseVille)) {
                    erreur = true;
                }
                if ("".equals(adressePays)) {
                    erreur = true;
                }
                if (erreur) {
                    session.setAttribute("erreurAdresse", "Tous les champs sont obligatoires");
                } else {
                    try {
                        cDAO = new ClientDAO();
                        c = (Client) session.getAttribute("user");
                        boolean ajoutAdresse = cDAO.clientAdresse(c.getEmail(), numeroBoiteAuxLettres, c.getPrenom(), c.getNom(), adresseNumeroVoie, adresseComplement, adresseCodePostal, adresseVille, adressePays);
                        if (ajoutAdresse){
                            session.removeAttribute("AjouterAdresse");
                            session.setAttribute("adresseSucces", "Adresse ajoutée avec succès.");
                            c.setAdresses(cDAO.clientAdresse(c.getEmail()));
                        }
                    } catch (NamingException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } else {
                if ("Ajouter une adresse".equals(ajouter)) {
                    session.setAttribute("AjouterAdresse", "AjouterAdresse");
                } else {
                    if ("Modifier".equals(modifier)) {
                        session.removeAttribute("erreurPrenomClient");
                        session.removeAttribute("erreurNomClient");
                        session.removeAttribute("updateSucces");
                        String prenomClient = request.getParameter("PrenomClient");
                        String nomClient = request.getParameter("NomClient");
                        boolean erreur = false;
                        if (prenomClient == null || "".equals(prenomClient)) {
                            erreur = true;
                            session.setAttribute("erreurPrenomClient", "Champ Prenom vide !");
                        }
                        if (nomClient == null || "".equals(nomClient)) {
                            erreur = true;
                            session.setAttribute("erreurNomClient", "Champ Nom vide !");
                        }
                        if (!erreur) {
                            try {
                                c = (Client) session.getAttribute("user");
                                cDAO = new ClientDAO();
                                boolean update = cDAO.clientUpdateNomPrenom(nomClient, prenomClient, c.getEmail());
                                if (update) {
                                    session.setAttribute("updateSucces", "Données mises a jour avec succès");
                                    c.setPrenom(prenomClient);
                                    c.setNom(nomClient);
                                    session.setAttribute("user", c);
                                }
                            } catch (NamingException ex) {
                                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }

        }
        // fin de 7eme Section Espace Client  */

        //* 8eme section Ajouter Adresse
        if ("ajouterAdresse".equalsIgnoreCase(section)) {
            url = "fragments/ajouterAdresse";
        }
        // Fin de 8eme section Ajouter Adresse */
        
        // 7eme Section pour choix du panier 
        if ("panier".equalsIgnoreCase(section)) {
            if (choix != null) {
                try {
                    monPanier = (Panier) session.getAttribute("panier");
                    if (monPanier == null) {
                        monPanier = new Panier();
                    }
                    String ISBN = request.getParameter("choix");
                    ldao = new LivreDao();
                    Livre livre = ldao.rechercheLivre(ISBN);

                    monPanier.getMesLivres().add(livre);
                    System.out.println("Mon Panier : " + monPanier.getMesLivres());
                    session.setAttribute("panier", monPanier);

                } catch (SQLException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                url="panier";
            }
        }
        // fin de section pour affichage du panier
        
        //debut section rubrique 
        if("rubrique".equals(section)){
            try {
                evenementDao evDao =new evenementDao();
                HashMap<String,ArrayList<Livre>> map=new HashMap<>();
                map=evDao.allEvenement();
                session.setAttribute("evenements", map);
//                for(Map.Entry<String,ArrayList<String>> mp : map.entrySet()){
//                    
//                }
                        
            } catch (NamingException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            url="rubrique";
        }
        //fin de section rubrique
        
        //DEBUT SECTION DETAIL LIVRE
        if("detailLivre".equalsIgnoreCase(section)){
            Livre monLivre = new Livre();
            Livre livreEvent=new Livre();
        //on appelle la methode rechercheLivre
            choix = request.getParameter("choix");
            try{
                ldao = new LivreDao();
                monLivre = ldao.rechercheLivre(choix);
                livreEvent=ldao.verifierPrix(monLivre);
                
            }catch (SQLException |NamingException ex) {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        //on envoi à la request le livre 
            session.setAttribute("monLivre", monLivre);
            request.setAttribute("livreEvent", livreEvent);
            url= "detailLivre";
        }
        // FIN SECTION DETAIL LIVRE

        url = response.encodeURL(prefixe + url + suffixe);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
