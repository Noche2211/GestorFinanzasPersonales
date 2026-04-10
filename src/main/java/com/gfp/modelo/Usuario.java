package com.gfp.modelo;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String fechaNac;
    private String genero;
    private String correo;
    private String contrasena;

    public Usuario() {}

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getFechaNac() { return fechaNac; }
    public void setFechaNac(String fechaNac) { this.fechaNac = fechaNac; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}