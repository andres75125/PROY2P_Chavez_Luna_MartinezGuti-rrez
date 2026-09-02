package com.example.proy2p_chavez_luna_martnezgutirrez;

/**
 * Clase abstracta que representa la entidad base de un usuario en el sistema.
 * Contiene la información de identificación y credenciales comunes.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public abstract class Usuario {

    /** Identificador único del usuario */
    protected String idUsuario;
    /** Nombre de usuario para el inicio de sesión */
    protected String nombreUsuario;
    /** Contraseña de acceso al sistema */
    protected String contrasena;
    /** Nombres y apellidos completos del usuario */
    protected String nombreCompleto;
    /** Rol del usuario (PARTICIPANTE o ADMINISTRADOR) */
    protected String tipoUsuario;

    /**
     * Constructor parametrizado para la clase abstracta Usuario.
     *
     * @param idUsuario Identificador único.
     * @param nombreUsuario Nombre de usuario para login.
     * @param contrasena Clave de acceso.
     * @param nombreCompleto Nombre y apellido.
     * @param tipoUsuario Tipo o rol del usuario.
     */
    public Usuario(String idUsuario, String nombreUsuario, String contrasena, String nombreCompleto, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene el ID del usuario.
     * @return String con el ID.
     */
    public String getIdUsuario() { return idUsuario; }

    /**
     * Obtiene el nombre de usuario.
     * @return String con el nombre de usuario.
     */
    public String getNombreUsuario() { return nombreUsuario; }

    /**
     * Obtiene la contraseña.
     * @return String con la clave.
     */
    public String getContrasena() { return contrasena; }

    /**
     * Obtiene el nombre completo del usuario.
     * @return String con el nombre completo.
     */
    public String getNombreCompleto() { return nombreCompleto; }

    /**
     * Obtiene el tipo de usuario.
     * @return String con el tipo.
     */
    public String getTipoUsuario() { return tipoUsuario; }
}
