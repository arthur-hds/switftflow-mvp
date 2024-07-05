//--------------Global Variables--------------


let Values = []
let tab = ``;
const url = "http://localhost:8080/"

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


//--------------Function to populate the div with Session Storage items--------------
function loadUpOrder(data) {

    console.log(data)


    function loadUpSelected(orderJson) {

        tab += `
        <div class="providers">
            <h2>#${orderJson.id} - ${orderJson.provider_id.name}</h2>
            <h4 id="order-${orderJson.id}"> R$${orderJson.total}</h4>

            
        </div>

        `
    } 



    for (let i of data) {

        loadUpSelected(i);


    }

    const container = document.getElementById("items-container");

    container.innerHTML = tab;


}




async function getData(){

    const urlOrder = url + "orders"

    const data = await getApi(urlOrder)

    loadUpOrder(data);

}

//--------------Function to set details of the main order--------------
function loadUpDetails() {
    const statusValue = document.getElementById("statusValue");
    const shirtsText = document.getElementById("shirtsQuantity");
    const providerText = document.getElementById("provider");
 



    statusValue.textContent = "R$ " + Values.profit
    providerText.textContent = SelectedList[0].provider;
    shirtsText.textContent = SelectedList.length;


}



getData()