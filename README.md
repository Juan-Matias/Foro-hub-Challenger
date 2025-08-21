# 🖥️ Foro Hub Challenger
![Java JDK 17+](https://img.shields.io/badge/Java-17-blue)
![Spring Boot 3+](https://img.shields.io/badge/Spring%20Boot-3.1-green)
![MySQL 8+](https://img.shields.io/badge/MySQL-8.0-blue)
![Hibernate](https://img.shields.io/badge/Hibernate-5.6-orange)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.7-lightgrey)
![Flyway](https://img.shields.io/badge/Flyway-Migration-yellow)
![Maven 4+](https://img.shields.io/badge/Maven-4.0-red)
![Lombok](https://img.shields.io/badge/Lombok-1.18-purple)
![Bean Validation](https://img.shields.io/badge/Validation-API-lightblue)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.1-darkgreen)
![DevTools](https://img.shields.io/badge/DevTools-Enabled-lightgreen)
![Insomnia](https://img.shields.io/badge/Insomnia-REST-pink)

<p align="center">
  <img src="docs/img/foro-hub.png" alt="Insignia foro hub" />
</p>


## 📄 Descripción del proyecto

Foro Hub es un desafío backend para construir una API REST que permita gestionar un foro donde los usuarios puedan crear, consultar, actualizar y eliminar tópicos.
El objetivo es replicar las funcionalidades principales del backend de un foro usando Spring Boot, centrándonos en el manejo de tópicos, usuarios y sus interacciones.
La API ofrecerá los siguientes endpoints:

- [x] Crear un nuevo tópico
- [x] Mostrar todos los tópicos
- [x] Mostrar un tópico específico
- [x] Actualizar un tópico
- [x] Eliminar un tópico

Este proyecto ayuda a profundizar en el diseño de APIs REST, persistencia de datos, validaciones y seguridad.


## 🛠️ Tecnologías y herramientas utilizadas

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


## ⚙️ Instrucciones para configurar el entorno

1. Instalar Java JDK versión 17 o superior.
2. Tener Maven versión 4 o superior instalado.
3. Instalar MySQL versión 8 o superior y configurar la base de datos.
4. Generar el proyecto inicial desde [Spring Initializr](https://start.spring.io/) con las dependencias mencionadas.
5. (Opcional) Usar un IDE como IntelliJ IDEA para facilitar el desarrollo.



## 🗂️ Estructura del proyecto

- **Modelos:** Clases que representan las entidades de la base de datos.
- **Repositorios:** Interfaces para realizar operaciones CRUD en las entidades.
- **Servicios:** Implementación de la lógica de negocio.
- **Controladores:** Endpoints REST para la interacción con clientes.

```
src/
├─ main/
│  ├─ java/
│  │  └─ com/alura/
│  │     ├─ ForoHubChallengerApplication.java        # Clase principal de Spring Boot
│  │     ├─ config/                                  # Configuraciones generales (seguridad, beans)
│  │     ├─ controller/                              # Endpoints REST (controladores)
│  │     │   └─ TopicoController.java
│  │     ├─ domain/                                  # Lógica de negocio y entidades
│  │     │   ├─ topic/                               # Entidades, DTOs y servicios de tópicos
│  │     │   │   ├─ dto/
│  │     │   │   ├─ interfaz/                        # Repositorios (persistencia)
│  │     │   │   └─ service/                         # Lógica de negocio de tópicos
│  │     │   └─ usuario/                             # Entidades y DTOs de usuarios
│  │     │       └─ dto/
│  │     └─ infra/                                   # Infraestructura (security, configuraciones extras)
│  └─ resources/                                     # Recursos de la aplicación
│     ├─ application.properties                      # Configuración principal
│     ├─ db/migration/                               # Migraciones Flyway
│     ├─ static/                                     # Recursos estáticos
│     └─ templates/                                  # Plantillas si se usan vistas

```



## 🗄️ Diagrama de Base de datos

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

## 📊 Diagrama de clases

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


## 🚀 Funcionalidades futuras

- Implementar autenticación y autorización de usuarios.
- Validaciones y manejo de errores.
- Paginación y filtrado de tópicos.

## 🔹 Nomenclatura de commits

```
feat: → Nueva funcionalidad 
fix: → Corrección de errores
refactor: → Cambios internos sin afectar funcionalidad
docs: → Cambios en documentación
style: → Formato, espacios, comas, sin cambios de código
test: → Agregar o actualizar pruebas
chore: → Tareas de mantenimiento (build, dependencias, configs)
```

## 📬 Contacto

Para dudas o colaboraciones, no dudes en contactarme.

---

*Este proyecto forma parte del desafío Backend de Alura Latam.*
