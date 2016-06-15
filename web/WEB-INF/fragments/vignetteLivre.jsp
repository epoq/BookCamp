<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<div class="vignette">
    <a href="Controleur?section=detailLivre&choix=${livreAffichable.ISBN}" >
        <p>
            <img src="ImagesLivres/${livreAffichable.urlImage}" alt="image" title=""/>
        </p>
        <p > 
            <c:out value="${livreAffichable.titre}"/>
        </p>
    </a>
    <c:choose >
        
        <c:when test="${livreAffichable.prixHT!=livreEv.prixHT}">
            <s> <p><fmt:formatNumber  value="${livreAffichable.prixHT}"/> €</p></s> 

            <p id="prixReduit"><fmt:formatNumber type="currency" currencySymbol="€" value="${livreEv.prixHT}"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:formatNumber type="currency" value="${livreAffichable.prixHT}"/> </p>
        </c:otherwise>
    </c:choose>
            
    <p><a href="Controleur?section=panier&choix=${livreAffichable.ISBN}">Ajouter au panier </a></p>

</div>