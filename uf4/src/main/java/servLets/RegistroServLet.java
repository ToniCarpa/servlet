package servLets;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="RegistroServLet", urlPatterns = "/RegistroServLet.do")
public class RegistroServLet extends HttpServlet {
    private PostService postService;

    public RegistroServLet() {
        super();
        this.postService = postService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
