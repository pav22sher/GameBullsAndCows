package game.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * LoginServlet put the user's
 * name and password in the session
 * @autor Scherbakov Pavel
 * @version 2.1
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Encoder enc = Base64.getEncoder();
        password=new String(enc.encode(password.getBytes()));

        final HttpSession session = req.getSession();
        session.setAttribute("password",password);
        session.setAttribute("name",name);
        resp.sendRedirect(req.getContextPath() + "/game.jsp");
    }

}
