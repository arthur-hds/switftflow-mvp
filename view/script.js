const url = "http://localhost:8080/"
let CurrentColumn = ""

//--------------Functions to get and set data at table--------------
function show(data){

    let tab =
    `<thead>`;

    const sampleData = data[0] !== undefined ? data[0] : data;

    for (let column in sampleData){

        tab += `
        <th scope="col">${column}</th>
        `
    }

    tab += 
    `
        </thead>
    `;


    function renderRow(value){  //Create tables

        tab += `
            <tr>`;

        for(let column in value){ 

            if(typeof value[column] === "object" && value[column] !== null){
    
                tab+= `<td scope="row"> ${value[column].id} - ${value[column].name !== undefined ? value[column].name : value[column].team_id.name}  </td>`

            }else{

                tab+= `<td scope="row"> ${value[column]} </td>`;
            
            }
        }

        tab +=`  
            </tr>
        `;
    }



    if(data[0] === undefined){  //Solo JSON

        renderRow(data);
    
    }else{                      //Array JSON

        for(let j = 0; j< Object.values(data).length; j++){  //Populate every row

            renderRow(data[j]);

        }
    }


    document.getElementById("table").innerHTML = tab;       //Populate the table div
    document.getElementById("items-content").innerHTML = ``;  //Clears the order div
    

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
function showOrder(JsonPath, DBpath){

    const tittles = ["disponibility", "Interest"]  //Tittles id
    const DBColumn = ["disponibility", "order_client"]

    document.getElementById("table").innerHTML = ``;       //Clears the table div
    document.getElementById("items-content").innerHTML = ``;  //Clears the div
    

    for(let i = 0; i < tittles.length; i++){    //Creates all the tittles
        
        let tittleID = tittles[i]

        tab = ``

        tab += `  

        <div class="group-content">
            <h2>${tittleID.toUpperCase()}</h2>

            <div id = ${tittleID} class="items">

            </div>

        </div>

        `

        document.getElementById("items-content").innerHTML += tab;

    }
        
    for(let i = 0; i < tittles.length; i++){

        tab = ``
        let tittleID = tittles[i]
        let columnsID = new Array(); // Id's array

        for(let column of JsonPath[i]){    // loop to populate all the columns

            
            let columnID = column.name.replace(/\s+/g, '-') + "-" + column.id  // Gets the content name and replaces the spaces if necessary


            tab += `
            <div id = ${columnID} class="providers">
                <h3>${column.name}</h3>      
            </div>
            `
        
            columnsID.push(columnID);  //Adds each id to an array, so that it can be defined by the eventListener after
            
        }
        //Shows every item at their respective column
        let item = document.getElementById(tittleID)
        item.innerHTML = tab;
        

        for(let j of columnsID){  //Adds each button their respective eventListener

            let columns = j.split("-");
            let columnID = columns[columns.length -1]; //These 2 lines gets the final id at it respective clicked column
            
            let contentButton = document.getElementById(j);
            contentButton.addEventListener("click", function(){
                ChangeData(DBpath[i] + columnID);
                console.log("DBCOLUMN: "+ DBColumn[i])
                ChangeModalData(DBColumn[i])
            });

        }
        
    }



}   
    




async function changeDataOrder(){

    const paths = ["provider", "client"];  //This tables will be used to populate the items



    const url = "http://localhost:8080/"

    let provider = await getAPI(url+ paths[0]);
    let orderClient = await getAPI(url + paths[1]);

    const JsonPath = [provider, orderClient]   //JSON's that will be used to 
    const DbPaths = ["disponibility/provider/", "orderClient/client/"]

    showOrder(JsonPath, DbPaths);



}


// 







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

    async function renderSelect(paths){     // Function to render the multi options element
        const data = await getAPI(url+paths);
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
    }
    

    let tab = ``


    for(let i of columns){



        if(i.includes("_id") && handleForeignKey){
            console.log("FOUND IT")
            console.log(path)
            
            try {
                //! SELECT ELEMENTS NOT SHOWING
                if(Array.isArray(path)){

                    for(let k of path){
                        renderSelect(k);
                    }

                }else{

                    renderSelect(path)

                }

                
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

        case "DISPONIBILITY":
            tittle.innerHTML = "DISPONIBILITY";
            UpdateModal(["Provider_id", "Shirt_id", "Price", "Sale"], "provider", true)
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

















