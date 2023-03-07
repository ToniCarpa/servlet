package servLets;

import model.Post;
import model.Usuario;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RegistroServLet", urlPatterns = "/RegistroServLet.do")
public class RegistroServLet extends HttpServlet {

    public RegistroServLet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        String email = req.getParameter("mail");
        String linkdn = req.getParameter("link");
        String git = req.getParameter("git");
        try {
            Usuario u = PostService.getInstance().newUser(name, pass, email, linkdn, git);
            ArrayList<Post> listPostUsuario = PostService.getInstance().listPostUsusario(u);
            req.setAttribute("usuario", u);
            req.setAttribute("listPostUsuario", listPostUsuario);
            // System.out.println("bienvenido" + name);
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
