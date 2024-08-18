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


    return response;

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


alreadyLogged()