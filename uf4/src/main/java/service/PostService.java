package service;

import dao.Dao;
import dao.Jdbc;
import model.Post;
import model.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
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
    }

    public static PostService getInstance() {
        if (postService == null) {
            postService = new PostService();
        }
        return postService;
    }

    // --------------------------------------------------LOGIN-------------------------------------------------------

    public boolean loginUser(HttpServletRequest request) {
        try {
            Usuario u = existUser(request);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario existUser(HttpServletRequest request) throws SQLException {
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");

        Usuario u = dao.getUsuarioByMailPass(password, email);

        if (u == null) {
            throw new SQLException();
        }
        return u;

    }

    // --------------------------------------------------REGSITER-------------------------------------------------------
    public Usuario newUser(HttpServletRequest request) throws SQLException {
        HttpSession respuesta = request.getSession(true);

        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");
        String linkdn = request.getParameter("link");
        String gitlab = request.getParameter("git");

        Usuario t = dao.getUserByName(name);

        if (t != null) {
            respuesta.setAttribute("El nombre ya està cogido", respuesta);
            throw new SQLException();
        }
        Usuario u = new Usuario(name, password, email, linkdn, gitlab);
        return u;
    }

    public boolean createUser(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);
        try {
            Usuario u = newUser(request);
            dao.insertUsuario(u);
            respuesta.setAttribute("Bienvenido" + u.getName(), respuesta);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------HOME-------------------------------------------------------
    public ArrayList<Post> listPosts(HttpServletRequest request) {
        try {
            return dao.allPostList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Post> listUserPost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("id", id);
        try {
            return dao.allPostUserList(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------POSTS-------------------------------------------------------
    public void createPost(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);

        String name = request.getParameter("usuario");
        String tit = request.getParameter("titulo");
        String url = request.getParameter("url");
        String mens = request.getParameter("mensaje");
        Date data = Date.valueOf(request.getParameter("date"));
        int likes = Integer.parseInt(request.getParameter("likes"));

        try {
            dao.creaPost(new Post(name, tit, url, mens, data, likes));
            respuesta.setAttribute("Post creado", respuesta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePost(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);
        try {
            int idPost = Integer.parseInt(request.getParameter("idPost"));
            int id = (int) request.getSession().getAttribute("id");
            request.getSession().setAttribute("id", id);
            ArrayList<Post> postUserList = dao.allPostUserList(id);

            for (Post p : postUserList) {
                if (p.getId() == idPost) {
                    dao.deletePost(idPost);
                    respuesta.setAttribute("Post Borrado", respuesta);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
