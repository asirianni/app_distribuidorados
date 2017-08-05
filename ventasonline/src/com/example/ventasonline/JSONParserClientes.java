package com.example.ventasonline;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class JSONParserClientes {
	
	private Activity activity;
	private JSONObject jObject; 
	private ProgressDialog progressDialog = null;
	private Runnable runReadAndParseJSON;
	private JSONClase url_accesos= new JSONClase();

	public JSONParserClientes(Activity a){
	   activity = a;
	}

	public void readAndParseJSONClientes() throws JSONException{
	   runReadAndParseJSON = new Runnable() {
	      @Override
	      public void run() {
	       try{
	    	   readJSONClientes();
	       } catch(Exception e){
	    	   
	       }
	      }
	   };
	   Thread thread = new Thread(null, runReadAndParseJSON,"bgReadJSONLibros");
	   thread.start();
	   progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
	}

	private void readJSONClientes() throws JSONException{
	   jObject = JSONParser.getJSONfromURL(url_accesos.getUrl_clientes_get());
	   if(jObject != null){
		   ArrayList<Cliente> lista_cliente=parseJSONClientes(jObject.getJSONArray("lista_cliente"));
		   actualizarClientes(lista_cliente);
		   activity.runOnUiThread(returnRes);
	   }   
	}

	private ArrayList<Cliente> parseJSONClientes(JSONArray clienteArray){
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
	  for(int i = 0; i < clienteArray.length(); i++){
	     Cliente c = new Cliente();
	     try {
	    	 c.setCodigo(clienteArray.getJSONObject(i).getInt("id"));
	    	 c.setNombre(clienteArray.getJSONObject(i).getString("nombre"));
		     c.setApellido(clienteArray.getJSONObject(i).getString("apellido"));
		     c.setTelefono(clienteArray.getJSONObject(i).getString("telefono"));
		     c.setCorreo(clienteArray.getJSONObject(i).getString("correo"));
		     c.setDireccion(clienteArray.getJSONObject(i).getString("direccion"));
		     c.setLocalidad(clienteArray.getJSONObject(i).getString("desc_localidad"));
		     c.setTipo(clienteArray.getJSONObject(i).getString("desc_estado"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     lista.add(c);
	    /* c.save(activity);*/
	  }
	  return lista;
	}

	private Runnable returnRes = new Runnable(){ 
	 @Override 
	 public void run() {
	  progressDialog.dismiss();
	 }
	};

	private void actualizarClientes( ArrayList<Cliente> clientes){
				
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(activity.getApplicationContext(), "administracion", null, 1);
		SQLiteDatabase bd=admin.getWritableDatabase();
		ContentValues registro = new ContentValues();
		bd.execSQL("Delete from  cliente");
		for (Cliente cliente : clientes) {
			
	        registro.put("codigo", cliente.getCodigo());
	        registro.put("nombre", cliente.getApellido());
	        registro.put("apellido", cliente.getApellido());
	        registro.put("telefono", cliente.getTelefono());
	        registro.put("correo", cliente.getCorreo());
	        registro.put("direccion", cliente.getDireccion());
	        registro.put("localidad", cliente.getLocalidad());
	        registro.put("tipo", cliente.getTipo());
	        bd.insert("cliente", null, registro);
		}
		
		
        bd.close();
		
	}
	
	
}
