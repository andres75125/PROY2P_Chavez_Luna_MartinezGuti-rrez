package com.example.proy2p_chavez_luna_martnezgutirrez;

import java.io.Serializable;

/**
 * Clase que representa a un partido del Mundial FIFA 2026.
 * Implementa Serializable para permitir su transferencia de estado.
 *
 * @author Javier Fernando Chavez
 * @version 1.0
 */
public class Partido implements Serializable {

    private String idPartido;
    private String fase;
    private String fecha;
    private String hora;
    private String estadio;
    private String seleccion1;
    private String seleccion2;
    private String estado; // ABIERTO, CERRADO, FINALIZADO

    /**
     * Constructor parametrizado de Partido.
     *
     * @param idPartido ID del partido.
     * @param fase Fase del torneo.
     * @param fecha Fecha programada.
     * @param hora Hora del encuentro.
     * @param estadio Estadio sede.
     * @param seleccion1 Equipo local.
     * @param seleccion2 Equipo visitante.
     * @param estado Estado actual (ABIERTO, CERRADO, FINALIZADO).
     */
    public Partido(String idPartido, String fase, String fecha, String hora, String estadio, String seleccion1, String seleccion2, String estado) {
        this.idPartido = idPartido;
        this.fase = fase;
        this.fecha = fecha;
        this.hora = hora;
        this.estadio = estadio;
        this.seleccion1 = seleccion1;
        this.seleccion2 = seleccion2;
        this.estado = estado;
    }

    public String getIdPartido() { return idPartido; }
    public String getFase() { return fase; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getEstadio() { return estadio; }
    public String getSeleccion1() { return seleccion1; }
    public String getSeleccion2() { return seleccion2; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}