package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase que gestiona el menú principal del participante.
 * Permite la navegación hacia la tabla de posiciones, el registro de pronósticos,
 * la consulta de pronósticos realizados y el cierre de sesión.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public class MenuParticipanteActivity extends AppCompatActivity {

    /**
     * Método de inicialización de la actividad. Enlaza los componentes visuales
     * y asigna los escuchadores de eventos a cada botón.
     *
     * @param savedInstanceState Estado guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_participante);

        // Referencias a los componentes gráficos
        TextView tvBienvenida = findViewById(R.id.tvBienvenida);
        Button btnTabla = findViewById(R.id.btnTabla);
        Button btnPronosticos = findViewById(R.id.btnPronosticos);
        Button btnMisPronosticos = findViewById(R.id.btnMisPronosticos);
        Button btnSalir = findViewById(R.id.btnSalir);

        // Obtención de datos pasados por el Intent
        String nombre = getIntent().getStringExtra("NOMBRE_COMPLETO");
        String idUsuario = getIntent().getStringExtra("ID_USUARIO");

        tvBienvenida.setText("Bienvenido(a):\n" + (nombre != null ? nombre : "Participante"));

        // Habilitación explícita de los botones de interacción
        btnPronosticos.setEnabled(true);
        btnMisPronosticos.setEnabled(true);

        // Eventos de clic para navegación entre pantallas
        btnTabla.setOnClickListener(v -> startActivity(new Intent(this, TablaPosicionesActivity.class)));

        btnPronosticos.setOnClickListener(v -> {
            Intent intent = new Intent(this, PronosticosActivity.class);
            intent.putExtra("ID_USUARIO", idUsuario);
            startActivity(intent);
        });

        btnMisPronosticos.setOnClickListener(v -> {
            Intent intent = new Intent(this, MisPronosticosActivity.class);
            intent.putExtra("ID_USUARIO", idUsuario);
            startActivity(intent);
        });

        btnSalir.setOnClickListener(v -> finish());
    }
}

