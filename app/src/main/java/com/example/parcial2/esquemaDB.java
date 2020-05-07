package com.example.parcial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class esquemaDB extends SQLiteOpenHelper {

    private static final String NOMBRE_DB = "parcial.bd";
    private static final int VERSION_DB = 2;
    public static final String COL_1 = "IDENTIFICACION";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "ESTRATO";
    public static final String COL_4 = "SALARIO";
    public static final String COL_5 = "NIVEL";
    private static final String TABLA_PARCIAL = "CREATE TABLE PARCIAL (IDENTIFICACION TEXT PRIMARY KEY, NOMBRE TEXT, ESTRATO TEXT, SALARIO TEXT, NIVEL TEXT)";

    public esquemaDB(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_PARCIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_PARCIAL);
        db.execSQL(TABLA_PARCIAL);

    }

    public void agregarDatos(String identificacion,String nombre, String estrato, String salario, String nivel){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO PARCIAL VALUES('"+identificacion+"','"+nombre+"','"+estrato+"','"+salario+"','"+nivel+"')");
            db.close();
        }
    }

    public List<Usuarios> mostrarDatos(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PARCIAL", null);
        List<Usuarios> usuarios = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                usuarios.add(new Usuarios(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return usuarios;
    }

    public void buscar(Usuarios usuario,String identificacion) {

        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM PARCIAL WHERE IDENTIFICACION='"+identificacion+"'",null);
        if (c.moveToFirst()) {
            do {
                usuario.setIdentificacion(c.getString(0));
                usuario.setNombre(c.getString(1));
                usuario.setEstrato(c.getString(2));
                usuario.setSalario(c.getString(3));
            } while (c.moveToNext());

        }
    }

    public boolean actualizarDatos(String id,String nombre,String estrato, String salario,String nivel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,estrato);
        contentValues.put(COL_4,salario);
        contentValues.put(COL_5,nivel);
        db.update("PARCIAL",contentValues,"IDENTIFICACION = ?", new String[]{id});
        return true;
    }

    public Integer eliminarDatos(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PARCIAL", "IDENTIFICACION = ?", new String[]{id});
    }
}
