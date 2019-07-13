package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class coneccion {

    private Connection cn;

    public void conectar() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc.sqlserver://localhost:1433;databaseName=CompuTech", "usu", "pas");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error: " + e);
        }
    }

    public void desconectar() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    public static void main(String[] args) {
        coneccion dao = new coneccion();
        dao.getCn();
        try {
            if (dao.cn != null) {
                System.out.println("conectado");
            }
        } catch (Exception e) {
            throw e;
        }
        
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

}
