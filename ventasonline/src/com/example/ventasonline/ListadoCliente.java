package com.example.ventasonline;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListadoCliente extends Activity {
	
	private ListView listado;
	private EditText buscar;
	private ArrayList<Cliente> listado_clientes= new ArrayList() ;
	ClienteListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);
		listado=(ListView)findViewById(R.id.listado_cliente);
		buscar=(EditText)findViewById(R.id.buscar);
		
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
		SQLiteDatabase bd=admin.getReadableDatabase();
		
		int indice=0;
		
		String[] valores_recuperar = {"codigo", "nombre", "apellido", "telefono", "correo", "direccion", "localidad", "tipo"};
        Cursor c = bd.query("cliente", valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
        	Cliente cliente=new Cliente();
        	int codigo=c.getInt(c.getColumnIndex("codigo"));
        	String nombre=c.getString(c.getColumnIndex("nombre"));
        	String apellido=c.getString(c.getColumnIndex("apellido"));
        	String telefono=c.getString(c.getColumnIndex("telefono"));
        	String correo=c.getString(c.getColumnIndex("correo"));
        	String direccion=c.getString(c.getColumnIndex("direccion"));
        	String localidad=c.getString(c.getColumnIndex("localidad"));
        	String tipo=c.getString(c.getColumnIndex("tipo"));
        	cliente.setCodigo(codigo);
        	cliente.setNombre(nombre);
        	cliente.setApellido(apellido);
        	cliente.setTelefono(telefono);
        	cliente.setCorreo(correo);
        	cliente.setDireccion(direccion);
        	cliente.setLocalidad(localidad);
        	cliente.setTipo(tipo);
        	listado_clientes.add(cliente);
        } while (c.moveToNext());
        bd.close();
        c.close();
        adapter=new ClienteListAdapter(this,listado_clientes);
		listado.setAdapter(adapter);
		
		listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
	        	switch (i) {
				case 0:
					
					JSONParserClientes json = new JSONParserClientes(ListadoCliente.this);
					try {
						json.readAndParseJSONClientes();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		
		/* Activando el filtro de busqueda */
		buscar.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            	Toast.makeText(getApplicationContext(), arg0, Toast.LENGTH_SHORT).show();
            	ListadoCliente.this.adapter.getFilter().filter(arg0.toString()); 
            	
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                 
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