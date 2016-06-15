<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Détail du livre ${monLivre.titre}</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/owl.carousel.css" rel="stylesheet" type="text/css"/>
        <link href="css/responsive.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
<body>
    <c:url value="Controleur?section=menuMain" var="url" />
    <c:import url="${url}" />
<div class="detail">
       
            <p>
                <img src="ImagesLivres/${sessionScope.monLivre.urlImage}" alt="image" title=""/>
            </p>
            <p id="titre"> 
                <c:out value="${sessionScope.monLivre.titre}"/>
            </p>
            
            <c:forEach var="auteur"  items="${monLivre.auteurs}">
               Auteur: <c:out value="${auteur.nom}"/>
           <c:out value="${auteur.prenom}"/>
               
            </c:forEach>
            
            <p>
               Editeur: <c:out value="${sessionScope.monLivre.editeur}"/>
            </p>
            
            <p>
                <c:out value="${sessionScope.monLivre.resume}"/>
            </p>
            
            <p>
               Quantité: <c:out value="${sessionScope.monLivre.quantiteDisponible}"/>
            </p>
            
            <p>
                <c:out value="${sessionScope.monLivre.nbrePages}"/> pages
            </p>
            
            <p>
                <c:out value="${sessionScope.monLivre.dimensions}"/> cm
            </p>
            
               <c:choose >
        
        <c:when test="${monLivre.prixHT!=livreEvent.prixHT}">
            <s> <p><fmt:formatNumber  value="${monLivre.prixHT}"/> €</p></s> 

            <p id="prixReduit"><fmt:formatNumber type="currency" currencySymbol="€" value="${livreEvent.prixHT}"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:formatNumber type="currency" value="${monLivre.prixHT}"/> </p>
        </c:otherwise>  
    </c:choose>
            <p>magda's code</p>
            <p>
        <fmt:formatNumber  value="${sessionScope.monLivre.prixHT}"/> €
            </p>
        <p><a href="Controleur?section=panier choix=${sessionScope.monLivre.ISBN}">Ajouter au panier </a></p>
 
    </div>
</body>
</html>