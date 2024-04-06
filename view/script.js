const url = "http://localhost:8080/"
let CurrentColumn = ""


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

    
    CurrentColumn = path;
  
   
    const url = "http://localhost:8080/"+ CurrentColumn;
    
    let data = await getAPI(url);

    
   

    ChangeModalData(path)

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



//--------------

//CREATE BUTTON

const modal = document.getElementById("add-modal");

const btn = document.getElementById("btn-add");

const btnClose = document.getElementById("btn-close")

const btnCreate = document.getElementById("btn-create")


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
        <label>${j}</label>
        <input type="text" name="" id="">
        `
    
    }



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

//SET FUNCTIONS AT THE BUTTONS
btn.addEventListener("click", showModal);
btnClose.addEventListener("click", showModal);
btnCreate.addEventListener("click", CreateData);


function GetModalValues(){

    let div = document.getElementById("modal-body")
    let labels = div.querySelectorAll("label")
    let values = div.querySelectorAll("input")
 
    const formData = new FormData();
    const formDataJSON = {};


    for(let j = 0; j < labels.length; j++){

        formData.append(labels[j].outerText.toLowerCase(), values[j].value);
        
    }


    formData.forEach((value, key) => {
        
        formDataJSON[key] = value

    })


  
    return formDataJSON;

}




async function CreateData(){
    
    
    
    const AllParams = GetModalValues();
    
    console.log(AllParams)
    try {
        const response = await fetch("http://localhost:8080/client", { //SHOWING 400 ERROR 
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: AllParams // JSON Body
        });

        if (!response.ok) {
            throw new Error("Erro ao enviar os dados.");
        }

        const data = await response;
        console.log("Success: ", data);

    } catch (error) {

        console.error("Erro: ", error);

    }
    
 


}

















