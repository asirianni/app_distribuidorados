package com.example.ventasonline;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductoListAdapter extends ArrayAdapter<Producto> {

    private final Activity context;
    private final ArrayList<Producto>itemname;
    

    public ProductoListAdapter(Activity context, ArrayList<Producto> itemname) {
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

        txtTitle.setText(itemname.get(c).getDescripcion());
        imageView.setImageResource(R.drawable.productos);
        etxDescripcion.setText(
        		" precio 1: $"+itemname.get(c).getPrecio1()+
        		"\n precio 2: $"+itemname.get(c).getPrecio2()+
        		"\n precio 3: $"+itemname.get(c).getPrecio3()+
        		"\n precio 4: $"+itemname.get(c).getPrecio4());

        return rowView;
    }
}   