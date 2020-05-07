package com.example.parcial2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,nombre,estrato,salario,nivel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.txtId);
            nombre= itemView.findViewById(R.id.txtUsuarios);
            estrato= itemView.findViewById(R.id.txtEstrato);
            salario= itemView.findViewById(R.id.txtSalario);
            nivel= itemView.findViewById(R.id.txtNivel);
        }
    }
    public List<Usuarios> usuariosList;

    public UsuariosAdapter(List<Usuarios> usuariosList){this.usuariosList = usuariosList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_usuarios,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(usuariosList.get(position).getIdentificacion());
        holder.nombre.setText(usuariosList.get(position).getNombre());
        holder.estrato.setText(usuariosList.get(position).getEstrato());
        holder.salario.setText(usuariosList.get(position).getSalario());
        holder.nivel.setText(usuariosList.get(position).getNivel());

    }



    @Override
    public int getItemCount() {
        return usuariosList.size();
    }
}
