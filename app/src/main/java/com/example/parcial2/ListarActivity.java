package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuariosAdapter usuariosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        recyclerView = findViewById(R.id.rvLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        esquemaDB db = new esquemaDB(getApplicationContext());
        usuariosAdapter = new UsuariosAdapter(db.mostrarDatos());
        recyclerView.setAdapter(usuariosAdapter);

    }
}
