# Foro Hub Challenger 
![Java Version](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green)

![Insignia foro hub](docs/img/foro-hub.png)

## Descripción del proyecto

Foro Hub es un desafío backend para construir una API REST que permita gestionar un foro donde los usuarios puedan crear, consultar, actualizar y eliminar tópicos. El objetivo es replicar las funcionalidades principales del backend de un foro usando Spring Boot, centrándonos en el manejo de tópicos, usuarios y sus interacciones.

La API ofrecerá los siguientes endpoints:

- [x] Crear un nuevo tópico
- [ ] Mostrar todos los tópicos
- [ ] Mostrar un tópico específico
- [ ] Actualizar un tópico
- [ ] Eliminar un tópico

Este proyecto ayuda a profundizar en el diseño de APIs REST, persistencia de datos, validaciones y seguridad.

---

## Tecnologías y herramientas utilizadas
## Tecnologías y herramientas utilizadas

| Capa / Propósito  | Tecnologías y Herramientas |
|-------------------|----------------------------|
| **Lenguaje**      | Java JDK 17+               |
| **Framework**     | Spring Boot 3+, Spring Web |
| **Persistencia**  | MySQL 8+, Hibernate, Spring Data JPA, Flyway Migration |
| **Construcción**  | Maven 4+, Lombok           |
| **Validación**    | Jakarta Bean Validation (Validation API) |
| **Seguridad**     | Spring Security            |
| **Desarrollo**    | Spring Boot DevTools, IntelliJ IDEA / Eclipse |
| **Testing/API**   | Insomnia                   |

---

## Instrucciones para configurar el entorno

1. Instalar Java JDK versión 17 o superior.
2. Tener Maven versión 4 o superior instalado.
3. Instalar MySQL versión 8 o superior y configurar la base de datos.
4. Generar el proyecto inicial desde [Spring Initializr](https://start.spring.io/) con las dependencias mencionadas.
5. (Opcional) Usar un IDE como IntelliJ IDEA para facilitar el desarrollo.

---

## Estructura del proyecto

- **Modelos:** Clases que representan las entidades de la base de datos.
- **Repositorios:** Interfaces para realizar operaciones CRUD en las entidades.
- **Servicios:** Implementación de la lógica de negocio.
- **Controladores:** Endpoints REST para la interacción con clientes.

---

## Diagrama de Base de datos

```mermaid
erDiagram
    
USUARIOS ||--o{ TOPICOS : author_id
USUARIOS {
bigint id pk
varchar nombre
varchar email
}
TOPICOS {
bigint id pk
varchar title
text message
datetime created_at
enum status
bigint author_id fk
varchar course
}
```

## Diagrama de Base de datos

```mermaid

%% Diagrama de clases detallado para ForoHubChallenger
classDiagram
    direction LR
%% Paquete: domain.topic
class Topico {
+Long id
+String title
+String message
+LocalDateTime fechaCreacion
+String estatus
+String autor
+String curso
+boolean active
+Topico(DatosRespuestaTopico dto)
}

    class DatosRespuestaTopico {
        +Long id
        +String titulo
        +String mensaje
        +String estatus
    }

    class ITopicoRepository {
        +existsByTitleIgnoreCase(title)
        +existsByMessageIgnoreCase(message)
        +findByActiveTrue(Pageable paged)
    }

%% Paquete: domain.usuario
class Usuario {
+Long id
+String nombre
+String email
+String password
}

%% Paquete: controller
class TopicoController {
-ITopicoRepository topicoRepository
+registrarTopic(DatosRespuestaTopico datos, UriComponentsBuilder uriBuilder)
}


%% Relaciones
TopicoController --> ITopicoRepository : usa
ITopicoRepository --> Topico : maneja
Topico --> DatosRespuestaTopico : mapea desde
Topico --> Usuario : autor_id
```


## Funcionalidades futuras

- Implementar autenticación y autorización de usuarios.
- Validaciones y manejo de errores.
- Paginación y filtrado de tópicos.

---

## Contacto

Para dudas o colaboraciones, no dudes en contactarme.

---

*Este proyecto forma parte del desafío Backend de Alura Latam.*
