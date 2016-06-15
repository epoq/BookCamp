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
    <center>
        <form method="GET" action="Controleur">
            <input type="hidden" name="section" value="nosSelections"/>
            <input placeholder="Rechercher" name="demande" type="text"/>
            <select name="selection">
                <option value="ISBN">ISBN</option>
                <option value="Titre">Titre</option>
                <option value="Auteur">Auteur</option>
                <option value="Theme">Theme</option>
                <option value="MotCle">Mot clé</option>
            </select>
            <input type="submit" value="rechercher" name="rechercher" />
        </form>
    </center>
    <c:url value="Controleur?section=affichageLivre" var="url" />
    <c:import url="${url}" />
</body>
</html>
