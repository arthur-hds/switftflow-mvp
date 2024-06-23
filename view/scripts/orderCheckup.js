//--------------Global Variables--------------

let SelectedList = [];
let ClientsList = [];
let Values = []
let tab = ``;

//--------------------------------------------


//--------------Function to get the Session Storage items--------------
function getData(){

    SelectedList = JSON.parse(sessionStorage.getItem("Orders"));
    ClientsList = JSON.parse(sessionStorage.getItem("Clients"));
    Values = JSON.parse(sessionStorage.getItem("Values"));
    
    console.log(ClientsList[0])

}


//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(page) {

    
    window.location.href = `${page}.html`;
}


//--------------Function to populate the div with Session Storage items--------------
function loadUpOrder(data){


     


    function loadUpSelected(shirtJson) {

        tab += `
        <div class="providers">
            <h2>${shirtJson.client}</h2>
            <h4 id="${shirtJson.team}-${shirtJson.id}">${shirtJson.team} - ${shirtJson.type} - ${shirtJson.season}</h4>
            <div class="providers__price">
                <h4 id="${shirtJson.team}-${shirtJson.id}">Price: R$${shirtJson.price}</h4>
                <h4 id="${shirtJson.team}-${shirtJson.id}">Revenue: R$${shirtJson.revenue}</h4>
            </div> 
            
        </div>

        `
    }



    for(let i of data){
        
        loadUpSelected(i);


    }

    const container = document.getElementById("items-container");

    container.innerHTML = tab;


}



//--------------Function to set details of the main order--------------
function loadUpDetails() {
    const clientsText = document.getElementById("clientsQuantity");
    const shirtsText = document.getElementById("shirtsQuantity");
    const providerText = document.getElementById("provider");
    const costText = document.getElementById("costValue");
    const profitText = document.getElementById("profitValue");


    costText.textContent = "R$ "+Values.cost;
    profitText.textContent = "R$ "+Values.profit
    providerText.textContent = SelectedList[0].provider;
    shirtsText.textContent = SelectedList.length;
    clientsText.textContent = ClientsList.length;

}



//--------------Function create main order--------------
async function createOrder(){

    const url = "http://localhost:8080/orders"

    const AllParams ={

        "status": false,
        "total": Values.cost,
        "provider_id": {"id": SelectedList[0].providerID}

    } 


    try {
    
        const response = await fetch(url, {

            method: "POST",
            headers:{
                "Content-Type": "application/json"
            },
            body: JSON.stringify(AllParams)
    
        })

        if (!response.ok) {
            throw new Error("Error in the process of sending data.");
        }

        const data = await response;
        console.log("Success: ", data);


    } catch (error) {

        console.error("Erro: ", error);

    }


}



getData();
loadUpOrder(SelectedList);
loadUpDetails();