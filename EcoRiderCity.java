package uva.poo.practica1;

/**
 * Representa una ciudad con aparcamientos EcoRiderSharing
 * @author jaiandr, jorglop.
 **/

import java.util.ArrayList;


/**
 * Representa una ciudad con aparcamientos EcoRiderSharing.
 * @author jaiandr, jorglop.
 */
public class EcoRiderCity {
	
	private double fianza;
	private ArrayList<EcoRiderParking> aparcamientos;
	
	/**
	 * Construye una nueva instancia de con una fianza inicial
	 * Inicializa la lista de aparcamientos como vacía
	 * @param fianzaInicial La cantidad de fianza que se establecerá por defecto
	 * @throws IllegalArgumentException si la fianza inicial es 0 o menor que 0
	 */
	public EcoRiderCity(double fianzaInicial) {
		if(fianzaInicial <= 0) {
			throw new IllegalArgumentException("La fianza inicial no puede ser ni 0 ni menor que 0");
		}
		this.fianza = fianzaInicial;
		this.aparcamientos = new ArrayList<>();
		
	}
	
	/**
	 * Agrega un nuevo aparcamiento a la lista de aparcamientos de la ciudad
	 * @param parking a agregar
	 * @throws IllegalArgumentException si el parking a es nulo
	 */
	public void agregarAparcamiento(EcoRiderParking parking) {
		if(parking == null) {
			throw new IllegalArgumentException("El parking no puede ser nulo");
		}
		aparcamientos.add(parking);
	}
	
	/**
	 * Elimina un aparcamiento de la lista de aparcamientos de la ciudad
	 * @param parking a eliminar
	 * @throws IllegalArgumentException si el parking a eliminar es nulo
	 */
	public void eliminarAparcamiento(EcoRiderParking parking) {
		if(parking == null) {
			throw new IllegalArgumentException("El parking no puede ser nulo");
		}
		aparcamientos.remove(parking);
	}

	/**
	 * Devuelve la fianza actual
	 * @return La cantidad de la fianza
	 */
	public double getFianza() {
		return fianza;
	}
	
	/**
	 * Modifica la cantidad establecida para la fianza
	 * @param fianza La nueva cantidad de la fianza
	 * @throws IllegalArgumentException si la fianza es 0 o menor que 0
	 */
	public void setFianza(double fianza) {
		if(fianza <= 0) {
			throw new IllegalArgumentException("La fianza no puede ser ni 0 ni menor que 0");
		}
		this.fianza = fianza;
	}
	
	/**
	 * Devuelve la lista de todos los aparcamientos en la ciudad
	 * @return Un ArrayList de los aparcamientos en la ciudad
	 */
	public ArrayList<EcoRiderParking> getAparcamientos(){
		return aparcamientos;
	}
	
	/**
	 * Devuelve una lista de todos los aparcamientos que se consideran operativos,
	 * es decir, aquellos que no tienen plazas inoperativas.
	 * @return Un ArrayList de aparcamientos operativos 
	 */
	public ArrayList<EcoRiderParking> getAparcamientosOperativos(){
		ArrayList<EcoRiderParking> operativos = new ArrayList<>();
		for(int i = 0; i < aparcamientos.size(); i ++) {
			EcoRiderParking parking = aparcamientos.get(i);
			if(parking.getInoperativas() == 0) {
				operativos.add(parking);
			}
		}
		return operativos;
	}
	/**
	 * Devuelve una lista de todos los aparcamientos que se consideran completos,
	 * es decir, aquellos que no tienen plazas disponibles para devolver un vehículo
	 * @return Un ArrayList de aparcamientos completos
	 */
	public ArrayList<EcoRiderParking> getAparcamientosCompletos() {
		ArrayList<EcoRiderParking> completos = new ArrayList<>();
		for(int i = 0; i < aparcamientos.size(); i ++) {
			EcoRiderParking parking = aparcamientos.get(i);
			if(parking.getDisponibles() == 0) {
				completos.add(parking);
			}
		
		}
		return completos;
	}
	
	/**
	 * Devuelve una lista de todos los aparcamientos en los que haya al menos un sitio
	 * disponible para devolver un vehículo
	 * @return Un ArrayList de aparcamientos con disponibilidad
	 */
	public ArrayList<EcoRiderParking> getAparcamientosDisponibles(){
		ArrayList<EcoRiderParking> disponibles = new ArrayList<>();
		for(int i = 0; i < aparcamientos.size(); i ++) {
			EcoRiderParking parking = aparcamientos.get(i);
			if(parking.getDisponibles() > 0) {
				disponibles.add(parking);
			}
		
		}
		return disponibles;
	}
	/**
	 * Devuelve una lista de todos los aparcamientos que tengan alguna plaza inoperativa,
	 * pero que no estén totalmente inoperativos (es decir, tienen al menos una plaza operativa)
	 * @return Un ArrayList de aparcamientos con plazas inoperativas.
	 */
	public ArrayList<EcoRiderParking> getAparcamientosconInoperativas(){
		ArrayList<EcoRiderParking> operativos = new ArrayList<>();
		for(int i = 0; i < aparcamientos.size(); i ++) {
			EcoRiderParking parking = aparcamientos.get(i);
			if(parking.getInoperativas() > 0 && parking.getInoperativas() < parking.getTotalPlazas()) {
				operativos.add(parking);
			}
		}
		return operativos;
	}
	
	/**
	 * Obtiene los aparcamientos cercanos a una coordenada GPS dada que tienen plazas disponibles
	 * Se consideran cercanos aquellos cuya distancia al punto de referencia es menor o igual al radio especificado
	 * @param coordenadas El punto de referencia GPS
	 * @param radio El radio de búsqueda
	 * @return Un ArrayList de aparcamientos cercanos y disponibles, o la lista vacia
	 * @throws IllegalArgumentException si las coordenadas de referencia son nulas
	 * @throws IllegalArgumentException si el radio es menor que 0
	 */
	public ArrayList<EcoRiderParking> getAparcamientosCercanos(Coordenadas coordenadas, double radio) {
		if(coordenadas == null) {
			throw new IllegalArgumentException("Las coordenadas de referencia no pueden ser nulas");
		}
		if(radio < 0) {
			throw new IllegalArgumentException("No puede tener un radio menor que 0");
		}
		ArrayList<EcoRiderParking> cercanos = new ArrayList<>();
		for(int i = 0; i < aparcamientos.size(); i ++) {
			EcoRiderParking parking = aparcamientos.get(i);
			if(parking.getDisponibles() > 0) {
				double distancia = parking.distancia(coordenadas);
				if (distancia <= radio) {
	                cercanos.add(parking);
	            }
			}
		}
		
		return cercanos;
	}


}
