package game.servlets;

import game.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * StartServlet start game
 * @autor Scherbakov Pavel
 * @version 2.1
 */
public class StartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserDao dao = (UserDao) getServletContext().getAttribute("dao");
        final HttpSession session = req.getSession();

        if (isNull(session) ||
                isNull(session.getAttribute("name")) ||
                isNull(session.getAttribute("password")) ||
                !dao.userIsExist((String)session.getAttribute("name"),
                        (String)session.getAttribute("password"))) {
            session.removeAttribute("password");
            session.removeAttribute("name");
            req.setAttribute("loginStatus", "Пользователя с таким именем и паролем не существует!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else {
            final String name = (String) session.getAttribute("name");
            dao.restart(name);
            req.setAttribute("isGame", true);
            req.setAttribute("attempts", dao.getAttempts(name));
            getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
        }
    }

}