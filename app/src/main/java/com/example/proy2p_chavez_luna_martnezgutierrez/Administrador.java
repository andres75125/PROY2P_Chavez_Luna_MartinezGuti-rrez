package com.example.proy2p_chavez_luna_martnezgutirrez;
public class Administrador extends Usuario {
    private String cargo;

    public Administrador(String idUsuario, String nombreUsuario, String contrasena, String nombreCompleto, String tipoUsuario, String cargo) {
        super(idUsuario, nombreUsuario, contrasena, nombreCompleto, tipoUsuario);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
}
