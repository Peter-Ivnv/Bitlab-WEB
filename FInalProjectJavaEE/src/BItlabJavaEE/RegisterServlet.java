package BItlabJavaEE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet ("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/register?email-is-exist";
        String email = req.getParameter("user-email");
        String password = req.getParameter("user-password");
        String fullName = req.getParameter("user-fullName");


        User checkUser = DBManager.getUser(email);
        if(checkUser == null){
            redirect = "/register?password-is-not-match";
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setRole_id(2);
            DBManager.addUser(user);
            if(DBManager.addUser(user) == true){
                redirect = "/register?success";
            }
        }
        resp.sendRedirect(redirect);
    }

}
