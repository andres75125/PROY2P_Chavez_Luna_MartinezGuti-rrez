package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

/**
 * Pantalla que permite al administrador gestionar el estado de los partidos e ingresar marcadores oficiales.
 *
 * @author Samantha Luna
 * @version 1.0
 */
public class AdministrarPartidosActivity extends AppCompatActivity {

    private Spinner spinnerFases;
    private LinearLayout containerAdminPartidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_partidos);

        spinnerFases = findViewById(R.id.spinnerFases);
        containerAdminPartidos = findViewById(R.id.containerAdminPartidos);
        Button btnVolver = findViewById(R.id.btnVolver);

        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }

        String[] fases = {
                "Fase de grupos",
                "Dieciseisavos de final",
                "Octavos de final",
                "Cuartos de final",
                "Semifinales",
                "Partido por el tercer lugar",
                "Final"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fases);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFases.setAdapter(adapter);

        spinnerFases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cargarPartidosAdmin(fases[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void cargarPartidosAdmin(String faseSeleccionada) {
        containerAdminPartidos.removeAllViews();
        List<Partido> lista = GestorArchivos.cargarPartidos(this);
        Map<String, int[]> oficiales = GestorArchivos.cargarResultadosOficiales(this);

        for (Partido p : lista) {
            if (esMismaFase(p.getFase(), faseSeleccionada)) {
                View card = LayoutInflater.from(this).inflate(R.layout.item_admin_partido, containerAdminPartidos, false);

                TextView tvInfo = card.findViewById(R.id.tvInfoMatch);
                TextView tvEquipos = card.findViewById(R.id.tvEquipos);
                TextView tvEstado = card.findViewById(R.id.tvEstado);
                ImageView img1 = card.findViewById(R.id.imgFlag1);
                ImageView img2 = card.findViewById(R.id.imgFlag2);
                Button btnCerrar = card.findViewById(R.id.btnCerrar);
                Button btnRegRes = card.findViewById(R.id.btnRegRes);
                LinearLayout layoutInput = card.findViewById(R.id.layoutInput);
                EditText etG1 = card.findViewById(R.id.etGoles1);
                EditText etG2 = card.findViewById(R.id.etGoles2);
                Button btnGuardarRes = card.findViewById(R.id.btnGuardarRes);

                if (tvInfo != null) tvInfo.setText(p.getFecha() + " | " + p.getEstadio());
                if (tvEquipos != null) tvEquipos.setText(p.getSeleccion1() + " VS " + p.getSeleccion2());
                if (tvEstado != null) tvEstado.setText("Estado: " + p.getEstado());

                if (img1 != null) img1.setImageResource(ObtenedorEscudos.getEscudoResource(this, p.getSeleccion1()));
                if (img2 != null) img2.setImageResource(ObtenedorEscudos.getEscudoResource(this, p.getSeleccion2()));

                if (p.getEstado().equalsIgnoreCase("ABIERTO")) {
                    btnCerrar.setVisibility(View.VISIBLE);
                    btnRegRes.setVisibility(View.GONE);
                    layoutInput.setVisibility(View.GONE);

                    btnCerrar.setOnClickListener(v -> {
                        GestorArchivos.actualizarEstadoPartido(AdministrarPartidosActivity.this, p.getIdPartido(), "CERRADO");
                        Toast.makeText(this, "Pronósticos cerrados para este partido.", Toast.LENGTH_SHORT).show();
                        cargarPartidosAdmin(faseSeleccionada);
                    });
                } else if (p.getEstado().equalsIgnoreCase("CERRADO")) {
                    btnCerrar.setVisibility(View.GONE);
                    btnRegRes.setVisibility(View.VISIBLE);
                    layoutInput.setVisibility(View.GONE);

                    btnRegRes.setOnClickListener(v -> {
                        layoutInput.setVisibility(View.VISIBLE);
                        btnRegRes.setVisibility(View.GONE);
                    });

                    btnGuardarRes.setOnClickListener(v -> {
                        try {
                            guardarResultadoAdmin(p.getIdPartido(), etG1.getText().toString(), etG2.getText().toString());
                            cargarPartidosAdmin(faseSeleccionada);
                        } catch (DatosIncompletosException e) {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (p.getEstado().equalsIgnoreCase("FINALIZADO")) {
                    btnCerrar.setVisibility(View.GONE);
                    btnRegRes.setVisibility(View.GONE);
                    layoutInput.setVisibility(View.GONE);
                    if (oficiales.containsKey(p.getIdPartido())) {
                        int[] r = oficiales.get(p.getIdPartido());
                        if (tvEstado != null) tvEstado.setText("FINALIZADO (" + r[0] + " - " + r[1] + ")");
                    }
                }

                containerAdminPartidos.addView(card);
            }
        }
    }

    private boolean esMismaFase(String fase1, String fase2) {
        if (fase1 == null || fase2 == null) return false;
        String f1 = fase1.toLowerCase().replace("_", " ").replace("de final", "").trim();
        String f2 = fase2.toLowerCase().replace("_", " ").replace("de final", "").trim();
        return f1.equals(f2) || f1.startsWith(f2) || f2.startsWith(f1);
    }

    private void guardarResultadoAdmin(String idPartido, String g1Str, String g2Str) throws DatosIncompletosException {
        if (g1Str.trim().isEmpty() || g2Str.trim().isEmpty()) {
            throw new DatosIncompletosException("Debe ingresar la cantidad de goles de ambas selecciones.");
        }

        int g1, g2;
        try {
            g1 = Integer.parseInt(g1Str.trim());
            g2 = Integer.parseInt(g2Str.trim());
            if (g1 < 0 || g2 < 0) throw new Exception();
        } catch (Exception e) {
            throw new DatosIncompletosException("Los goles deben ser números enteros mayores o iguales a cero.");
        }

        GestorArchivos.guardarResultadoOficial(this, idPartido, g1, g2);
        Toast.makeText(this, "Resultado guardado y partido FINALIZADO.", Toast.LENGTH_SHORT).show();
    }
}