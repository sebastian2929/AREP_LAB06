# Diseño de Aplicaciones de Seguridad

## Resumen del Proyecto

Este taller tiene como objetivo diseñar y desplegar una aplicación segura y escalable utilizando la infraestructura de AWS, centrándose en las mejores prácticas de seguridad. La arquitectura de la aplicación contará con dos componentes principales:

- **Servidor 1**: Servidor Apache
El servidor Apache será responsable de servir un cliente asíncrono HTML+JavaScript a través de una conexión segura utilizando TLS. El código del lado del cliente se entregará a través de canales encriptados, garantizando la integridad y confidencialidad de los datos durante la descarga.

- **Servidor 2**: Spring Framework
El servidor Spring manejará los servicios backend, ofreciendo endpoints de API RESTful. Estos servicios también estarán protegidos mediante TLS, asegurando una comunicación segura entre el cliente y el backend.

- **Servidor 3**: Servidor MYSQL

## Características Clave de Seguridad

- **Encriptación TLS**: Transmisión segura de datos utilizando certificados TLS generados a través de Let’s Encrypt, asegurando la confidencialidad e integridad.

- **Cliente Asíncrono**: Nuestro cliente HTML+JavaScript aprovechará técnicas asíncronas para optimizar el rendimiento mientras mantiene una comunicación segura.
- **Seguridad en el Login**: Implementaremos autenticación de inicio de sesión, con contraseñas almacenadas de manera segura como hashes.
- **Despliegue en AWS**: Todos los servicios se desplegarán y gestionarán en AWS, aprovechando su infraestructura segura y confiable.

## Interacción entre los Componentes
- El cliente se comunicará con el servidor Apache a través de conexiones seguras utilizando TLS.
- El servidor Spring gestionará las solicitudes RESTful y proporcionará datos seguros al cliente.
- La infraestructura de AWS permitirá la escalabilidad y seguridad de ambos servidores, gestionando el tráfico y la integridad de los datos.

## Arquitectura del Sistema

El sistema está compuesto por tres partes principales:

1. **Frontend (HTML + JavaScript) servidor de Apache (httpd)**:
   - Interfaz gráfica que permite a los usuarios interactuar con el sistema.
   - Los usuarios se puede registrar o acceder por medio de un login
   - Los usuarios pueden agregar, visualizar, editar y eliminar propiedades mediante formularios y listas.
   - Se utiliza JavaScript con Fetch API o AJAX para enviar las solicitudes al backend.

2. **Backend (Spring Boot REST API)**:
   - Proporciona los servicios REST necesarios para realizar las operaciones CRUD.
   - Contiene las clases y controladores para gestionar los usuarios y las propiedades (creación, obtención, actualización y eliminación).
   - Conecta el backend con la base de datos para la persistencia de datos.

3. **Base de Datos (MySQL)**:
   - Almacena la información de las propiedades en una tabla llamada `properties`.
   - Almacena la información de los usuarios en una tabla llamada `users`.
   - Se utiliza JPA/Hibernate para mapear los objetos del backend a la base de datos.

### Interacción entre los componentes

- El **frontend** envía solicitudes HTTPS al **backend** 
- El **backend** maneja estas solicitudes con controladores que interactúan con la **base de datos** utilizando JPA/Hibernate.
- Las respuestas del backend son enviadas de vuelta al frontend, donde se actualiza la interfaz de usuario en tiempo real.

## Diseño de Clases

Las clases principales del sistema incluyen:

1. **Property**: Clase que representa una propiedad, con atributos como `id`, `address`, `price`, `size`, y `description`.
2. **PropertyService**: Servicio que contiene la lógica de negocio para las operaciones CRUD de las propiedades.
3. **PropertyController**: Controlador REST que gestiona las solicitudes HTTP y coordina las operaciones con la base de datos.
4. **PropertyRepository**: Repositorio que se encarga de las interacciones directas con la base de datos.

### Diagramas

![image](https://github.com/user-attachments/assets/fe7c4547-38f7-4690-b09e-58a780191be3)


## Instrucciones de Despliegue

Sigue estos pasos para configurar y desplegar el sistema en un sistema local:

1. Configuración del entorno local:
- Asegúrate de tener instalado Java, Maven y Docker.
- Clona el repositorio del proyecto:
``` 
git clone https://github.com/sebastian2929/AREP_LAB06.git
```
2. Configuración de la Base de Datos (MySQL):

- La base de datos se va a crear por medio de un docker compose para levantar y ejecutar los servicios definidos en un archivo
- Usa el siguiente comando estando en el directorio del proyecto:
``` 
docker compose up -d
```

3. Compilación y ejecución del Backend:
- Utiliza Maven para compilar y ejecutar la aplicación:
``` 
mvn clean install
mvn spring-boot:run
``` 

4. Ejecución

- [Ir a localhost](http://localhost:8080)

## Capturas de Pantalla

#### registrarse

![image](https://github.com/user-attachments/assets/8f0fb0c5-cc27-44a5-8d58-e00bbf4e3ff9)

#### Loguearse
![image](https://github.com/user-attachments/assets/0bf60703-39a7-411b-bbfb-9ea8107e1805)


#### Crear Nueva Propiedad
![image](https://github.com/user-attachments/assets/856217fd-0302-4942-9949-926875441407)

![image](https://github.com/user-attachments/assets/ebaead67-d48b-4168-85e4-d0a3d4593acf)

#### Lista de Propiedades

![image](https://github.com/user-attachments/assets/873260e5-459b-4fc0-afc3-49f271b30e6a)

![image](https://github.com/user-attachments/assets/ebaead67-d48b-4168-85e4-d0a3d4593acf)

#### Actualizar Propiedad

![image](https://github.com/user-attachments/assets/c9404fe0-9016-4af5-adda-3da055f7f54d)
![image](https://github.com/user-attachments/assets/6c30d03e-4870-4122-a3ab-561530d3b48f)
![image](https://github.com/user-attachments/assets/232249d5-1bbd-46e3-a4b3-75c8d8a64352)
![image](https://github.com/user-attachments/assets/e565e8dd-5df8-427f-a350-8b939b3c71b1)
![image](https://github.com/user-attachments/assets/21271101-6e0a-4310-a875-92e304d727e3)
#### Eliminar Propiedad


![image](https://github.com/user-attachments/assets/4e854806-2fe4-4fe1-82c0-3ea54a00578f)
![image](https://github.com/user-attachments/assets/b416409a-295e-4662-8d3d-102b119cf709)
![image](https://github.com/user-attachments/assets/afe5e23a-333a-4b60-be6f-fd6bea146127)



## Autor

- [Sebastián David Blanco Rodríguez](https://github.com/Sebastian2929)


## Licencia


Este proyecto está bajo la Licencia (MIT) - ver el archivo [LICENSE](LICENSE.md) para ver más detalles.


