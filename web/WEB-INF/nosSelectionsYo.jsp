<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nos Selections</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/owl.carousel.css" rel="stylesheet" type="text/css"/>
        <link href="css/responsive.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:url value="Controleur?section=menuMain" var="url" />
        <c:import url="${url}" />
        <form method="POST" action="Controleur?section=nosSelections" accept-charset="UTF-8">
            <input placeholder="Rechercher" type="text" name="demande" />
            <select name="selection">
                <option value="ISBN">ISBN</option>
                <option value="titre">Titre</option>
                <option value="auteur">Auteur</option>
                <option value="theme">Theme</option>
                <option value="motcle">Mot clé</option>
            </select>
            <input type="submit" value="rechercher" name="rechercher" />
        </form>
        <!-- on regarde si l'attribut meslivres n'es pas null et on fait un foreach pour chaque objet livre dans l'arrayList mes Livres -->
        <c:if test="${ requestScope.mesLivres!=null}" >
            <c:forEach var ="livre" items="${requestScope.mesLivres}">
                <c:out value="${livre.ISBN}" />
                <br/>
                <c:out value="${livre.titre}" />
                <br/>
                <c:out value="${livre.sousTitre}" />
                <c:out value="${livre.resume}" />
                <br/>
                <img src="" alt="image"/>
                <p> **********************</p></br>
            </c:forEach>
            </c:if>
            
        </body>
    </html>
