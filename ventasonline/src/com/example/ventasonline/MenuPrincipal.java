package com.example.ventasonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuPrincipal extends Activity {
	private String[] lista = {"PRODUCTOS", "CLIENTES", "PEDIDOS", "USUARIOS", "CONFIGURACION"};
	private Integer[] imgid={
            R.drawable.productos,
            R.drawable.cliente,
            R.drawable.pedidos,
            R.drawable.usuarios,
            R.drawable.config,
    };
	
	private ListView lista_menu; 
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		lista_menu=(ListView)findViewById(R.id.menu);
		ArrayAdapter <String>adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
		
		LenguajeListAdapter adapter=new LenguajeListAdapter(this,lista,imgid);
		
		lista_menu.setAdapter(adapter);
		
		lista_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            	switch (i) {
				case 0:
					productos(view);
					break;
				case 1:
					clientes(view);
					break;
				case 2:
					
					break;
				case 3:
					
					break;

				default:
					configuracion(view);
					break;
				}
            	
                /*Toast.makeText(MenuPrincipal.this,lista[i]+i, Toast.LENGTH_LONG).show();*/
            }
        });
	}
	
	
	public void configuracion(View v) {
	    Intent config = new Intent(this, Configuracion.class);
	    startActivity(config);
	}
	
	public void clientes(View v) {
	    Intent clientes = new Intent(this, ListadoCliente.class);
	    startActivity(clientes);
	}
	
	public void productos(View v) {
	    Intent productos = new Intent(this, ListadoProducto.class);
	    startActivity(productos);
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
}
