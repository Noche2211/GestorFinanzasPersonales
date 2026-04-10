package com.gfp.dao;

import com.gfp.conexion.ConexionDB;
import com.gfp.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERTAR
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellido, fecha_nac, genero, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getFechaNac());
            ps.setString(4, usuario.getGenero());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // 🔥 ESTO ES LO IMPORTANTE
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = ConexionDB.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setFechaNac(rs.getString("fecha_nac"));
                u.setGenero(rs.getString("genero"));
                u.setCorreo(rs.getString("correo"));
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ELIMINAR
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, apellido=?, fecha_nac=?, genero=?, correo=? WHERE id_usuario=?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getFechaNac());
            ps.setString(4, usuario.getGenero());
            ps.setString(5, usuario.getCorreo());
            ps.setInt(6, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}