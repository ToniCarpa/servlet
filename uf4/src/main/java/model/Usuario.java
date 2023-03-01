package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Usuario {
    private int id;
    private String name;
    private String password;
    private String email;
    private String linkdin;
    private String gitlab;
    private ArrayList<Post> listUserPost = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(int id, String name, String password, String email, String linkdin, String gitlab, ArrayList<Post> listPost) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.linkdin = linkdin;
        this.gitlab = gitlab;
        this.listUserPost = listPost;
    }

    public Usuario(int id, String usuario, String password, String email, String linkdin, String gitlab) {
        this.id = id;
        this.name = usuario;
        this.password = password;
        this.email = email;
        this.linkdin = linkdin;
        this.gitlab = gitlab;
    }

    public Usuario(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getUsuario() {
        return name;
    }

    public void setUsuario(String usuario) {
        this.name = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkdin() {
        return linkdin;
    }

    public void setLinkdin(String linkdin) {
        this.linkdin = linkdin;
    }

    public String getGitlab() {
        return gitlab;
    }

    public void setGitlab(String gitlab) {
        this.gitlab = gitlab;
    }

    public ArrayList<Post> getListUserPost() {
        return listUserPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListUserPost(ArrayList<Post> listPost) {
        this.listUserPost = listPost;
    }
}
