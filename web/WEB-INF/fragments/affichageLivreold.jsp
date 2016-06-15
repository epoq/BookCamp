<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="0">
        <c:set scope="request" var="compteur" value="0"/>
        <c:forEach var="l" items="${sessionScope.mesLivres}">
            <c:if test="${compteur==0}">
                <tr>
                </c:if> 
                <td>
                    <a href="">
                        <div style="width: 250px; margin-right: 20px">
                            <center>
                                <img style="text-align: center" src="ImagesLivres/${l.urlImage}" width="100px" height="120px"/>
                            </center>
                            <h4 style="text-align: center;"><c:out value="${l.titre}"/></h4>
                        </div>
                    </a>
                </td>
                <c:if test="${compteur==5}">
                </tr>
            </c:if>
            <c:set scope="request" var="compteur" value="${compteur+1}"/>
            <c:if test="${compteur==5}">
                <c:set scope="request" var="compteur" value="0"/>
            </c:if>    
        </c:forEach>
    </table>
</div>