package servlet;


import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "edit", urlPatterns = {"/edit.do"})
public class EditPostServLet extends HttpServlet {
    private PostService postService;

    public EditPostServLet() {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (postService.loginUser(req)) {
            //boton delete-> pOstDELETE
            postService.deletePost(req);
            //boton create-> postCREATE
            postService.createPost(req);
            getServletContext().getRequestDispatcher("jsp/cretePost.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("login.jsp").forward(req, resp);
    }


}

