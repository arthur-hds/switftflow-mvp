//--------------Global Variables--------------

let SelectedList = [];
let tab = ``;

//--------------------------------------------




//--------------Function to get the JSON data--------------
async function getApi(url) {
    const response = await fetch(url, {
        method: "GET"
    })

    const data = await response.json();
    return data

}

//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(page) {

    
    window.location.href = `${page}.html`;
}

//--------------Function to load shirts that are disponible--------------



//--------------Function to set all the disponibilities at the selected provider--------------
async function providerSelected(value) {

    const providerID = value.target.value
    const url = "http://localhost:8080/orderClient/disponibility/provider/" + providerID
    const urlProvider = "http://localhost:8080/provider/" + providerID
    const urlDisponibilty = "http://localhost:8080/disponibility/request/provider/" + providerID
    const data = await getApi(url)

    tab = ``
    SelectedList = []

    //--------------Function to load shirts that are disponible--------------
    function loadUpDisponibilty(value) {

        const shirt = {
            "team": value.shirt_id.team_id.name,
            "type": value.shirt_id.type,
            "season": value.shirt_id.season,
            "id": value.shirt_id.id
        }

        const user = {
            "name": value.client_id.name,
            "id": value.id
        }


        tab += `
        <div class="providers">
            <h2 id="${user.name}-${user.id}">${user.name}</h2>
            <h4 id="${shirt.team}-${shirt.id}">${shirt.team} - ${shirt.type} - ${shirt.season}</h4>
        </div>
        `

    }

    //--------------Function to load minimum shirts per provider--------------
    async function loadUpMinimumShirts() {

        const provider = await getApi(urlProvider);

        const providerMinimum = provider.minimum;

        return providerMinimum;


    }


    for (let i of data) {

        loadUpDisponibilty(i)

    }


    const providerMinimum = await loadUpMinimumShirts()



    const miniumShirts = document.getElementById("item-minimum-info")
    const disponibilities = document.getElementById("items-left-container")
    const selected = document.getElementById("items-right-container")

    selected.innerHTML = ``
    disponibilities.innerHTML = tab
    miniumShirts.textContent = providerMinimum


    const items = document.getElementsByClassName("providers")
    tab = ``



    //Function to set each EventListener at the buttons
    for (let i of items) {

        i.addEventListener("click", function () {

            moveToSelected(i, urlDisponibilty)  //It process the actual element and the link to acess all Disponibility data
            i.remove()      //!I need to somehow register the object in the right container. That way, when it is clicked, the option will return to the left.


        })

    }


}


//--------------Transfer data to selected camp--------------
async function moveToSelected(row, url) {

    const data = await getApi(url)

    const order_client = {
        tittle: row.firstElementChild.id.split("-")[0],
        id: row.firstElementChild.id.split("-")[1],
        shirt_id: Number(row.lastElementChild.id.split("-")[1])


    }


    function loadUpSelected(shirtJson) {

        tab = `
        <div class="providers">
            <h2>${shirtJson.tittle}</h2>
            <h4 id="${shirtJson.team}-${shirtJson.id}">${shirtJson.team} - ${shirtJson.type} - ${shirtJson.season}</h4>
            <div class="providers__price">
                <h4 id="${shirtJson.team}-${shirtJson.id}">Price: R$${shirtJson.price}</h4>
                <h4 id="${shirtJson.team}-${shirtJson.id}">Revenue: R$${shirtJson.revenue}</h4>
            </div> 
            
        </div>

        `
    }


    tab = ``

    let shirtJSON = {};

    for (let i of data) {


        if (i.shirt_id.id === order_client.shirt_id) {  //Checks if the id of the disponibility matches the request


            shirtJSON = {
                "client": order_client.tittle,
                "team": i.shirt_id.team_id.name,
                "type": i.shirt_id.type,
                "season": i.shirt_id.season,
                "id": i.shirt_id.id,
                "price": i.price,
                "revenue": i.sale
            }


            SelectedList.push(shirtJSON)  //It declares the values from the object into a list

            loadUpSelected(shirtJSON)





        }


    }

    let priceSum = 0;

    let profitSum = 0;

    let totalSum;




    for (let i of SelectedList) {  //Add the total values to variable

        priceSum += i.price;
        profitSum += i.revenue;

    }

    totalSum = (profitSum - priceSum).toFixed();






    const selectedShirts = document.getElementById("item-selected-info");
    const price = document.getElementById("price__cost");
    const profit = document.getElementById("price__profit");
    const total = document.getElementById("price__total");
    const selected = document.getElementById("items-right-container")


    selected.innerHTML += tab;

    selectedShirts.textContent = SelectedList.length
    price.textContent = "R$ " + priceSum;
    profit.textContent = "R$ " + profitSum;
    total.textContent = "R$ " + totalSum



}








//--------------Function to load the providers at the select option--------------
async function loadUpOptions() {
    const url = "http://localhost:8080/provider"
    const values = await getApi(url)
    tab = ``



    function setOptions(object) {
        tab += `
        <option value="${object.id}">${object.name}</option>
        `
    }


    for (let i of values) {
        setOptions(i)
    }

    const Options = document.getElementById("select-provider")
    Options.innerHTML = tab


}




//--------------Function that checks if the minimum required shirts have been selected--------------
function checkQuantity(){

    const selectedShirts = document.getElementById("item-selected-info");
    const miniumShirts = document.getElementById("item-minimum-info")

    const minimum = miniumShirts.textContent;
    const selected = selectedShirts.textContent;

    const IsMinimum = selected < minimum

    if( IsMinimum ){
        showModal();
        return;
    }

    
    sendData();
    


}

//--------------Function to save all selected items to be used at the review page--------------
function sendData(){

    sessionStorage.setItem("Orders", JSON.stringify(SelectedList));
    changeWindow("orderCheckup")

}




function showModal(){

    const modal = document.getElementById("error-modal");
 

    if(modal.style.display === "block"){

        modal.style.display = "none";

    }else if(modal.style.display === "none" || modal.style.display === ""){

        modal.style.display = "block";

    }
}



loadUpOptions()




