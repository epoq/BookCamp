<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>
                        <c:if test="${empty sessionScope.user}">
                        <li><a href="Controleur?section=loginInscription"><i class="fa fa-user"></i> Identification</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.user}">
                        <li><a href="Controleur?section=client"><i class="fa fa-user"></i><c:out value="${user.prenom} ${user.nom}"/></a></li>
                        </c:if>
                        <li><a href="Controleur?section=panier"><i class="fa fa-user"></i>Mon Panier</a></li>
                        <li><a href=""><i class="fa fa-user"></i>Paiement</a></li>
                        <c:if test="${not empty sessionScope.user}">
                        <li><a href="Controleur?section=loginInscription&logout=y"><i class="fa fa-user"></i>Log out</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>

            <div class="col-md-4">
                <div class="header-right">
                    
                </div>
            </div>
        </div>
    </div>
</div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="./"><img height="" width="" src="img/bookcamp.png"></a></h1>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="cart.html">Panier - <span class="cart-amunt">0€</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">
                            <c:if test="${not empty sessionScope.panier}"><c:out value="${panier.mesLivres.size()}"/></c:if> </span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div> 
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="Controleur?section=nosSelections">Nos Selections</a></li>
                    <li><a href="Controleur?section=rubrique">Rubrique Evenements</a></li>
                    <li><a href="">FAQ</a></li>
                </ul>
            </div>  
        </div>
    </div>
</div> <!-- End mainmenu area -->

