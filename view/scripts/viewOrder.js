//--------------Global Variables--------------


let Values = []
let shirtsQuantity;
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



    //Function to set each EventListener at the buttons
    const items = document.getElementsByClassName("providers");

    for (let i of items) {
        i.addEventListener("click", async function () {
            const orderIdNumber = i.querySelector("h4").id.split("-")[1]

            const dataId = await getApi(url + "orders/" + orderIdNumber)
            shirtsQuantity = await getApi(url + "orderItem/orders/" + orderIdNumber)

            Values = dataId;


            loadUpDetails();

        })


    }


}




async function getData() {

    const urlOrder = url + "orders"

    const data = await getApi(urlOrder)

    loadUpOrder(data);




}

//--------------Function to set details of the main order--------------
function loadUpDetails() {


    function showInfo() {
        const rightContent = document.getElementById("right-content")
        const verticalLine = document.getElementById("vertical-line")
        const viewOrderBtn = document.getElementById("btn-view")

        const IS_CONTENT_HIDDEN = rightContent.style.display === "none";

        if (IS_CONTENT_HIDDEN) {
            rightContent.style.display = "block"
            verticalLine.style.display = "block"
            viewOrderBtn.style.display = ""
        }
    }

    showInfo();

    const statusValue = document.getElementById("statusValue");
    const shirtsText = document.getElementById("shirtsQuantity");
    const providerText = document.getElementById("provider");



    statusValue.textContent = Values.status
    providerText.textContent = Values.provider_id.name;
    shirtsText.textContent = shirtsQuantity.length;


}


function viewOrder(){

    //!Redirect to OrderItem HTML

}






getData()