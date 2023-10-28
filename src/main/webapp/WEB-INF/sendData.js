function sendData(method, action,ID_Input, data) {
        var xhr = new XMLHttpRequest();
        xhr.open(method, action);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            if (xhr.status === 200) {
                console.log('Data sent successfully');
            }
            else {
                console.log('Error sending data');
            }
        };
        let inputElement = document.getElementById(ID_Input);
        if (inputElement !== null) {
          const inputValue = inputElement.value;
          data = data + inputValue; 
        }
        xhr.send(data);
        
        // When the AJAX request has completed, reload the page
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
              location.reload();
            }
        }
}