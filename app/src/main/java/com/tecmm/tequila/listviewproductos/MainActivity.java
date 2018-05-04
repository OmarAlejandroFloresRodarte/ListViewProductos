package com.tecmm.tequila.listviewproductos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView lista;
    private List<String> lProductos = new ArrayList<>();
    private List<String> lcategoria = new ArrayList<>();
    /*private String productos[] = {"Computadora", "Mouse", "Dulces", "Hojas", "Lapices", "Lentes", "Reloj", "Cuchara", "Celular",
            "Mesa", "Refrigerador", "Horno", "Audifonos"};
    private String categoria[] = {"Electronica","Electronica","Dulceria","Papeleria","Papeleria","Moda","Perfumeria ", "Hogar",
            "Electronicos", "Hogar", "Electrodomesticos", "Electrodomesticos", "Electronica"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textView);
        lista = (ListView) findViewById(R.id.lista);
        actualizarTabla();
        registerForContextMenu(lista);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt.setText("Categoria elegido: " + categoria[position]);
            }
        });*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_salir:
                finish();
                break;
            case R.id.op_info:
                Toast.makeText(this, "Informacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(lista.getAdapter().getItem(info.position).toString());
        getMenuInflater().inflate(R.menu.menu_emergente, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.emergente1:
                String nombre =lProductos.get(info.position);
                Toast.makeText(this, "Modificando " + nombre, Toast.LENGTH_SHORT).show();
                break;

            case R.id.emergente2:
                String nombre2 = lProductos.get(info.position);
                /*Toast.makeText(this, "Eliminando " + nombre2, Toast.LENGTH_SHORT).show();*/

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lProductos.add(data.getStringExtra("nombre"));
        lcategoria.add(data.getStringExtra("categoria"));
        actualizarTabla();
    }

    public void llamaActividad (View x) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivityForResult(i,123);
    }

    public void actualizarTabla () {
        String productos [] = new String[lProductos.size()];
        lProductos.toArray(productos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt.setText("Categoria elegida: " + lcategoria.get(position));
            }
        });
    }
}
