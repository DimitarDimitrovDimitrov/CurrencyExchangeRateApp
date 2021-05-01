function getAllCurrencies() {

    var dropdownFrom = document.getElementById("dropdown-from");
    var dropdownTo = document.getElementById("dropdown-to");

    var xmlRequest = new XMLHttpRequest();

    xmlRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            var jsonParsed = JSON.parse(this.responseText);
            fillCurrencies(dropdownFrom, jsonParsed);
            fillCurrencies(dropdownTo, jsonParsed);

            fillButtonInfo();
        }
    };

    xmlRequest.open("GET", "/currencies", true);
    xmlRequest.send();
}

function fillCurrencies(dropdown, jsonParsed) {
    for (var i = 0; i < jsonParsed.length; i++) {
        var a = document.createElement("a");
        a.setAttribute("class", "dropdown-item");
        var text = document.createTextNode(jsonParsed[i].name);
        a.appendChild(text);
        a.setAttribute("value", jsonParsed[i].name);
        dropdown.appendChild(a);
    }
}

function filterFunction(input, dropdown) {
    if(input == null) {
        return;
    }
    var input, filter, ul, li, a, i;
    input = document.getElementById(input);
    filter = input.value.toUpperCase();
    div = document.getElementById(dropdown);
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}

function fillButtonInfo() {

    var listAs = document.querySelectorAll(".dropdown-menu a");

    for(var i = 0; i < listAs.length; i++) {
        var a = listAs[i];
        a.addEventListener("click", function(evt) {
            var a = evt.target;
            var button = a.parentElement.previousElementSibling;
            button.innerHTML = a.getAttribute("value");
        })
    }
}


$("#convertButton").click(function() {

     // Get from
    var from = $('#dropdownFromButton').text();

    // Get to
    var to = $('#dropdownToButton').text();

    // Get value
    var value = $('#valueInput').val();

    // Make the http request using Ajax
    $.ajax({
        type: "POST",
        url: "/currency-converter",
        data: JSON.stringify({
            "from": from,
            "to": to,
            "value": value
        }),
        contentType: "application/json;",
        success: function(data) {
            console.log(data);
            $("#convertResult").text(data);
        },
        error: function(data) {
            alert('There was a problem converting!');
        }
    });



});