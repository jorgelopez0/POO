package uva.poo.test.practica2;

import static org.junit.Assert.*;


import uva.poo.practica2.*;

import java.util.ArrayList;

import org.junit.Before;

import org.junit.Test;

/**
 * Test unitario de la clase EcoRiderCityTest.
 * @author jaiandr, jorglop.
 */
public class EcoRiderCityTest {
	
	public static final double ERROR_ADMISIBLE = 0.000001; 
	
	private Coordenadas coordsDefault;
	private EcoRiderParking parkingOperativo;
	private EcoRiderParking parkingCompleto;
	private EcoRiderParking parkingParcialmenteInoperativo;
	private EcoRiderParking parkingSoloInoperativo;
	private EcoRiderParking parkingBusqueda;
	private double fianza;
 
	@Before
	public void setUp() {
		coordsDefault = new Coordenadas(10.0, 20.0);
		fianza = 5.50;
		// P1: Operativo (10 disponibles, 0 inoperativas)
		parkingOperativo = new EcoRiderParking("ID1", "P1", "Dir1", coordsDefault, 5, 10, fianza);
		// Todas las 10 plazas estan DISPONIBLES.

		// P2: Completo (0 disponibles, 10 ocupadas, 0 inoperativas)
		parkingCompleto = new EcoRiderParking("ID2", "P2", "Dir2", coordsDefault, 5, 10, fianza);
		for (Plaza plaza : parkingCompleto.getPlazas()) {
			plaza.setOcupada();
		}
		
		// P3: Inoperativo Parcial (5 disponibles, 5 inoperativas)
		parkingParcialmenteInoperativo = new EcoRiderParking("ID3", "P3", "Dir3", coordsDefault, 5, 10, fianza);
		ArrayList<Plaza> plazasP3 = parkingParcialmenteInoperativo.getPlazas();
		for (int i = 0; i < plazasP3.size()-1; i++) {
			plazasP3.get(i).setInoperativa();
		}

		// P4: Totalmente Inoperativo (0 disponibles, 10 inoperativas)
		parkingSoloInoperativo = new EcoRiderParking("ID4", "P4", "Dir4", coordsDefault, 5, 10, fianza);
		ArrayList<Plaza> plazasP4 = parkingSoloInoperativo.getPlazas();
		for (int i = 0; i < parkingSoloInoperativo.getTotalPlazas(); i++) {
			plazasP4.get(i).setInoperativa();
		}

		// P5: Parking con coordenadas especificas para prueba de busqueda
		Coordenadas coordsBusqueda = new Coordenadas(50.0, 50.0);
		parkingBusqueda = new EcoRiderParking("ID5", "Target", "DirT", coordsBusqueda, 5, 1, fianza);
	}
	
	// -----------------------------------------------------------------
	// 1. Tests para Constructores y Fianza
	// -----------------------------------------------------------------
	
	@Test
	public void testInicializarValido() {
		EcoRiderCity ciudad = new EcoRiderCity(fianza);
		assertNotNull(ciudad);
		assertEquals(5.50, ciudad.getFianza(), ERROR_ADMISIBLE);
		assertTrue(ciudad.getAparcamientos().isEmpty());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarInvalidoFianzaCero() {
		new EcoRiderCity(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarInvalidoFianzaNegativa() {
		new EcoRiderCity(-10.0);
	}
	
	@Test
	public void testSetFianzaValida() {
		EcoRiderCity ciudad = new EcoRiderCity(fianza);
		ciudad.setFianza(25.75);
		assertEquals(25.75, ciudad.getFianza(), ERROR_ADMISIBLE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetFianzaInvalidaCero() {
		// Asumiendo que setFianza valida que sea > 0
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.setFianza(0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetFianzaInvalidaNegativa() {
		// Asumiendo que setFianza valida que sea > 0
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.setFianza(-1.0);
	}
	
	// -----------------------------------------------------------------
	// 2. Tests para Agregar/Eliminar Aparcamientos
	// -----------------------------------------------------------------
	
	@Test
	public void testAgregarAparcamientoValido() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingOperativo);
		assertEquals(1, ciudad.getAparcamientos().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarAparcamientoNulo() {
		// Asumiendo validacion para parking nulo
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(null);
	}
	
	@Test
	public void testEliminarAparcamientoExistente() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingOperativo);
		ciudad.eliminarAparcamiento(parkingOperativo);
		assertEquals(0, ciudad.getAparcamientos().size());
	}
	
	@Test
	public void testEliminarAparcamientoNoExistente() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingOperativo);
		ciudad.eliminarAparcamiento(parkingCompleto); // No esta en la lista
		assertEquals(1, ciudad.getAparcamientos().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarAparcamientoNulo() {
		// Asumiendo validacion para parking nulo
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.eliminarAparcamiento(null);
	}

	// -----------------------------------------------------------------
	// 3. Tests para Getters Filtrados
	// -----------------------------------------------------------------
	
	private EcoRiderCity setupCityTodosLosParkings() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingOperativo); // P1: Disp:10, Inop:0
		ciudad.agregarAparcamiento(parkingCompleto);  // P2: Disp:0, Inop:0
		ciudad.agregarAparcamiento(parkingParcialmenteInoperativo); // P3: Disp:5, Inop:5
		ciudad.agregarAparcamiento(parkingSoloInoperativo); // P4: Disp:0, Inop:10
		return ciudad;
	}

	@Test
	public void testGetAparcamientosOperativos() {
		// Operativos = Inoperativas == 0 (P1, P2)
		EcoRiderCity ciudad = setupCityTodosLosParkings();
		ArrayList<EcoRiderParking> operativos = ciudad.getAparcamientosOperativos();
		
		assertEquals(2, operativos.size());
		assertTrue(operativos.contains(parkingOperativo));
		assertTrue(operativos.contains(parkingCompleto));
	}
	
	@Test
	public void testGetAparcamientosCompletos() {
		// Completos = Disponibles == 0 (P2, P4)
		EcoRiderCity ciudad = setupCityTodosLosParkings();
		ArrayList<EcoRiderParking> completos = ciudad.getAparcamientosCompletos();
		
		assertEquals(2, completos.size());
		assertTrue(completos.contains(parkingCompleto));
		assertTrue(completos.contains(parkingSoloInoperativo));
	}
	
	@Test
	public void testGetAparcamientosDisponibles() {
		// Disponibles = Disponibles > 0 (P1, P3)
		EcoRiderCity ciudad = setupCityTodosLosParkings();
		ArrayList<EcoRiderParking> disponibles = ciudad.getAparcamientosDisponibles();
		
		assertEquals(2, disponibles.size());
		assertTrue(disponibles.contains(parkingOperativo));
		assertTrue(disponibles.contains(parkingParcialmenteInoperativo));
	}
	
	@Test
	public void testGetAparcamientosconInoperativas() {
		// Con Inoperativas = Inoperativas > 0 Y < TotalPlazas (P3)
		EcoRiderCity ciudad = setupCityTodosLosParkings();
		ArrayList<EcoRiderParking> conInoperativas = ciudad.getAparcamientosconInoperativas();
		
		assertEquals(1, conInoperativas.size());
		assertTrue(conInoperativas.contains(parkingParcialmenteInoperativo));
		assertFalse(conInoperativas.contains(parkingSoloInoperativo)); // Excluido por tu condicion: Inoperativas < TotalPlazas
	}

	// -----------------------------------------------------------------
	// 4. Tests para getAparcamientosCercanos
	// -----------------------------------------------------------------

	@Test(expected = IllegalArgumentException.class)
	public void testCoordenadasNulas() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingBusqueda);
		ciudad.getAparcamientosCercanos(null, 12.0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testRadioBusquedaNegativo() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingBusqueda);
		ciudad.getAparcamientosCercanos(coordsDefault, -12.0);
	}
 
	@Test
	public void testBuscaAparcamientoEncontradoPorReferencia() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingOperativo); // Coords Default (10, 20)
		ciudad.agregarAparcamiento(parkingBusqueda); // Coords (50, 50)
		
		Coordenadas coordsTarget = parkingBusqueda.getCoordenadas();
		ArrayList<EcoRiderParking> encontrado = ciudad.getAparcamientosCercanos(coordsTarget, 50);
		
		assertNotNull(encontrado);
		assertEquals("ID5", encontrado.get(0).getId());
	}
	
	@Test
	public void testBuscaAparcamientoNoEncontrado() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingOperativo);
		
		Coordenadas coordsInexistentes = new Coordenadas(99.0, 74.0);
		ArrayList<EcoRiderParking> encontrado = ciudad.getAparcamientosCercanos(coordsInexistentes,10);
		
		assertEquals(0, encontrado.size());
	}
	
	@Test
	public void testBuscaAparcamientoSinPlazas() {
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.agregarAparcamiento(parkingSoloInoperativo);
		
		ArrayList<EcoRiderParking> encontrado = ciudad.getAparcamientosCercanos(coordsDefault, 50);
		assertEquals(0, encontrado.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBuscaAparcamientoCoordenadasNulas() {
		// Asumiendo validacion para coordenadas nulas
		EcoRiderCity ciudad = new EcoRiderCity(1.0);
		ciudad.getAparcamientosCercanos(null,10);
	}
	
}