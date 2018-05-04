package com.tecmm.tequila.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

public class Modificar extends AppCompatActivity {
    Spinner spinner;
    EditText txtNombre;
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);


        String categorias [] = {"Electronica", "Dulceria","Papeleria", "Moda","Perfumeria ", "Hogar", "Electrodomesticos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);

        spinner = (Spinner) findViewById(R.id.spnProducto);
        spinner.setAdapter(adapter);
        txtNombre = (EditText) findViewById(R.id.edTxtNombre);

        Intent i = getIntent();
        String x = i.getStringExtra("nombre");
        String z = i.getStringExtra("categoria");
        p = i.getIntExtra("Posicion", -1);

        txtNombre.setText(x);
        int f;
        Arrays.sort(categorias);
        f = Arrays.binarySearch(categorias, z);
        spinner.setSelection(f);
    }

    public void modificar (View v) {

        Intent i = new Intent();
        i.putExtra("nombre", txtNombre.getText().toString());
        i.putExtra("categoria", spinner.getSelectedItem().toString());
        i.putExtra("pos", p);
        setResult(RESULT_OK, i);
        finish();
    }
}

