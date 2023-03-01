package dao;

import utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    protected Connection conn;
    public void conect() throws SQLException, ClassNotFoundException {

        Class.forName(Constants.DB_DRIVER_CONNECTION);
        conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION, Constants.DB_USER_CONNECTION, Constants.DB_PASS_CONNECTION);
        System.out.println("Todo OK");

        conn.setAutoCommit(false);
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error al Cerrar : " + e);
        }
    }
}
