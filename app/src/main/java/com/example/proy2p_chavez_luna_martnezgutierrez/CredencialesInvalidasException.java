package com.example.proy2p_chavez_luna_martnezgutirrez;

/**
 * Excepción verificada lanzada cuando el usuario o la contraseña ingresados
 * en el formulario de inicio de sesión no coinciden con los registros.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public class CredencialesInvalidasException extends Exception {

    /**
     * Constructor que inicializa la excepción con un mensaje descriptivo.
     *
     * @param mensaje Detalle del error de autenticación.
     */
    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
