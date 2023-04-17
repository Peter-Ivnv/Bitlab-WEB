package BItlabJavaEE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet (value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.getRequestDispatcher("/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/update?error";
        String email = req.getParameter("user-email");
        String fullName = req.getParameter("user-fullname");
        String password = req.getParameter("user-password");
        Long id = Long.valueOf(req.getParameter("user-id"));
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        if (DBManager.updateUser(user)== true){
            req.getSession().removeAttribute("currentUser");
            redirect = "/login";
        }
        resp.sendRedirect(redirect);
    }
}
