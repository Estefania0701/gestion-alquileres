# API REST en Spring Boot para sistema de gestión de alquileres de vehículos

## Métodos y Endpoints:

### ----------- Usuarios

#### (GET) Consultar todos los usuarios: http://localhost:8080/api/v1/usuarios 
#### (GET) Obtener un usuario por su ID: http://localhost:8080/api/v1/usuarios/ {id del usuario}
#### (POST) Crear un usuario: http://localhost:8080/api/v1/usuarios

- Ejemplo de Body JSON: {"username":"pedrorivera++","nombre":"Pedro","apellido":"Rivera","password":"hola23"}

#### (DELETE) Eliminar un usuario por su ID: http://localhost:8080/api/v1/usuarios/ {id del usuario}




### ----------- Rentas

####  (GET) Consultar todas las rentas: http://localhost:8080/api/v1/rentas
#### (GET) Consultar una renta por su ID: http://localhost:8080/api/v1/rentas/ {id de la renta}
#### (POST) Crear una renta (se debe especificar el ID de un medio de pago registrado): http://localhost:8080/api/v1/rentas/ {id del medio de pago}

- Ejemplo de Body JSON: {"vehiculo":"Honda Civic","valor":300000}

- NOTA: Por defecto, el estado de establece como "En revisión", y las fechas de inicio y fin en NULL

#### (PUT) Actualizar el estado de una renta por su ID: http://localhost:8080/api/v1/rentas/ {id de la renta}

Ejemplo de Body JSON: {"estado": "Cerrado"}

NOTA: Cuando se actualiza el estado a "Abierto" se asigna la fecha actual en fecha inicio y NULL en fecha fin, cuando se actualiza a "Cerrado" se asigna la fecha actual a fecha fin




### ----------- Medios de pago

#### (GET) Obtener los medios de pago de todos los usuarios: http://localhost:8080/api/v1/mediospago
#### (GET) Obtener los medios de pago de un usuario por su ID: http://localhost:8080/api/v1/mediospago/ {id del usuario}
#### (POST) Agregar medio de pago a un usuario por su ID: http://localhost:8080/api/v1/mediospago/ {id del usuario}

- Ejemplo de Body JSON: {"tipo":"Tarjeta crédito","numerotarjeta":44444444}

- Nota: Cuando el tipo es "Efectivo", se asigna por defecto 0 en "numerotarjeta"

#### (PUT) Actualizar un medio de pago por su ID: http://localhost:8080/api/v1/mediospago/ {id del medio de pago}

- Ejemplo de Body JSON: {"tipo":"Tarjeta crédito", "numerotarjeta":88888888}

- NOTA: Cuando se actualiza a tipo "Efectivo", se asigna por defecto 0 en "numerotarjeta"

#### (DELETE) Eliminar un medio de pago por su ID: http://localhost:8080/api/v1/mediospago/ {id del medio de pago}



