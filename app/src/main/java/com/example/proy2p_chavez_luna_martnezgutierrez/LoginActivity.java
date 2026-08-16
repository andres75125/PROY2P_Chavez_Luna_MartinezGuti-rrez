package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasena;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GestorArchivos.copiarAssetsAInterno(this);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            try {
                procesarIngreso();
            } catch (CredencialesInvalidasException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void procesarIngreso() throws CredencialesInvalidasException {
        String u = etUsuario.getText().toString().trim();
        String p = etContrasena.getText().toString().trim();

        if (u.isEmpty() || p.isEmpty()) {
            throw new CredencialesInvalidasException("El usuario o la contraseña son incorrectos.");
        }

        List<Usuario> usuarios = GestorArchivos.cargarUsuarios(this);
        for (Usuario usr : usuarios) {
            if (usr.getNombreUsuario().equals(u) && usr.getContrasena().equals(p)) {
                Intent intent;
                if (usr instanceof Participante) {
                    intent = new Intent(this, MenuParticipanteActivity.class);
                } else {
                    intent = new Intent(this, MenuAdminActivity.class);
                }
                intent.putExtra("NOMBRE_COMPLETO", usr.getNombreCompleto());
                startActivity(intent);
                finish();
                return;
            }
        }
        throw new CredencialesInvalidasException("El usuario o la contraseña son incorrectos.");
    }
}
