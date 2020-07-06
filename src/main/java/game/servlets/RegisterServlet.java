package game.servlets;

import game.dao.UserDao;
import game.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * RegisterServlet register user
 * @autor Scherbakov Pavel
 * @version 2.1
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserDao dao = (UserDao) getServletContext().getAttribute("dao");
        String name = req.getParameter("newname");
        String password = req.getParameter("newpassword");
        Encoder enc = Base64.getEncoder();
        password=new String(enc.encode(password.getBytes()));

        if(dao.getUserByName(name)==null){
            dao.save(new User(name,password));
            req.setAttribute("registrationStatus", "Вы успешно зарегистрировались!");
        }
        else{
            req.setAttribute("registrationStatus", "Пользователь с таким именем уже существует!");
        }

        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

}
