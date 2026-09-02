package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla para ejecutar el cálculo masivo de puntajes por parte del Administrador.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public class ActualizarPuntajesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_puntajes);

        Button btnActualizar = findViewById(R.id.btnActualizarPuntajes);
        Button btnVolver = findViewById(R.id.btnVolverMenu);

        btnVolver.setOnClickListener(v -> finish());

        btnActualizar.setOnClickListener(v -> {
            String mensaje = GestorArchivos.recalcularYActualizarPuntajes(this);
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        });
    }
}
