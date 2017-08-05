package com.example.ventasonline;

public class JSONClase {
	private String url_clientes_get="http://192.168.1.7:80/distribuidorados/index.php/administrador/get_api_clientes";
	private String url_productos_get="http://192.168.1.7:80/distribuidorados/index.php/administrador/get_api_productos";
	private String url_usuarios_get="http://192.168.1.7:80/distribuidorados/index.php/administrador/get_api_usuarios";
	private String url_rubros_get="http://192.168.1.7:80/distribuidorados/index.php/administrador/get_api_rubros";
	
	public String getUrl_clientes_get() {
		return url_clientes_get;
	}
	public String getUrl_productos_get() {
		return url_productos_get;
	}
	public String getUrl_usuarios_get() {
		return url_usuarios_get;
	}
	public String getUrl_rubros_get() {
		return url_rubros_get;
	}
	
	
}
