/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project


function updateTable() {
	var a = "1D 10H 26Min 23Seg"    //consultBid().time
	var b = "R$" + "3.130,00"       //consultBid().value
	var c = "#" + "7592659"         //consultBid().user

	//http://worldtimeapi.org/api/timezone/America/Sao_Paulo

	var rowCount = tbodyEl.rows.length;

	// Create an empty <tr> element and add it to the 1st position of the table:
	var row = tbodyEl.insertRow(0);
	// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	// Add some text to the new cells:
	cell1.innerHTML = a;
	cell2.innerHTML = b;
	cell3.innerHTML = c;

	if (rowCount >= 5) {
		tbodyEl.deleteRow(rowCount);
	}
}


function consultBid() {
	let request = new XMLHttpRequest()
	request.open("GET", "http://worldtimeapi.org/api/timezone/America/Sao_Paulo", false)
	request.send()
	let data = request.responseText
	let resp = JSON.parse(data)
	//caso a API traga varios resultados, resp vai ser um array

	return resp.datetime;
}

function bid() {
	//event.preventDefault()
	let bidValue = document.getElementById("inputQuantity").value
	let user = "#9836581"
	let url = "localhost:8080/usuarios/salvar"

	body = {
		"bidValue": bidValue,
		"user": user
	}

	POST_bid(body, url)
}

function POST_bid(body, url) {
	console.log(body)
}