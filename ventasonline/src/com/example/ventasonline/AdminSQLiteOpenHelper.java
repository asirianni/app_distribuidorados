package com.example.ventasonline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	public AdminSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table usuarios(codigo integer primary key, usuario text, pass text, nombre text, apellido text)");		
		
		db.execSQL("INSERT INTO usuarios (codigo, usuario, pass, nombre, apellido) VALUES (1,'asirianni','cirilo1128','adrian', 'sirianni')");
		db.execSQL("INSERT INTO usuarios (codigo, usuario, pass, nombre, apellido) VALUES (2,'emiliano','emiVerri1922','emiliano', 'verri')");
		
		db.execSQL("create table cliente(codigo integer primary key, nombre text, apellido text, telefono text, correo text, direccion text, localidad text, tipo text)");
		db.execSQL("INSERT INTO cliente (codigo, nombre, apellido, telefono, correo, direccion, localidad, tipo) VALUES (1345,'prueba','prueba','prueba', 'prueba','prueba','prueba', 'prueba')");
		
		db.execSQL("create table pedido(numero integer primary key, fecha text, cliente integer, total text, estado text)");
		db.execSQL("create table perfil(codigo integer primary key, usuario text, pass text, nombre text, apellido text, correo text, tipo text, estado text)");
		db.execSQL("create table producto(codigo integer primary key, descripcion text, rubro text, subrubro text, precio1 real, precio2 real, precio3 real, precio4 real)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists cliente");
		db.execSQL("create table cliente(codigo integer primary key, nombre text, apellido text, telefono text, correo text, direccion text, localidad text, tipo text)"); 
		db.execSQL("drop table if exists pedido");
		db.execSQL("create table pedido(numero integer primary key, fecha text, cliente integer, total text, estado text)");
		db.execSQL("drop table if exists perfil");
		db.execSQL("create table perfil(codigo integer primary key, usuario text, pass text, nombre text, apellido text, correo text, tipo text, estado text)");
		db.execSQL("drop table if exists producto");
		db.execSQL("create table producto(codigo integer primary key, descripcion text, rubro text, subrubro text, precio1 real, precio2 real, precio3 real, precio4 real)");
		
	}

}
