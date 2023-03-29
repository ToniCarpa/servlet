package servlets;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register", urlPatterns = {"/register.do","/register"})
public class RegistroServLet extends HttpServlet {
    private PostService postService;

    public RegistroServLet() {
        super();
        this.postService = new PostService();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (postService.createUser(request)) {
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            response.sendRedirect("/index1.jsp");
        }
    }
}