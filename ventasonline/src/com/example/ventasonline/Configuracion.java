package com.example.ventasonline;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Configuracion extends Activity {
private String[] lista = {"ACTUALIZAR CLIENTES", "ACTUALIZAR PRODUCTOS", "ACTUALIZAR USUARIOS"};
private Integer[] imgid={
        R.drawable.actualizar,
        R.drawable.actualizar,
        R.drawable.actualizar
};
	
	private ListView listado; 
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracion);
		listado=(ListView)findViewById(R.id.listado);
		ArrayAdapter <String>adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
		LenguajeListAdapter adapter=new LenguajeListAdapter(this,lista,imgid);
		listado.setAdapter(adapter);
		
		listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            	switch (i) {
				case 0:
					JSONParserClientes json = new JSONParserClientes(Configuracion.this);
					try {
						json.readAndParseJSONClientes();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 1:
					JSONParserProductos json_productos=new JSONParserProductos(Configuracion.this);
					try {
						json_productos.readAndParseJSONProductos();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
}
