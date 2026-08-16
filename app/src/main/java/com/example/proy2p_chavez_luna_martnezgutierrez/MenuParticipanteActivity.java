package main.java.com.example.proy2p_chavez_luna_martnezgutierrez;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MenuAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        TextView tvBienvenida = findViewById(R.id.tvBienvenida);
        String nombre = getIntent().getStringExtra("NOMBRE_COMPLETO");
        tvBienvenida.setText("Bienvenido Administrator:\n" + (nombre != null ? nombre : "Admin"));

        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(v -> finish());
    }
}