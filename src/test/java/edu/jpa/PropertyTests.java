package edu.jpa;

import org.junit.jupiter.api.Test; // Importa la anotación para definir métodos de prueba
import static org.junit.jupiter.api.Assertions.*; // Importa las aserciones de JUnit

class PropertyTests {

    // Prueba para verificar la creación de una nueva propiedad
    @Test
    void propertyCreationTest() {
        Property property = new Property("123 Main St", 250000, 100, "Beautiful house"); // Crea una nueva propiedad
        assertNotNull(property); // Verifica que la propiedad no sea nula
        assertEquals("123 Main St", property.getAddress()); // Verifica la dirección
        assertEquals(250000, property.getPrice()); // Verifica el precio
        assertEquals(100, property.getSize()); // Verifica el tamaño
        assertEquals("Beautiful house", property.getDescription()); // Verifica la descripción
    }

    // Prueba para verificar el ID de una propiedad
    @Test
    void propertyIdTest() {
        Property property = new Property("456 Elm St", 300000, 150, "Spacious apartment");
        property.setId(1L); // Establece un ID
        assertEquals(1L, property.getId()); // Verifica que el ID se haya establecido correctamente
    }

    // Prueba para verificar el constructor por defecto
    @Test
    void defaultConstructorTest() {
        Property property = new Property(); // Crea una propiedad usando el constructor por defecto
        assertNotNull(property); // Verifica que la propiedad no sea nula
    }

    // Prueba para verificar la dirección de una propiedad
    @Test
    void propertyAddressTest() {
        Property property = new Property("321 Oak St", 150000, 80, "Cozy cottage");
        assertEquals("321 Oak St", property.getAddress()); // Verifica la dirección
    }

    // Prueba para verificar el precio de una propiedad
    @Test
    void propertyPriceTest() {
        Property property = new Property("654 Maple St", 350000, 120, "Modern home");
        assertEquals(350000, property.getPrice()); // Verifica el precio
    }

    // Prueba para establecer un nuevo tamaño en una propiedad
    @Test
    void setSizeTest() {
        Property property = new Property("987 Birch St", 500000, 250, "Spacious mansion");
        property.setSize(300); // Establece un nuevo tamaño
        assertEquals(300, property.getSize()); // Verifica que el tamaño se haya actualizado
    }

    // Prueba para establecer una nueva descripción en una propiedad
    @Test
    void setDescriptionTest() {
        Property property = new Property("111 Cedar St", 275000, 90, "Charming bungalow");
        property.setDescription("Updated charming bungalow"); // Establece una nueva descripción
        assertEquals("Updated charming bungalow", property.getDescription()); // Verifica que la descripción se haya actualizado
    }

    // Prueba para verificar que el ID de una propiedad es nulo inicialmente
    @Test
    void propertyIdIsNullInitially() {
        Property property = new Property("222 Spruce St", 120000, 70, "Lovely studio");
        assertNull(property.getId()); // Verifica que el ID sea nulo al inicio
    }

    // Prueba para establecer y verificar el ID de una propiedad
    @Test
    void propertySetIdTest() {
        Property property = new Property("333 Fir St", 250000, 110, "Quaint apartment");
        property.setId(3L); // Establece un ID
        assertEquals(3L, property.getId()); // Verifica que el ID se haya establecido correctamente
    }
}
