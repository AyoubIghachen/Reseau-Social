<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="LoginPackage.*"%>
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
#text{
    height: 39px; width: 460px; resize:none;
}
#Description{
    height: 100px; width: 460px; resize:none;
}
</style>
<style type="text/css"><%@ include file = "/WEB-INF/style.css" %></style>
</head>
<body>
<%@ include file = "/WEB-INF/navbar.jsp" %>
<div class="topnav">
  <a href ="AcceuilServlet" target="topFrame">Accueil</a>
  <a href ="ActualiteServlet" target="topFrame">Actualite</a>
  <a class="active" href ="ProfilServlet" target="topFrame">Profil</a>
  <a href ="RechercheServlet" target="topFrame">Recherche</a>
</div>
<br>



<p><img class="fit-picture" src="http://localhost:8080/NecessaryCodeToUse/images/${profil.getPhotoProfil()}" alt="image Profil"></p>

<form action="EditProfilServlet" method="post" enctype="multipart/form-data">
  <label for="image">Changer la photo de profil :</label>
  <input type="file" id="image" name="image" disabled><br>
  
  <label for="Username">Username :</label>
  <input type="text" id="Username" name="Username" value="${profil.getUsername()}" readonly><br>
  
  <label for="Nom">Nom :</label>
  <input type="text" id="Nom" name="Nom" value="${profil.getNom()}" disabled><br>
  
  <label for="Description">Description :</label>
  <textarea id="Description" name="Description" disabled>${profil.getDescription()}</textarea><br>
  
  <label for="Loisirs">Loisirs :</label>
  <input type="text" id="Loisirs" name="Loisirs" value="${profil.getLoisirs()}" disabled><br>

  <label for="Ville">Ville :</label>
  <input type="text" id="Ville" name="Ville" value="${profil.getVille()}" disabled><br>

  <button type="button" id="editButton" onclick="enableFields()">Edit</button>
  <button type="submit" id="saveButton" disabled>Save</button>
</form>
<br><br><br>



<%@ include file = "/WEB-INF/CreatePublication.jsp" %>
<br><br><br>



<div>
<p>Publications :</p>

<c:forEach items="${profil.getListPublication()}" var="Publication">
<%@ include file = "/WEB-INF/Publication.jsp" %>
</c:forEach>

</div>
<br><br>



<script>
function enableFields() {
  document.getElementById("image").disabled = false;
  document.getElementById("Nom").disabled = false;
  document.getElementById("Description").disabled = false;
  document.getElementById("Loisirs").disabled = false;
  document.getElementById("Ville").disabled = false;
  
  document.getElementById("editButton").disabled = true;
  document.getElementById("saveButton").disabled = false;
}
</script>

<script src="${pageContext.request.contextPath}/sendData.js"></script>
</body>
</html>