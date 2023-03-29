package service;

import dao.Dao;
import model.Post;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostService {
    private Dao dao;
    private static PostService postService;

    public PostService() {
        dao = new Dao();
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
    public Boolean log(String mail, String pass) {
        try {
            Usuario u = dao.getUsuarioByMailPass(mail, pass);
            if (u == null) {
                throw new SQLException();
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try {
            HttpSession respuesta = request.getSession(true);
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
            HttpSession ses = request.getSession(true);
            ArrayList<Post>listPost = dao.allPostList();
            ses.setAttribute("listPost", listPost);
            return listPost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Post> listPostsNew(int id ) {
        try {
            return dao.allPostList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Post> listUserPost(HttpServletRequest request) {
        try {
            HttpSession ses = request.getSession(true);
            int id = (int) ses.getAttribute("id");

            ArrayList<Post>listUserPost = dao.listUserPost(id);
            ses.setAttribute("listUserPost", listUserPost);
            return listUserPost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------POSTS-------------------------------------------------------
    public void createPost(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);
        int id = (int) request.getSession().getAttribute("id");

        int idPost = Integer.parseInt(request.getParameter("idPost"));
        String tit = request.getParameter("titulo");
        String url = request.getParameter("url");
        String mens = request.getParameter("mensaje");
        Object img = request.getParameter("image");
        Date data = Date.valueOf(request.getParameter("date"));
        int likes = Integer.parseInt(request.getParameter("likes"));

        try {
            dao.creaPost(new Post(idPost,dao.getUsuarioById(id), tit, url, mens, img, data, likes));
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
            ArrayList<Post> postUserList = dao.listUserPost(id);

            for (Post p : postUserList) {
                if (dao.getUsuarioById(id) == p.getUsuario()) {
                    dao.deletePost(idPost);
                    respuesta.setAttribute("Post Borrado", respuesta);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sumLikes(HttpServletRequest request) {
        try {
            int id = (int) request.getSession().getAttribute("id");
            int likes = Integer.parseInt(request.getParameter("likes"));
            dao.likes(id, likes);
            int postID = Integer.parseInt(request.getParameter("postId"));

            ArrayList<Post> postlist = dao.allPostList();
            for(Post o :postlist){
                if(o.getId()==postID){
                    likes++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] imgByte (HttpServletRequest req) throws ServletException, IOException {
        Part filePart = req.getPart("image");
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        return imageBytes;
    }
}
