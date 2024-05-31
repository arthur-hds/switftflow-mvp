//--------------Function to get the JSON data--------------
async function getApi(url){ 
    const response = await fetch(url, {
        method: "GET"})

    const data = await response.json();
    return data

}

//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(){
    window.location = "index.html";
}


//--------------Function to set all the disponibilities at the selected provider--------------
async function providerSelected(value){

    const providerID = value.target.value
    const url = "http://localhost:8080/orderClient/disponibility/provider/"+providerID
    const data = await getApi(url)

    let tab = ``

    function loadUpDisponibilty (value){

        const shirt = {
            "team": value.shirt_id.team_id.name,
            "type": value.shirt_id.type,
            "season": value.shirt_id.season
        }

        const user = {
            "name": value.client_id.name
        }


        tab += `
        <div class="providers">
            <h2>${user.name}</h2>
            <h4>${shirt.team} - ${shirt.type} - ${shirt.season}</h4>
        </div>
        `
        
    }

    for (let i of data){
        
        loadUpDisponibilty(i)
    }



    const disponibilities = document.getElementById("items-left-container")
    const selected = document.getElementById("items-right-container")
    
    selected.innerHTML = ``
    disponibilities.innerHTML = tab



    const items = document.getElementsByClassName("providers")
    tab = ``


    
    //Function to set each EventListener at the buttons
    for (let i of items){

        i.addEventListener("click", function(){
            
            
            console.log(i.innerText)
            moveToSelected(i)
            i.remove()      //!I need to somehow register the object in the right container. That way, when it is clicked, the option will return to the left.
            

        })

    }


}


function moveToSelected(row){
    const tittle = row.innerText
    

    let tab = `
    <div class="providers">
        <h4>${tittle}</h4>
    </div>
    `


    const selected = document.getElementById("items-right-container")
    selected.innerHTML += tab

}


//--------------Function to load the providers at the select option--------------
async function loadUpOptions(){
    const url = "http://localhost:8080/provider"
    const values = await getApi(url)
    let tab = ""


    function setOptions(object){
        tab += `
        <option value="${object.id}">${object.name}</option>
        ` 
    }


    for(let i of values){
        setOptions(i)
    }

    const Options = document.getElementById("select-provider")
    Options.innerHTML = tab


}
loadUpOptions()




