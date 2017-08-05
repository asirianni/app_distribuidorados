package com.example.ventasonline;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class ClienteListAdapter extends ArrayAdapter<Cliente> implements Filterable{

    private final Activity context;
    private ArrayList<Cliente>itemname;
    

    public ClienteListAdapter(Activity context, ArrayList<Cliente> itemname) {
        super(context, R.layout.fila_lista, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        
    }

    public View getView(int c, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.fila_lista,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        txtTitle.setText(itemname.get(c).getNombre());
        imageView.setImageResource(R.drawable.cliente);
        etxDescripcion.setText(itemname.get(c).getNombre()+itemname.get(c).getApellido());

        return rowView;
    }
    
    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
         Filter filter = new Filter() {

        	@Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // TODO Auto-generated method stub
    	 		itemname = (ArrayList<Cliente>) results.values; // has the filtered values
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // TODO Auto-generated method stub
                FilterResults results = new FilterResults();
                ArrayList<Cliente> FilteredArrList = new ArrayList<Cliente>();

                if (itemname == null) {
                	itemname = new ArrayList<Cliente>(); // saves the original data in mOriginalValues
                }
                 if (constraint == null || constraint.length() == 0) {

                     // set the Original result to return  
                     results.count = itemname.size();
                     results.values = itemname;
                 } else {
                     constraint = constraint.toString().toLowerCase();
                     for (int i = 0; i < itemname.size(); i++) {
                         Cliente data = itemname.get(i);
                         if (data.getNombre().contains((constraint.toString()))) {
                             FilteredArrList.add(data);
                         }
                     }
                     // set the Filtered result to return
                     results.count = FilteredArrList.size();
                     results.values = FilteredArrList;
                 }
                 return results;
             }


    };
        return filter;


    }
    
    
}    
    