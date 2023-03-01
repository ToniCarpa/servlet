package servLets;

import dao.Dao;
import model.Post;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "HomeServLet", urlPatterns = "/HomeSerLet.do")
public class HomeServLet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PostService postService;

    public HomeServLet() {
        super();
        postService = new PostService();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("posts", PostService.getInstance().postList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Post> postList = null;
        try {
            postList = PostService.getInstance().getPostsOrdered();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("posts", postList);
        getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }


}
