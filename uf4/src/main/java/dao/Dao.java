package dao;

import model.Post;
import model.Usuario;
import utils.Constants;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {
    private Jdbc jdbc;

    public Dao() {
        jdbc = new Jdbc();
    }


    // --------------------------------------------------USUARIO--------------------------------------------------------

    // INSERT USER
    public void insertUsuario(Usuario usuario) throws SQLException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkdin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // DELETE USER
    public void deleteUsuario(Usuario usuario) throws SQLException { //PERFIL
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_USER)) {
            ps.setInt(1, usuario.getId());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // UPDATE USER
    public void updateUsuario(Usuario usuario) throws SQLException { //PERFIL
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_UPDATE_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkdin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // SELECT USUER BY ID
    public Usuario getUsuarioById(int id) throws SQLException { // ID HIDDEN
        jdbc.conect();
        Usuario u = null;
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
                }
            }
        }
        jdbc.close();
        return u;
    }

    public Usuario getUserByName(String name) throws SQLException {
        jdbc.conect();
        Usuario u = null;
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_BYNAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
                }
            }
        }
        jdbc.close();
        return u;
    }

    // SELECT USUER BY MAIL & PASWD
    public Usuario getUsuarioByMailPass(String mail, String pass) throws SQLException {
        Usuario u = null;
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_BYPASSMAIL)) {
            ps.setString(1, mail);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    u = new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad"));
                }
            }
        }
        jdbc.close();
        return u;
    }

    // SELECT ALLUSERS
    public ArrayList<Usuario> allUsuariosList() throws SQLException {
        ArrayList<Usuario> listUsuarios = new ArrayList<>();
        Usuario u = null;
        jdbc.conect();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALLUSERS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listUsuarios.add(new Usuario(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("linkdin"), rs.getString("gitlad")));
                }
            }
        }
        jdbc.close();
        return listUsuarios;
    }


    // --------------------------------------------------POSTS----------------------------------------------------------

    // CREATE/INSERT POST
    public void creaPost(Post post) throws SQLException {
        jdbc.conect();

        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_POST)) {
            ps.setInt(1, post.getUsuario().getId());
            ps.setString(2, post.getTitulo());
            ps.setString(3, post.getUrl());
            ps.setString(4, post.getMessage());
            ps.setBytes(5, (byte[]) post.getImage());
            ps.setInt(6, post.getLikes());
            ps.setDate(7, (Date) post.getDate());

            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // DELETE POST
    public void deletePost(int id) throws SQLException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_POST)) {
            ps.setInt(1, id);
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // SELECT ALLPOST USER
    public ArrayList<Post> listUserPost(int id) throws SQLException {
        jdbc.conect();
        ArrayList<Post> listUserAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
            pre.setInt(1, id);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    listUserAllPosts.add(new Post(rs.getInt("id"), getUsuarioById(rs.getInt("id_usuari")), rs.getString("titulo"), rs.getString("url"), rs.getString("message"), rs.getObject("image"), rs.getDate("date"), rs.getInt("likes")));
                }
            }
        }
        jdbc.close();
        return listUserAllPosts;
    }

    // SELECT ALL POSTS
    public ArrayList<Post> allPostList() throws SQLException {
        jdbc.conect();
        ArrayList<Post> listAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALL_POSTS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listAllPosts.add(new Post(rs.getInt("id"), getUsuarioById(rs.getInt("id_usuari")), rs.getString("titulo"), rs.getString("url"), rs.getString("message"), rs.getObject("image"), rs.getDate("date"), rs.getInt("likes")));
                }
            }
        }
        jdbc.close();
        return listAllPosts;
    }

    public void likes(int id, int likes) throws SQLException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.INSERT_LIKE)) {
            ps.setInt(1, likes + 1);
            ps.setInt(2, id);
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();

    }

}