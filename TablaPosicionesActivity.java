package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Actividad encargada de calcular y desplegar la Tabla de Posiciones general.
 * Forzará el recálculo automático de puntos contra los resultados oficiales cargados.
 *
 * @author Samantha Luna
 * @version 1.0
 */
public class TablaPosicionesActivity extends AppCompatActivity {

    private LinearLayout containerPosiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_posiciones);

        containerPosiciones = findViewById(R.id.containerPosiciones);
        Button btnVolver = findViewById(R.id.btnVolver);

        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }

        // Recálculo automático antes de renderizar la tabla
        GestorArchivos.recalcularYActualizarPuntajes(this);

        cargarTablaPosiciones();
    }

    private void cargarTablaPosiciones() {
        if (containerPosiciones == null) return;
        containerPosiciones.removeAllViews();

        List<Usuario> usuarios = GestorArchivos.cargarUsuarios(this);
        List<Participante> participantes = new ArrayList<>();

        for (Usuario usr : usuarios) {
            if (usr instanceof Participante) {
                participantes.add((Participante) usr);
            }
        }

        // Ordenación por puntaje acumulado y nombre de usuario (Comparable)
        Collections.sort(participantes);

        int posicion = 1;
        for (Participante part : participantes) {
            View row = LayoutInflater.from(this).inflate(R.layout.item_posicion, containerPosiciones, false);

            TextView tvPos = row.findViewById(R.id.tvPosicion);
            TextView tvNombre = row.findViewById(R.id.tvNombreParticipante);
            TextView tvPuntos = row.findViewById(R.id.tvPuntaje);

            if (tvPos != null) tvPos.setText(String.valueOf(posicion));
            if (tvNombre != null) tvNombre.setText(part.getNombreCompleto());
            if (tvPuntos != null) tvPuntos.setText(String.valueOf(part.getPuntajeAcumulado()));

            containerPosiciones.addView(row);
            posicion++;
        }
    }
}