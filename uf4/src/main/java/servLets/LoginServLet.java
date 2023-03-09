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


@WebServlet(name = "index", urlPatterns = "/index.do")
public class LoginServLet extends HttpServlet {
    private PostService postService;

    public LoginServLet() {
        super();
        this.postService = new PostService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (postService.loginUser(request)) {
            getServletContext().getRequestDispatcher("jsp/home.jsp").forward(request, response);
        }
        response.sendRedirect("login.jsp");
    }
}