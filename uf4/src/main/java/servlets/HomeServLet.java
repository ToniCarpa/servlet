
package servlets;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "home", urlPatterns = "/home.do")
public class HomeServLet extends HttpServlet {
    private PostService postService;
    public HomeServLet() {
        super();
        postService = new PostService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(true);
        session.setAttribute("id", id);
        if (postService.loginUser(request)) {
            postService.listPostsNew(id);
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("jsp/regsiter.jsp").forward(request, response);

        /*
        if (postService.loginUser(request)) {
            postService.listPosts(request);
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("jsp/regsiter.jsp").forward(request, response);
        */

    }
}