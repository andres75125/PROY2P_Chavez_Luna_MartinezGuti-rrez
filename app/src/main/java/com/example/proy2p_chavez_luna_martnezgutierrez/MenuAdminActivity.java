package main.java.com.example.proy2p_chavez_luna_martnezgutierrez;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MenuParticipanteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_participante);

        TextView tvBienvenida = findViewById(R.id.tvBienvenida);
        String nombre = getIntent().getStringExtra("NOMBRE_COMPLETO");
        tvBienvenida.setText("Bienvenido(a):\n" + (nombre != null ? nombre : "Participante") + "\nParticipante");

        Button btnTabla = findViewById(R.id.btnTabla);
        Button btnSalir = findViewById(R.id.btnSalir);

        btnTabla.setOnClickListener(v -> startActivity(new Intent(this, TablaPosicionesActivity.class)));
        btnSalir.setOnClickListener(v -> finish());
    }
}