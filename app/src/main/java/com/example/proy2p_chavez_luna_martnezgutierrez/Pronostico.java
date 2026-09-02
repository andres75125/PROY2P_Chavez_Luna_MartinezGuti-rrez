package com.example.proy2p_chavez_luna_martnezgutirrez;

import java.io.Serializable;

/**
 * Clase que representa el pronóstico ingresado por un participante para un partido específico.
 * Implementa Serializable para su guardado binario en archivos .dat.
 *
 * @author Javier Fernando Chavez
 * @version 1.0
 */
public class Pronostico implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idPronostico;
    private String idUsuario;
    private String idPartido;
    private int goles1;
    private int goles2;
    private int puntosObtenidos;

    /**
     * Constructor de Pronostico.
     *
     * @param idPronostico Identificador único del pronóstico.
     * @param idUsuario ID del participante que realiza el pronóstico.
     * @param idPartido ID del partido pronosticado.
     * @param goles1 Goles estimaciones para la Selección 1.
     * @param goles2 Goles estimaciones para la Selección 2.
     */
    public Pronostico(String idPronostico, String idUsuario, String idPartido, int goles1, int goles2) {
        this.idPronostico = idPronostico;
        this.idUsuario = idUsuario;
        this.idPartido = idPartido;
        this.goles1 = goles1;
        this.goles2 = goles2;
        this.puntosObtenidos = 0;
    }

    public String getIdPronostico() { return idPronostico; }
    public String getIdUsuario() { return idUsuario; }
    public String getIdPartido() { return idPartido; }
    public int getGoles1() { return goles1; }
    public int getGoles2() { return goles2; }
    public int getPuntosObtenidos() { return puntosObtenidos; }
    public void setPuntosObtenidos(int puntosObtenidos) { this.puntosObtenidos = puntosObtenidos; }
}
