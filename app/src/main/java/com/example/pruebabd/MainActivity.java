package com.example.pruebabd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Button btninsertar;
    private Button btnborrar;
    private Button btnmodificar;
    private Button btnconsulta;
    private EditText actividad;
    private EditText descripcion;
    private EditText importe;
    private EditText codigo;
    //    private TextView resultado;
    private ListView lstResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btninsertar = (Button) findViewById(R.id.btninsert);
        btnborrar = (Button) findViewById(R.id.btndelete);
        btnmodificar = (Button) findViewById(R.id.btnupdate);
        btnconsulta = (Button) findViewById(R.id.btnquery);
        actividad = (EditText) findViewById(R.id.actividad);
        descripcion = (EditText) findViewById(R.id.descripcion);
        importe = (EditText) findViewById(R.id.importe);
        codigo = (EditText) findViewById(R.id.id);
        lstResultado = (ListView) findViewById(R.id.lstResultado);


        // Bot�n Insertar
        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Abrimos la base de datos 'BDActividades' en modo escritura
                ActividadSQliteHelper acdbh = new ActividadSQliteHelper(MainActivity.this, "BDActividades", null, 1);
                SQLiteDatabase db = acdbh.getWritableDatabase();

                //Creamos el registro a insertar como objeto ContentValues
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("id", codigo.getText().toString());
                nuevoRegistro.put("Nombre", actividad.getText().toString());
                nuevoRegistro.put("Descripcion", descripcion.getText().toString());
                nuevoRegistro.put("Importe", importe.getText().toString());

                //Insertamos el registro en la base de datos
                db.insert("Actividades", null, nuevoRegistro);

                //Cerramos la base de datos
                db.close();
            }
        });

        // Bot�n Borrar
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Abrimos la base de datos 'BDActividades' en modo escritura
                ActividadSQliteHelper acdbh = new ActividadSQliteHelper(MainActivity.this, "BDActividades", null, 1);
                SQLiteDatabase db = acdbh.getWritableDatabase();

                //Borramos el registro en la base de datos
                db.delete("Actividades", "id='" + codigo.getText().toString() + "'", null);

                //Cerramos la base de datos
                db.close();
            }
        });

        // Bot�n Modificar
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Abrimos la base de datos 'BDActividades' en modo escritura
                ActividadSQliteHelper acdbh = new ActividadSQliteHelper(MainActivity.this, "BDActividades", null, 1);
                SQLiteDatabase db = acdbh.getWritableDatabase();

                //Establecemos los campos-valores a actualizar
                ContentValues updRegistro = new ContentValues();
                updRegistro.put("id", codigo.getText().toString());
                updRegistro.put("Nombre", actividad.getText().toString());
                updRegistro.put("Descripcion", descripcion.getText().toString());
                updRegistro.put("Importe", importe.getText().toString());

                //Modificamos el registro en la base de datos
                db.update("Actividades", updRegistro, "id='" + codigo.getText().toString() + "'", null);

                //Cerramos la base de datos
                db.close();
            }
        });

        // Bot�n Consulta
        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Abrimos la base de datos 'BDActividades' en modo escritura
                ActividadSQliteHelper acdbh = new ActividadSQliteHelper(btnconsulta.getContext(), "BDActividades", null, 1);
                SQLiteDatabase db = acdbh.getWritableDatabase();


                //Consultamos el registro en la base de datos
                //String[] args = new String[] {"5"};
                //Cursor c = db.rawQuery(" SELECT * FROM Actividades WHERE id=?", args);


//                String[] campos = new String[]{"id", "Nombre", "Descripcion"};

//                Cursor c = db.query("Actividades", campos, "id=?", new String[]{codigo.getText().toString()}, null, null, null);
//                Cursor c = db.query("Actividades", campos, null,null, null, null, null);
                Cursor c1 = db.rawQuery("SELECT rowid _id,* from Actividades", null);
                if (c1.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya m�s registros
                    do {
                        System.out.println("----> " + c1.getString(0) + " " + c1.getString(1) + " " + c1.getString(2));
                        System.out.println("/////////////////////////");
                    } while(c1.moveToNext());
                }

                Cursor c = db.rawQuery("SELECT rowid _id,* from Actividades", null);

                String[] datos = new String[]{"id", "Nombre", "Descripcion", "Importe"};
                int[] referencias = new int[]{R.id.resultado_id, R.id.resultado_nombre, R.id.resultado_descripcion, R.id.resultado_importe};

                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.list_resultados, c, datos,referencias , 0);
                lstResultado.setAdapter(simpleCursorAdapter);


//                //Nos aseguramos de que existe al menos un registro
//                if (c.moveToFirst()) {
//                    //Recorremos el cursor hasta que no haya m�s registros
//                    do {
//                        resultado.append(c.getString(0) + "  " + c.getString(1) +
//                                "  " + c.getString(2) + "\n");
//                    } while(c.moveToNext());
//                }


//                System.out.println("AAAAAAAAAAAAAAAAAA: " + resultado.toString());

                //Cerramos la base de datos
                db.close();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}