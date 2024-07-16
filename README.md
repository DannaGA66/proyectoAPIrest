# proyectoAPIrest
~ Descripcion

Este proyecto es una REST API para un foro, y fue creado como proyecto final para el curso Java Y Spring Boot del programa ONE (Oracle Next Education).

~ Funcionalidades

La principal funcionalidad del Foro hub es crear los topicos que nacen de las inquietudes de los alumnos de un curso. El topico puede tener tres distintos estados, "ACTIVO" - "RESUELTO" - "DESACTIVADO".

~ Cómo hacer uso

Para consumir la API basta con implementar las siguientes direcciones URL después del host:

/Login - POST

/Registrar - POST

/topico - PUT | POST

/topico/listar - GET

/topico/{id} - GET | DELETE
SpringDoc

También se puede ver la documentación que genera Swagger añadiendo la siguiente ruta a la URL desde el host : /swagger-ui/index.html
