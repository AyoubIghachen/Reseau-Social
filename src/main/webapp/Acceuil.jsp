<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="LoginPackage.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acceuil</title>
</head>
<body>
<%@ include file = "/WEB-INF/navbar.jsp" %>
<div class="topnav">
  <a class="active" href ="AcceuilServlet" >Accueil</a>
  <a href ="ActualiteServlet" target="topFrame">Actualite</a>
  <a href ="ProfilServlet" target="topFrame">Profil</a>
  <a href ="RechercheServlet" target="topFrame">Recherche</a>
</div>
<br>


<p>Chercher les publications suivants :</p>

<div>
<form action="OrderPublicationServlet" id="formulaire" method="get">
<label for="OrderBy" >Ordonner suivant : </label><br>
  <input type="checkbox" id="Order1" name="OrderBy" value="Last">
  <label for="Order1"> Publications récentes</label><br>
  <input type="checkbox" id="Order2" name="OrderBy" value="Populaire">
  <label for="Order2"> Publications populaires</label><br>
 <br>
</form>
<button type="submit" form="formulaire" value="Submit" >Afficher</button>
</div>





</body>
</html>