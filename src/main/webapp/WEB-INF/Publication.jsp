<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    
<div class="publication-container">
  <div class="publication-header">
    <img class="profile-image" src="http://localhost:8080/NecessaryCodeToUse/images/${Publication.getPhotoProfil()}" alt="image Profil">
    <div class="publication-info">
      <p class="name">${Publication.getNom()}</p>
      <p class="date">${Publication.getDateTime()}</p>
    </div>
  </div>
  <p class="text">${Publication.getTexte()}</p>
  <img class="publication-image" src="http://localhost:8080/NecessaryCodeToUse/images/${Publication.getPhoto()}" alt="image Publication">
  <div class="publication-footer">
    <button class="like-btn" onclick="sendData('POST','/ReseauSocialE2/LikePublicationServlet', 'ID_empty', 'ID_Publication=${Publication.getID_Publication()}')">Like</button>
    <p class="likes">${Publication.getListAimer().size()} people like this.</p>
  </div>
  <div class="comment-section">
    <input type="text" id="Commentaire${Publication.getID_Publication()}" class="comment-input" placeholder="Write a comment...">
    <button class="comment-btn" onclick="sendData('POST','/ReseauSocialE2/AddCommenterServlet', 'Commentaire${Publication.getID_Publication()}', 'ID_Publication=${Publication.getID_Publication()}&Commentaire=')">Ajouter</button>
    <div class="comment-list">
      <c:forEach items="${Publication.getListCommentaire()}" var="Commentaire">
        <div class="comment">
          <p class="comment-info">${Commentaire.getNom()}  | ${Commentaire.getDateTime()}</p>
          <p class="comment-text">${Commentaire.getCommentaire()}</p>
        </div>
      </c:forEach>
    </div>
  </div>
</div>

<br><br>
