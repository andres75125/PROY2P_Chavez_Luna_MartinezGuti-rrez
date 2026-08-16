package com.example.proy2p_chavez_luna_martnezgutirrez;

// Implementación obligatoria de Comparable para el ordenamiento de la tabla
public class Participante extends Usuario implements Comparable<Participante> {
    private int puntajeAcumulado;

    public Participante(String idUsuario, String nombreUsuario, String contrasena, String nombreCompleto, String tipoUsuario, int puntajeAcumulado) {
        super(idUsuario, nombreUsuario, contrasena, nombreCompleto, tipoUsuario);
        this.puntajeAcumulado = puntajeAcumulado;
    }

    public int getPuntajeAcumulado() { return puntajeAcumulado; }
    public void setPuntajeAcumulado(int puntajeAcumulado) { this.puntajeAcumulado = puntajeAcumulado; }

    @Override
    public int compareTo(Participante otro) {
        // 1. Criterio principal: Mayor a menor puntaje
        if (this.puntajeAcumulado != otro.puntajeAcumulado) {
            return Integer.compare(otro.puntajeAcumulado, this.puntajeAcumulado);
        }
        // 2. Criterio secundario: Orden alfabético por nombreUsuario
        return this.nombreUsuario.compareToIgnoreCase(otro.nombreUsuario);
    }
}

