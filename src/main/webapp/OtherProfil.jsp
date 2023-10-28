<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${profil.getNom()}</title>
<style>
.fit-picture {
    width: 250px;
}
.fit-Mini-picture {
    width: 30px;
}
#TextPublication{
    height: 39px; width: 460px; resize:none;
}
</style>
<style type="text/css"><%@ include file = "/WEB-INF/style.css" %></style>
</head>
<body>
<%@ include file = "/WEB-INF/navbar.jsp" %>
<div class="topnav">
  <a href ="AcceuilServlet" target="topFrame">Accueil</a>
  <a href ="ActualiteServlet" target="topFrame">Actualite</a>
  <a href ="ProfilServlet" target="topFrame">Profil</a>
  <a href ="RechercheServlet" target="topFrame">Recherche</a>
</div>
<br>


<p><img class="fit-picture" src="http://localhost:8080/NecessaryCodeToUse/images/${profil.getPhotoProfil()}" alt="image Profil"></p>
<p><c:out value="Username: ${profil.getUsername()}"> </c:out></p>
<p><c:out value="Nom: ${profil.getNom()}"> </c:out></p>
<p><c:out value="Description: ${profil.getDescription()}"> </c:out></p>
<p><c:out value="Loisirs: ${profil.getLoisirs()}"> </c:out></p>
<p><c:out value="Ville: ${profil.getVille()}"> </c:out></p>
<br><br><br><br>



<div>
<p>Publications :</p>

<c:forEach items="${profil.getListPublication()}" var="Publication">
<%@ include file = "/WEB-INF/Publication.jsp" %>
</c:forEach>

</div>
<br><br>




<script src="${pageContext.request.contextPath}/sendData.js"></script>
</body>
</html>