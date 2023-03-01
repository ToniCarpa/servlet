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


@WebServlet(name = "LoginServLet", urlPatterns = "/LoginServLet.do")
public class LoginServLet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServLet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
        view.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            ArrayList<String> t = PostService.getInstance().checkUser(id);
            if (password.equals(t.get(1)) && email.equals(t.get(0))) {
                getServletContext().getRequestDispatcher("jsp/home.jsp").forward(request, response);
                //response.sendRedirect( "/home.jsp" );
            } else{
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}


