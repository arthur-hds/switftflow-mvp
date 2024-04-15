const url = "http://localhost:8080/"
let CurrentColumn = ""

//--------------Functions to get and set data at table--------------
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

//--------------Functions to set options in the order page--------------
function showOrder(data){

    const tittles = ["Disponibilty", "Interest","Prompt-Delivery"]


    

    for(let i = 0; i < tittles.length; i++){
        
        tab = ``

        tab += `

        <div class="group-content">
            <h2>${tittles[i]}</h2>

            <div id = ${tittles[i].toLowerCase()} class="items">

                

            </div>

        </div>

        `


        document.getElementById("items-content").innerHTML += tab;

        tab = ``


        for(let column of data[i]){

            console.log(column.name)

            tab += `
            <div class="providers">
                <h3>${column.name !== undefined ? column.name : column.client_id.name}</h3>      
            </div>
            `
        
        }

        document.getElementById(tittles[i].toLowerCase()).innerHTML = tab;

    }   
    


}



async function changeDataOrder(){

    const paths = ["provider", "orderClient"];

    const url = "http://localhost:8080/"

    let provider = await getAPI(url+ paths[0]);
    let orderClient = await getAPI(url + paths[1]);




    showOrder([provider, orderClient]);



}



//--------------Headers buttons functions being assigned--------------
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
    changeDataOrder();


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




//SET ALL THE LABELS AND DOES SELECT TAG FOR FOREIGN COLUMNS
async function UpdateModal(columns, path=null, handleForeignKey = false){

    const body = document.getElementById("modal-body");

   
    

    let tab = ``


    for(let i of columns){



        if(i.includes("_id") && handleForeignKey){
            console.log("FOUND IT")
            console.log(path)
            
            try {
                
                const data = await getAPI(url+path);
                console.log(data)

                tab += `
                <label class="modal-body-div-content-label-foreignkey">${i}</label>
                <select>
                `

                for(let j of data){
                    console.log(j)
                    tab+=
                    `
                    <option>${j.id + " - " + j.name} </option>
                    
                    `

                }

                tab += `
                </select>
                `
            } catch (error) {
                
                console.error("URL isn't available or the wrong syntax is being returned: ", error)

            }

        }else{

            tab += `
            <label>${i}</label>
            <input type="text" name="" id="">
            `

        }


        
    
    }



    body.innerHTML = tab 


}
    

//CHANGES THE TITTLE OF THE CURRENT FORM
function ChangeModalData(path){
    let column = path.toUpperCase();


    let tittle = document.getElementById("tittle-modal");


    switch(column){
        case "CLIENT":
            tittle.innerHTML = "CLIENT";
            UpdateModal(["Name", "Type", "Number"])
            break;
        
        case "SHIRT":
            tittle.innerHTML = "SHIRT";
            UpdateModal(["Team_id", "Type", "Season"], "team", true)
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
    let values = div.querySelectorAll("input, select")
 
    const formDataJSON = {}; //JSON that will contain all the params


    for(let j = 0; j < labels.length; j++){

        let key = labels[j].outerText.toLowerCase();
        let value = values[j].value;


        //Condition that prepares the body in cases of Foreign Keys
        if(labels[j].className === "modal-body-div-content-label-foreignkey"){

            value = {"id": value.charAt(0)}
           
        }        

        formDataJSON[key] = value


    
    }
    

    return formDataJSON;

}




async function CreateData(){
    
    
    
    const AllParams = GetModalValues();
    
    
    try {
        const response = await fetch(url + CurrentColumn, { 
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(AllParams) // JSON Body
        });

        if (!response.ok) {
            throw new Error("Erro ao enviar os dados.");
        }

        const data = await response;
        console.log("Success: ", data);

    } catch (error) {

        console.error("Erro: ", error);

    }
    
 
    showModal();


}

















