//--------------Global Variables--------------


let Values = []
let shirtsQuantity;
let orderIdNumber;
let tab = ``;
const url = "http://localhost:8080/"

//--------------------------------------------



//--------------Functions to get and set data at table--------------
function show(data) {

    console.log("Data: ", data)

    if (data.length > 0) {  // Checks if the response is null or not
        let tab =
            `<thead>`;

        const sampleData = data[0] !== undefined ? data[0] : data;  //Returns the Table JSON

        for (let column in sampleData) {

            tab += `
            <th scope="col">${column}</th>
            `
        }

        tab +=
            `
            </thead>
        `;


        function renderRow(value) {  //Create tables

            tab += `
                <tr>`;

            for (let column in value) {

                if (typeof value[column] === "object" && value[column] !== null) {

                    console.log("Column::: ", value[column])

                    
                    function getColumn(value, column) { // Checks if the response is valid or not

                        if (value[column].provider_id && value[column].shirt_id && value[column].shirt_id.team_id && value[column].shirt_id.team_id.name) {
                            return value[column].shirt_id.team_id.name
                        } else if (value[column].provider_id && value[column].provider_id.name) {
                            return value[column].provider_id.name;
                        } else if (value[column].client_id && value[column].client_id.name ) {
                            return value[column].client_id.name
                        } else {
                            return "N/A"
                        }

                    }


                    tab += `<td scope="row"> ${value[column].id} - ${getColumn(value, column)}  </td>`

                } else {

                    tab += `<td scope="row"> ${value[column]} </td>`;

                }
            }

            tab += `  
                </tr>
            `;
        }



        if (data[0] === undefined) {  //Solo JSON

            renderRow(data);

        } else {                      //Array JSON

            for (let j = 0; j < Object.values(data).length; j++) {  //Populate every row

                renderRow(data[j]);

            }
        }


        document.getElementById("table").innerHTML = tab;       //Populate the table div



    } else {

        let tab = `<i class="bi bi-emoji-dizzy" style="font-size: 10rem; color: #5f4d8a;"></i>`;
        tab += `<h2 style="color: #5f4d8a;"> There is no data avaiable here... </h2>`

        document.getElementById("items-content").innerHTML = tab;  //Adds the following error alert content


    }


}


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
            orderIdNumber = i.querySelector("h4").id.split("-")[1]

            const dataId = await getApi(url + "orders/" + orderIdNumber)
            shirtsQuantity = await getApi(url + "orderItem/orders/" + orderIdNumber)

            Values = dataId;


            loadUpDetails();

        })


    }


}



//--------------Functions to get orders data--------------
async function getData() {

    const urlOrder = url + "orders"

    const data = await getApi(urlOrder)

    loadUpOrder(data);




}

//--------------Function to set details of the main order--------------
function loadUpDetails() {

    //--------------Functions to show and hide info when clicked--------------
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



//--------------Functions to push viewOrderItem data and populate at div--------------
function viewOrder() {


    function changePath(id){
        const newPath = `${window.location.pathname}?id=${id}`
        history.pushState(null, '', newPath);
    }

    changePath(orderIdNumber);

    fetch('viewOrderItem.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('body-content-items-orders').style.display = "none";
            document.getElementById('btn-view').style.display = "none";
            document.getElementById('orderItemContent').innerHTML = html;
            document.getElementById('orderItemContent').style.display = "";
            return fetchData(orderIdNumber);

        })
        .then(data => {
            show(data)
        })
        .catch(error => console.error('Error loading order item content:', error));


}

//--------------Functions to set ?id at paths--------------
async function fetchData(id) {

    const urlOrderItem = url + "orderItem/orders/" + id

    const data = await getApi(urlOrderItem)

    return data;

}



getData()

