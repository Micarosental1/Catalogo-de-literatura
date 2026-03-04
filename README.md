Gemini ha dicho
¡Claro, Mica! Como futura ingeniera, sabés que una buena documentación es tan importante como el código mismo. Este README va a hacer que tu proyecto de LiterAlura se vea súper profesional en GitHub.

Aquí tenés una estructura completa que resalta todo lo que lograste (Spring Boot, Hibernate, PostgreSQL y la API de Gutendex):

LiterAlura - Catálogo de Libros 📚
¡Bienvenido a LiterAlura! Este es el desafío final del programa Oracle Next Education (ONE) en conjunto con Alura Latam. El proyecto consiste en un catálogo de libros interactivo que consume datos reales de la API Gutendex, los procesa en formato JSON y los persiste en una base de datos relacional.

🚀 Funcionalidades
El sistema permite interactuar a través de un menú en consola con las siguientes opciones:

Buscar libro por título: Consulta la API externa y, si encuentra el libro, lo guarda automáticamente en la base de datos junto con su autor.

Listar libros registrados: Muestra todos los libros que ya han sido guardados localmente.

Listar autores registrados: Muestra la lista de autores almacenados en la base de datos.

Listar autores vivos en un año: Filtra autores que estaban con vida en el año ingresado por el usuario.

Listar libros por idioma: Permite filtrar los libros guardados por su código de idioma (ej. es, en, fr).

🛠️ Tecnologías Utilizadas
Java 17: Lenguaje principal de desarrollo.

Spring Boot 3: Framework para la creación de la aplicación y la inyección de dependencias.

Spring Data JPA: Para la persistencia de datos y el mapeo objeto-relacional (ORM).

PostgreSQL: Base de datos relacional para el almacenamiento de la información.

Jackson: Librería para el manejo y conversión de datos JSON provenientes de la API.

Maven: Gestor de dependencias y construcción del proyecto.

📋 Estructura del Proyecto
El código está organizado siguiendo las mejores prácticas de arquitectura:

model: Contiene las Entities (Libro, Autor) y los Records para la API (DatosLibro, DatosAutor).

repository: Interfaces que extienden de JpaRepository para la gestión de la base de datos.

service: Lógica para el consumo de la API y la conversión de datos (ConsumoAPI, ConvierteDatos).

principal: Contiene la clase Principal con el menú y la lógica de usuario.

⚙️ Configuración e Instalación
Clona este repositorio.

Configura tu base de datos en PostgreSQL con el nombre literalura.

Ajusta las credenciales en el archivo src/main/resources/application.properties.

Ejecuta la aplicación desde tu IDE o mediante el comando ./mvnw spring-boot:run.

Desarrollado por: Micaela Rosental 💻

Estudiante de Ingeniería en Informática (UBA)
