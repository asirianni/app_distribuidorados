package com.example.ventasonline;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListadoProducto extends Activity{
	private ListView listado;
	private ArrayList<Producto> listado_producto= new ArrayList() ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_producto);
		listado=(ListView)findViewById(R.id.listado_producto);
		
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
		SQLiteDatabase bd=admin.getReadableDatabase();
		
		int indice=0;
		
		String[] valores_recuperar = {"codigo", "descripcion", "rubro", "subrubro", "precio1", "precio2", "precio3", "precio4"};
        Cursor c = bd.query("producto", valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
        	Producto p=new Producto();
        	
        	int codigo=c.getInt(c.getColumnIndex("codigo"));
        	String descripcion=c.getString(c.getColumnIndex("descripcion"));
        	String rubro=c.getString(c.getColumnIndex("rubro"));
        	String subrubro=c.getString(c.getColumnIndex("subrubro"));
        	Float precio1=Float.valueOf(c.getString(c.getColumnIndex("precio1")));
        	Float precio2=Float.valueOf(c.getString(c.getColumnIndex("precio2")));
        	Float precio3=Float.valueOf(c.getString(c.getColumnIndex("precio3")));
        	Float precio4=Float.valueOf(c.getString(c.getColumnIndex("precio4")));
       	        	
        	p.setCodigo(codigo);
        	p.setDescripcion(descripcion);
        	p.setRubro(rubro);
        	p.setSubrubro(subrubro);
        	p.setPrecio1(precio1);
        	p.setPrecio2(precio2);
        	p.setPrecio3(precio3);
        	p.setPrecio4(precio4);
        	
        	listado_producto.add(p);
        } while (c.moveToNext());
        bd.close();
        c.close();
        ProductoListAdapter adapter=new ProductoListAdapter(this,listado_producto);
		listado.setAdapter(adapter);
		
		listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
	        	switch (i) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					break;
				}
	        	
	            /*Toast.makeText(MenuPrincipal.this,lista[i]+i, Toast.LENGTH_LONG).show();*/
	        }
	    });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	private void cargarListado(){    
	}
}
