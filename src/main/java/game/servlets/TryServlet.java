package game.servlets;

import game.dao.UserDao;
import game.service.ComputerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * TryServlet try to guess the number
 * @autor Scherbakov Pavel
 * @version 2.1
 */
public class TryServlet extends HttpServlet {

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
            String inputNumber = req.getParameter("inputNumber");
            boolean isGame;
            String gameStatus;
            if (!ComputerService.isValidNumber(inputNumber)) {
                isGame = true;
                gameStatus = "Число состоит из 4 цифр и цифры числа не повторяются!";
            } else {
                if (dao.isHaveAttempt(name, inputNumber)) {
                    isGame = true;
                    gameStatus = "Такое число уже было!";
                } else {
                    isGame = dao.addAttempt(inputNumber, name);
                    if (isGame) {
                        gameStatus = "Ты не угадал! Попробуй еще раз!";
                    } else {
                        dao.calculateRating(name);
                        gameStatus = "Ты победил! Нажми рестарт и играй еще раз!";
                    }
                }
            }
            req.setAttribute("gameStatus", gameStatus);
            req.setAttribute("isGame", isGame);
            req.setAttribute("attempts", dao.getAttempts(name));
            getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
        }
    }

}