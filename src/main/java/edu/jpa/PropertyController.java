package edu.jpa;

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity para construir respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones para manejar solicitudes web

import java.util.List; // Importa la clase List para manejar colecciones de objetos

@RestController // Anotación que indica que esta clase es un controlador REST
@RequestMapping("/properties") // Define la ruta base para todas las solicitudes manejadas por este controlador
public class PropertyController {

    @Autowired // Inyecta la dependencia de PropertyRepository
    private PropertyRepository repository;

    // Maneja solicitudes GET para obtener todas las propiedades
    @GetMapping
    public List<Property> getAllProperties() {
        return repository.findAll(); // Devuelve la lista de todas las propiedades
    }

    // Maneja solicitudes GET para obtener una propiedad por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return repository.findById(id) // Busca la propiedad por ID
                .map(property -> ResponseEntity.ok().body(property)) // Si se encuentra, devuelve una respuesta 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra, devuelve 404 Not Found
    }

    // Maneja solicitudes POST para crear una nueva propiedad
    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return repository.save(property); // Guarda la nueva propiedad y la devuelve
    }

    // Maneja solicitudes PUT para actualizar una propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        if (!repository.existsById(id)) { // Verifica si la propiedad existe
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }
        property.setId(id); // Establece el ID de la propiedad a actualizar
        return ResponseEntity.ok(repository.save(property)); // Guarda y devuelve la propiedad actualizada
    }

    // Maneja solicitudes DELETE para eliminar una propiedad por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        if (!repository.existsById(id)) { // Verifica si la propiedad existe
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }
        repository.deleteById(id); // Elimina la propiedad por su ID
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content tras la eliminación
    }
}
