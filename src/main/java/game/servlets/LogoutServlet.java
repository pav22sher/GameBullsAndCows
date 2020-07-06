package game.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogoutServlet delete the user's
 * name and password from session
 * @autor Scherbakov Pavel
 * @version 2.1
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final HttpSession session = req.getSession();
        session.removeAttribute("password");
        session.removeAttribute("name");
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}

