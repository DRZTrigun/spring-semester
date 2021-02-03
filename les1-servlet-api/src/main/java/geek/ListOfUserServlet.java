package geek;

import geek.persist.User;
import geek.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list-of-users")
public class ListOfUserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<table style=width:100%>");
        resp.getWriter().println("<tr>");
        resp.getWriter().println("<th>Number</th>");
        resp.getWriter().println("<th>User</th>");
        resp.getWriter().println("<th>Id</th>");
        resp.getWriter().println("</tr>");
        int j = 0;

        for (User user : userRepository.findAll()) {
            j = j + 1;
            resp.getWriter().println("<tr>");
            resp.getWriter().println("<th>" + j + "</th>");
            resp.getWriter().println("<th> <a href='" + getServletContext().getContextPath() + "/user/" + user.getId() + "'>" + user.getUsername() + "</a></th>");
            resp.getWriter().println("<th>" + user.getId() + "</th>");
            resp.getWriter().println("</tr>");
        }
        resp.getWriter().println("</table>\r\n");

    }
}
