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
            showModal("invalidCredentials");
            throw new Error("User or password is incorrect");
            
        }

        return response;

    } catch (error) {
    
        console.log(error.message);
        throw error;

    }
    

}



//--------------Function that verifies if the user are already logged in--------------
async function alreadyLogged(){
    
    const url = "http://localhost:8080/client"

    const token = window.localStorage.getItem("Authorization");

    const IS_USER_LOGGED = token;

    let IS_TOKEN_VALID = false;

    try {
        const response = await fetch(url, {
            method: "GET",
            headers: new Headers({
                Authorization: token,
            }),
        });
    
        if (response.ok) {
            IS_TOKEN_VALID = true; 
        } else {
            console.error("Authorization token is invalid! -", response.status);
            showModal("invalidToken");
        }
    
    } catch (error) {
        console.error("Error fetching the data! -", error);
    }
    


    if(IS_USER_LOGGED && IS_TOKEN_VALID){
        changeWindow("index");
    }


}



//--------------Function to change HTML when back arrow pressed--------------
function changeWindow(page) {

    window.location.href = `${page}.html`;
}


function showModal(error) {

    const modal = document.getElementById("error-modal");
    let modalError = document.getElementById("content-modal");


    const errorMessage = {
        "invalidCredentials": `<strong>Username</strong> or <strong>password</strong> are wrong or doesn't exist`,
        "invalidToken": `Current <strong>token</strong> is invalid or expired!`
    }


    modalError.innerHTML = errorMessage[error];



    if (modal.style.display === "block") {

        modal.style.display = "none";

    } else if (modal.style.display === "none" || modal.style.display === "") {

        modal.style.display = "block";

    }
}


alreadyLogged()