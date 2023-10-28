<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualite</title>
<style>
.fit-picture {
    width: 250px;
}
.fit-Mini-picture {
    width: 30px;
}
</style>
<style type="text/css"><%@ include file = "/WEB-INF/style.css" %></style>
</head>
<body>
<%@ include file = "/WEB-INF/navbar.jsp" %>
<div class="topnav">
  <a href ="AcceuilServlet" target="topFrame">Accueil</a>
  <a class="active" href ="ActualiteServlet" target="topFrame">Actualite</a>
  <a href ="ProfilServlet" target="topFrame">Profil</a>
  <a href ="RechercheServlet" target="topFrame">Recherche</a>
</div>
<br>



<div>
<p>Publications :</p>

<c:forEach items="${ListPublication}" var="Publication">
<%@ include file = "/WEB-INF/Publication.jsp" %>
</c:forEach>

</div>
<br><br>



<script src="${pageContext.request.contextPath}/sendData.js"></script>
</body>
</html>