# EjercicioPokedexConPokeAPI

Este proyecto es un ejercicio sobre el uso de la API de PokeApi, el proyecto esta realizado para android con Kotlin desde Android Studio
he seguido el patron de diseño MVVM, la aplicacion consta con el permiso de acceso a internet para que pueda hacer uso de la propia API
use la libreria de Retrofit para poder obtener la información del endpoint y poder transformar los JSON de la api  de los 
pokemon en objetos, use la libreria de Glide Para poder usar y cargar la imagenes de los pokemon (Sprites) ya que gestiona la caché y la 
carga de modo asíncrona para que el listado de pokemon carge adecuadamente,

al iniciar la aplicacion sale la pantalla de carga hacia la vista principal, en la cual hay una lista de cards scrollable de 20 pokemons 
con la imagen del pokemon, nombre y ID, se pueden seleccionar, en la parte inferior hay dos botones de navegacion para realizar la 
paginacion y cuenta con la informacion de la pagina actual, al seleccionar un pokemon navega a la vista detalle en la cual muestra 
informacion sobre el pokemon seleccionado, imagen, id, tipo, altura, peso..

control de errores, al iniciar la aplicacion si el dispositivo esta en modo avion o sin red, saldra un error, tras las carga con un 
boton de reintento, el cual solo funciona si se recupera la conexion a internet.



