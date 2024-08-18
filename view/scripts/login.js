//--------------Function login into user--------------
async function login() {

    const username = document.getElementById("user").value;
    const password = document.getElementById("password").value;


    const response = await loginApi(username, password)

    let key = "Authorization";
    
    let token = response.headers.get(key);
    window.localStorage.setItem(key, token);

    changeWindow("index");


}

//--------------Function to call the login method from the API--------------
async function loginApi(username, password){



    try {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: new Headers({
                "Content-Type": "application/json; charset=utf8",
                Accept: "application/json",
            }),
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        });
    
        console.log(response)
        
        if(!response.ok){
            showModal();
            throw new Error("User or password is incorrect");
            
        }

        return response;

    } catch (error) {
    
        console.log(error.message);
        throw error;

    }
    

}



//--------------Function that verifies if the user are already logged in--------------
function alreadyLogged(){

    const IS_USER_LOGGED = window.localStorage.getItem("Authorization");

    if(IS_USER_LOGGED){
        changeWindow("index");
    }


}



//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(page) {

    window.location.href = `${page}.html`;
}


function showModal() {

    const modal = document.getElementById("error-modal");


    if (modal.style.display === "block") {

        modal.style.display = "none";

    } else if (modal.style.display === "none" || modal.style.display === "") {

        modal.style.display = "block";

    }
}


alreadyLogged()