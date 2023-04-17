<%@ page import="BItlabJavaEE.User" %>
<%@ page import="BItlabJavaEE.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BITLAB Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

</head>
<body>
<div class = "container">
<div class="container">
    <%
        User currentUser = (User) request.getSession().getAttribute("currentUser");
    %>
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(35,67,105,0.85);">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">BITLAB BLOG</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/home">News</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/add-news">Add news</a>
                    </li>
                    <%
                        if (currentUser == null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                    <%
                    } else if (currentUser != null) {
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Profile
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/profile">Profile</a></li>
                            <li><a class="dropdown-item" href="/settings">Settings</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>


<div class="container" style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-12">
            <form action="/profile" method="post">
                <div class="form-group">
                    <input type="text" name="user-id" value="<%=currentUser.getId()%>">
                </div>
                <div class="form-group">
                    <label class="form-label">Your name:</label>
                    <input type="text" class="form-control" name="user-fullname" value="<%=currentUser.getFullName()%>">
                </div>
                <div class="form-group">
                    <label class="form-label">Your email:</label>
                    <input type="text" class="form-control" name="user-email" value="<%=currentUser.getEmail()%>">
                </div>
                <div class="form-group">
                    <label class="form-label">Your password:</label>
                    <input type="text" class="form-control" name="user-password" value="<%=currentUser.getPassword()%>">
                </div>
                <p></p>
                <div class="form-group">
                    <button class="btn btn-success">UPDATE</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%
    }
%>

<div class="container p-4 fixed-bottom">
    <div class="row py-3 bg-dark text-light">
        <div class="col-12">
            <div class="text-center">
                Copyright &copy; All Rights Reserved
            </div>
        </div>
    </div>
</div>
</div>
</body>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</html>