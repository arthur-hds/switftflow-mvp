const url = "http://localhost:8080/"
let CurrentColumn = ""
let CurrentColumnOrigin = ""
//--------------Functions to get and set data at table--------------
function show(data){

    console.log("Data: ", data )

    if(data.length > 0){  // Checks if the response is null or not
        let tab =
        `<thead>`;
    
        const sampleData = data[0] !== undefined ? data[0] : data;  //Returns the Table JSON
    
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
                    
                    console.log("Column::: ", value[column])

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

        
    }else{

        let tab = `<i class="bi bi-emoji-dizzy" style="font-size: 10rem; color: #5f4d8a;"></i>`;
        tab += `<h2 style="color: #5f4d8a;"> There is no data avaiable here... </h2>`
        
        document.getElementById("items-content").innerHTML = tab;  //Adds the following error alert content
        

    }
    

}



//--------------Function to pull the db content--------------
async function getAPI(url){

    const response = await fetch(url, {     // Gets the response body
        method: "GET"});     

    
    const data = await response.json();  
    
    console.log(Object.values(data).length);
    
    return data;
}


//--------------Function to pull the db content--------------
async function ChangeData(path){

    
    CurrentColumn = path;   //Path that gonna be showed at the url and GET /method
    CurrentColumnOrigin = path.split("/").length > 1 ? path.split("/")[0] : path  // Path used to sent POST methods
    console.log("CURRENT COLUMN ORIGIN: "+ CurrentColumnOrigin)

    

    const url = "http://localhost:8080/"+ CurrentColumn;
    

    let data = await getAPI(url);

    
    updateButtons(btn, [btnCreateOrder, btnViewOrder])  // Shows the "add" button, while hiding the other one
   

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
        
    for(let i = 0; i < tittles.length; i++){  //Creates all the buttons

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
    



//--------------Functions to set the tittles and columns names when clicked at the Order option--------------
async function changeDataOrder(){

    const paths = ["provider", "client"];  //This tables will be used to populate the items



    const url = "http://localhost:8080/"

    let provider = await getAPI(url+ paths[0]);
    let orderClient = await getAPI(url + paths[1]);


    const JsonPath = [provider, orderClient]   //JSON's that will be used to 
    const DbPaths = ["disponibility/provider/", "orderClient/client/"]
    
    updateButtons([btnCreateOrder, btnViewOrder], btn) // Shows the "Create order" button, while hiding the other one


    showOrder(JsonPath, DbPaths);



}



function updateButtons(show, hide){  //It switch the values of buttons styles, to be displayed or not
    
    const IS_SHOW_ARRAY = Array.isArray(show);
    const IS_HIDE_ARRAY = Array.isArray(hide)
    
    IS_SHOW_ARRAY ? show.forEach( value => value.style.display = "") : show.style.display = "";
    IS_HIDE_ARRAY ? hide.forEach( value => value.style.display = "none") : hide.style.display = "none";    


}

// 







//--------------Headers buttons functions being assigned--------------
let clientButton = document.getElementById("client");
let shirtsButton = document.getElementById("shirt");
let ProvidersButton = document.getElementById("provider");
let OrderButton = document.getElementById("order");


//--------------Setting the click functions--------------
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

const btnCreateOrder = document.getElementById("btn-create-order")

const btnViewOrder = document.getElementById("btn-view-order")

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
    let tab = ``;
    let lengthIndex = 0;  //Variable that can provide the right index to array paths


    async function renderSelect(paths, nameColumn){     // Function to render the multi options element
        const data = await getAPI(url+paths);
        

        tab += `
        <label class="modal-body-div-content-label-foreignkey">${nameColumn}</label>
        <select>
        `

        data.forEach((j) => {



            tab+=
            `
            <option>${j.id} - ${j.name !== undefined ? j.name : j.team_id.name + " - " + j.season + " - "+ j.type} </option>
            
            `;
        });

        tab += `
        </select>
        `
        

    }
    


    const tasks = columns.map(async (i) =>{   //Loop that will render each value



        if(i.includes("_id") && handleForeignKey){
            console.log("FOUND IT")
            console.log(path)
            
            try {
                
                if(Array.isArray(path)){   // Render with there is more than one select

                    await renderSelect(path[lengthIndex++], i);
                

                }else{

                    await renderSelect(path, i);

                }

                
            } catch (error) {
                
                console.error("URL isn't available or the wrong syntax is being returned: ", error)

            }

        }else{  //Adds normal input

            tab += ` 
            <label>${i}</label>
            <input type="text" name="" id="">
            `

        }


        
    
    });

    await Promise.all(tasks);  //Wait for all tasks to complete

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
            UpdateModal(["Provider_id", "Shirt_id", "Price", "Sale"], ["provider", "shirt"], true)
            break

        case "ORDER_CLIENT":
            tittle.innerHTML = "ORDER CLIENT";
            UpdateModal(["Client_id", "Shirt_id", "Size", "Additional", "Discount"], ["client", "shirt"], true)
            break
        
        
    }
    


}

//SET FUNCTIONS AT THE BUTTONS
btn.addEventListener("click", showModal);
btnClose.addEventListener("click", showModal);
btnCreate.addEventListener("click", CreateData);
btnCreateOrder.addEventListener("click", function(){
    changeWindow("order");
})
btnViewOrder.addEventListener("click", function(){
    changeWindow("viewOrder");
})





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

            value = value.split("-").map((x) => x.trim()) //Gets the id value in the array select
            value = {"id": value[0]}
           
        }        

        formDataJSON[key] = value


    
    }
    

    return formDataJSON;

}



//--------------Functions that POST all the info that have been colected at the modal--------------
async function CreateData(){
    
    
    
    const AllParams = GetModalValues();
    
    
    try {
    
        const response = await fetch(url + CurrentColumnOrigin, { 
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(AllParams) // JSON Body
        });

        if (!response.ok) {
            throw new Error("Error in the process of sending data.");
        }

        const data = await response;
        console.log("Success: ", data);

    } catch (error) {

        console.error("Erro: ", error);

    }
    
 
    showModal();


}



//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(page) {

    window.location.href = `${page}.html`;
}














