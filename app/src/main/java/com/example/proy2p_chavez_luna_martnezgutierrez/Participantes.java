package com.example.proy2p_chavez_luna_martnezgutirrez;

import java.io.Serializable;

/**
 * Representa a un usuario participante en el sistema.
 * Implementa Comparable para permitir el ordenamiento automático en la Tabla de Posiciones.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public class Participante extends Usuario implements Comparable<Participante>, Serializable {

    private static final long serialVersionUID = 1L;
    /** Puntaje acumulado por los aciertos en los pronósticos */
    private int puntajeAcumulado;

    public Participante(String idUsuario, String nombreUsuario, String contrasena, String nombreCompleto, String tipoUsuario, int puntajeAcumulado) {
        super(idUsuario, nombreUsuario, contrasena, nombreCompleto, tipoUsuario);
        this.puntajeAcumulado = puntajeAcumulado;
    }

    public int getPuntajeAcumulado() { return puntajeAcumulado; }
    public void setPuntajeAcumulado(int puntajeAcumulado) { this.puntajeAcumulado = puntajeAcumulado; }

    /**
     * Criterio de ordenamiento según requerimientos del proyecto:
     * 1. Descendente por puntaje acumulado.
     * 2. En caso de empate, ascendente (alfabético) por nombre de usuario.
     */
    @Override
    public int compareTo(Participante otro) {
        int comparacionPuntos = Integer.compare(otro.puntajeAcumulado, this.puntajeAcumulado);
        if (comparacionPuntos != 0) {
            return comparacionPuntos;
        }
        return this.nombreUsuario.compareToIgnoreCase(otro.nombreUsuario);
    }
}
