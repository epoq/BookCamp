<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="0">
        <c:set scope="request" var="compteur" value="0"/>
        <c:forEach var="livre"  items="${sessionScope.mesLivres}">
            <c:if test="${compteur==0}">
                <tr>
                </c:if> 
                <td>
                   
                     <c:set scope="request" var="livre" value="${livre}"/>
                    <c:url value="Controleur?section=vignetteLivre" var="url"/>
                    <c:import url="${url}"/>
                    
                </td>
                <c:if test="${compteur==5}">
                </tr>
                <c:set scope="request" var="compteur" value="0"/>
            </c:if>
            <c:set scope="request" var="compteur" value="${compteur+1}"/>   
        </c:forEach>
    </table>
</div>
        
        
        
        
        
        
     
        <!-- <a href="">
                        <div style="width: 250px; margin-right: 20px">
                            <center>
                                <img style="text-align: center" src="ImagesLivres/ ${l.urlImage}" />
                            </center>
                            <h4 style="text-align: center;"><c:out value="${l.titre}"/></h4>
                        </div>
                    </a>
        -->
        