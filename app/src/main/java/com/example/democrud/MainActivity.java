package com.example.democrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtcodigo, txtdescripcion, txtprecio, txtcantidad;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtcodigo = (EditText) findViewById(R.id.et1);
        txtdescripcion = (EditText) findViewById(R.id.et2);
        txtprecio = (EditText) findViewById(R.id.et3);
        txtcantidad = (EditText) findViewById(R.id.et4);

    }
    public void Guardar (View v){
        try{
            AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "inventario", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String codigo = txtcodigo.getText().toString();
            String descripcion = txtdescripcion.getText().toString();
            Float precio = Float.parseFloat(txtprecio.getText().toString());
            Float cantidad = Float.parseFloat(txtcantidad.getText().toString());

            ContentValues contenedor = new ContentValues();
            contenedor.put("codigo", codigo);
            contenedor.put("descripcion",descripcion);
            contenedor.put("precio",precio);
            contenedor.put("cantidad",cantidad);

            //Se insertan los datos en el contenedor
            db.insert("articulos",null, contenedor);

            Toast.makeText(this,"Datos guardados correctamente", Toast.LENGTH_SHORT).show();

            limpiar();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

        public void limpiar()
        {
            txtcodigo.setText("");
            txtdescripcion.setText("");
            txtprecio.setText("");
            txtcantidad.setText("");
        }
        public void Consultar(View v)
        {
            try{
                AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "inventario",null,1);
                SQLiteDatabase db = admin.getWritableDatabase();
                String codigo = txtcodigo.getText().toString();
                Cursor cursor1 = db.rawQuery("select codigo, descripcion, precio, cantidad from articulos where codigo = "+codigo+"", null);

                if(cursor1.moveToFirst())
                {
                    txtcodigo.setText(cursor1.getString(0).toString());
                    txtdescripcion.setText(cursor1.getString(1).toString());
                    txtprecio.setText(cursor1.getString(2).toString());
                    txtcantidad.setText(cursor1.getString(3).toString());
                }
                else
                {
                    Toast.makeText(this, "No existen coincidencias en la base de datos", Toast.LENGTH_SHORT).show();
                }

            }catch(Exception ex)
            {
                Toast.makeText(this, "Error consultar datos", Toast.LENGTH_SHORT).show();

            }

        }

        public void Eliminar(View v)
        {
            AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "inventario",null,1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String codigo = txtcodigo.getText().toString();

            int dato = db.delete("articulos","codigo = "+codigo+"", null);
            if (dato==1)
            {
                Toast.makeText(this, "Dato ha sido eliminada con exito",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, "No se encontro el articulo",Toast.LENGTH_SHORT).show();
            }

        }

}