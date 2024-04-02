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


    for(let j = 0; j< Object.values(data).length; j++){

        tab += `
            <tr>`;

        for(let column in data[0]){

            let value = data[j];
            

            if(typeof value[column] === "object"){
                
                tab+= `<td scope="row"> ${value[column].id} - ${value[column].name}</td>`

            }else{

                tab+= `<td scope="row"> ${value[column]} </td>`;
            
            }

        }

        tab +=`  
            </tr>
        `;
    }

    document.getElementById("table").innerHTML = tab;

}



async function getAPI(url){

    const response = await fetch(url, {
        method: "GET"});

    
    const data = await response.json();
    
    console.log(Object.values(data).length);
    
    return data;
}


async function ChangeData(path){

    

    const url = "http://localhost:8080/" + path;

    let data = await getAPI(url);

    console.log(data)

    show(data)


   

}


let clientButton = document.getElementById("client");
let shirtsButton = document.getElementById("shirt");
let ProvidersButton = document.getElementById("provider");
let OrderButton = document.getElementById("order");


clientButton.addEventListener("click", function(){
    let name = clientButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);
    ChangeModalData(name);

}, false);

shirtsButton.addEventListener("click", function(){
    let name = shirtsButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);
    ChangeModalData(name);

}, false);

ProvidersButton.addEventListener("click", function(){
    let name = ProvidersButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);
    ChangeModalData(name);

}, false);

OrderButton.addEventListener("click", function(){
    let name = OrderButton.id;
    document.getElementById("tittle").innerHTML = name.toUpperCase();
    ChangeData(name);
    ChangeModalData(name);

}, false);



//--------------

//CREATE BUTTON

const modal = document.getElementById("add-modal");

const btn = document.getElementById("btn-add");

const btnClose = document.getElementById("btn-close")

function showModal(){
    if(modal.style.display === ""){

        modal.style.display = "block";

    }else if(modal.style.display === "block"){

        modal.style.display = "";

    }
}


//SET ALL THE LABELS
function UpdateModal(columns){

    const body = document.getElementById("modal-body");

    let tab = ``


    for(let j of columns){

        tab += `
        <label >${j}:</label>
        <input type="text" name="" id="">
        `
    
    }

    console.log(tab)
    body.innerHTML = tab

}
    

//CHANGES THE TITTLE OF THE CURRENT FORM
function ChangeModalData(path){
    let column = path.toUpperCase();

    const url = "http://localhost:8080/" + path;

    let tittle = document.getElementById("tittle-modal");


    switch(column){
        case "CLIENT":
            tittle.innerHTML = "CLIENT";
            UpdateModal(["Name", "Type", "Number"])
            break;
        
        case "SHIRT":
            tittle.innerHTML = "SHIRT";
            UpdateModal(["Team_id", "Type", "Season"])
            break

        case "PROVIDER":
            tittle.innerHTML = "PROVIDER";
            UpdateModal(["Name", "Delivery", "Minimum"])
            break
    }
    


}


btn.addEventListener("click", showModal);
btnClose.addEventListener("click", showModal);
















