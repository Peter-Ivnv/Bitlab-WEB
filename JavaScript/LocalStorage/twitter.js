let userArray;
    if(localStorage.getItem('users_data') == null){
      userArray = []
    } else {
        let json_data = localStorage.getItem('users_data');
        userArray = JSON.parse(json_data)
    }
    function registration() {
      let obj = {}
      obj.email = email.value
      obj.password = password.value
      obj.fullname = fullname.value
      obj.country = country.value
      obj.birth_date = birth_date.value
      userArray.push(obj)
      let json = JSON.stringify(userArray)
      localStorage.setItem('users_data', json)
      document.location.href = "enterTwitter.html";
    }

    function signIn(){
        let json_data = localStorage.getItem("users_data")
        let data = JSON.parse(json_data)
        let auth = true
        for(let obj of data){
            if(emailPerson.value == obj.email && passwordPerson.value == obj.password){
                document.location.href = "welcome.html"
                auth = false
                localStorage.setItem("mama", JSON.stringify(obj))
                break
            } 
        }

        if(auth){
            alert("YOU ARE NOT REGISTERED")
        }
    }