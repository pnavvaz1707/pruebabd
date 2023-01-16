package com.example.pruebabd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ActividadSQliteHelper extends SQLiteOpenHelper{

	//Sentencia SQL para crear la tabla de Actividades
	String sqlCrear = "CREATE TABLE Actividades (id INT PRIMARY KEY, "
			+ "Nombre TEXT, Descripcion TEXT, Importe REAL)";
	
	// Constructor de la clase. Podr�amos eliminar los par�metros
	// menos el contexto
	public ActividadSQliteHelper(Context contexto, String nombre,
			CursorFactory factory, int version) {
		super(contexto, nombre, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Creamos la tabla por SQL. Podr�a ya existir la BD
		db.execSQL(sqlCrear);
	}

	@Override
	// S�lo se ejecuta si las versiones son distintas
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Se elimina la versi�n anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Actividades");
		// Volvemos a crear la tabla
		db.execSQL(sqlCrear);
	}

}

