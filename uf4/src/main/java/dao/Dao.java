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

public class Dao {
    private static Jdbc jdbc;


    public static Jdbc getInstance() throws SQLException {
        if (jdbc == null) jdbc = new Jdbc();
        return jdbc;
    }


    // INSERT USER
    public void insertUsuario(Usuario usuario) throws SQLException {
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getName());
            ps.execute();
        }
    }

    // DELETE USER
    public void deleteUsuario(Usuario usuario) throws SQLException {
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_USER)) {
            ps.setInt(1, usuario.getId());
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
    public Usuario getUsuarioById(int id) throws SQLException {
        Usuario u = null;

        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
                }
            }
        }
        return u;
    }

    public Usuario getUsuarioByMailPass(String mail, String pass) throws SQLException {
        Usuario u = null;

        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setString(1, mail);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    u = new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
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
                    listUsuarios.add(new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad")));
                }
            }
        }
        return listUsuarios;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // INSERT POST
    // DELETE POST
    // CREATE POST
    // SELECT POST
    // SELECT ALLPOST USER
    public ArrayList<Post> allPostUserList(Usuario u) throws SQLException {
        ArrayList<Post> listUserAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
            pre.setInt(1, u.getId());
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
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

    public ArrayList<Post> createPosts() throws SQLException {
        ArrayList<Post> listRandomPost = allPostList();
        for (int i = 0; i < 10; i++) {
            listRandomPost.add(generateRandomPosts(i));
        }
        return listRandomPost;
    }

    private Post generateRandomPosts(int id) {
        Random random = new Random();
        String[] titulo = {"titu1", "titu2", "titu3"};
        String[] url = {"1@gmail", "2@gmail", "3@gmail"};
        String[] message = {"1mesnaje", "2mensaje", "3mensaje"};
        Post p = new Post(id, "Post" + id, titulo[random.nextInt(3)], url[random.nextInt(3)], message[random.nextInt(3)], new Date());
        return p;
    }


}
