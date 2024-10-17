package edu.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Property {

    // Campo que representa la clave primaria de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Genera el ID automáticamente
    private Long id;

    // Dirección de la propiedad
    private String address;

    // Precio de la propiedad
    private double price;

    // Tamaño de la propiedad en metros cuadrados
    private int size;

    // Descripción de la propiedad
    private String description;

    // Constructor protegido requerido por JPA para la creación de instancias
    protected Property() {}

    // Constructor que permite crear una nueva propiedad con sus atributos
    public Property(String address, double price, int size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    // Método getter para obtener el ID de la propiedad
    public Long getId() {
        return id;
    }

    // Método getter para obtener la dirección de la propiedad
    public String getAddress() {
        return address;
    }

    // Método setter para establecer la dirección de la propiedad
    public void setAddress(String address) {
        this.address = address;
    }

    // Método getter para obtener el precio de la propiedad
    public double getPrice() {
        return price;
    }

    // Método setter para establecer el precio de la propiedad
    public void setPrice(double price) {
        this.price = price;
    }

    // Método getter para obtener el tamaño de la propiedad
    public int getSize() {
        return size;
    }

    // Método setter para establecer el tamaño de la propiedad
    public void setSize(int size) {
        this.size = size;
    }

    // Método getter para obtener la descripción de la propiedad
    public String getDescription() {
        return description;
    }

    // Método setter para establecer la descripción de la propiedad
    public void setDescription(String description) {
        this.description = description;
    }

    // Método setter para establecer el ID de la propiedad
    public void setId(Long id) {
        this.id = id;
    }
}
