package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adaptador para gestionar el renderizado de la lista de partidos en la vista de Pronósticos.
 * Utiliza ObtenedorEscudos para la carga de banderas y asigna estados dinámicos.
 *
 * @author Javier Fernando Chavez
 * @version 1.0
 */
public class PronosticoAdapter extends RecyclerView.Adapter<PronosticoAdapter.ViewHolder> {

    private final Context context;
    private final List<Partido> listaPartidos;
    private final String idUsuario;
    private final Map<String, Pronostico> mapaPronosticosGuardados;
    private final OnPronosticoGuardadoListener listener;

    public interface OnPronosticoGuardadoListener {
        void onGuardar(String idPartido, int goles1, int goles2);
    }

    public PronosticoAdapter(Context context, List<Partido> listaPartidos, String idUsuario,
                             Map<String, Pronostico> mapaPronosticosGuardados,
                             OnPronosticoGuardadoListener listener) {
        this.context = context;
        this.listaPartidos = listaPartidos;
        this.idUsuario = idUsuario;
        this.mapaPronosticosGuardados = mapaPronosticosGuardados != null ? mapaPronosticosGuardados : new HashMap<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_partido_pronostico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partido partido = listaPartidos.get(position);

        // 1. Asignar metadatos del partido
        if (holder.tvInfoMatch != null) {
            holder.tvInfoMatch.setText(partido.getFecha() + " - " + partido.getHora() + " | " + partido.getEstadio());
        }
        if (holder.tvEquipo1 != null) holder.tvEquipo1.setText(partido.getSeleccion1());
        if (holder.tvEquipo2 != null) holder.tvEquipo2.setText(partido.getSeleccion2());

        // 2. Carga unificada de escudos/banderas desde ObtenedorEscudos
        if (holder.imgFlag1 != null) {
            holder.imgFlag1.setImageResource(ObtenedorEscudos.getEscudoResource(context, partido.getSeleccion1()));
        }
        if (holder.imgFlag2 != null) {
            holder.imgFlag2.setImageResource(ObtenedorEscudos.getEscudoResource(context, partido.getSeleccion2()));
        }

        // 3. Precargar marcadores si el usuario ya guardó un pronóstico previo
        Pronostico pronosticoPrevio = mapaPronosticosGuardados.get(partido.getIdPartido());
        if (pronosticoPrevio != null) {
            if (holder.etGoles1 != null) holder.etGoles1.setText(String.valueOf(pronosticoPrevio.getGoles1()));
            if (holder.etGoles2 != null) holder.etGoles2.setText(String.valueOf(pronosticoPrevio.getGoles2()));
        } else {
            if (holder.etGoles1 != null) holder.etGoles1.setText("");
            if (holder.etGoles2 != null) holder.etGoles2.setText("");
        }

        // 4. Aplicar estados del encuentro (ABIERTO, CERRADO, FINALIZADO)
        String estado = partido.getEstado() != null ? partido.getEstado().toUpperCase() : "ABIERTO";
        if (holder.tvEstadoBadge != null) {
            holder.tvEstadoBadge.setText(estado);
        }

        if ("ABIERTO".equals(estado)) {
            if (holder.tvEstadoBadge != null) holder.tvEstadoBadge.setTextColor(Color.parseColor("#2E7D32"));
            if (holder.etGoles1 != null) holder.etGoles1.setEnabled(true);
            if (holder.etGoles2 != null) holder.etGoles2.setEnabled(true);
            if (holder.btnGuardar != null) holder.btnGuardar.setVisibility(View.VISIBLE);
            if (holder.tvMensajeEstado != null) holder.tvMensajeEstado.setVisibility(View.GONE);
        } else {
            if (holder.etGoles1 != null) holder.etGoles1.setEnabled(false);
            if (holder.etGoles2 != null) holder.etGoles2.setEnabled(false);
            if (holder.btnGuardar != null) holder.btnGuardar.setVisibility(View.GONE);
            if (holder.tvMensajeEstado != null) {
                holder.tvMensajeEstado.setVisibility(View.VISIBLE);
                if ("CERRADO".equals(estado)) {
                    if (holder.tvEstadoBadge != null) holder.tvEstadoBadge.setTextColor(Color.parseColor("#F57F17"));
                    holder.tvMensajeEstado.setText("Los pronósticos para este partido están cerrados.");
                } else if ("FINALIZADO".equals(estado)) {
                    if (holder.tvEstadoBadge != null) holder.tvEstadoBadge.setTextColor(Color.parseColor("#757575"));
                    holder.tvMensajeEstado.setText("¡Partido finalizado! Ya conoces tus puntos.");
                }
            }
        }

        // 5. Listener para evento de guardado
        if (holder.btnGuardar != null) {
            holder.btnGuardar.setOnClickListener(v -> {
                String g1Str = holder.etGoles1.getText().toString().trim();
                String g2Str = holder.etGoles2.getText().toString().trim();

                if (g1Str.isEmpty() || g2Str.isEmpty()) {
                    Toast.makeText(context, "No se han ingresado todos los datos necesarios para registrar el pronóstico.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int g1 = Integer.parseInt(g1Str);
                    int g2 = Integer.parseInt(g2Str);
                    listener.onGuardar(partido.getIdPartido(), g1, g2);
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Por favor ingrese valores numéricos válidos.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPartidos != null ? listaPartidos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfoMatch, tvEquipo1, tvEquipo2, tvEstadoBadge, tvMensajeEstado;
        ImageView imgFlag1, imgFlag2;
        EditText etGoles1, etGoles2;
        Button btnGuardar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfoMatch = itemView.findViewById(R.id.tvInfoMatch);
            tvEquipo1 = itemView.findViewById(R.id.tvEquipo1);
            tvEquipo2 = itemView.findViewById(R.id.tvEquipo2);
            tvEstadoBadge = itemView.findViewById(R.id.tvEstadoBadge);
            tvMensajeEstado = itemView.findViewById(R.id.tvMensajeEstado);
            imgFlag1 = itemView.findViewById(R.id.imgFlag1);
            imgFlag2 = itemView.findViewById(R.id.imgFlag2);
            etGoles1 = itemView.findViewById(R.id.etGoles1);
            etGoles2 = itemView.findViewById(R.id.etGoles2);
            btnGuardar = itemView.findViewById(R.id.btnGuardar);
        }
    }
}
