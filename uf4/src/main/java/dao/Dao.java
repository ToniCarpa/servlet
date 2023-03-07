package dao;

import model.Post;
import model.Usuario;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Dao {
    private static Jdbc jdbc;


    public static Jdbc getInstance() throws SQLException {
        if (jdbc == null) jdbc = new Jdbc();
        return jdbc;
    }


    // INSERT USER
    public Usuario insertUsuario(Usuario usuario) throws SQLException {
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkdin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        return usuario;
    }

    // DELETE USER
    public void deleteUsuario(Usuario usuario) throws SQLException {
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_USER)) {
            ps.setString(1, String.valueOf(usuario.getId()));
            ps.execute();
        }
    }

    // UPDATE USER
    public void updateUsuario(Usuario usuario) throws SQLException {
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_UPDATE_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getLinkdin());
            ps.setString(3, usuario.getGitlab());
            ps.execute();
        }
    }

    // SELECT USUER BY ID
    public Usuario getUsuarioById(UUID id) throws SQLException {
        Usuario u = null;

        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setString(1, String.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
                }
            }
        }
        return u;
    }

    // SELECT USUER BY MAIL & PASWD
    public Usuario getUsuarioByMailPass(String mail, String pass) throws SQLException {
        Usuario u = null;

        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setString(1, mail);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    u = new Usuario(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
                }
            }
        }
        return u;
    }

    // SELECT ALLUSERS
    public ArrayList<Usuario> allUsuariosList() throws SQLException {
        ArrayList<Usuario> listUsuarios = new ArrayList<>();
        Usuario u = null;

        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALLUSERS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listUsuarios.add(new Usuario(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad")));
                }
            }
        }
        return listUsuarios;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // INSERT POST

    // DELETE POST

    // CREATE POST
    public Post creaPost(String tit, String url, String mnsage, String date){
        ArrayList<Post> listUserPosts = new ArrayList<>();
//        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
return null;
        }

    // SELECT ALLPOST USER
    public ArrayList<Post> allPostUserList(Usuario u) throws SQLException {
        ArrayList<Post> listUserAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
            pre.setString(1, String.valueOf(u.getId()));
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listUserAllPosts.add(new Post(rs.getInt("id"), rs.getString("usuario"), rs.getString("titulo"), rs.getString("url"), rs.getString("message"), rs.getDate("date")));
                }
            }
        }
        return listUserAllPosts;
    }

    // SELECT ALL POSTS
    public ArrayList<Post> allPostList() throws SQLException {
        ArrayList<Post> listAllPosts = new ArrayList<>();

        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALL_POSTS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listAllPosts.add(new Post(rs.getInt("id"), rs.getString("usuario"), rs.getString("titulo"), rs.getString("url"), rs.getString("message"), rs.getDate("date")));
                }
            }
        }
        return listAllPosts;
    }

    public ArrayList<Post> createRandomPosts() throws SQLException {
        ArrayList<Post> listRandomPost = allPostList();
        for (int i = 0; i < 10; i++) {
            listRandomPost.add(randomPosts(i));
        }
        return listRandomPost;
    }

    private Post randomPosts(int id) {
        Random random = new Random();
        String[] titulo = {"titu1", "titu2", "titu3"};
        String[] url = {"1@gmail", "2@gmail", "3@gmail"};
        String[] message = {"1mesnaje", "2mensaje", "3mensaje"};
        Post p = new Post(id, "Post" + id, titulo[random.nextInt(3)], url[random.nextInt(3)], message[random.nextInt(3)], new Date());
        return p;
    }


}
