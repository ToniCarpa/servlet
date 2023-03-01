package utils;

import java.time.format.DateTimeFormatter;

public class Constants {
    // CONEXION
    public static final String DB_USER_CONNECTION = "root";
    public static final String DB_PASS_CONNECTION = "root";
    public static final String DB_DRIVER_CONNECTION = "com.mysql.cj.jdbc.Driver";
    public static final String SCHEMA_NAME = "m06uf4servlets";
    public static final String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/DbName?useUnicode=true&serverTimezone=UTC&useSSL=false";
    final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    // POSTS
    public static final String SQL_INSERT_POST =
            "INSERT INTO posts (titulo, date, message) VALUES (?, ?, ?);";
    public static final String SQL_DELETE_POST =
            "DELETE FROM posts WHERE id = ?;";
    public static final String SQL_UPDATE_POST =
            "UPDATE posts SET titulo = ?, date = ?, message = ? WHERE id = ?;";
    public static final String SQL_SELECT_POST =
            "SELECT titulo, date, message FROM posts WHERE id = ?;";
    public static final String SQL_SELECT_ALL_POSTS =
            "SELECT * FROM posts ORDER BY date DESC;";
    public static final String SQL_SELECT_FILTER_TITULO_POSTS =
            "SELECT * FROM posts WHERE titulo =?;";

    // USUARIO
    public static final String SQL_INSERT_USER =
            "INSERT INTO usuario (email, password, name) VALUES (?,?,?)";
    public static final String SQL_DELETE_USER =
            "DELETE usuario WHERE id = ?";
    public static final String SQL_UPDATE_USER =
            "UPDATE usuario SET name = ?, linkdin = ?, gitlab = ? WHERE id = ?;";
    public static final String SQL_SELECT_USERBYID =
            "SELECT * FROM usuario WHERE id=?;";
    public static final String SQL_SELECT_PASSMAIL_BYUSER =
            "SELECT password, email FROM usuario WHERE id = ?;";
    public static final String SQL_SELECT_ALLUSERS =
            "SELECT * FROM usuario;";
    public static final String SQL_SELECT_USER_POSTS =
            "SELECT posts FROM usuario WHERE id = ?;";

}
