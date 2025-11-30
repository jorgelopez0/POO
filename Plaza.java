package uva.poo.practica1;
/**
 * Representa una plaza dentro del aparcamiento.
 * @author jaiandr, jorglop.
 *
 */

public class Plaza {
	
	private String id;
	private EstadoPlaza estado;
	
	/**
	 * Construye la plaza
	 * @param id
	 * @param estado -> DISPONIBLE, OCUPADA, INOPERATIVA o RESERVADA 
	 * @throws IllegalArgumentException si el ID es nulo o vacio
	 */
	public Plaza(String id, EstadoPlaza estado) {
		if(id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("El ID de la plaza no puede ser nulo o vacio.");
		}
		this.id = id;
		this.estado = estado;
	}
	
	/**
	 * Devuelve le id de la plaza
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Modifica el id de la plaza
	 * @param id
	 */
	public void setId(String id) {
		if(id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("El ID de la plaza no puede ser nulo o vacio.");
		}
		this.id = id;
	}
	
	/**
	 * Devuelve el estado de la plaza
	 * @return
	 */
	public EstadoPlaza getEstado() {
		return estado;
	}
	/**
	 * CAMBIOS DE ESTADO
	 * Las funciones para cambiar el estado se separan en 4 para poder controlar debidamente 
	 * los cambios de estado.
	 */

	/**
	 * Modifica el estado de la plaza a Reservada
	 * @param estado
	 * @throws IllegalStateException si la plaza esta INOPERATIVA u OCUPADA
	 */
	public void setReservada() {
		if (this.estado == EstadoPlaza.INOPERATIVA || this.estado == EstadoPlaza.OCUPADA) {
			throw new IllegalStateException("No se puede reservar una plaza que esta INOPERATIVA u OCUPADA");
		}
		this.estado = EstadoPlaza.RESERVADA;
	}
	
	/**
	 * Modifica el estado de la plaza a Ocupada
	 * @param estado
	 * @throws IllegalStateException si la plaza esta  INOPERATIVA u OCUPADA
	 */
	public void setOcupada() {
		if (this.estado == EstadoPlaza.INOPERATIVA || this.estado == EstadoPlaza.OCUPADA) {
			throw new IllegalStateException("No se puede ocupar una plaza que esta INOPERATIVA u OCUPADA");
		}
		this.estado = EstadoPlaza.OCUPADA;
	}
	
	/**
	 * Modifica el estado de la plaza a Disponible
	 * @param estado
	 * @throws IllegalStateException si la plaza esta DISPONIBLE
	 */
	public void setDisponible() {
		if (this.estado == EstadoPlaza.DISPONIBLE) {
			throw new IllegalStateException("La plaza debe estar RESERVADA u OCUPADA para poder liberarse");
		}
		this.estado = EstadoPlaza.DISPONIBLE;
	}
	
	/**
	 * Modifica el estado de la plaza a Inoperativa
	 * @param estado
	 * Este cambio se permite desde cualquier estado
	 */
	public void setInoperativa() {
		this.estado = EstadoPlaza.INOPERATIVA;
	}
	
	
}
