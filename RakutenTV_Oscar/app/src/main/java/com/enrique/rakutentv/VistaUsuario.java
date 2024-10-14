package com.enrique.rakutentv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import missing.namespace.R;

public class VistaUsuario extends AppCompatActivity {

    private EditText edtnombre;
    private EditText edtapellido;
    private EditText edtemail;
    private EditText edtfechaRegistro;
    private String nombre, apellido, email, fechaRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_usuario);

        edtnombre = (EditText) findViewById(R.id.nombre);
        edtapellido = (EditText) findViewById(R.id.apellido);
        edtemail = (EditText) findViewById(R.id.email);
        edtfechaRegistro = (EditText) findViewById(R.id.fechaRegistro);

        cargarDatosUsuario();
    }

    public void cargarDatosUsuario(){
        SharedPreferences guardarDatosUsuario = getSharedPreferences
                ("datosUsuario", Context.MODE_PRIVATE);

        String email= guardarDatosUsuario.getString("EMAIL", null);;
        String apellido = guardarDatosUsuario.getString("APELLIDO", null);;
        String nombre = guardarDatosUsuario.getString("NOMBRE", null);;
        String fechaRegistro = guardarDatosUsuario.getString("FECHAREG", null);;

        edtnombre.setText(nombre);
        edtapellido.setText(apellido);
        edtemail.setText(email);
        edtfechaRegistro.setText(fechaRegistro);
    }
}
