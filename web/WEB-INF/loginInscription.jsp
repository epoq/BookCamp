<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
    <center>
        <h1><a href="./"><img height="" width="" src="img/bookcamp.png"></a></h1>
    </center>
    <center>
        <c:if test="${empty sessionScope.inscription}">
        <h2>Identifiez vous</h2>
        <form method="GET" action="Controleur">
            <input type="hidden" name="section" value="loginInscription"/>
            <c:if test="${not empty sessionScope.erreurLogin}">
                <h4 style="color : red"><c:out value="${erreurLogin}"/></h4>
            </c:if>
            <center><input placeholder="Email" name="Email" type="text"/></center></br>
            <center><input placeholder="Mot de Passe" name="mdp" type="password"/></center></br>
            <center><input type="submit" value="Valider" name="Valider"/></center>
        </form>
        <center><a href="Controleur?section=loginInscription&inscription=y">Nouveau Compte ?</a></center>
        </c:if>
        <c:if test="${not empty sessionScope.inscription}">
        <center>
            <h2>Nouvelle inscription</h2>
            <c:if test="${not empty sessionScope.loginExiste}">
                <h4 style="color : red"><c:out value="${sessionScope.loginExiste}"/></h4>
            </c:if>
            <form method="GET" action="Controleur">
            <input type="hidden" name="section" value="loginInscription"/>
            <input type="hidden" name="inscription" value="nouveau"/>
            <c:if test="${not empty sessionScope.errnom}"><h4 style="color : red"><c:out value="${sessionScope.errnom}"/></h4></c:if>
            <center><input placeholder="${sessionScope.nomSaisi}" name="nom" type="text" value="${sessionScope.nomSaisi=="Nom" ? "" : nomSaisi }"/></center></br>
            <c:if test="${not empty sessionScope.errprenom}"><h4 style="color : red"><c:out value="${sessionScope.errprenom}"/></h4></c:if>
            <center><input placeholder="${sessionScope.prenomSaisi}" name="prenom" type="text" value="${sessionScope.prenomSaisi=="Prenom" ? "" : prenomSaisi }"/></center></br>
            <c:if test="${not empty sessionScope.erremail}"><h4 style="color : red"><c:out value="${sessionScope.erremail}"/></h4></c:if>
            <center><input placeholder="${sessionScope.emailSaisi}" name="Email" type="text" value="${sessionScope.emailSaisi=="Email" ? "" : emailSaisi }"/></center></br>
            <c:if test="${not empty sessionScope.errmdp}"><h4 style="color : red"><c:out value="${sessionScope.errmdp}"/></h4></c:if>
            <center><input placeholder="Mot de Passe" name="mdp" type="password"/></center></br>
            <center><input type="submit" value="Valider" name="Valider"/></center>
        </form>
        </center>
        </c:if>
    </center>
</body>
</html>
