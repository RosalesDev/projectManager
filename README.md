# projectManager

Sistema para gestiónar de proyectos.

### Postman collection y script

Se adjunta en la carpeta resource los script para la creacion de la base y un usaurio admin (pass: 12345). Aunque se deja configurado en "create" en el application.yml para que se cree la base cuando se levante. Se puede crear el rol, el user y la relacion de estos desde las request de la collection adjunta donde no estan todos pero si como para probar.

### Swagger

Se encuentra activo pero no priorice agregar las anotaciones para mejorar la documentación pero al menos queda funcionando.

### Lombok

No lo uso porque tenia algunos conflictos al usar ModelMapper y no quise perder tiempo con eso ya que me era mas util el mapper.

### Spring Security

Se implemento la autenticación con Jwt. Solo se pide a modo de ejemplo que solo un usuario con rol ADMIN pueda crear userPerson.
