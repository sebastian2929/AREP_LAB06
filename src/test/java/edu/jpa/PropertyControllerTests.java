package edu.jpa;

import org.junit.jupiter.api.BeforeEach; // Importa la anotación para ejecutar código antes de cada prueba
import org.junit.jupiter.api.Test; // Importa la anotación para definir métodos de prueba
import org.mockito.InjectMocks; // Importa la anotación para inyectar objetos simulados en el objeto a probar
import org.mockito.Mock; // Importa la anotación para crear un objeto simulado
import org.mockito.MockitoAnnotations; // Importa la clase para inicializar las anotaciones de Mockito
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity para construir respuestas HTTP

import java.util.Arrays; // Importa la clase Arrays para trabajar con arreglos
import java.util.Collections; // Importa la clase Collections para trabajar con colecciones
import java.util.List; // Importa la clase List para manejar colecciones de objetos
import java.util.Optional; // Importa la clase Optional para manejar valores que pueden ser nulos

import static org.junit.jupiter.api.Assertions.*; // Importa las aserciones de JUnit
import static org.mockito.ArgumentMatchers.anyLong; // Importa para usar cualquier valor long como argumento
import static org.mockito.Mockito.*; // Importa métodos estáticos de Mockito

class PropertyControllerTests {

    @Mock // Crea un objeto simulado de PropertyRepository
    private PropertyRepository propertyRepository;

    @InjectMocks // Inyecta el objeto simulado en PropertyController
    private PropertyController propertyController;

    // Método que se ejecuta antes de cada prueba para inicializar las anotaciones de Mockito
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para verificar que se obtienen todas las propiedades
    @Test
    void getAllPropertiesTest() {
        Property property1 = new Property("123 Main St", 250000, 100, "Beautiful house");
        Property property2 = new Property("456 Elm St", 300000, 150, "Spacious apartment");
        when(propertyRepository.findAll()).thenReturn(Arrays.asList(property1, property2)); // Simula la respuesta

        List<Property> properties = propertyController.getAllProperties(); // Llama al método
        assertEquals(2, properties.size()); // Verifica que el tamaño de la lista sea 2
    }

    // Prueba para verificar que se obtiene una propiedad por ID
    @Test
    void getPropertyByIdTest() {
        Property property = new Property("789 Pine St", 450000, 200, "Luxury villa");
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.of(property)); // Simula la respuesta

        ResponseEntity<Property> response = propertyController.getPropertyById(1L); // Llama al método
        assertTrue(response.getStatusCode().is2xxSuccessful()); // Verifica que la respuesta sea exitosa
        assertEquals(property, response.getBody()); // Verifica que el cuerpo de la respuesta sea la propiedad esperada
    }

    // Prueba para verificar el comportamiento al intentar obtener una propiedad que no existe
    @Test
    void getPropertyByIdNotFoundTest() {
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.empty()); // Simula que no se encuentra la propiedad

        ResponseEntity<Property> response = propertyController.getPropertyById(1L); // Llama al método
        assertEquals(404, response.getStatusCodeValue()); // Verifica que la respuesta sea 404 Not Found
    }

    // Prueba para verificar el comportamiento al intentar actualizar una propiedad que no existe
    @Test
    void updatePropertyNotFoundTest() {
        Property property = new Property("333 Fir St", 250000, 110, "Quaint apartment");
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.empty()); // Simula que no se encuentra la propiedad

        ResponseEntity<Property> response = propertyController.updateProperty(1L, property); // Llama al método
        assertEquals(404, response.getStatusCodeValue()); // Verifica que la respuesta sea 404 Not Found
    }

    // Prueba para verificar el comportamiento al intentar eliminar una propiedad que no existe
    @Test
    void deletePropertyNotFoundTest() {
        doThrow(new RuntimeException()).when(propertyRepository).deleteById(anyLong()); // Simula una excepción al intentar eliminar

        ResponseEntity<Void> response = propertyController.deleteProperty(1L); // Llama al método
        assertEquals(404, response.getStatusCodeValue()); // Verifica que la respuesta sea 404 Not Found
    }

    // Prueba para verificar que se obtienen propiedades cuando la lista está vacía
    @Test
    void getAllPropertiesReturnsEmptyListTest() {
        when(propertyRepository.findAll()).thenReturn(Collections.emptyList()); // Simula una lista vacía

        List<Property> properties = propertyController.getAllProperties(); // Llama al método
        assertEquals(0, properties.size()); // Verifica que el tamaño de la lista sea 0
    }
}
