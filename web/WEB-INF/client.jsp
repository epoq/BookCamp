<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace client</title>
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
        </br>
        <form method="GET" action="Controleur">
            <input type="hidden" name="section" value="client"/>
            <c:if test="${not empty sessionScope.erreurPrenomClient}">
                <h4 style="color : red"><c:out value="${sessionScope.erreurPrenomClient}"/></h4>
            </c:if>
            <input placeholder="Prenom" name="PrenomClient" type="text" value="${sessionScope.user.prenom}"/></br>
            <c:if test="${not empty sessionScope.erreurNomClient}">
                <h4 style="color : red"><c:out value="${sessionScope.erreurNomClient}"/></h4>
            </c:if>
            <input placeholder="Nom" name="NomClient" type="text" value="${sessionScope.user.nom}"/></br>
            <c:if test="${not empty sessionScope.updateSucces}">
                <h4 style="color : greenyellow"><c:out value="${sessionScope.updateSucces}"/></h4>
                <c:remove var="updateSucces" scope="session"/>
            </c:if>
            <input type="submit" value="Modifier" name="Modifier"/>
        </form>
        <c:forEach var="adresse"  items="${sessionScope.user.adresses}">
            <p>Adresse : ${adresse}</p>
        </c:forEach>
        <c:if test="${not empty sessionScope.adresseSucces}">
            <h4 style="color : greenyellow"><c:out value="${sessionScope.adresseSucces}"/></h4>
            <c:remove var="adresseSucces" scope="session"/>
        </c:if>    
        <c:if test="${empty sessionScope.AjouterAdresse}">
            <form method="GET" action="Controleur">
                <input type="hidden" name="section" value="client"/>
                <input type="submit" value="Ajouter une adresse" name="Ajouter"/>
            </form>
        </c:if>
        <c:if test="${not empty sessionScope.AjouterAdresse}">
            <c:url value="Controleur?section=ajouterAdresse" var="url" />
            <c:import url="${url}" />
        </c:if>

    </center>
</body>
</html>
