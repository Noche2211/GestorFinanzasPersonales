<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.gfp.modelo.Usuario" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Usuarios</title>
</head>
<body>

<h2>Resultado del registro</h2>

<%
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) {
%>
    <p style="color:green;">
        <%= mensaje %>
    </p>
<%
    }
%>

<!-- 🔥 BOTÓN GET (IMPORTANTE PARA EV02) -->
<a href="UsuarioServlet">Consultar usuarios (GET)</a>

<h3>Usuarios registrados:</h3>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Correo</th>
        <th>Acciones</th>
    </tr>

<%
    List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuarios");

    if (lista != null && !lista.isEmpty()) {
        for (Usuario u : lista) {
%>
    <tr>
        <td><%= u.getIdUsuario() %></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellido() %></td>
        <td><%= u.getCorreo() %></td>

        <td>
            <!-- ELIMINAR -->
            <form action="UsuarioServlet" method="POST" style="display:inline">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                <button type="submit">Eliminar</button>
            </form>

            <!-- EDITAR -->
            <button onclick="editarUsuario('<%= u.getIdUsuario() %>', '<%= u.getNombre() %>', '<%= u.getApellido() %>', '<%= u.getCorreo() %>')">
                Editar
            </button>
        </td>
    </tr>
<%
        }
    } else {
%>
    <tr>
        <td colspan="5">No hay usuarios registrados</td>
    </tr>
<%
    }
%>

</table>

<hr>

<h3>Actualizar Usuario</h3>

<form action="UsuarioServlet" method="POST">

    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="idUsuario" id="idUsuario">

    Nombre: <input type="text" name="nombre" id="nombre" required><br><br>
    Apellido: <input type="text" name="apellido" id="apellido" required><br><br>
    Correo: <input type="email" name="correo" id="correo" required><br><br>

    <button type="submit">Actualizar</button>

</form>

<script>
function editarUsuario(id, nombre, apellido, correo) {
    document.getElementById("idUsuario").value = id;
    document.getElementById("nombre").value = nombre;
    document.getElementById("apellido").value = apellido;
    document.getElementById("correo").value = correo;
}
</script>

</body>
</html>