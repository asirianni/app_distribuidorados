package com.example.ventasonline;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class JSONParserProductos {
	private Activity activity;
	private JSONObject jObject; 
	private ProgressDialog progressDialog = null;
	private Runnable runReadAndParseJSON;
	private JSONClase url_accesos= new JSONClase();

	public JSONParserProductos(Activity a){
	   activity = a;
	}

	public void readAndParseJSONProductos() throws JSONException{
	   runReadAndParseJSON = new Runnable() {
	      @Override
	      public void run() {
	       try{
	    	   readJSONProductos();
	       } catch(Exception e){
	    	   
	       }
	      }
	   };
	   Thread thread = new Thread(null, runReadAndParseJSON,"bgReadJSONProd");
	   thread.start();
	   progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
	}

	private void readJSONProductos() throws JSONException{
	   jObject = JSONParser.getJSONfromURL(url_accesos.getUrl_productos_get());
	   if(jObject != null){
		   ArrayList<Producto> lista_productos=parseJSONClientes(jObject.getJSONArray("lista_productos"));
		   actualizarProductos(lista_productos);
		   activity.runOnUiThread(returnRes);
	   }   
	}

	private ArrayList<Producto> parseJSONClientes(JSONArray productoArray){
		ArrayList<Producto> lista = new ArrayList<Producto>();
		for(int i = 0; i < productoArray.length(); i++){
			Producto p = new Producto();
		     try {
		    	 p.setCodigo(productoArray.getJSONObject(i).getInt("id"));
		    	 p.setSubrubro(productoArray.getJSONObject(i).getString("subrubro"));
			     p.setDescripcion(productoArray.getJSONObject(i).getString("descripcion"));
			     p.setPrecio1(Float.valueOf(productoArray.getJSONObject(i).getString("lista_1")));
			     p.setPrecio2(Float.valueOf(productoArray.getJSONObject(i).getString("lista_2")));
			     p.setPrecio3(Float.valueOf(productoArray.getJSONObject(i).getString("lista_3")));
			     p.setPrecio4(Float.valueOf(productoArray.getJSONObject(i).getString("lista_4")));
			     
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
		     lista.add(p);
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

	private void actualizarProductos( ArrayList<Producto> productos){
				
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(activity.getApplicationContext(), "administracion", null, 1);
		SQLiteDatabase bd=admin.getWritableDatabase();
		ContentValues registro = new ContentValues();
		bd.execSQL("Delete from producto");
		for (Producto producto : productos) {
			
	        registro.put("codigo", producto.getCodigo());
	        registro.put("descripcion", producto.getDescripcion());
	        registro.put("rubro", producto.getRubro());
	        registro.put("subrubro", producto.getSubrubro());
	        registro.put("precio1", producto.getPrecio1());
	        registro.put("precio2", producto.getPrecio2());
	        registro.put("precio3", producto.getPrecio3());
	        registro.put("precio4", producto.getPrecio4());
	        
	        bd.insert("producto", null, registro);
		}
		
		
        bd.close();
		
	}
}
