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

@WebServlet(name = "register", urlPatterns = "/register.do")
public class RegistroServLet extends HttpServlet {
    private PostService postService;

    public RegistroServLet() {
        super();
        this.postService = new PostService();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (postService.createUser(request)) {
            getServletContext().getRequestDispatcher("jsp/home.jsp").forward(request, response);
        }
        response.sendRedirect("jsp/register.jsp");
    }
}