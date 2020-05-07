package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtSalario, edtNombre, edtIdentificacion;
    Spinner spinnerItems, spinnerNivel;
    Button btnAgregar,btnEditar,btnEliminar,btnMostrar,btnBuscar;
     esquemaDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSalario = findViewById(R.id.edtSalario);
        edtNombre = findViewById(R.id.edtNombre);
        edtIdentificacion = findViewById(R.id.edtCedula);

        spinnerItems = findViewById(R.id.spinnerItem);
        spinnerNivel = findViewById(R.id.spinner_item_nivel);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        btnMostrar = findViewById(R.id.btListar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnBuscar = findViewById(R.id.btnBuscar);
        db = new esquemaDB(getApplicationContext());

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuarios usuarios = new Usuarios();
                db.buscar(usuarios,edtIdentificacion.getText().toString());
                MostrarDialogo(usuarios);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDato();
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarDato();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.agregarDatos(edtIdentificacion.getText().toString(),edtNombre.getText().toString(),spinnerItems.getSelectedItem().toString(),
                        edtSalario.getText().toString(),spinnerNivel.getSelectedItem().toString());
                Toast.makeText(MainActivity.this, "Se agregó correctamente", Toast.LENGTH_SHORT).show();
            }

            
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrar = new Intent(getApplicationContext(),ListarActivity.class);
                startActivity(mostrar);
            }
        });
    }

    private void actualizarDato() {
        boolean actualizar= db.actualizarDatos(edtIdentificacion.getText().toString(),edtNombre.getText().toString(),
                spinnerItems.getSelectedItem().toString(), edtSalario.getText().toString(),spinnerNivel.getSelectedItem().toString());
        if (actualizar == true){

            Toast.makeText(MainActivity.this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
        }
    }


    private void eliminarDato() {
        Integer eliminar = db.eliminarDatos(edtIdentificacion.getText().toString());
        if(eliminar > 0){
            Toast.makeText(MainActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    private void MostrarDialogo(Usuarios usuarios) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_modificar);

        TextView txtId = dialog.findViewById(R.id.txtIdd);
        TextView txtNombre = dialog.findViewById(R.id.txtNombree);
        TextView txtEstrato = dialog.findViewById(R.id.txtEstratoo);
        TextView txtSalario = dialog.findViewById(R.id.txtSalarioo);


        txtId.setText(usuarios.getIdentificacion());
        txtNombre.setText(usuarios.getNombre());
        txtEstrato.setText(usuarios.getEstrato());
        txtSalario.setText(usuarios.getSalario());

        dialog.show();



    }
}
