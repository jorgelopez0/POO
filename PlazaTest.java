package uva.poo.test.practica2;

import static org.junit.Assert.*;


import org.junit.Test;

import uva.poo.practica2.EstadoPlaza;
import uva.poo.practica2.Plaza;
/**
 * Test unitario de la clase PlazaTest.
 * @author jaiandr, jorglop.
 */
public class PlazaTest {
	
	// --- Tests para el constructor ---
	@Test
	public void testInicializacionValida() {
		Plaza plaza = new Plaza("P1",EstadoPlaza.DISPONIBLE);
		assertNotNull(plaza);
		assertEquals("P1",plaza.getId());
		assertEquals(EstadoPlaza.DISPONIBLE,plaza.getEstado());
		
	}
	
	@Test
	public void testInicializacionConEstadoOcupada() {
		Plaza plaza = new Plaza("P1",EstadoPlaza.OCUPADA);
		assertNotNull(plaza);
		assertEquals("P1",plaza.getId());
		assertEquals(EstadoPlaza.OCUPADA,plaza.getEstado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializacionInvalidadIdNulo() {
		//Asumiendo que el constructor lanza IllegalArgumentException si el ID es nulo
		new Plaza(null, EstadoPlaza.DISPONIBLE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializacionInvalidaIdVacio() {
		//Asumiendo que el constructor lanza IllegalArgumentException si el ID es vacio
		new Plaza(" ",EstadoPlaza.DISPONIBLE);
	}
	
	// --- Tests para Getters y Setters de ID ---
	
	@Test
	public void testGetId() {
		Plaza plaza = new Plaza("P2",EstadoPlaza.RESERVADA);
		assertEquals("P2", plaza.getId());
	}
	
	@Test
    public void testSetIdValido() {
        Plaza plaza = new Plaza("D20", EstadoPlaza.INOPERATIVA);
        plaza.setId("D21");
        assertEquals("D21", plaza.getId());
    }
	
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetIdInvalidoNulo() {
        Plaza plaza = new Plaza("D20", EstadoPlaza.INOPERATIVA);
        plaza.setId(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetIdInvalidoVacio() {
        Plaza plaza = new Plaza("D20", EstadoPlaza.INOPERATIVA);
        plaza.setId("");
    }
    
	
	// --- Tests para GetEstado ---

    @Test
    public void testGetEstado() {
        Plaza plaza = new Plaza("E10", EstadoPlaza.INOPERATIVA);
        assertEquals(EstadoPlaza.INOPERATIVA, plaza.getEstado());
    }
	
    // --- Tests para setReservada() ---
    
    @Test
    public void testSetReservadaDesdeDisponible() {
    	Plaza plaza = new Plaza("F05", EstadoPlaza.DISPONIBLE);
    	plaza.setReservada  ();
    	assertEquals(EstadoPlaza.RESERVADA, plaza.getEstado());
    }
    
    @Test
    public void testSetReservadaDesdeReservada() {
    	Plaza plaza = new Plaza("P02", EstadoPlaza.RESERVADA);
    	plaza.setReservada();
    	assertEquals(EstadoPlaza.RESERVADA, plaza.getEstado());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetReservadaInvalidaDesdeOcupada() {
        // No se puede reservar una plaza que ya esta ocupada
        Plaza plaza = new Plaza("F07", EstadoPlaza.OCUPADA);
        plaza.setReservada();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetReservadaInvalidaDesdeInoperativa() {
        // Una plaza inoperativa no se puede reservar
        Plaza plaza = new Plaza("F08", EstadoPlaza.INOPERATIVA);
        plaza.setReservada();
    }
    
 // --- Tests para setOcupada() ---

    @Test
    public void testSetOcupadaDesdeDisponible() {
        Plaza plaza = new Plaza("G01", EstadoPlaza.DISPONIBLE);
        plaza.setOcupada();
        assertEquals(EstadoPlaza.OCUPADA, plaza.getEstado());
    }
    
    @Test
    public void testSetOcupadaDesdeReservada() {
        // Es comun que una plaza reservada pase a ocupada
        Plaza plaza = new Plaza("G02", EstadoPlaza.RESERVADA);
        plaza.setOcupada();
        assertEquals(EstadoPlaza.OCUPADA, plaza.getEstado());
    }

    @Test(expected = IllegalStateException.class)
    public void testSetOcupadaInvalidaDesdeOcupada() {
        // Ya esta ocupada
        Plaza plaza = new Plaza("G03", EstadoPlaza.OCUPADA);
        plaza.setOcupada();
    }

    @Test(expected = IllegalStateException.class)
    public void testSetOcupadaInvalidaDesdeInoperativa() {
        // Una plaza inoperativa no se puede ocupar
        Plaza plaza = new Plaza("G04", EstadoPlaza.INOPERATIVA);
        plaza.setOcupada();
    }
    
 // --- Tests para setDisponible() ---

    @Test
    public void testSetDisponibleDesdeOcupada() {
        Plaza plaza = new Plaza("H01", EstadoPlaza.OCUPADA);
        plaza.setDisponible();
        assertEquals(EstadoPlaza.DISPONIBLE, plaza.getEstado());
    }
    
    @Test
    public void testSetDisponibleDesdeReservada() {
        // Se cancela una reserva
        Plaza plaza = new Plaza("H02", EstadoPlaza.RESERVADA);
        plaza.setDisponible();
        assertEquals(EstadoPlaza.DISPONIBLE, plaza.getEstado());
    }

    @Test(expected = IllegalStateException.class)
    public void testSetDisponibleInvalidaDesdeDisponible() {
        // Ya esta disponible
        Plaza plaza = new Plaza("H03", EstadoPlaza.DISPONIBLE);
        plaza.setDisponible();
    }

    
// --- Tests para setInoperativa() ---
    
    @Test
    public void testSetInoperativaDesdeDisponible() {
        // Una plaza puede pasar a inoperativa por mantenimiento
        Plaza plaza = new Plaza("I01", EstadoPlaza.DISPONIBLE);
        plaza.setInoperativa();
        assertEquals(EstadoPlaza.INOPERATIVA, plaza.getEstado());
    }
    
    @Test
    public void testSetInoperativaDesdeOcupada() {
        // Se estropea mientras esta ocupada, se marca como tal.
        Plaza plaza = new Plaza("I02", EstadoPlaza.OCUPADA);
        plaza.setInoperativa();
        assertEquals(EstadoPlaza.INOPERATIVA, plaza.getEstado());
    }

    @Test
    public void testSetInoperativaDesdeReservada() {
        // Una plaza reservada pasa a estar fuera de servicio
        Plaza plaza = new Plaza("I03", EstadoPlaza.RESERVADA);
        plaza.setInoperativa();
        assertEquals(EstadoPlaza.INOPERATIVA, plaza.getEstado());
    }
}
