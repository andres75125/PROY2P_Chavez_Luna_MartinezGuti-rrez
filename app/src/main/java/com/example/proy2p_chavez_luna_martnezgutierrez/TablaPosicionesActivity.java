package main.java.com.example.proy2p_chavez_luna_martnezgutierrez;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class TablaPosicionesActivity extends AppCompatActivity {
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_posiciones);

        tableLayout = findViewById(R.id.tableLayout);
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> finish());

        construirTabla();
    }

    private void construirTabla() {
        List<Usuario> usuarios = GestorArchivos.cargarUsuarios(this);
        List<Participante> participantes = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u instanceof Participante) {
                participantes.add((Participante) u);
            }
        }

        // Ordena automaticamente llamando a compareTo de Participante
        Collections.sort(participantes);

        // Encabezado
        TableRow header = new TableRow(this);
        header.setPadding(8, 16, 8, 16);
        header.addView(crearCelda("Pos", true));
        header.addView(crearCelda("Participante", true));
        header.addView(crearCelda("Puntos", true));
        tableLayout.addView(header);

        // Filas de datos
        int pos = 1;
        for (Participante p : participantes) {
            TableRow row = new TableRow(this);
            row.setPadding(8, 12, 8, 12);
            row.addView(crearCelda(String.valueOf(pos++), false));
            row.addView(crearCelda(p.getNombreCompleto(), false));
            row.addView(crearCelda(String.valueOf(p.getPuntajeAcumulado()), false));
            tableLayout.addView(row);
        }
    }

    private TextView crearCelda(String texto, boolean esHeader) {
        TextView tv = new TextView(this);
        tv.setText(texto);
        tv.setPadding(12, 8, 12, 8);
        tv.setGravity(Gravity.CENTER);
        if (esHeader) {
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextSize(16);
            tv.setTextColor(0xFF1A237E);
        } else {
            tv.setTextSize(14);
        }
        return tv;
    }
}

