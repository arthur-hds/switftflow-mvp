//--------------Global Variables--------------
const url = "http://localhost:8080/"
const token = window.localStorage.getItem("Authorization");
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


        
    }else{

        let tab = `<i class="bi bi-emoji-dizzy" style="font-size: 10rem; color: #5f4d8a;"></i>`;
        tab += `<h2 style="color: #5f4d8a;"> There is no data avaiable here... </h2>`
        
        document.getElementById("items-content").innerHTML = tab;  //Adds the following error alert content
        

    }
    

}

//--------------Function to pull the db content--------------
async function getAPI(url){

    const response = await fetch(url, {     // Gets the response body
        method: "GET", 
        headers: new Headers({
            Authorization: token,
        })
    });     

    
    const data = await response.json();  
    
    console.log(Object.values(data).length);
    
    return data;
}

//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(page) {


    window.location.href = `${page}.html`;
}


//--------------Function to get the api value and pass to table--------------
async function fetchDataAndShow() {
    try {
        const data = await getAPI(url + "orders");
        show(data);
    } catch (error) {
        console.error("Error at getting data:", error);
    }
}

fetchDataAndShow()

