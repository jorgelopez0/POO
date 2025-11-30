package uva.poo.practica1;

/**
 * Representa las coordenadas GPS de un punto especifico, como un aparcamiento.
 * Almacena la posicion en Grados Decimales (GD) e implementa metodos para
 * convertir a y desde el formato de Grados, Minutos y Segundos Sexagesimales (GMS).
 * @author jaiandr, jorglop.
 *
 */
public class Coordenadas {

	private double longitud;
	private double latitud;
	
	/**	
	 * Construye una posicion GPS inicializando la latitud y la longitud
	 * en el formato de **Grados Decimales (GD)**
	 * @param longitud La longitud en GD, debe estar en el rango [-180, 180]
	 * @param latitud La latitud en GD, debe estar en el rango [-90, 90]
	 * @throws IllegalArgumentException si la longitud no esta en [-180, 180]
	 * @throws IllegalArgumentException si la latitud no esta en [-90, 90]	
	 */
	public Coordenadas(double longitud, double latitud){
		if (longitud < -180.0 || longitud > 180.0) {
	        throw new IllegalArgumentException("La longitud debe estar entre -180 y 180 grados.");
	    }
		if (latitud < -90.0 || latitud > 90.0) {
	        throw new IllegalArgumentException("La latitud debe estar entre -90 y 90 grados.");
	    }
		this.longitud = longitud;
		this.latitud = latitud;
	}
	
	/**
	 * Construye una posicion GPS inicializando la latitud y la longitud
	 * a partir de sus representaciones en **Grados, Minutos y Segundos (GMS)**
	 * Internamente, convierte los GMS a Grados Decimales (GD) para su almacenamiento
	 * @param longitud La longitud en formato GMS 
	 * @param latitud La latitud en formato GMS 
	 * @throws IllegalArgumentException si la longitud convertida a GD no esta en [-180, 180]
	 * @throws IllegalArgumentException si la latitud convertida a GD no esta en [-90, 90]	
	 * @throws NumberFormatException si los grados, minutos o segundos no son numeros validos
	 */
	public Coordenadas(String longitud, String latitud) {
		double longitudGD = cambioGMSaGD(longitud);
		double latitudGD = cambioGMSaGD(latitud);
		if (longitudGD < -180.0 || longitudGD > 180.0) {
	        throw new IllegalArgumentException("La longitud debe estar entre -180 y 180 grados.");
	    }
		if (latitudGD < -90.0 || latitudGD > 90.0) {
	        throw new IllegalArgumentException("La latitud debe estar entre -90 y 90 grados.");
	    }
		this.longitud = cambioGMSaGD(longitud);
		this.latitud = cambioGMSaGD(latitud);
		
	}
	
	/**
	 * Devuelve la longitud de la posicion en **Grados Decimales (GD)**
	 * @return La longitud en GD (double)
	 */
	public double getLongitudGD() {
		return longitud;
	}
	
	/**
	 * Devuelve la longitud de la posicion en **Grados, Minutos y Segundos (GMS)**
	 * @return La longitud en GMS (String), incluyendo el simbolo del hemisferio (E/O)
	 */
	public String getLongitudGMS() {
		return getLongitudGDaGMS();
	}
	
	/**
	 * Devuelve la latitud de la posicion en **Grados Decimales (GD)**
	 * @return La latitud en GD (double)
	 */
	public double getLatitudGD() {
		return latitud;
	}
	
	/**
	 * Devuelve la latitud de la posicion en **Grados, Minutos y Segundos (GMS)**
	 * @return La latitud en GMS (String), incluyendo el simbolo del hemisferio (N/S)
	 */
	public String getLatitudGMS() {
		return getLatitudGDaGMS();
	}
	
	/**
	 * Modifica la longitud de la posicion
	 * @param longitud La nueva longitud en Grados Decimales (GD)
	 * @throws IllegalArgumentException si la longitud esta fuera de [-180, 180]
	 *	
	 */
	public void setLongitud(double longitud) {
		if (longitud < -180.0 || longitud > 180.0) {
	        throw new IllegalArgumentException("La longitud debe estar entre -180 y 180 grados.");
	    }
		this.longitud = longitud;
	}
	
	/**	
	 * Modifica la latitud de la posicion
	 * @param latitud La nueva latitud en Grados Decimales (GD)
	 * @throws IllegalArgumentException si la latitud esta fuera de [-90, 90]
	 */
	public void setLatitud(double latitud) {
		if (latitud < -90.0 || latitud > 90.0) {
	        throw new IllegalArgumentException("La latitud debe estar entre -90 y 90 grados.");
	    }
		this.latitud = latitud;
	}
	
	/**
	 * Convierte una coordenada de **Grados, Minutos y Segundos (GMS)** a **Grados Decimales (GD)**
	 * @param gms La coordenada en formato GMS
	 * @return El valor de la coordenada en Grados Decimales (double)
	 * @throws ArrayIndexOutOfBoundsException si la cadena GMS no contiene los 4 componentes esperados
	 * @throws NumberFormatException si los componentes de grados, minutos o segundos no son enteros
	 */
	public double cambioGMSaGD(String gms) {
		String[] partes = gms.split(", ");
		
		int grados = Integer.parseInt(partes[0]);
		int minutos = Integer.parseInt(partes[1]);
		int segundos = Integer.parseInt(partes[2]);
		
		char hemisferio = partes[3].charAt(0);
		
		
		double gd = grados + minutos / 60.0 + segundos / 3600.0;
		if(hemisferio == 'S' || hemisferio == 'O') {
			gd = -gd;
		}
		return gd;
	}
	/**
	 * Convierte el valor de latitud (en Grados Decimales) de este objeto	
	 * a su representacion en Grados, Minutos y Segundos (GMS)
	 * @return Una cadena en GMS
	 */
    public String getLatitudGDaGMS() {
        double gd = this.latitud; 
        char hemisferio = (gd >= 0) ? 'N' : 'S';
        double valorabsolutogd = Math.abs(gd); 
        int grados = (int) valorabsolutogd;
        double minutosDecimales = (valorabsolutogd - grados) * 60.0;
        int minutos = (int) minutosDecimales;
        int segundos = (int) Math.round((minutosDecimales - minutos) * 60.0);

        String gms = (int)gd + "°" + minutos + "'" + segundos + "''" + " "+hemisferio;
	    return gms;
    }
    
    /**
	 * Convierte el valor de longitud (en Grados Decimales) de este objeto	
	 * a su representacion en Grados, Minutos y Segundos (GMS)
	 * @return Una cadena en GMS
	 */
    public String getLongitudGDaGMS() {
        double gd = this.longitud;
        char hemisferio = (gd >= 0) ? 'E' : 'O';
        double valorabsolutogd = Math.abs(gd); 
        int grados = (int) valorabsolutogd;
        double minutosDecimales = (valorabsolutogd - grados) * 60.0;
        int minutos = (int) minutosDecimales;
        int segundos = (int) Math.round((minutosDecimales - minutos) * 60.0);
        String gms = (int)gd + "°" + minutos + "'" + segundos + "''" + " "+hemisferio;
	    return gms;
    }

	
}
