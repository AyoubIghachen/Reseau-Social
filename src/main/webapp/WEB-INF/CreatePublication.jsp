<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
<p>Creer une publication :</p>
<form id="publication-form" enctype="multipart/form-data">
  <label for="text">Texte :</label><br>
  <textarea id="text" name="Commentaire" rows="4" cols="50" placeholder="Saisissez ici..."></textarea>
  <br>
  
  <label for="image">Image :</label><br>
  <input type="file" name="image" id="image">
  <br><br>
</form>

<button type="submit" form="publication-form" value="Publish" >Publier</button>
</div>




<script>
function submitPublicationForm(event) {
	  event.preventDefault();
	  
	  let form = document.querySelector("#publication-form");
	  let formData = new FormData(form);
	  
	  let xhr = new XMLHttpRequest();
	  xhr.open("POST", "/ReseauSocialE2/CreatePublicationServlet", true);
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	      location.reload();
	    }
	  };
	  xhr.send(formData);
}

let form = document.querySelector("#publication-form");
form.addEventListener("submit", submitPublicationForm);
</script>
