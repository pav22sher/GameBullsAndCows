package game.servlets.filters;

import game.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * if you are not logged in
 * then LoginFilter forward you to login.jsp
 * @autor Scherbakov Pavel
 * @version 2.1
 */

public class LoginFilter implements Filter {
    private UserDao dao;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dao=(UserDao)filterConfig.getServletContext().getAttribute("dao");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        if (isNull(session) ||
                isNull(session.getAttribute("name")) ||
                isNull(session.getAttribute("password")) ||
                !dao.userIsExist((String)session.getAttribute("name"),
                        (String)session.getAttribute("password"))) {
            session.removeAttribute("password");
            session.removeAttribute("name");
            req.setAttribute("loginStatus", "Пользователя с таким именем и паролем не существует!");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
