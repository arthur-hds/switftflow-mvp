//--------------Global Variables--------------

let SelectedList = [];
let tab = ``;

//--------------------------------------------


//--------------Function to get the Session Storage items--------------
function getData(){

    SelectedList = JSON.parse(sessionStorage.getItem("Orders"));

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

getData();
loadUpOrder(SelectedList);
