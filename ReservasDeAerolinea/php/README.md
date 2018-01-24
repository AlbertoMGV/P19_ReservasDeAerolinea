# Scripts PHP
El script get_photos.php ha sido escrito en PHP por ser más fácil de implementar que en Java. Una opción mejor hubiera sido Python. Su funci�n es la de descargar autom�ticamente desde Flickr 5 im�genes por cada 'tag' contenida en el archivo aircraft.dat (modificable).

## Uso del Script get_photos.php:

El script necesita 2 modificaciones para funcionar correctamente. Primero, en la línea 4 deberemos modificar la siguiente línea:

```
$access_token = 'TU ACCESS_TOKEN';  //código de acceso a la API de Flickr (secreto).
```
Y cambiar 'TU ACCESS_TOKEN' por el correspondiente API KEY de Flickr. (Obtenerlo [aquí](https://www.flickr.com/services/api/)).

Además, en la línea 35: 

```
$pathname = 'D:/xampp/htdocs/deusto/imagenes/'.$aircraft_name.'_images/';
```

se deberá modificar la ruta por la correspondiente, manteniendo la última porción (.$aircraft_name.'_images/').

## aircraft.dat:

Este fichero contiene un listado de los aviones disponibles en la base de datos. Ha sido generado utilizando la clase FormatData.java del paquete utilidades, en concreto, con el método generarArchivoAviones().
Fragmento del archivo:

```
F100,B461,B462,B463,A310,A318,A319,A320,A321,A330,A332,A333,A340,A342,A343,A345,A346,A359,A388,B703,...
```
 