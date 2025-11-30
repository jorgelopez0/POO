package uva.poo.practica1;



import java.util.ArrayList;


import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 *Representa un aparcamiento dentro de una ciudad
 * @author jaiandr, jorglop.
 **/
public class EcoRiderParking {
	
	private String id;
	private String nombre;
	private String direccion;
	private Coordenadas coordenadas;
	private int bloqueExpansion;
	private ArrayList<Plaza> plazas;
	private double fianza;
	
	/**
	 * Construye el parking en una localizacion especifica con un numero inicial de plazas
	 * @param id Identificador unico del aparcamiento en el sistema
	 * @param nombre Nombre descriptivo del aparcamiento
	 * @param direccion Direccion postal del aparcamiento
	 * @param coordenadas Posicion GPS (latitud y longitud) del aparcamiento
	 * @param bloqueExpansion Cantidad de plazas a anadir cuando se amplia el aparcamiento
	 * @param plazasIniciales Numero inicial de plazas en el aparcamiento
	 * @param fianza Cantidad de dinero que se requiere como fianza por el alquiler
	 * @throws IllegalArgumentException si el ID, nombre o direccion son nulos o vacios
	 * @throws IllegalArgumentException si las coordenadas son nulas
	 * @throws IllegalArgumentException si el bloque de expansion es negativo
	 * @throws IllegalArgumentException si las plazas iniciales son negativas
	 */
	public EcoRiderParking(String id, String nombre, String direccion, Coordenadas coordenadas, int bloqueExpansion, int plazasIniciales, double fianza){
		if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || direccion == null || direccion.trim().isEmpty()) {
	        throw new IllegalArgumentException("ID, nombre y dirección no pueden ser nulos o vacios.");
	    }
	    if (coordenadas == null) {
	        throw new IllegalArgumentException("Las coordenadas no pueden ser nulas.");
	    }
	    if (bloqueExpansion < 0) {
	        throw new IllegalArgumentException("El bloque de expansión debe ser un valor positivo.");
	    }
	    if (plazasIniciales < 0) {
	        throw new IllegalArgumentException("Las plazas iniciales no pueden ser negativas.");
	    }
	   
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.coordenadas = coordenadas;
		this.bloqueExpansion = bloqueExpansion;
		this.fianza = fianza;
		this.plazas = new ArrayList<>();
		for(int i = 0; i < plazasIniciales; i++) {
			String idPlaza = "P" + (i + 1);
			Plaza plaza = new Plaza(idPlaza,EstadoPlaza.DISPONIBLE);
			plazas.add(plaza);
		}
	
	}
	
	/**
	 * Devuelve el identificador unico del aparcamiento
	 * @return El ID del aparcamiento
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Modifica el identificador del aparcamiento
	 * @param id El nuevo ID del aparcamiento
	 * @throws IllegalArgumentException si el ID es nulo o vacio
	 */	
	public void setId(String id) {
		if (id == null || id.trim().isEmpty()) {
	        throw new IllegalArgumentException("ID no puede ser nulo o vacio.");
	    }
		this.id = id;
	}
	
	/**
	 * Devuelve el nombre del aparcamiento
	 * @return El nombre del aparcamiento
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modifica el nombre del aparcamiento
	 * @param nombre El nuevo nombre del aparcamiento
	 * @throws IllegalArgumentException si el nombre es nulo o vacio
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("Nombre no puede ser nulo o vacio.");
	    }
		this.nombre = nombre;
	}

	/**
	 * Devuelve la direccion postal del aparcamiento
	 * @return La direccion del aparcamiento
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Modifica la direccion del aparcamiento
	 * @param direccion La nueva direccion del aparcamiento
	 * @throws IllegalArgumentException si la direccion es nula o vacia
	 */
	public void setDireccion(String direccion) {
		if( direccion == null|| direccion.trim().isEmpty()) {
			throw new IllegalArgumentException("La direccion no puede ser nula o vacia.");
		}
		this.direccion = direccion;
	}

	/**
	 * Devuelve las coordenadas GPS del aparcamiento
	 * @return Objeto Coordenadas que contiene la latitud y longitud
	 */
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}
	
	/**
	 * Devuelve la cantidad de plazas que se amplian en cada ampliacion
	 * @return El tamano del bloque de expansion
	 */
	public int getBloqueExpansion() {
		return bloqueExpansion;
	}
	
	/**
	 * Modifica la cantidad de plazas que se amplian en cada ampliacion
	 * @param bloqueExpansion El nuevo tamano del bloque de expansion
	 * @throws IllegalArgumentException si el bloque de expansion es negativo
	 */
	public void setBloqueExpansion(int bloqueExpansion) {
		if(bloqueExpansion < 0) {
			throw new IllegalArgumentException("El bloque de expansion es negativo.");
		}
		this.bloqueExpansion = bloqueExpansion;
	}
	
	/**
	 * Devuelve la cantidad total de plazas en el aparcamiento
	 * @return El numero total de plazas
	 */
	public int getTotalPlazas() {
		return plazas.size();
	}
	
	/**
	 * Devuelve una copia de la lista de plazas, permitiendo solo operaciones de solo lectura
	 * @return Un nuevo ArrayList que contiene todas las plazas
	 */
	public ArrayList<Plaza> getPlazas() {
		return new ArrayList<>(plazas);
	}
	
	/**
	 * Modifica la lista completa de plazas del aparcamiento, 
	 * hay que tener en cuenta que reemplaza todas las plazas existentes
	 * @param plazas La nueva lista de plazas. No debe ser nula
	 * @throws IllegalArgumentException si la lista de plazas es nula o vacia
	 */
	public void setPlazas(ArrayList<Plaza> plazas) {
	    if (plazas == null || plazas.isEmpty()) {
	    	throw new IllegalArgumentException("La lista de plazas es nula o vacia.");
	    }
	    this.plazas = new ArrayList<>(plazas);
	}
	
	/**
	 * Devuelve la cantidad de plazas ocupadas
	 * @return El numero de plazas en estado OCUPADA
	 */
	public int getOcupados() {
		int nPlazasOcupadas = 0;
		for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getEstado() == EstadoPlaza.OCUPADA) {
				nPlazasOcupadas++;
			}
		}
		return nPlazasOcupadas;
	}
	
	/**
	 * Devuelve la cantidad de plazas disponibles 
	 * @return El numero de plazas en estado DISPONIBLE
	 */
	public int getDisponibles() {
		int nPlazasDisponibles = 0;
		for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getEstado() == EstadoPlaza.DISPONIBLE) { 
				nPlazasDisponibles++;
			}
		}
		return nPlazasDisponibles;
	}

	/**
	 * Devuelve la cantidad de plazas inoperativas
	 * @return El numero de plazas en estado INOPERATIVA
	 */
	public int getInoperativas() {
		int nPlazasInoperativas = 0;
		for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getEstado() == EstadoPlaza.INOPERATIVA) {
				nPlazasInoperativas++;
			}
		}
		return nPlazasInoperativas;
	}
	
	/**
	 * Devuelve la cantidad de plazas reservadas
	 * @return El numero de plazas en estado RESERVADA
	 */
	public int getReservadas() {
		int nPlazasReservadas = 0;
		for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getEstado() == EstadoPlaza.RESERVADA) {
				nPlazasReservadas++;
			}
		}
		return nPlazasReservadas;
	}
	/**
	 * Devuelve el estado de una plaza buscando por su identificador
	 * @param idPlaza El identificador unico de la plaza dentro del aparcamiento
	 * @return El estado de la plaza (DISPONIBLE, OCUPADA, RESERVADA, INOPERATIVA) o nulo si el ID no existe
	 * @throws IllegalArgumentException si el ID de la plaza es nulo o vacio
	 */
	public EstadoPlaza getEstadoPlaza(String idPlaza) {
		 if (idPlaza == null || idPlaza.trim().isEmpty()) {
		    	throw new IllegalArgumentException("La lista de plazas es nula o vacia.");
		 }
		 for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getId().equals(idPlaza)) {
				return plaza.getEstado();
			}
		 }
		return null;
	}
	
	/**
	 * Intenta alquilar un vehiculo en la plaza especificada, descontando la fianza de la tarjeta del cliente
	 * @param idPlaza El identificador de la plaza donde se intenta alquilar
	 * @param tarjetaCliente La tarjeta monedero del cliente para gestionar la fianza
	 * @return true si el alquiler fue exitoso (la plaza estaba DISPONIBLE o RESERVADA y el saldo es suficiente),
	 * false en caso contrario (saldo insuficiente, plaza ocupada/inoperativa, o ID no encontrado)
	 * @throws IllegalArgumentException si el ID de la plaza o la tarjeta cliente son nulos o vacios
	 */
	public boolean alquilarVehiculo(String idPlaza, TarjetaMonedero tarjetaCliente) {
		if (idPlaza == null || idPlaza.trim().isEmpty()) {
	    	throw new IllegalArgumentException("La lista de plazas es nula o vacia.");
		}
		if (tarjetaCliente == null) {
	        throw new IllegalArgumentException("La tarjeta del cliente no puede ser nula.");
	    }
		for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getId().equals(idPlaza)){
				EstadoPlaza estado = plaza.getEstado();
				if(estado == EstadoPlaza.DISPONIBLE || estado == EstadoPlaza.RESERVADA) {
					String credencialdeDescarga = "6Z1y00Nm31aA-571";
					if (tarjetaCliente.getSaldoActual() < this.fianza) {
				        return false; 
				    }else {
						tarjetaCliente.descontarDelSaldo(credencialdeDescarga, fianza);
						plaza.setOcupada();
						return true;
				    }
				
				}
			}
		}
		return false;	
	}
	
	/**
	 * Devuelve un vehiculo en la plaza especificada, descargando el saldo del cliente con la fianza
	 * @param idPlaza El identificador de la plaza donde se intenta devolver
	 * @param tarjetaCliente La tarjeta monedero del cliente para gestionar la fianza
	 * @return true si la devolucion fue exitosa, false en caso contrario.
	 * @throws IllegalArgumentException si el ID de la plaza o la tarjeta cliente son nulos o vacios
	 */
	public boolean devolverVehiculo(String idPlaza, TarjetaMonedero tarjetaCliente) {
		if (idPlaza == null || idPlaza.trim().isEmpty()) {
	    	throw new IllegalArgumentException("La lista de plazas es nula o vacia.");
		}
		for(int i = 0; i < plazas.size(); i++) {
			Plaza plaza = plazas.get(i);
			if(plaza.getId().equals(idPlaza)){
				EstadoPlaza estado = plaza.getEstado();
				if(estado == EstadoPlaza.OCUPADA) {
					String credencialdeDescarga = "A156Bv09_1zXo894";
					tarjetaCliente.recargaSaldo(credencialdeDescarga, fianza);
					plaza.setDisponible();
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Calcula la distancia entre el aparcamiento actual y una coordenada GPS de referencia
	 * El calculo se realiza mediante la formula del Haversine
	 * @param coordenadas El punto de referencia
	 * @return La distancia en metros
	 * @throws IllegalArgumentException si las coordenadas de referencia son nulas
	 */
	public double distancia(Coordenadas coordenadas) {
		if(coordenadas == null) {
			throw new IllegalArgumentException("Las coordenadas de referencia son nulas");
		}
		double R = 6371000; //Radio de la tierra en metros
		double lat1Rad = Math.toRadians(this.coordenadas.getLatitudGD());
	    double lat2Rad = Math.toRadians(coordenadas.getLatitudGD());
		double dLat = Math.toRadians(coordenadas.getLatitudGD() - this.coordenadas.getLatitudGD());
		double dLon = Math.toRadians(coordenadas.getLongitudGD() - this.coordenadas.getLongitudGD());
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + 
	               Math.cos(lat1Rad) * Math.cos(lat2Rad) * (Math.sin(dLon / 2) * Math.sin(dLon / 2));
		double c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		return R*c;
	}
	
	/**
	 * Calcula la distancia entre el aparcamiento actual y otro aparcamiento de referencia
	 * @param aparcamiento El aparcamiento de referencia
	 * @return La distancia en metros
	 * @throws IllegalArgumentException si el aparcamiento de referencia es nulo
	 */
	public double distanciaAlAparcamientoACoordenada(EcoRiderParking aparcamiento) {
		if(aparcamiento == null) {
			throw new IllegalArgumentException("El aparcamiento de referencia es nulo");
		}
		return distancia(aparcamiento.getCoordenadas());
	}
	
	
	/**
	 * Agrega plazas nuevas al aparcamiento predeterminadas
	 * Las nuevas plazas se inicializan en estado DISPONIBLE
	 */
	public void ampliar() {
		int plazasActuales = plazas.size();
		for(int i = 0; i < bloqueExpansion; i++) {
			String nuevoID = "P" + (plazasActuales + i + 1);
			Plaza plazaNueva = new Plaza(nuevoID, EstadoPlaza.DISPONIBLE);
			plazas.add(plazaNueva);
		}
		
	}
}
