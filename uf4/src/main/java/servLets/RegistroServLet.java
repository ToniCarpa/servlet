package servLets;

import model.Post;
import model.Usuario;
import service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RegistroServLet", urlPatterns = "/RegistroServLet.do")
public class RegistroServLet extends HttpServlet {
private PostService postService;
    public RegistroServLet() {
        super();
        this.postService = new PostService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = getServletContext().getRequestDispatcher("/jsp/register.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        String email = req.getParameter("mail");
        String linkdn = req.getParameter("link");
        String git = req.getParameter("git");
        HttpSession repo = req.getSession();

        try {
            repo.getAttributeNames();
            Usuario u = postService.newUser(id, name, pass, email, linkdn, git);
            repo.setAttribute("usuario", u);
            resp.
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
