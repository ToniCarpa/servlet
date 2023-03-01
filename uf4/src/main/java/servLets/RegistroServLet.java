package servLets;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="RegistroServLet", urlPatterns = "/RegistroServLet.do")
public class RegistroServLet extends HttpServlet {
    private PostService postService;

    public RegistroServLet() {
        super();
        this.postService = postService;
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
            if(postService.newUser(name, pass, email,linkdn,git)){
                getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
            }else{
                getServletContext().getRequestDispatcher("/jsp/regsitro.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
