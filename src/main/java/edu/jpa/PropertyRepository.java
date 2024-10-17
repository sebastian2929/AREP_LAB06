package edu.jpa; // Declaración del paquete donde se encuentra la interfaz

import org.springframework.data.repository.CrudRepository; // Importación de CrudRepository, una interfaz de Spring Data para operaciones CRUD

import java.util.List; // Importación de la clase List para manejar colecciones de objetos

// Declaración de la interfaz PropertyRepository que extiende CrudRepository
public interface PropertyRepository extends CrudRepository<Property, Long> {

    // Método para obtener una lista de todas las propiedades
    List<Property> findAll(); // Este método retorna una lista de objetos Property
}
