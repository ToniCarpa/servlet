package dao;

import model.Post;
import model.Usuario;
import utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Dao {
    private static Jdbc jdbc;


    public static Jdbc getInstance() throws SQLException {
        if (jdbc == null) {
            jdbc = new Jdbc();
        }
        return jdbc;
    }


    // --------------------------------------------------USUARIO--------------------------------------------------------


    // INSERT USER
    public void insertUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkdin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        jdbc.close();
    }

    // DELETE USER
    public void deleteUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_USER)) {
            ps.setInt(1, usuario.getId());
            ps.execute();
        }
        jdbc.close();
    }

    // UPDATE USER
    public void updateUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_UPDATE_USER)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkdin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        jdbc.close();

    }

    // SELECT USUER BY ID
    public Usuario getUsuarioById(int id) throws SQLException, ClassNotFoundException {
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

    // SELECT USUER BY MAIL & PASWD
    public Usuario getUsuarioByMailPass(String mail, String pass) throws SQLException, ClassNotFoundException {
        Usuario u = null;
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
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
    public ArrayList<Usuario> allUsuariosList() throws SQLException, ClassNotFoundException {
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
    public void creaPost(Post post) throws SQLException, ClassNotFoundException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_POST)) {
            ps.setString(1, post.getUsuario());
            ps.setString(2, post.getTitulo());
            ps.setString(3, post.getUrl());
            ps.setString(4, post.getMessage());
            ps.setDate(5, (Date) post.getDate());
            ps.setInt(5, post.getLikes());
            ps.execute();
        }
        jdbc.close();
    }

    // DELETE POST
    public void deletePost(Post post) throws SQLException, ClassNotFoundException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_POST)) {
            ps.setString(1, String.valueOf(post.getId()));
            ps.execute();
        }
        jdbc.close();
    }

    // SELECT ALLPOST USER
    public ArrayList<Post> allPostUserList(Usuario u) throws SQLException, ClassNotFoundException {
        jdbc.conect();
        ArrayList<Post> listUserAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
            pre.setString(1, String.valueOf(u.getId()));
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listUserAllPosts.add(new Post(rs.getInt("id"), rs.getString("usuario"), rs.getString("titulo"), rs.getString("url"), rs.getString("message"), rs.getDate("date")));
                }
            }
        }
        jdbc.close();
        return listUserAllPosts;
    }

    // SELECT ALL POSTS
    public ArrayList<Post> allPostList() throws SQLException, ClassNotFoundException {
        jdbc.conect();
        ArrayList<Post> listAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALL_POSTS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listAllPosts.add(new Post(rs.getInt("id"), rs.getString("usuario"), rs.getString("titulo"), rs.getString("url"), rs.getString("message"), rs.getDate("date")));
                }
            }
        }
        jdbc.close();
        return listAllPosts;
    }


}
