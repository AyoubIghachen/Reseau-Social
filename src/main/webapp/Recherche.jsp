<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="LoginPackage.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recherche</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.fit-Mini-picture {
    width: 100px;
}
</style>
</head>
<body>
<%@ include file = "/WEB-INF/navbar.jsp" %>
<div class="topnav">
  <a href ="AcceuilServlet" target="topFrame">Accueil</a>
  <a href ="ActualiteServlet" target="topFrame">Actualite</a>
  <a href ="ProfilServlet" target="topFrame">Profil</a>
  <a class="active" href ="RechercheServlet" target="topFrame">Recherche</a>
</div>
<br>





<form action="RechercheServlet" id="SearchForm" method="post">
  <label for="IdTextSearch" >Search by Name :</label><br>
  <input type="text" id="IdTextSearch" name="TextSearch" placeholder="type to search"><br><br>
</form>
<button type="submit" form="SearchForm" value="Submit" name="">Find</button>
<br><br>



<div>
    <p>Result :</p>
    <c:forEach items="${ListProfil.getListProfil()}" var="ProfilUser">
        <ul>
            <li> <img class="fit-Mini-picture" src="http://localhost:8080/NecessaryCodeToUse/images/${ProfilUser.getPhotoProfil()}" alt="image Profil">
                <form action="OtherProfilServlet" method="get"> <input value="${ProfilUser.getNom()}" disabled="disabled"> <button type="submit" value="${ProfilUser.getUsername()}" name="otherUser">Voir profil</button> </form>
                <div> <button class="follow-btn" data-user-id="${ProfilUser.getUsername()}" data-follow-state="${ProfilUser.isFollowState()}"> ${ProfilUser.isFollowState() ? 'Unfollow' : 'Follow'} </button> </div>
            </li>
        </ul>
    </c:forEach>
</div>



<script type="text/javascript">
$(document).ready(function() {
    $('.follow-btn').click(function() {
    	var button = $(this); // save reference to the button
        var userId = button.data('user-id');
        var followState = button.data('follow-state');

        $.ajax({
            url: '/ReseauSocialE2/FollowButtonServlet',
            method: 'POST',
            data: {
                userId: userId,
                followState: followState
            },
            success: function(response) {
                followState = !followState;
                button.data('follow-state', followState);
                button.text(followState ? 'Unfollow' : 'Follow');
            }
        });
    });
});
</script>
</body>
</html>