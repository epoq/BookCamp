<%-- 
    Document   : rubrique
    Created on : 18 mai 2016, 10:49:17
    Author     : yoann g 
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/owl.carousel.css" rel="stylesheet" type="text/css"/>
        <link href="css/responsive.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nos evenements du moment</title>
    </head>
    <body>
        <c:url value="Controleur?section=menuMain" var="url" />
        <c:import url="${url}" />  
        <c:set scope="request" var="compteur" value="0"/>     
        <c:forEach var="ev" items="${evenements}">
            <div >
                <c:if test="${ not empty ev.value}">
                <h2 id="titreRubrique"> ${ev.key}</h2>
                </c:if>
                <div class="rubrique">
                <c:set var="livres" scope="page"  value="${ev.value}"/>
                <table>
                    <c:set var="count"  scope="request" value="0"/>

                    <c:forEach var ="l"  items="${livres}">
                        
              <c:if test="${count==0}">
                                    <tr>
                                    </c:if>
                                    <td>
                                        <c:set scope="request" var="livre" value="${l}"/>
                                        <c:url value="Controleur?section=vignetteLivre" var="url"/>
                                        <c:import url="${url}"/>
                                    </td>
                                    <c:if test="${count==4}">
                                    </tr>
                                    <c:set scope="request" var="count" value="0"/>
                                </c:if>
                                <c:set scope="request" var="count" value="${count+1}"/>
                    </c:forEach>

                </table>
                </div>
            </div>
        </c:forEach>
    </body>
</html>


<!--

<c:if test="${compteur==0}">
                            <tr>
</c:if> 
<td>
    <div class="vignette">
        <a href="" >
            <p>
                <img src="ImagesLivres/${l.urlImage}" alt="image" title=""/>
            </p>
            <p id="titre"> 
<c:out value="${l.titre}"/>
</p>
</a>
<p><fmt:formatNumber  value="${l.prixHT}"/> €</p>
<p><a href="Controleur?section=panier choix=${sessionScope.l}">Ajouter au panier </a></p>
</div>
</td>
<c:if test="${compteur==5}">
</tr>
</c:if>
<c:set scope="request" var="compteur" value="${compteur+1}"/>
<c:if test="${compteur==5}">
    <c:set scope="request" var="compteur" value="0"/>
</c:if> 
-->
