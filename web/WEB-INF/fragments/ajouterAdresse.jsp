<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <center>
        <form method="GET" action="Controleur">
            <input type="hidden" name="section" value="client"/>
            <c:if test="${not empty sessionScope.erreurAdresse}">
                <h4 style="color : red"><c:out value="${sessionScope.erreurAdresse}"/></h4>
                <c:remove var="erreurAdresse" scope="session"/>
            </c:if>
            <input placeholder="N°Boite aux lettres" name="numeroBoiteAuxLettres" type="text"/></br>
            <input placeholder="N° de voie" name="adresseNumeroVoie" type="text"/></br>
            <input placeholder="Complement d'adresse" name="adresseComplement" type="text"/></br>
            <input placeholder="Code Postal" name="adresseCodePostal" type="text"/></br>
            <input placeholder="Ville" name="adresseVille" type="text"/></br>
            <input placeholder="Pays" name="adressePays" type="text"/></br>
            <input type="submit" value="Ajouter l'adresse" name="creerAdresse"/>
        </form>
    </center>
</div>
