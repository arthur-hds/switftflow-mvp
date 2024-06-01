//--------------Function to get the JSON data--------------
async function getApi(url) {
    const response = await fetch(url, {
        method: "GET"
    })

    const data = await response.json();
    return data

}

//--------------Function to change HTML when back arrow pressed--------------
function changeWindow() {
    window.location = "index.html";
}


//--------------Function to set all the disponibilities at the selected provider--------------
async function providerSelected(value) {

    const providerID = value.target.value
    const url = "http://localhost:8080/orderClient/disponibility/provider/" + providerID
    const urlDisponibilty = "http://localhost:8080/disponibility/request/provider/" + providerID
    const data = await getApi(url)

    let tab = ``

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

    for (let i of data) {

        loadUpDisponibilty(i)
    }



    const disponibilities = document.getElementById("items-left-container")
    const selected = document.getElementById("items-right-container")

    selected.innerHTML = ``
    disponibilities.innerHTML = tab



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


async function moveToSelected(row, url) {

    
    const data = await getApi(url)

    const order_client = {
        tittle: row.firstElementChild.id.split("-")[0],
        id: row.firstElementChild.id.split("-")[1],
        shirt_id: Number(row.lastElementChild.id.split("-")[1])


    }

    let tab =``



    for (let i of data) {
  

        if (i.shirt_id.id === order_client.shirt_id) {  //Checks if the id of the disponibility matches the request

            const shirt = {
                "team": i.shirt_id.team_id.name,
                "type": i.shirt_id.type,
                "season": i.shirt_id.season,
                "id": i.shirt_id.id,
                "price": i.price,
                "revenue": i.sale
            }

            tab = `
            <div class="providers">
                <h2>${order_client.tittle}</h2>
                <h4 id="${shirt.team}-${shirt.id}">${shirt.team} - ${shirt.type} - ${shirt.season}</h4>
                <div>
                    <h4 id="${shirt.team}-${shirt.id}">Price: R$${shirt.price}</h4>
                    <h4 id="${shirt.team}-${shirt.id}">Revenue: R$${shirt.revenue}</h4>
                </div> 
                
            </div>

            `
                    
        }

            
    }



   

    const selected = document.getElementById("items-right-container")
    selected.innerHTML += tab

}






//--------------Function to load the providers at the select option--------------
async function loadUpOptions() {
    const url = "http://localhost:8080/provider"
    const values = await getApi(url)
    let tab = ""


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
loadUpOptions()




