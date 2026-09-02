package com.example.proy2p_chavez_luna_martnezgutirrez;

/**
 * Clase que representa el resultado oficial registrado por un administrador.
 *
 * @author Javier Fernando Chavez
 * @version 1.0
 */
public class Resultado {

    private String idResultado;
    private String idPartido;
    private int golesSeleccion1;
    private int golesSeleccion2;

    /**
     * Constructor de Resultado.
     *
     * @param idResultado ID del resultado.
     * @param idPartido ID del partido.
     * @param golesSeleccion1 Goles reales de la selección 1.
     * @param golesSeleccion2 Goles reales de la selección 2.
     */
    public Resultado(String idResultado, String idPartido, int golesSeleccion1, int golesSeleccion2) {
        this.idResultado = idResultado;
        this.idPartido = idPartido;
        this.golesSeleccion1 = golesSeleccion1;
        this.golesSeleccion2 = golesSeleccion2;
    }

    public String getIdResultado() { return idResultado; }
    public String getIdPartido() { return idPartido; }
    public int getGolesSeleccion1() { return golesSeleccion1; }
    public int getGolesSeleccion2() { return golesSeleccion2; }
}
