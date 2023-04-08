package com.example.democrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSqliteOpenHelper extends SQLiteOpenHelper{

    private final String sql = "CREATE TABLE articulos(codigo int primary key, descripcion text, precio real, cantidad integer)";
    private SQLiteDatabase sqLiteDatabase;
    // private final String sql_clientes = "CREATE TABLE articulos(codigo int primary key, descripcion text, precio real, cantidad integer)";
   //Funciona para crear otro tipo de tabla con otro nombre de sql para crear diferente con otros datos.


    public AdminSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS articulos");
            onCreate(sqLiteDatabase);
    }
/*
    public void llenar_tabla()
    {
        String sql = "insert into articulos values(1,'tomate', 10, 1);

    }*/
}
