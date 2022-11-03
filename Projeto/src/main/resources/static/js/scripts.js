/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project


function updateBidTable(idProduto, tbodyEl) {
    var rowCount = tbodyEl.rows.length;

    if (rowCount > 0) {
        tbodyEl.deleteRow(0)
        tbodyEl.deleteRow(0)
        tbodyEl.deleteRow(0)
        tbodyEl.deleteRow(0)
        tbodyEl.deleteRow(0)
    }

    let request = new XMLHttpRequest()
    request.open("GET", "/lances/" + idProduto + "/listar", false)
    request.send()
    let data = request.responseText
    let bidsList = JSON.parse(data)
    const lastBids = bidsList.slice(bidsList.length - 5, bidsList.length);

    lastBids.forEach(element => {
        var a = element.dataHora
        var b = element.preco
        var c = element.comprador.nome

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
    }
    );
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
    let produto = "87579348759384"
    let url = ""

    bidBody = {
        "bidValue": bidValue,
        "produto": produto,
        "user": user
    }

    POST_bid(bidBody, url)

    //if (request.responseText == "OK")
    //alert("Lance feito com sucesso")
}

function POST_bid(bidBody, url) {
    let request = new XMLHttpRequest
    request.open("POST", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(bidBody))

    request.onload = function () {
        console.log(this.responseText)
    }

    return request.responseText
}

function cadNovoUsuario() {

    //buscar CPF informado no banco para ver se ja está cadastrado
    //se não existir, iniciar processo abaixo

    let nome = document.getElementById("nome").value
    let cpf = document.getElementById("cpf").value
    let email = document.getElementById("email").value
    let password = document.getElementById("inputPassword4").value

    let url = ""

    cadBody = {
        "nome": nome,
        "cpf": cpf,
        "email": email,
        "password": password
    }

    //console.log(JSON.stringify(cadBody))
    POST_cad(cadBody, url)

    //if (request.responseText == "OK")
    //alert("Cadastro feito com sucesso")

}

function POST_cad(cadBody, url) {
    let request = new XMLHttpRequest
    request.open("POST", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(cadBody))

    request.onload = function () {
        console.log(this.responseText)
    }

    return request.responseText
}

function showProducts() {

    consultProd();

    produtos.forEach(element => {
        const exibProds = document.getElementById("exibProds")
        var prodDesc = produtos.descricao
        var prodName = produtos.nome
        exibProds.innerHTML += '<div class="col mb-5"><div class="card h-100"><img class="card-img-top" '
            + 'src="../src/assets/product.jpg" alt="teste" /><div class="card-body p-4"><div class="text-center"><h5 class="fw-bolder" id="prodName">'
            + response.nome[i]
            + '</h5><h7 id="prodDesc">'
            + response.descricao[i]
            + '</h7></div></div><div class="card-footer p-4 pt-0 border-top-0 bg-transparent"><div class="text-center"><a class="btn btn-outline-dark mt-auto" href="produto.html">Ver mais</a></div></div></div></div>'
    });
}

function consultProd() {
    let request = new XMLHttpRequest()
    request.open("GET", "/produtos/listarTodos", false)
    request.send()
    let data = request.responseText
    let produtos = JSON.parse(data)
    //caso a API traga varios resultados, resp vai ser um array

    return produtos;
}