package model;

import utils.Error;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Post {
    private int id;
    private String usuario;
    private String titulo;
    private String url;
    private String message;
    private Object image;
    private Date date;
    private int likes;


    public Post(int id, String usuario, String titulo, String url, String message, Object image, Date date, int likes) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.url = url;
        this.message = message;
        this.image = image;
        this.date = date;
        this.likes = likes;
    }

    public Post(String usuario, String titulo, String url, String message, Object image, Date date, int likes) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.url = url;
        this.message = message;
        this.image = image;
        this.date = date;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}