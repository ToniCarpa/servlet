
package servLets;

import dao.Dao;
import model.Post;
import service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "home", urlPatterns = "/home.do")
public class HomeServLet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PostService postService;

    public HomeServLet() {
        super();
        postService = new PostService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (postService.loginUser(request)) {
            postService.listPosts(request);
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
            //boton user -> postMISposts
            //boton like -> postLikes
        }
        getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
    }

}