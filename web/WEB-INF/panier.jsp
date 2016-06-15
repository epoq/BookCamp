<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/owl.carousel.css" rel="stylesheet" type="text/css"/>
        <link href="css/responsive.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:url value="Controleur?section=menuMain" var="url" />
        <c:import url="${url}" />
    <center><h2>Choix du panier</h2></center>
    <table border ="1">
        <tr>
            <td>Titre</td>
            <td>Sous Titre</td>
            <td>Quantité</td>
            <td>Prix</td>
        </tr>
        <c:forEach var="livre" items="${sessionScope.panier.mesLivres}" >
            <tr>
                <td><c:out value="${livre.titre}"/></td>
            <td><c:out value="${livre.sousTitre}"/></td>
            <td></td>
            <td><c:out value="${livre.prixHT}"/>€</td>
        </tr>

        </c:forEach>

    </table>



</body>
</html>
