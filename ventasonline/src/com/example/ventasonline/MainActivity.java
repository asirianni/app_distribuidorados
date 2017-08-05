package com.example.ventasonline;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText usuario, pass;
	private TextView mostrar;
	private static String url = "http://192.168.1.80:80/distribuidorados/index.php/administrador/get_api_clientes";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		usuario=(EditText)findViewById(R.id.usuario);
		pass=(EditText)findViewById(R.id.pass);
		mostrar=(TextView)findViewById(R.id.mostrar); 
	}
	
	public void verificar_usuario(View view) {
		String usuario= this.usuario.getText().toString();
		String pass= this.pass.getText().toString();
		
		if (usuario.equals("")) {
		     Toast.makeText(this, "complete el campo usuario", 
		             Toast.LENGTH_LONG).show();
		}else{
			if (pass.equals("")) {
			     Toast.makeText(this, "complete el campo pass", 
			             Toast.LENGTH_LONG).show();
			}else{
				AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
				SQLiteDatabase bd=admin.getWritableDatabase();
				Cursor cursor= bd.rawQuery("SELECT * FROM usuarios where usuario='" + usuario + "' and pass='" + pass + "'" , null);
			    if (cursor.moveToFirst()){
			    	Toast.makeText(this, "bienvenido "+cursor.getString(1), Toast.LENGTH_LONG).show();
			    	Intent menu = new Intent(this, MenuPrincipal.class );
			    	startActivity(menu); 
			    } else{
			    	Toast.makeText(this, "no existe el usuario", Toast.LENGTH_LONG).show();
			    }
			}
		}
		
		
	}
	
	public void verificarBase(){
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
		SQLiteDatabase bd=admin.getWritableDatabase();
		Cursor cursor= bd.rawQuery("SELECT COUNT (*) FROM perfil", null);
	    cursor.moveToFirst();
	    int count= cursor.getInt(0);
	    if(count>0){
	    	mostrar.setText("tiene"); 
	    }else{
	    	
	    	new JSONParse().execute();
	    	
	    	
	    }
	    //cursor.close();
	}
	
	public JSONObject getJson(String url){
		InputStream is = null;
		String result = "";
		JSONObject jsonObject = null;
		
		// HTTP
		try {	    	
			HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch(Exception e) {
			return null;
		}
	    
		// Read response to string
		try {	    	
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();	            
		} catch(Exception e) {
			return null;
		}

		// Convert string to object
		try {
			jsonObject = new JSONObject(result);            
		} catch(JSONException e) {
			return null;
		}
    
		return jsonObject;
	}
	
	public void getHttpGet(String url) {

		try{
		    // Create a new HTTP Client
		    DefaultHttpClient defaultClient = new DefaultHttpClient();
		    // Setup the get request
		    HttpGet httpGetRequest = new HttpGet(url);

		    // Execute the request in the client
		    HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
		    // Grab the response
		    BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
		    String json = reader.readLine();
		    mostrar.setText(json);
		    new JSONObject(json);

		} catch(Exception e){
		    // In your production code handle any errors and catch the individual exceptions
		    e.printStackTrace();
		}
    }
	
	private class JSONParse extends AsyncTask<String, String, JSONObject> {
   	
	private ProgressDialog pDialog;
   	@Override
       protected void onPreExecute() {
           super.onPreExecute();
            //uid = (TextView)findViewById(R.id.uid);
			 //name1 = (TextView)findViewById(R.id.name);
			 //email1 = (TextView)findViewById(R.id.email);
           pDialog = new ProgressDialog(MainActivity.this);
           pDialog.setMessage("Consultando...");
           pDialog.setIndeterminate(false);
           pDialog.setCancelable(true);
           pDialog.show();

   	}

   	@Override
    protected JSONObject doInBackground(String... args) {
   		JSONParser jParser = new JSONParser();

   		// Getting JSON from URL
   		JSONObject json = jParser.getJSONFromUrl(url);
   		return json;
   	}
   	 @Override
     protected void onPostExecute(JSONObject json) {
   		 pDialog.dismiss();
   		 /*try {
   				// Getting JSON Array
   				user = json.getJSONArray(TAG_USER);
   				JSONObject c = user.getJSONObject(0);

   				// Storing  JSON item in a Variable
   				String id = c.getString(TAG_ID);
   				String name = c.getString(TAG_NAME);
   				String email = c.getString(TAG_EMAIL);

   				//Set JSON Data in TextView
   				uid.setText(id);
   				name1.setText(name);
   				email1.setText(email);

   		} catch (JSONException e) {
   			e.printStackTrace();
   		}*/

   	 }
   }
}
