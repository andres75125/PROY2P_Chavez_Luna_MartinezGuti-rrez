package com.example.proy2p_chavez_luna_martnezgutirrez;

/**
 * Clase que representa a un usuario con rol de Administrador.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public class Administrador extends Usuario {

    /** Cargo o función dentro de la organización de la FIFA */
    private String cargo;

    /**
     * Constructor de la clase Administrador.
     *
     * @param idUsuario ID único.
     * @param nombreUsuario Nombre de usuario.
     * @param contrasena Contraseña.
     * @param nombreCompleto Nombre completo.
     * @param tipoUsuario Tipo de usuario.
     * @param cargo Cargo desempeñado.
     */
    public Administrador(String idUsuario, String nombreUsuario, String contrasena, String nombreCompleto, String tipoUsuario, String cargo) {
        super(idUsuario, nombreUsuario, contrasena, nombreCompleto, tipoUsuario);
        this.cargo = cargo;
    }

    /**
     * Obtiene el cargo del administrador.
     * @return String con el cargo.
     */
    public String getCargo() { return cargo; }
}
