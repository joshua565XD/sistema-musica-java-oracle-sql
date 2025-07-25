# Sistema de Música en Java y Oracle SQL

Este proyecto consiste en una aplicación desarrollada en Java que funciona como un reproductor de música en formato MP3. Su objetivo principal es permitir a los usuarios cargar, organizar y reproducir su música mediante listas de reproducción (PlayLists) personalizadas, con una interfaz simple y amigable.

## 🎵 Características de la Aplicación

- Carga de archivos MP3 desde el sistema de archivos.
- Creación, visualización y eliminación de PlayLists por parte del usuario.
- Reproducción de canciones con controles básicos: **Anterior**, **Play**, **Stop** y **Siguiente**.
- Búsqueda de canciones dentro de las PlayLists.
- Edición de los datos de las PlayLists (artista y álbum).
- Interfaz visual sencilla desarrollada en NetBeans con soporte para botones y buscadores.

## 💻 Características Técnicas del Código

- Implementación en **Java SE 8** o superior.
- Uso de librerías externas:
  - `jlayer-1.0.1.jar` (para reproducción MP3)
  - `jmf-2.1.1e.jar`
  - `jaudiotagger-3.0.1.jar` (para manejo de metadatos MP3)
- Estructura orientada a objetos con clases como:
  
### Clase `Cancion`

```java
private String titulo;
private String artista;
private String album;
private String duracion;
private String rutaArchivo;

Clase ListaReproduccion

private ArrayList<Cancion> playlist = new ArrayList<>();

Funciones principales:

- agregarCancion(Cancion cancion)

- eliminarCancion(int index)

- getPlaylist()

- imprimirPlaylist()

🧰 Requisitos del Sistema

- Java SE 8 o superior instalado

- NetBeans como entorno de desarrollo (recomendado)

- Sistema operativo Windows

🚀 Instalación y Ejecución

1. Clona este repositorio:

git clone https://github.com/joshua565XD/sistema-musica-java-oracle-sql.git

2. Abre el proyecto en NetBeans.

2. Asegúrate de añadir las librerías .jar requeridas al classpath del proyecto.

2. Ejecuta el proyecto desde NetBeans.

🕹️ Uso de la Aplicación

- Para crear una PlayList, llena los campos de Artista y Álbum.

- Usa el botón Agregar para seleccionar canciones desde tus carpetas.

- Presiona Guardar para guardar tu PlayList.

- Usa los botones de control para reproducir tu música:

      - Play: Reproducir canción seleccionada

      - Stop: Detener reproducción

      - Anterior / Siguiente: Navegar entre canciones

- Usa el buscador para encontrar una canción por su nombre.

- Puedes editar una PlayList usando el botón Editar.

- Para eliminar una canción, selecciónala y haz clic en Eliminar.

📦 Estructura de Archivos
build/
lib/
nbproject/
src/
├── .gitignore
├── build.mf
├── manifest.mf
├── Manual_de_Usuario_Spooty.pdf
├── README.md
└── SpootyApp.java
