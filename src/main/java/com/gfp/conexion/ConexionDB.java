package com.gfp.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Configuración con IP 127.0.0.1 y puerto 3306
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/gfp_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "tu_contraseña";

    public static Connection obtenerConexion() throws SQLException {
        try {
            // Carga explícita del driver para el entorno del servidor
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error crítico: Driver de MySQL no encontrado.");
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}