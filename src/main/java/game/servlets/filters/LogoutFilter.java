package game.servlets.filters;

import game.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * if you logged in
 * then LogoutFilter forward you to game.jsp
 * @autor Scherbakov Pavel
 * @version 2.1
 */

public class LogoutFilter implements Filter {
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
        if (nonNull(session) &&
                nonNull(session.getAttribute("name")) &&
                nonNull(session.getAttribute("password")) &&
                dao.userIsExist(session.getAttribute("name").toString(),
                        session.getAttribute("password").toString())) {
            res.sendRedirect(req.getContextPath() + "/game.jsp");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
