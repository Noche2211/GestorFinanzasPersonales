package com.gfp.servlet;

import com.gfp.dao.UsuarioDAO;
import com.gfp.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        // INSERTAR
        if ("insertar".equals(accion)) {

            Usuario u = new Usuario();
            u.setNombre(request.getParameter("nombre"));
            u.setApellido(request.getParameter("apellido"));
            u.setFechaNac(request.getParameter("fechaNac"));
            u.setGenero(request.getParameter("genero"));
            u.setCorreo(request.getParameter("correo"));
            u.setContrasena(request.getParameter("contrasena"));

            boolean exito = dao.insertarUsuario(u);

            request.setAttribute("mensaje",
                    exito ? "Usuario registrado correctamente" : "Error al registrar");

        }

        // ELIMINAR
        else if ("eliminar".equals(accion)) {

            int id = Integer.parseInt(request.getParameter("idUsuario"));
            boolean exito = dao.eliminarUsuario(id);

            request.setAttribute("mensaje",
                    exito ? "Usuario eliminado correctamente" : "Error al eliminar");

        }

        // ACTUALIZAR
        else if ("actualizar".equals(accion)) {

            Usuario u = new Usuario();
            u.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
            u.setNombre(request.getParameter("nombre"));
            u.setApellido(request.getParameter("apellido"));
            u.setCorreo(request.getParameter("correo"));

            boolean exito = dao.actualizarUsuario(u);

            request.setAttribute("mensaje",
                    exito ? "Usuario actualizado correctamente" : "Error al actualizar");

        }

        // SI NO VIENE ACCIÓN
        else {
            request.setAttribute("mensaje", "Acción no válida");
        }

        // SIEMPRE LISTAR USUARIOS
        request.setAttribute("listaUsuarios", dao.obtenerTodos());

        // REDIRECCIONAR A JSP
        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
    }
}