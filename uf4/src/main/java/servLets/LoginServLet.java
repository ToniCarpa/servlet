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

    public LoginServLet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");
        UUID id = UUID.fromString(request.getParameter("id"));

        try {
            if (PostService.getInstance().checkUser(email, password)) {
                request.setAttribute("id", id);

                Usuario u = PostService.getInstance().usuarioId(id);
                request.setAttribute("usuario", u);

                RequestDispatcher view = getServletContext().getRequestDispatcher("/jsp/home.jsp");
                view.forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/registro.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}


