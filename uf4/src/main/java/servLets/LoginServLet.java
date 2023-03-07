package servLets;

import dao.Dao;
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
import java.util.UUID;


@WebServlet(name = "LoginServLet", urlPatterns = "/LoginServLet.do")
public class LoginServLet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostService postService;

    public LoginServLet() {
        super();
        this.postService = new PostService();
    }

    //response.sendRedirect(request.getContextPath() + "/OtroServlet");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");
        HttpSession repo = request.getSession();

        try {
            Usuario u = postService.checkUser(email, password);
            if (postService.ExistUser(u)) {
                repo.setAttribute("usuario", u);
                RequestDispatcher view = getServletContext().getRequestDispatcher("/jsp/home.jsp");
                view.forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("jsp/register.jsp").forward(request, response);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}








