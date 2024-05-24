//--------------Function to get the JSON data--------------
async function getApi(url){ 
    const response = await fetch(url, {
        method: "GET"})

    const data = await response.json();
    return data

}


function changeWindow(){
    window.location = "index.html";
}


async function providerSelected(value){

    const providerID = value.target.value
    const url = "http://localhost:8080/disponibility/provider/"+providerID
    const data = await getApi(url)

    let tab = ""

    function loadUpDisponibilty (value){

        const shirt = {
            "team": value.shirt_id.team_id.name,
            "type": value.shirt_id.type,
            "season": value.shirt_id.season
        }

        tab += `
        <div class="providers">
            <h3>${shirt.team} - ${shirt.type} - ${shirt.season}</h3>
        </div>
        `
        
    }

    for (let i of data){
        
        loadUpDisponibilty(i)
    }

    const disponibilities = document.getElementById("items-left-container")
    disponibilities.innerHTML = tab


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




