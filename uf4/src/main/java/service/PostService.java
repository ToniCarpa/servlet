package service;

import dao.Dao;
import dao.Jdbc;
import model.Post;
import model.Usuario;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class PostService {
    private Dao dao;
    private Jdbc jdbc;
    private static PostService postService;
    private Usuario usuario;
    private ArrayList<Post> ListaPosts;

    public PostService() {
        dao = new Dao();
        jdbc = new Jdbc();
        this.postService = postService.getInstance();
    }

    public static PostService getInstance() {
        if (postService == null)
            postService = new PostService();
        return postService;
    }

    // --------------------------------------------------USUARIO-------------------------------------------------------

    public boolean loginUser(HttpServletRequest request) throws ClassNotFoundException {
        try {
            Usuario u = existUser(request);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario existUser(HttpServletRequest request) throws ClassNotFoundException {
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");
try {
        Usuario u = dao.getUsuarioByMailPass(password, email);
        if (u == null) {
            throw new SQLException();
        }
        return u;
    } catch (SQLException e) {
    throw new RuntimeException(e);
} catch (ClassNotFoundException e) {
    throw new RuntimeException(e);
}

        // --------------------------------------------------POSTS-------------------------------------------------------


    // CREATE-DELETE


    // --------------------------------------------------LISTS-------------------------------------------------------

    public ArrayList<Post> postList() throws SQLException, ClassNotFoundException {
        return dao.allPostList();
    }

    public ArrayList<Post> listPostUsusario(Usuario u) throws SQLException, ClassNotFoundException {
        return dao.allPostUserList(u);
    }

    public ArrayList<Usuario> usuariosList() throws SQLException, ClassNotFoundException {
        return dao.allUsuariosList();
    }

}
