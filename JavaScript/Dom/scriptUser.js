let usersArray = [

        { name: 'Petr', login: 'Pit', password: '123456' },

        { name: 'Aidar', login: 'Aidar', password: '654321' },

        { name: 'Nursultan', login: 'Nursultan', password: '12345678' }

];

function checkUser() {
        let loginField = myForm.elements.login;
        let paswordField = myForm.elements.password;

        if (loginField.value =="") {
                loginError.innerHTML = 'Please enter login'
                passwordError.innerHTML = 'Please enter password'
        }
        if(loginField.value!="" && passwordField!=""){
                let auth = false;
                for(let u of usersArray){
                        if(loginField.value == u.login && passwordField.value == u.password){
                                auth = true;
                                break;
                        }
                }
        if(!auth){
                alert("No user with such login and password")
        }
        }
}