package uva.poo.test.practica2;

import static org.junit.Assert.*;


import  org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;

import java.util.ArrayList;

import org.junit.Before;

import uva.poo.practica2.*;

/**
 * Test unitario de la clase EcoRiderParkingTest.
 * @author jaiandr, jorglop.
 */
public class EcoRiderParkingTest {

    private EcoRiderParking aparcamiento;
    private Coordenadas coords1;
    private Coordenadas coords2;

    private static final double PARQUESOL_LAT = 41.636080;
    private static final double PARQUESOL_LON = -4.770950;
    private static final double CMD_LAT = 41.666989;
    private static final double CMD_LON = -4.697491;
    private static final double DISTANCIA_ESPERADA_MTS = 8000;
    private TarjetaMonedero tarjetaConSaldo;
    private TarjetaMonedero tarjetaSinSaldo;
    private double fianza;

    @Before
    public void setUp() {
        coords1 = new Coordenadas(PARQUESOL_LAT, PARQUESOL_LON);
        coords2 = new Coordenadas(CMD_LAT, CMD_LON);
        
        String idAparcamiento ="A001";
        String nombreAparcamiento = "Nombre";
        String direccionAparcamiento = "Direccion";
        int bloqueExpansion = 3;
        int plazasIniciales = 5;
        fianza = 50.0;
        String credencialdecarga = "A156Bv09_1zXo894";
        tarjetaConSaldo = new TarjetaMonedero(credencialdecarga);
        tarjetaConSaldo.recargaSaldo(credencialdecarga, 100.0);
        tarjetaSinSaldo = new TarjetaMonedero(credencialdecarga);
        tarjetaSinSaldo.recargaSaldo(credencialdecarga, 10.0);
        aparcamiento = new EcoRiderParking(idAparcamiento, nombreAparcamiento, direccionAparcamiento, coords1, bloqueExpansion, plazasIniciales, fianza);
        
        Plaza p1 = aparcamiento.getPlazas().get(0); 
        Plaza p2 = aparcamiento.getPlazas().get(1); 
        Plaza p3 = aparcamiento.getPlazas().get(2); 

        p1.setOcupada(); 
        p2.setInoperativa();
        p3.setReservada();

    }
    
    @Test
    public void testParkingValido() {
    	EcoRiderParking parking = new EcoRiderParking("ID","Nombre","Direccion",coords1,5,10, fianza);
    	assertNotNull(parking);
    	assertEquals("ID", parking.getId());
    	assertEquals("Nombre", parking.getNombre());
    	assertEquals("Direccion", parking.getDireccion());
    	assertEquals(coords1, parking.getCoordenadas());
    	assertEquals(5, parking.getBloqueExpansion());
    	assertEquals(10, parking.getTotalPlazas());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorIdNulo() {
        new EcoRiderParking(null, "Nombre", "Direccion Valida", coords1, 5, 10, fianza);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorIdVacio() {
        new EcoRiderParking(" ", "Nombre", "Direccion Valida", coords1, 5, 10, fianza);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorNombreNulo() {
        new EcoRiderParking("A001", null, "Direccion", coords1, 5, 10, fianza);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorNombreVacio() {
        new EcoRiderParking("ID", "", "Direccion Valida", coords1, 5, 10, fianza);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorDireccionNula() {
        new EcoRiderParking("A001", "Nombre", null, coords1, 5, 10, fianza);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorDireccionVacio() {
        new EcoRiderParking("ID", "Nombre", "", coords1, 5, 10, fianza);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorCoordenadasNulas() {
        new EcoRiderParking("A001", "Nombre", "Direccion", null, 5, 10, fianza);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorBloqueExpansionNegativo() {
    	new EcoRiderParking("A001", "Nombre", "Direccion", coords1, -3, 10, fianza);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFalloPorPlazasInicialesNegativas() {
    	new EcoRiderParking("A001", "Nombre", "Direccion", coords1, 3, -5, fianza);
    }
    
    @Test
    public void testgetId() {
    	assertEquals("A001", aparcamiento.getId());
    }
    
    @Test
    public void testgetNombre() {
        assertEquals("Nombre", aparcamiento.getNombre());
    }
    
    @Test
    public void testgetDireccion() {
        assertEquals("Direccion", aparcamiento.getDireccion());
    }
    
    @Test
    public void testgetCoordenadas() {
        assertEquals(coords1, aparcamiento.getCoordenadas());
    }
    
    @Test
    public void testgetBloqueExpansion() {
        assertEquals(3, aparcamiento.getBloqueExpansion());
    }
    
    @Test
    public void testgetTotalPlazas() {
    	assertEquals(5, aparcamiento.getTotalPlazas());
    }
    
    @Test
    public void testgetPlazas() {
        ArrayList<Plaza> plazasDevueltas = aparcamiento.getPlazas();
        assertEquals(5, plazasDevueltas.size()); 
    }
   
    @Test
    public void testgetOcupados() {
    	assertEquals(1, aparcamiento.getOcupados());
    }
     
    @Test
    public void testgetDisponibles() {
    	assertEquals(2, aparcamiento.getDisponibles());
    }
    
    @Test
    public void testgetInoperativas() {
    	assertEquals(1, aparcamiento.getInoperativas());	
    }
    
    @Test
    public void testgetReservadas() {
    	assertEquals(1, aparcamiento.getReservadas());
    }

    @Test
    public void testsetNombre() {
    	aparcamiento.setNombre("Renombrado");
        assertEquals("Renombrado", aparcamiento.getNombre());

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testsetNombreNulo() {
    	aparcamiento.setNombre(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testsetNombreVacio() {
    	aparcamiento.setNombre("");
    }

    @Test
    public void testsetDireccion() {
    	aparcamiento.setDireccion("Direccion");
        assertEquals("Direccion", aparcamiento.getDireccion());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testsetDireccionNulo() {
    	aparcamiento.setDireccion(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testsetDireccionVacia() {
    	aparcamiento.setDireccion("");
    }

    @Test
    public void testsetBloqueExpansionpositivo() {
    	aparcamiento.setBloqueExpansion(10);
        assertEquals(10,aparcamiento.getBloqueExpansion()); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testsetBloqueExpansionnegativo() {
    	aparcamiento.setBloqueExpansion(-10);
    }
    
    @Test
    public void testsetPlazas() {
    	ArrayList<Plaza> nuevasPlazas = new ArrayList<>();
        int tamanoEsperado = 10;
        for (int i = 0; i < tamanoEsperado; i++) {
            nuevasPlazas.add(new Plaza("P" + i, EstadoPlaza.DISPONIBLE));
        }
       aparcamiento.setPlazas(nuevasPlazas);
        assertEquals(tamanoEsperado,aparcamiento.getTotalPlazas());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testsetPlazasIdNulo() {
    	aparcamiento.setPlazas(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testsetPlazasIdVacio() {
    	aparcamiento.setPlazas(new ArrayList<Plaza>());
    }
    

    @Test
    public void testgetEstadoPlaza() {
        assertEquals(EstadoPlaza.OCUPADA,aparcamiento.getEstadoPlaza("P1"));
        assertEquals(EstadoPlaza.INOPERATIVA,aparcamiento.getEstadoPlaza("P2"));
        assertEquals(EstadoPlaza.RESERVADA,aparcamiento.getEstadoPlaza("P3"));
        assertEquals(EstadoPlaza.DISPONIBLE,aparcamiento.getEstadoPlaza("P4"));
    }
    
    @Test (expected = IllegalArgumentException.class) 
    public void testgetEstadoPlazanull() {
    	aparcamiento.getEstadoPlaza(null);
    }
    @Test
    public void testgetEstadoPlazaaparcamientosinsize() {
    	assertNull(aparcamiento.getEstadoPlaza("P-1"));
    }
    @Test
    public void testdistancia() {
    	double distanciaLejana = aparcamiento.distancia(coords2);
    	assertEquals(DISTANCIA_ESPERADA_MTS,distanciaLejana,1000);
    	double distanciaCero = aparcamiento.distancia(aparcamiento.getCoordenadas());
    	assertEquals(0.0,distanciaCero,0.00);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testDistanciaNula() {
    	aparcamiento.distancia(null);
    }
    
    @Test
    public void testAlquilarVehiculoDisponible() {
        assertTrue(aparcamiento.alquilarVehiculo("P4",tarjetaConSaldo));
        assertEquals(EstadoPlaza.OCUPADA,aparcamiento.getEstadoPlaza("P4")); 

    }
    
    @Test
    public void testAlquilarVehiculoReservado() {
    	assertTrue(aparcamiento.alquilarVehiculo("P3",tarjetaConSaldo));
        assertEquals(EstadoPlaza.OCUPADA,aparcamiento.getEstadoPlaza("P3"));
    }
    
    @Test 
    public void testAlquilarVehiculoInoperativo() {
    	assertFalse(aparcamiento.alquilarVehiculo("P2",tarjetaConSaldo));
        assertEquals(EstadoPlaza.INOPERATIVA,aparcamiento.getEstadoPlaza("P2"));
    }
    
    @Test
    public void testAlquilarVehiculoInexistente() {
    	assertFalse(aparcamiento.alquilarVehiculo("P-1",tarjetaConSaldo));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAlquilarVehiculoTarjetaNula(){
    	aparcamiento.alquilarVehiculo("P3",null);
    }
    
    @Test
    public void testAlquilarVehiculoSaldoInsuficiente(){
    	assertFalse(aparcamiento.alquilarVehiculo("P3",tarjetaSinSaldo));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAlquilarVehiculoNulo() {
    	aparcamiento.alquilarVehiculo(null,tarjetaConSaldo);
    }
   
    @Test (expected = IllegalArgumentException.class)
    public void testAlquilarVehiculoIdPlazaVacio() {
    	aparcamiento.alquilarVehiculo("",tarjetaConSaldo);
    }
    
    @Test
    public void testDevolverVehiculoPlazaOcupada() {
        assertTrue(aparcamiento.devolverVehiculo("P1", tarjetaConSaldo));           
    }
    
    @Test
    public void testDevolverVehiculoPlazaInoperativa() {
        assertFalse(aparcamiento.devolverVehiculo("P2", tarjetaConSaldo));
    }
    
    @Test
    public void testDevolverVehiculoPlazaDisponible() {
        assertFalse(aparcamiento.devolverVehiculo("P4", tarjetaConSaldo));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testDevolverVehiculoNulo() {
        aparcamiento.devolverVehiculo(null, tarjetaConSaldo);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testDevolverVehiculoVacio() {
    	aparcamiento.devolverVehiculo("",tarjetaConSaldo);
    }
    
    @Test
    public void testAmpliar() {
        int plazasIniciales = aparcamiento.getTotalPlazas(); 
        int bloque = aparcamiento.getBloqueExpansion(); 
        
        aparcamiento.ampliar();
       
        assertEquals(plazasIniciales + bloque,aparcamiento.getTotalPlazas());     
        assertEquals(EstadoPlaza.DISPONIBLE,aparcamiento.getEstadoPlaza("P6"));
    }
    

    @Test
    public void testDistanciaOtroParking() {
        EcoRiderParking aparcamiento2 = new EcoRiderParking("P002", "Nombre", "Direccion", coords2, 5, 2, fianza);      
        double distanciaCalculada = aparcamiento.distanciaAlAparcamientoACoordenada(aparcamiento2);
        assertEquals(DISTANCIA_ESPERADA_MTS, distanciaCalculada, 1000);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testDistanciaOtroParkingnull() {        
    	aparcamiento.distanciaAlAparcamientoACoordenada(null);
        
    }
}

