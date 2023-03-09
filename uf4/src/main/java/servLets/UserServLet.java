package servLets;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user", urlPatterns = "/user.do")
public class UserServLet extends HttpServlet {
    private PostService postService;

    public UserServLet() {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (postService.loginUser(req)) {
            postService.listUserPost(req);
            getServletContext().getRequestDispatcher("/jsp/postUser.jsp").forward(req, resp);
            //boton edit -> editServer

        }
        getServletContext().getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
