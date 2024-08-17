
# Spoty App Java

Este proyecto consiste en una aplicación en Java la cual tiene como funcionalidad principal funcionar como un reproductor de música (archivos MP3). El propósito principal de esta aplicación es crear PlayLists de la música ingresada por el usuario y a su vez funcionar de manera flexible y amigable para que los usuarios puedan disfrutar de su música favorita de manera sencilla y cómoda.

## Características de la aplicación

- Funcionalidad para cargar archivos MP3.

- Opción para crear y guardar PlayLists creadas por el usuario.

- Opción de búsqueda de canciones que se encuentren en las PlayLists creadas por el usuario.

- Opción para la eliminación de una PlayList si el usuario así lo desea.

- Opciones en el reproductor cuando se reproduzca un archivo MP3 (seleccionar canción anterior en la PlayList, Play o reproducir la canción selecionada, Stop o parar la canción selecionada en la PlayList y seleccionar la siguiente canción en la PlayList).

## Características del codigo

- Creacion de clases Cancion y ListaReproduccion

- Uso de librerias jlayer-1.0.1.jar, jmf-2.1.1e.jar y jaudiotagger-3.0.1.jar

- (Clase cancion) atributos: titulo, artista, album, duracion, rutaArchivo. Se creo un constructor para esta clase, constructor: public Cancion(String titulo, String artista, String album, String duracion, String rutaArchivo) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.rutaArchivo = rutaArchivo;

funciones: // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }


- (Clase ListaReproduccion) Implementacion de un ArrayList, creacion de la clase PlayList, en esta clase se generan todas las opciones que ofrece el programa para la generacion de las PlayList, funciones: public class ListaReproduccion {
    private ArrayList<Cancion> playlist = new ArrayList<>();

    public void agregarCancion(Cancion cancion) {
        playlist.add(cancion);
        imprimirPlaylist();

    public void eliminarCancion(int index) {
        playlist.remove(index);
        imprimirPlaylist();

     public ArrayList<Cancion> getPlaylist() {
        return playlist;

     public void imprimirPlaylist() {
        System.out.println("Lista de Reproducción:");
        for (Cancion cancion : playlist) {
            System.out.println(cancion);              

## Requisitos del sistema

- Java SE 8 o superior

- IDE recomendado: NetBeans

## Instalación

- 

## Uso de la aplicación

- Para crear una PlayList, debes llenar los datos que llevará la PlayList, siendo estos "Artista" y "Album" o el nombre que tendrá tu PlayList.

- Una vez ingresados los datos anteriormente solocitados, podrás seleccionar las canciones que deseas agregar con el botón "Agregar", el cual desplegará una ventana en la cual podrás ver tus carpetas y podrás buscar las canciones que desees agregar a la PlayList; una vez agregues las canciones que deseas a tu PlayList, simplemente da click en el botón "Guardar" y ya tendrás tu PlayList lista para su uso.

- Al ya tener una PlayList creada, podrás reproducir tu música con el botón "Play" o pausarla con el botón "Stop", seleccionar la canción anterior con el botón "Anterior" y podrás selecionar la siguiente canción en la PlayList con el botón siguiente o "Next".

- Si deseas buscar alguna canción específica en tu PlayList, basta con solo colocar el nombre de la canción en el buscador y dar click en el botón "Buscar" y, automáticamente, el programa encontrará la canción solicitada por el usuario. 

También puedes editar los datos de tu PlayList, al dar click en el botón "Editar" te desplegará los datos de tu PlayList y podrás cambiarlos.

- Si deseas eliminar alguna canción de una PlayList, solo debes selecionar la canción que deseas eliminar y dar clic en el botón "Eliminar" y automáticamente el programa eliminará la canción de la PlayList.



