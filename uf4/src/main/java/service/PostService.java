package service;

import dao.Dao;
import dao.Jdbc;
import model.Post;
import model.Usuario;

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


    // PARAMETROS UNICOS
    public Usuario checkUser(String pass, String mail) throws SQLException {
        ArrayList<Usuario> listUsuarios = dao.allUsuariosList();
        Usuario u = null;
        for (Usuario o : listUsuarios) {
            if (o.getEmail().equals(mail) && (o.getPassword().equals(pass))) {
                u = o;
            } else {
                System.out.println("Usuario no encontrado, registrate");
            }
        }
        return u;
    }


    public boolean ExistUser(Usuario u) throws SQLException {
        ArrayList<Usuario> usuarioArrayList = dao.allUsuariosList();
        boolean status = false;
        for (Usuario x : usuarioArrayList) {
            if (x.getId().equals(x.getId())) {
                status = true;
            } else {
                status = false;
            }
        }
        return status;
    }

    public Usuario newUser(String name, String pass, String mail, String link, String git) throws SQLException {
        Usuario t = null;
        ArrayList<Usuario> usuList = dao.allUsuariosList();
        for (Usuario u : usuList) {
            if (u.getName().equals(name)) {
                System.out.println("Escoje otro nombre");
            } else {
                t = dao.insertUsuario(new Usuario(UUID.randomUUID(), name, pass, mail, link, git));
                usuList.add(t); //new Usuario(UUID.randomUUID(), name, pass, mail, link, git));
            }
        }
        return t;
    }


    public ArrayList<Post> postList() throws SQLException {
        return dao.allPostList();
    }

    public ArrayList<Post> listPostUsusario(Usuario u) throws SQLException {
        return dao.allPostUserList(u);
    }

    public ArrayList<Usuario> usuariosList() throws SQLException {
        return dao.allUsuariosList();
    }

    public Usuario usuarioId(UUID id) throws SQLException {
        return dao.getUsuarioById(id);
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
