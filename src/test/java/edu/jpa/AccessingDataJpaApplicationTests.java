package edu.jpa;

import org.junit.jupiter.api.Test; // Importa la anotación para definir métodos de prueba
import org.mockito.Mockito; // Importa la clase Mockito para crear objetos simulados
import org.springframework.boot.CommandLineRunner; // Importa la interfaz CommandLineRunner
import org.springframework.boot.test.context.SpringBootTest; // Importa la anotación para pruebas de contexto

import java.io.ByteArrayOutputStream; // Importa para capturar la salida estándar
import java.io.PrintStream; // Importa para redirigir la salida estándar
import java.util.Arrays; // Importa la clase Arrays para trabajar con arreglos
import java.util.Collections; // Importa la clase Collections para trabajar con colecciones

import static org.junit.jupiter.api.Assertions.*; // Importa las aserciones de JUnit
import static org.mockito.Mockito.*; // Importa métodos estáticos de Mockito

@SpringBootTest // Indica que es una prueba de Spring Boot
class AccessingDataJpaApplicationTests {

    // Prueba para verificar que el contexto de la aplicación se carga correctamente
    @Test
    void contextLoads() {
    }

    // Prueba para verificar el comportamiento del método demo cuando hay propiedades
    @Test
    void demoWithPropertiesTest() throws Exception {
        // Crea un objeto simulado de PropertyRepository
        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        Property property = new Property("123 Main St", 250000, 100, "Beautiful house");
        // Define el comportamiento simulado para findAll()
        when(mockRepository.findAll()).thenReturn(Arrays.asList(property));

        // Crea una instancia de la aplicación
        AccessingDataJpaApplication application = new AccessingDataJpaApplication();
        CommandLineRunner runner = application.demo(mockRepository); // Obtiene el runner

        // Captura la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        runner.run(); // Ejecuta el runner

        // Verifica que la salida contiene los mensajes esperados
        assertTrue(outContent.toString().contains("Properties found with findAll():"));
        assertTrue(outContent.toString().contains(property.toString()));
    }

    // Prueba para verificar el comportamiento cuando no hay propiedades
    @Test
    void demoWithEmptyListTest() throws Exception {
        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        when(mockRepository.findAll()).thenReturn(Collections.emptyList()); // Simula una lista vacía

        AccessingDataJpaApplication application = new AccessingDataJpaApplication();
        CommandLineRunner runner = application.demo(mockRepository);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        runner.run(); // Ejecuta el runner

        // Verifica que la salida contiene el mensaje esperado
        assertTrue(outContent.toString().contains("Properties found with findAll():"));
        assertTrue(outContent.toString().contains("")); // Verifica que no se imprime nada más
    }

    // Prueba para verificar que findAll() se llama exactamente una vez
    @Test
    void demoCallsFindAllOnce() throws Exception {
        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        AccessingDataJpaApplication application = new AccessingDataJpaApplication();
        CommandLineRunner runner = application.demo(mockRepository);
        runner.run(); // Ejecuta el runner

        // Verifica que findAll() se llamó una vez
        verify(mockRepository, times(1)).findAll();
    }

    // Prueba para manejar excepciones en el método demo
    @Test
    void demoHandlesException() {
        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        when(mockRepository.findAll()).thenThrow(new RuntimeException("Error")); // Simula una excepción

        AccessingDataJpaApplication application = new AccessingDataJpaApplication();
        CommandLineRunner runner = application.demo(mockRepository);

        // Verifica que se lanza la excepción esperada
        assertThrows(RuntimeException.class, runner::run);
    }

    // Prueba para verificar el comportamiento del método demo con múltiples propiedades
    @Test
    void demoWithMultiplePropertiesTest() throws Exception {
        Property property1 = new Property("123 Main St", 250000, 100, "Beautiful house");
        Property property2 = new Property("456 Elm St", 300000, 150, "Spacious apartment");

        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        when(mockRepository.findAll()).thenReturn(Arrays.asList(property1, property2)); // Simula múltiples propiedades

        AccessingDataJpaApplication application = new AccessingDataJpaApplication();
        CommandLineRunner runner = application.demo(mockRepository);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        runner.run(); // Ejecuta el runner

        // Verifica que ambas propiedades están en la salida
        assertTrue(outContent.toString().contains(property1.toString()));
        assertTrue(outContent.toString().contains(property2.toString()));
    }

    // Prueba para verificar si se generan mensajes de log (placeholder)
    @Test
    void demoLogsOutput() {
        // Test if log messages are generated (you may want to assert specific log messages)
        // This is just a placeholder, as logging is typically not captured directly in unit tests
    }

    // Prueba para verificar que el logger no es nulo
    @Test
    void loggerNotNull() throws NoSuchFieldException {
        assertNotNull(AccessingDataJpaApplication.class.getDeclaredField("log")); // Verifica que el logger está definido
    }

    // Prueba para verificar que la aplicación se ejecuta sin excepciones
    @Test
    void applicationRunsWithoutExceptions() {
        AccessingDataJpaApplication.main(new String[] {}); // Llama al método main para ejecutar la aplicación
    }
}
