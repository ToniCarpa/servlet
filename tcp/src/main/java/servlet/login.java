package servlet;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "login", urlPatterns = "../login.do")
public class login extends HttpServlet {
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    public login() {
        super();
        this.postService = new PostService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail");
        String password = request.getParameter("pass");
        if (postService.log(email, password)) {
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }
}