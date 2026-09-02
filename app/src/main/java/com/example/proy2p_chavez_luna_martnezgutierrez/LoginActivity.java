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

        // Copia preventiva de los archivos .txt desde assets
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
            throw new CredencialesInvalidasException("Debe ingresar el usuario y la contraseña.");
        }

        List<Usuario> usuarios = GestorArchivos.cargarUsuarios(this);
        for (Usuario usr : usuarios) {
            // Se usa equalsIgnoreCase para el nombre de usuario y equals para la clave
            if (usr.getNombreUsuario().equalsIgnoreCase(u) && usr.getContrasena().equals(p)) {
                Intent intent;
                if (usr instanceof Participante) {
                    intent = new Intent(this, MenuParticipanteActivity.class);
                    Participante part = (Participante) usr;
                    intent.putExtra("PUNTAJE_ACUMULADO", part.getPuntajeAcumulado());
                } else {
                    intent = new Intent(this, MenuAdminActivity.class);
                    Administrador admin = (Administrador) usr;
                    intent.putExtra("CARGO", admin.getCargo());
                }

                // Datos clave para la sesión
                intent.putExtra("ID_USUARIO", usr.getIdUsuario());
                intent.putExtra("NOMBRE_COMPLETO", usr.getNombreCompleto());
                intent.putExtra("TIPO_USUARIO", usr.getTipoUsuario());

                startActivity(intent);
                finish();
                return;
            }
        }
        throw new CredencialesInvalidasException("El usuario o la contraseña son incorrectos.");
    }
}
