package service;

import dao.Dao;
import dao.Jdbc;
import model.Post;
import model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

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


    public boolean checkUser(String pass, String mail) throws SQLException {
        ArrayList<Usuario> listUsuarios = dao.allUsuariosList();
        boolean status = false;

        for (Usuario o : listUsuarios) {
            if (o.getEmail().equals(mail) && (o.getPassword().equals(pass))) {
                status = true;
            } else {
                listUsuarios.add(new Usuario(pass, mail));
                status = false;
            }
        }
            return status;
    }

    public ArrayList<Post> postList() throws SQLException {
        return dao.allPostList();
    }

    public ArrayList<Usuario> usuariosList() throws SQLException {
        return dao.allUsuariosList();
    }


    public ArrayList<Post> getPostsOrdered() throws SQLException {
        ArrayList<Post> postList = dao.allPostList();
        ArrayList<Post> postReturn = new ArrayList<Post>();
        for (Post p : postList) {
            //ORDENAR ARRAY date inverso
            postReturn.add(p);
        }
        return postReturn;
    }

}
