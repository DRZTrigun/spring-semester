package geek;

import geek.persist.User;
import geek.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class FindUserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //     /user/1
        String pathInfo = req.getPathInfo();
        String[] hope = pathInfo.split("/");

        User user = userRepository.findById(Long.parseLong(hope[1]));
        Long ID = user.getId();
        resp.getWriter().println("<ul>");
        resp.getWriter().println("<li>" + "Name user: " + user.getUsername());
        resp.getWriter().println("</li>");
        resp.getWriter().println("<li>");
        resp.getWriter().println("Id user: " + user.getId());
        resp.getWriter().println("</li>");
        resp.getWriter().println("</ul>");
    }
}
