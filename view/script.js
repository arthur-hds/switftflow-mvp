function show(data){

    let tab =
    `<thead>`;

    for (let column in data[0]){

        tab += `
        <th scope="col">${column}</th>
        `
    }

    tab += 
    `
        </thead>
    `;


    for(let j; j< data[0].length; j++){
        console.log(j);
    }


    document.getElementById("table").innerHTML = tab;

}



async function getAPI(url){
    const response = await fetch(url, {
        method: "GET"});

    const data = await response.json();

    show(data);
}


function ChangeData(path){

    const url = "http://localhost:8080/" + path;

    getAPI (url);

}


let clientButton = document.getElementById("client");
let shirtsButton = document.getElementById("shirt");
let ProvidersButton = document.getElementById("provider");
let OrderButton = document.getElementById("order");


clientButton.addEventListener("click", function(){
    let name = clientButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);

}, false);

shirtsButton.addEventListener("click", function(){
    let name = shirtsButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);

}, false);

ProvidersButton.addEventListener("click", function(){
    let name = ProvidersButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);

}, false);

OrderButton.addEventListener("click", function(){
    let name = OrderButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);

}, false);





