const url = "http://localhost:8080/client/5"

function show(data){

    let tab =
    `<thead>
        <th scope="col">#</th>
        <th scope="col">Description</th>
    </thead>

    <tr>
        <td scope="row">${data?.id ? data.id : "Inexistente"}</td>
        <td>${data?.name ? data.name : "Inexistente"}</td>
    </tr>
    
    `
    
    ;


    document.getElementById("table").innerHTML = tab;

}



async function getAPI(url){
    const response = await fetch(url, {
        method: "GET"});

    const data = await response.json();

    console.log(data);

    show(data);
}
   
getAPI(url);



