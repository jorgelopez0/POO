package uva.poo.test.practica2;

import static org.junit.Assert.*;


import uva.poo.practica2.Coordenadas;

import org.junit.Test;

/**
 * Test unitario de la clase CoordenadasTest.
 * @author jaiandr, jorglop.
 */
public class CoordenadasTest {
	// Error admisible para la comparacion de dobles (coordenadas)
		public static final double ERROR_ADMISIBLE = 0.000001; 

		// -----------------------------------------------------------------
		// 1. Tests para Constructores (Coordenadas Decimales - GD)
		// -----------------------------------------------------------------
		
		@Test
		public void testConstructorGDValido() {
			Coordenadas coordenada = new Coordenadas(-4.724722, 41.655278);
			assertNotNull(coordenada);
			assertEquals(-4.724722, coordenada.getLongitudGD(), ERROR_ADMISIBLE);
			assertEquals(41.655278, coordenada.getLatitudGD(), ERROR_ADMISIBLE);
		}

		// --- Tests de Excepciones para Constructor GD (Latitud y Longitud) ---
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGDInvalidoLatitudMayor() {
			// Latitud > 90
			new Coordenadas(10.0, 90.00001);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGDInvalidoLongitudMayor() {
			// Longitud > 180
			new Coordenadas(180.00001, 40.0);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGDInvalidoLatitudMenor() {
			// Latitud < -90
			new Coordenadas(10.0, -90.00001);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGDInvalidoLongitudMenor() {
			// Longitud < -180
			new Coordenadas(-180.00001, 40.0);
		}

		// -----------------------------------------------------------------
		// 2. Tests para Constructores (Coordenadas Grados Minutos Segundos - GMS)
		// -----------------------------------------------------------------
		
		@Test
		public void testConstructorGMSValidoNorteOeste() {
			// GMS: 4, 43, 29, O (Longitud) y 41, 39, 19, N (Latitud) -> Valladolid: -4.724722, 41.655278
			Coordenadas coordenada = new Coordenadas("4, 43, 29, O", "41, 39, 19, N");
			assertEquals(-4.724722, coordenada.getLongitudGD(), ERROR_ADMISIBLE);
			assertEquals(41.655278, coordenada.getLatitudGD(), ERROR_ADMISIBLE);
		}
		
		@Test
		public void testConstructorGMSValidoSurEste() {
			//Coordenadas coordenada = new Coordenadas("");
		}
		
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGMSInvalidoLatitudMayor() {
			// Latitud muy grande (100, 0, 0, N) -> 100.0 > 90.0
			new Coordenadas("10, 0, 0, E", "100, 0, 0, N");
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGMSInvalidoLatitudMenor() {
			new Coordenadas("10, 0, 0, E", "-100, 0, 0, N");
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGMSInvalidoLongitudMayor() {
			new Coordenadas("190, 0, 0, E", "80, 0, 0, N");
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testConstructorGMSInvalidoLongitudMenor() {
			new Coordenadas("-190, 0, 0, E", "100, 0, 0, N");
		}

		// -----------------------------------------------------------------
		// 3. Tests para Getters y Setters
		// -----------------------------------------------------------------

		// ------------------------ Tests Getters --------------------------
		
		@Test
		public void testGetLongitudGD() {
			Coordenadas coordenada = new Coordenadas(-10.5, 50.5);
			assertNotNull(coordenada);
			assertEquals(-10.5, coordenada.getLongitudGD(), ERROR_ADMISIBLE);
		}
		
		@Test
		public void testGetLatitudGD() {
			Coordenadas coordenada = new Coordenadas(-10.5, 50.5);
			assertNotNull(coordenada);
			assertEquals(50.5, coordenada.getLatitudGD(), ERROR_ADMISIBLE);
		}
		
		@Test
		public void testGetLongitudGMS() {
			Coordenadas coordenada = new Coordenadas(-4.724722, 41.655278);
			assertNotNull(coordenada);
			assertEquals("-4°43'29'' O", coordenada.getLongitudGMS());
		}
		
		@Test
		public void testGetLatitudGMS() {
			Coordenadas coordenada = new Coordenadas(-4.724722, 41.655278);
			assertNotNull(coordenada);
			assertEquals("41°39'19'' N", coordenada.getLatitudGMS());
		}
		
		// ------------------------ Tests Setters --------------------------
		
		@Test
		public void testSetLongitudValida() {
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.setLongitud(150.75);
			assertEquals(150.75, coordenada.getLongitudGD(), ERROR_ADMISIBLE);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testSetLongitudInvalidaMayor() {
			// Longitud > 180
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.setLongitud(180.1);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testSetLongitudInvalidaMenor() {
			// Longitud < -180
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.setLongitud(-180.1);
		}
		
		@Test
		public void testSetLatitudValida() {
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.setLatitud(-45.2);
			assertEquals(-45.2, coordenada.getLatitudGD(), ERROR_ADMISIBLE);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testSetLatitudInvalidaMayor() {
			// Latitud < -90
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.setLatitud(-90.001);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testSetLatitudInvalidaMenor() {
			// Latitud > 90
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.setLatitud(90.001);
		}

		// -----------------------------------------------------------------
		// 4. Tests para cambioGMSaGD (Conversion)
		// -----------------------------------------------------------------

		@Test
		public void testCambioGMSaGDEsteNorte() {
			// 15 grados, 30 minutos, 0 segundos, E/N -> 15.5
			Coordenadas coordenada = new Coordenadas(0.0, 0.0); 
			assertEquals(15.5, coordenada.cambioGMSaGD("15, 30, 0, E"), ERROR_ADMISIBLE);
			assertEquals(15.5, coordenada.cambioGMSaGD("15, 30, 0, N"), ERROR_ADMISIBLE);
		}
		
		@Test
		public void testCambioGMSaGDOesteSur() {
			// 15 grados, 30 minutos, 0 segundos, O/S -> -15.5
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			assertEquals(-15.5, coordenada.cambioGMSaGD("15, 30, 0, O"), ERROR_ADMISIBLE);
			assertEquals(-15.5, coordenada.cambioGMSaGD("15, 30, 0, S"), ERROR_ADMISIBLE);
		}

		@Test(expected = ArrayIndexOutOfBoundsException.class)
		public void testCambioGMSaGDInvalidoFaltanPartes() {
			// Faltan datos (e.g., el hemisferio)
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.cambioGMSaGD("10, 30, 15");
		}
		
		@Test(expected = NumberFormatException.class)
		public void testCambioGMSaGDInvalidoDatosNoNumericos() {
			// Minutos no son un número
			Coordenadas coordenada = new Coordenadas(0.0, 0.0);
			coordenada.cambioGMSaGD("10, XX, 15, N");
		}

		// -----------------------------------------------------------------
		// 5. Tests para cambioGDaGMS (Conversion Inversa)
		// -----------------------------------------------------------------
		
		@Test
		public void testCambioGDaGMSNorte() {
			// Latitud positiva
			Coordenadas coordenada = new Coordenadas(-4.724722, 41.655278);
			assertEquals("41°39'19'' N", coordenada.getLatitudGDaGMS());
		}
		
		@Test
		public void testCambioGDaGMSSur() {
			Coordenadas coordenada = new Coordenadas(4.724722, -41.655278);
			assertEquals("-41°39'19'' S", coordenada.getLatitudGDaGMS());
			
		}
		
		@Test
		public void testCambioGDaGMSOeste() {
			// Longitud negativa
			Coordenadas coordenada = new Coordenadas(-4.724722, 41.655278);
			assertEquals("-4°43'29'' O", coordenada.getLongitudGDaGMS());
		}
		
		
		@Test
		public void testCambioGDaGMSEste() {
			Coordenadas coordenada = new Coordenadas(4.724722, -41.655278);
			assertEquals("4°43'29'' E", coordenada.getLongitudGDaGMS());
			
		}
		
}